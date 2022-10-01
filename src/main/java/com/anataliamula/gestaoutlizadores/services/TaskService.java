package com.anataliamula.gestaoutlizadores.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anataliamula.gestaoutlizadores.models.Task;
import com.anataliamula.gestaoutlizadores.models.User;
import com.anataliamula.gestaoutlizadores.repositories.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserService userService; //Usamos Userservice pois já existe - reutilição
	
	public Task findById(Long id) {
		Optional<Task> task = this.taskRepository.findById(id); //para evitar receber null/evitar erros
		//Apenas retorna o user se ele estiver preenchido/nao for null - caso não, lança uma excepção
		return task.orElseThrow(() -> new RuntimeException(
				"Tarefa não encontrado! Id: " + id + ", Tipo: " + Task.class.getName()
				)); 		
	}
	
	@Transactional
	public Task create(Task obj) {
		//precisamos verificar se o user existe ou não
		User user = this.userService.findById(obj.getUser().getId());
		obj.setId(null);
		obj.setUser(user);//para garantir que sao realmente os dados que estão na BD.
		obj = this.taskRepository.save(obj);
		return obj;
	}
	
	@Transactional
	public Task update(Task obj) {
		Task newObj = findById(obj.getId());
		newObj.setDescription(obj.getDescription());
		return this.taskRepository.save(newObj);
	}
	
	public void delete(Long id) {
		findById(id);
		//Não precisamos de try catch pois a task não está relacionada a nenhuma tabela, não é chave estrangeira, mas por questoes
		//de actualizações melhor colocar
		try {
			this.taskRepository.deleteById(id);
		}catch(Exception e) {
			throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
		}
	}
	
}
