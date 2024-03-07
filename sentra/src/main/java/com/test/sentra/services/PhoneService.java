package com.test.sentra.services;

import java.util.List;
import java.util.Optional;

import com.test.sentra.entities.Phone;

public interface PhoneService {
	
	List<Phone> findAll();
	
	Optional<Phone> findById(Long phoneID);
	
	Phone save(Phone phone);
	
	Optional<Phone> update(Phone phone, Long phoneID);
	
	Optional<Phone> delete(Long phoneID);

}
