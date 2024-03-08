package com.test.sentra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.sentra.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	int countAllByEmail(String email);

}
