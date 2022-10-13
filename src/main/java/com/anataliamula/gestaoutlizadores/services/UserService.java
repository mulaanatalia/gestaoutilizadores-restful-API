package com.anataliamula.gestaoutlizadores.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anataliamula.gestaoutlizadores.models.User;
import com.anataliamula.gestaoutlizadores.repositories.UserRepository;
import com.anataliamula.gestaoutlizadores.services.exceptions.DataBindingViolationException;
import com.anataliamula.gestaoutlizadores.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	//O nome do método pode ser qualquer
	public User findById(Long id) {
		Optional<User> user = this.userRepository.findById(id); //para evitar receber null/evitar erros
		//Apenas retorna o user se ele estiver preenchido/nao for null - caso não, lança uma excepção
		return user.orElseThrow(() -> new ObjectNotFoundException(
				"Utilizador não encontrado! Id: " + id + ", Tipo: " + User.class.getName()
				)); 		
	}
	
	@Transactional
	public User create(User obj) {
		obj.setId(null); //para garantir que o id seja criado pela BD
		obj = this.userRepository.save(obj);
		return obj;
	}
	
	@Transactional
	public User update(User obj) {
		User newObj = findById(obj.getId()); //para garantir que o user existe-procura o user a ser actualizado na BD
		newObj.setPassword(obj.getPassword()); //Não vamos actualizar tudo, apenas o password
		return this.userRepository.save(newObj);
	}
	
	public void delete(Long id) {
		findById(id);
		try {			
			this.userRepository.deleteById(id); //se tiver vinculado a uma tabela não será possivel
		}catch(Exception e) {
			throw new DataBindingViolationException ("Não é possível excluir pois há entidades relacionadas!");
		}
	}
	
}
