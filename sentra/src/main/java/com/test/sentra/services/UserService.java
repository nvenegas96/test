package com.test.sentra.services;

import java.util.List;
import java.util.Optional;

import com.test.sentra.entities.UserEntity;

public interface UserService {
	
	List<UserEntity> findAll();
	
	Optional<UserEntity> findById(Long userID);
	
	UserEntity save(UserEntity userEntity);
	
	Optional<UserEntity> update(UserEntity userEntity, Long userID);
	
	Optional<UserEntity> delete(Long userID);
	

}
