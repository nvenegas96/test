package com.test.sentra.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.sentra.entities.Role;
import com.test.sentra.entities.User;
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
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly=true)
	public List<User> findAll() {
	
		return (List<User>) userRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<User> findById(Long userID) {
		
		return userRepository.findById(userID);
	}

	@Override
	@Transactional(readOnly=true)
	public User save(User user) {
		
		Optional<Role> optRoleUser = roleRepository.findByName("ROLE_USER");
		List<Role> roles = new ArrayList<>();
		optRoleUser.ifPresent(roles::add);
		user.setActive(true);
		
		if(user.isAdmin()) {
			Optional<Role> optRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
			optRoleAdmin.ifPresent(roles::add);
		}
		
		user.setRoles(roles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<User> update(User user, Long userID) {
		
		Optional<User> UserDBOPT = userRepository.findById(userID);
		if(UserDBOPT.isPresent()) {
			User UserDB = UserDBOPT.orElseThrow();
			
			UserDB.setName(user.getName());
			UserDB.setEmail(user.getEmail());
			UserDB.setPassword(user.getPassword());
			UserDB.setPhones(user.getPhones());
			
			return Optional.of(userRepository.save(UserDB));
		}
		
		return UserDBOPT;
	}


	@Override
	@Transactional(readOnly=true)
	public Optional<User> delete(Long userID) {
	
		Optional<User> UserDBOPT = userRepository.findById(userID);
		UserDBOPT.ifPresent(UserDB -> {
			userRepository.delete(UserDB);
		});
		
		return UserDBOPT;
	}

}
