package com.test.sentra.services;

import java.util.List;
import java.util.Optional;

import com.test.sentra.entities.User;

public interface UserService {
	
	List<User> findAll();
	
	Optional<User> findById(Long userID);
	
	User save(User user);
	
	Optional<User> update(User user, Long userID);
	
	Optional<User> delete(Long userID);
	

}
