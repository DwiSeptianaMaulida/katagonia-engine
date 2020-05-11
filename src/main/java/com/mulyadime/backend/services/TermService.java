package com.mulyadime.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulyadime.backend.models.Term;
import com.mulyadime.backend.payload.CreateUpdateTerm;
import com.mulyadime.backend.repository.TermRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TermService {
	
	@Autowired
	private TermRepository termRepository;
	
	public List<Term> findAll() {
		List<Term> resultAsList = new ArrayList<>();
		termRepository.findAll().iterator().forEachRemaining(resultAsList::add);
		log.debug("", resultAsList.toString());
		
		return resultAsList;
	}

	public Term save(CreateUpdateTerm request) {
		log.info("Fetch data from user input: {}", request.toString());
		
		Term data = new Term();
		data.setName(request.getName());
		data.setTranslation(request.getTranslation());
		data.setCategory(request.getCategory());
		data.setLanguage(request.getLanguage());
		
		return termRepository.save(data);
	}

	public boolean remove(Long id) {
		Term data = termRepository.findById(id).get();
		
		if (data != null) {
			termRepository.deleteById(id);
			return true;
		}
		
		return false;
	}

	public Term findOne(Long id) {
		return termRepository.findById(id).get();
	}

	public Term update(Long id, CreateUpdateTerm request) {
		log.info("Fetch data from user update: {}", request.toString());
		Term dataFromDB = termRepository.findById(id).get();
		
		if (dataFromDB != null) {
			dataFromDB.setName(request.getName());
			dataFromDB.setTranslation(request.getTranslation());
			dataFromDB.setCategory(request.getCategory());
			dataFromDB.setLanguage(request.getLanguage());
			return termRepository.save(dataFromDB);
		}
		
		return null;
	}

}
