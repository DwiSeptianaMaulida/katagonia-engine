package com.mulyadime.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulyadime.backend.models.Acronym;
import com.mulyadime.backend.payload.CreateUpdateAcronym;
import com.mulyadime.backend.repository.AcronymRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AcronymService {
	
	@Autowired
	private AcronymRepository acronymRepository;
	
	public List<Acronym> findAll() {
		List<Acronym> resultAsList = new ArrayList<>();
		acronymRepository.findAll().iterator().forEachRemaining(resultAsList::add);
		log.debug("{}", resultAsList.toString());
		
		return resultAsList;
	}

	public Acronym save(CreateUpdateAcronym request) {
		log.info("Fetch data from user input: {}", request.toString());
		
		Acronym data = new Acronym();
		data.setName(request.getName());
		data.setCategory(request.getCategory());
		data.setIndonesia(request.getIndonesia());
		data.setEnglish(request.getEnglish());
		
		return acronymRepository.save(data);
	}

	public boolean remove(Long id) {
		Acronym data = acronymRepository.findById(id).get();
		
		if (data != null) {
			acronymRepository.deleteById(id);
			return true;
		}
		
		return false;
	}

	public Acronym findOne(Long id) {
		return acronymRepository.findById(id).get();
	}

	public Acronym update(Long id, CreateUpdateAcronym request) {
		log.info("Fetch data from user update: {}", request.toString());
		Acronym dataFromDB = acronymRepository.findById(id).get();
		
		if (dataFromDB != null) {
			dataFromDB.setName(request.getName());
			dataFromDB.setCategory(request.getCategory());
			dataFromDB.setIndonesia(request.getIndonesia());
			dataFromDB.setEnglish(request.getEnglish());
			return acronymRepository.save(dataFromDB);
		}
		
		return null;
	}

}
