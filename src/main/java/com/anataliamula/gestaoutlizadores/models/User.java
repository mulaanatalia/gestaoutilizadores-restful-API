package com.anataliamula.gestaoutlizadores.models;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Task.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class User {

	public interface CreateUser {}
	public interface UpdateUser {}
	
	public static final String TABLE_NAME = "user";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;
	
	@Column(name = "username", length = 100, nullable = false, unique = true) 
	@NotNull(groups = CreateUser.class) //serve para validar ao criar
	@NotEmpty(groups = CreateUser.class)
	@Size(groups = CreateUser.class, min = 2, max = 100)
	private String username;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "password", length = 60, nullable = false)
	@NotNull(groups = {CreateUser.class, UpdateUser.class}) //para validar ao criar e ao actualizar
	@NotEmpty(groups = {CreateUser.class, UpdateUser.class})
	@Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60)
	private String password;
	
	//Um usuário pode ter várias 
	//O a variavel user está em Task
	@OneToMany(mappedBy = "user")
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Task> tasks = new ArrayList<Task>();
	
	
}
