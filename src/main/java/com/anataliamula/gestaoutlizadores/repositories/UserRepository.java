package com.anataliamula.gestaoutlizadores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anataliamula.gestaoutlizadores.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
}
