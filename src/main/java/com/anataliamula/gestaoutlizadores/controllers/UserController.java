package com.anataliamula.gestaoutlizadores.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.anataliamula.gestaoutlizadores.models.User;
import com.anataliamula.gestaoutlizadores.models.User.CreateUser;
import com.anataliamula.gestaoutlizadores.models.User.UpdateUser;
import com.anataliamula.gestaoutlizadores.services.UserService;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

	@Autowired
	private UserService userService;
	
	//O id fica entre chavetas, pois é uma variável
	//o getMapping especifica o que vem depois de /user - requestMapping
	//localhost:8080/user/1
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = this.userService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	//Usamos a interface CreateUser do model de user para validar o user
	@Validated(CreateUser.class)
	public ResponseEntity<Void> create(@Valid @RequestBody User obj) {
		this.userService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
		//created-para retornar o 201 no Postman, uri-para retornar o caminho
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	@Validated(UpdateUser.class)
	public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id ){
		obj.setId(id);
		this.userService.update(obj);
		return ResponseEntity.noContent().build(); //noContent pois nao retornaremos nenhum dado
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		this.userService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
