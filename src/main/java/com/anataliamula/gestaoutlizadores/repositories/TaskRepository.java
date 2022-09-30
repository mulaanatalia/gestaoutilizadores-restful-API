package com.anataliamula.gestaoutlizadores.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anataliamula.gestaoutlizadores.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	//Buscar uma lista de tasks de um user - devemos usar User_id pois na task temos user e nao id, entao, primeiro colocamos o user
		//que está no model de task e depois colocamos o id que está em user. USANDO O Spring Boot
		List<Task> findByUser_Id(Long id);
		
		//USANDO O jpql - nao há regra para o nome
		//@Query(value = "SELECT t FROM Task t where t.user.id = :id")
		//List<Task> findByUser_Id(@Param("id")Long id);
		
		//Usando sql puro
		//@Query(value = "SELECT * FROM task t where t.user_id = :id", nativeQuery = true)
		//List<Task> findByUser_Id(@Param("id")Long id);
	
}
