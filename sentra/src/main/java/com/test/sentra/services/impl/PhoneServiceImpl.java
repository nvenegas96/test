package com.test.sentra.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.sentra.entities.PhoneEntity;
import com.test.sentra.repositories.PhoneRepository;
import com.test.sentra.services.PhoneService;

@Service
public class PhoneServiceImpl implements PhoneService{
	
	@Autowired
	private PhoneRepository phoneRepository;

	@Override
	@Transactional(readOnly=true)
	public List<PhoneEntity> findAll() {
	
		return (List<PhoneEntity>) phoneRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<PhoneEntity> findById(Long phoneID) {
		
		return phoneRepository.findById(phoneID);
	}

	@Override
	@Transactional(readOnly=true)
	public PhoneEntity save(PhoneEntity phoneEntity) {
		
		return phoneRepository.save(phoneEntity);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<PhoneEntity> update(PhoneEntity phoneEntity, Long phoneID) {
		
		Optional<PhoneEntity> phoneDBOPT = phoneRepository.findById(phoneID);
		if(phoneDBOPT.isPresent()) {
			PhoneEntity phoneDB = phoneDBOPT.orElseThrow();
			
			phoneDB.setNumber(phoneEntity.getNumber());
			phoneDB.setContrycode(phoneEntity.getContrycode());
			phoneDB.setCitycode(phoneEntity.getCitycode());
			
			return Optional.of(phoneRepository.save(phoneDB));
		}
		
		return phoneDBOPT;
	}


	@Override
	@Transactional(readOnly=true)
	public Optional<PhoneEntity> delete(Long phoneID) {
	
		Optional<PhoneEntity> phoneDBOPT = phoneRepository.findById(phoneID);
		phoneDBOPT.ifPresent(phoneDB -> {
			phoneRepository.delete(phoneDB);
		});
		
		return phoneDBOPT;
	}

}
