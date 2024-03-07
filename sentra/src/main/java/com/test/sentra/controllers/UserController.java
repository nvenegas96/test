package com.test.sentra.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.sentra.entities.User;
import com.test.sentra.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService UserService;
	
	@GetMapping
	public List<User> UserList(){
		return UserService.findAll();
	}
	
	@GetMapping("/{userID}")
	public ResponseEntity<?> UserById(@PathVariable Long userID) {
		Optional<User> UserOPT = UserService.findById(userID);
		if(UserOPT.isPresent()) {
			return ResponseEntity.ok(UserOPT.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result){
		if(result.hasFieldErrors()) {
			return validation(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(UserService.save(user));
	}
	
	@PutMapping("/{userID}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user, BindingResult result, @PathVariable Long userID){
		if(result.hasFieldErrors()) {
			return validation(result);
		}
		Optional<User> UserOPT = UserService.update(user, userID);
		if(UserOPT.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(UserOPT.orElseThrow());
		}
		return ResponseEntity.notFound().build();
		
	}

	@DeleteMapping("/{userID}")
	public ResponseEntity<?> UserDelete(@PathVariable Long userID) {
		Optional<User> UserOPT = UserService.delete(userID);
		if(UserOPT.isPresent()) {
			return ResponseEntity.ok(UserOPT.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}
	
	private ResponseEntity<?> validation(BindingResult result) {
		
		Map<String, String> errors = new HashMap<>();
		result.getFieldErrors().forEach(error -> {
			errors.put(error.getField(), "mensaje de error");
			});
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
}
