package com.test.sentra.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.sentra.entities.Phone;
import com.test.sentra.repositories.PhoneRepository;
import com.test.sentra.services.PhoneService;

@Service
public class PhoneServiceImpl implements PhoneService{
	
	@Autowired
	private PhoneRepository phoneRepository;

	@Override
	@Transactional(readOnly=true)
	public List<Phone> findAll() {
	
		return (List<Phone>) phoneRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Phone> findById(Long phoneID) {
		
		return phoneRepository.findById(phoneID);
	}

	@Override
	@Transactional(readOnly=true)
	public Phone save(Phone phone) {
		
		return phoneRepository.save(phone);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Phone> update(Phone phone, Long phoneID) {
		
		Optional<Phone> phoneDBOPT = phoneRepository.findById(phoneID);
		if(phoneDBOPT.isPresent()) {
			Phone phoneDB = phoneDBOPT.orElseThrow();
			
			phoneDB.setNumber(phone.getNumber());
			phoneDB.setcontrycode(phone.getcontrycode());
			phoneDB.setCitycode(phone.getCitycode());
			
			return Optional.of(phoneRepository.save(phoneDB));
		}
		
		return phoneDBOPT;
	}


	@Override
	@Transactional(readOnly=true)
	public Optional<Phone> delete(Long phoneID) {
	
		Optional<Phone> phoneDBOPT = phoneRepository.findById(phoneID);
		phoneDBOPT.ifPresent(phoneDB -> {
			phoneRepository.delete(phoneDB);
		});
		
		return phoneDBOPT;
	}

}
