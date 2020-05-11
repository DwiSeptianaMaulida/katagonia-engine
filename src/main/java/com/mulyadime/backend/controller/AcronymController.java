package com.mulyadime.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mulyadime.backend.models.Acronym;
import com.mulyadime.backend.payload.CreateUpdateAcronym;
import com.mulyadime.backend.services.AcronymService;

@RestController
@RequestMapping("/api/acronym")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AcronymController {
	

	@Autowired
	private AcronymService acronymService;
	
	@RequestMapping(
			method = RequestMethod.GET,
			value = "")
	public ResponseEntity<List<Acronym>> findAll() {
		List<Acronym> result = acronymService.findAll();
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			value = "{id}")
	public ResponseEntity<Acronym> findOne(@PathVariable("id") Long id) {
		Acronym result = acronymService.findOne(id);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST,
			value = "")
	public ResponseEntity<Acronym> create(@Valid @RequestBody CreateUpdateAcronym request) {
		Acronym result = acronymService.save(request);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.PUT,
			value = "{id}")
	public ResponseEntity<Acronym> update(@PathVariable("id") Long id, @Valid @RequestBody CreateUpdateAcronym request) {
		Acronym result = acronymService.update(id, request);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.DELETE,
			value = "{id}")
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		return ResponseEntity.ok(acronymService.remove(id));
	}

}
