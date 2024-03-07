package com.test.sentra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.sentra.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	

}
