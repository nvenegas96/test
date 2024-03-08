package com.test.sentra.services;

import java.util.List;
import java.util.Optional;

import com.test.sentra.entities.PhoneEntity;

public interface PhoneService {
	
	List<PhoneEntity> findAll();
	
	Optional<PhoneEntity> findById(Long phoneID);
	
	PhoneEntity save(PhoneEntity phoneEntity);
	
	Optional<PhoneEntity> update(PhoneEntity phoneEntity, Long phoneID);
	
	Optional<PhoneEntity> delete(Long phoneID);

}
