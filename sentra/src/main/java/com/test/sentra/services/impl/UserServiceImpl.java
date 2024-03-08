package com.test.sentra.services.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.sentra.entities.PhoneEntity;
import com.test.sentra.entities.RoleEntity;
import com.test.sentra.entities.UserEntity;
import com.test.sentra.repositories.PhoneRepository;
import com.test.sentra.repositories.RoleRepository;
import com.test.sentra.repositories.UserRepository;
import com.test.sentra.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PhoneRepository phoneRepository;

	@Override
	@Transactional(readOnly=true)
	public List<UserEntity> findAll() {
	
		return (List<UserEntity>) userRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<UserEntity> findById(Long userID) {
		
		return userRepository.findById(userID);
	}
	
	private void validateEmail(UserEntity userEntity) {
		if(userRepository.countAllByEmail(userEntity.getEmail()) != 0) {
			throw new RuntimeException("El correo ya registrado");
		}
		
	}

	@Override
	@Transactional(readOnly=true)
	public UserEntity save(UserEntity userEntity) {
		validateEmail(userEntity);
		Optional<RoleEntity> optRoleUser = roleRepository.findByName("ROLE_USER");
		List<RoleEntity> roleEntities = new ArrayList<>();
		optRoleUser.ifPresent(roleEntities::add);
		userEntity.setActive(true);
		userEntity.setCreated(new Date());
		userEntity.setModified(new Date());
		userEntity.setLastlogin(new Date());
		userEntity.setToken(UUID.randomUUID().toString());
		
		
		
		if(userEntity.isAdmin()) {
			Optional<RoleEntity> optRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
			optRoleAdmin.ifPresent(roleEntities::add);
		}
		
		userEntity.setRoleEntities(roleEntities);
		userEntity.setPassword(Base64.getEncoder().encodeToString(userEntity.getPassword().getBytes()));
		userEntity = userRepository.save(userEntity);
		
		for(PhoneEntity phoneEntity: userEntity.getPhones()) {
			phoneEntity.setUserID(userEntity.getUserID());
			phoneRepository.save(phoneEntity);
		}
		
		return userEntity;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<UserEntity> update(UserEntity userEntity, Long userID) {
		
		Optional<UserEntity> UserDBOPT = userRepository.findById(userID);
		if(UserDBOPT.isPresent()) {
			UserEntity UserDB = UserDBOPT.orElseThrow();
			
			UserDB.setName(userEntity.getName());
			UserDB.setEmail(userEntity.getEmail());
			UserDB.setPassword(userEntity.getPassword());
			
			return Optional.of(userRepository.save(UserDB));
		}
		
		return UserDBOPT;
	}


	@Override
	@Transactional(readOnly=true)
	public Optional<UserEntity> delete(Long userID) {
	
		Optional<UserEntity> UserDBOPT = userRepository.findById(userID);
		UserDBOPT.ifPresent(UserDB -> {
			userRepository.delete(UserDB);
		});
		
		return UserDBOPT;
	}

}
