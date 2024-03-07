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

import com.test.sentra.entities.Phone;
import com.test.sentra.services.PhoneService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/phones")
public class PhoneController {
	
	@Autowired
	private PhoneService phoneService;
	
	@GetMapping
	public List<Phone> phoneList(){
		return phoneService.findAll();
	}
	
	@GetMapping("/{phoneID}")
	public ResponseEntity<?> phoneById(@PathVariable Long phoneID) {
		Optional<Phone> phoneOPT = phoneService.findById(phoneID);
		if(phoneOPT.isPresent()) {
			return ResponseEntity.ok(phoneOPT.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> createPhone(@Valid @RequestBody Phone phone, BindingResult result){
		if(result.hasFieldErrors()) {
			return validation(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(phoneService.save(phone));
	}
	
	@PutMapping("/{phoneID}")
	public ResponseEntity<?> updatePhone(@Valid @RequestBody Phone phone, BindingResult result,@PathVariable Long phoneID){
		if(result.hasFieldErrors()) {
			return validation(result);
		}
		Optional<Phone> phoneOPT = phoneService.update(phone, phoneID);
		if(phoneOPT.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(phoneOPT.orElseThrow());
		}
		return ResponseEntity.notFound().build();
		
	}

	@DeleteMapping("/{phoneID}")
	public ResponseEntity<?> phoneDelete(@PathVariable Long phoneID) {
		Optional<Phone> phoneOPT = phoneService.delete(phoneID);
		if(phoneOPT.isPresent()) {
			return ResponseEntity.ok(phoneOPT.orElseThrow());
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
