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

import com.mulyadime.backend.models.Term;
import com.mulyadime.backend.payload.CreateUpdateTerm;
import com.mulyadime.backend.services.TermService;

@RestController
@RequestMapping("/api/term")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TermController {
	
	@Autowired
	private TermService termService;
	
	@RequestMapping(
			method = RequestMethod.GET,
			value = "")
	public ResponseEntity<List<Term>> findAll() {
		List<Term> result = termService.findAll();
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			value = "{id}")
	public ResponseEntity<Term> findOne(@PathVariable("id") Long id) {
		Term result = termService.findOne(id);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST,
			value = "")
	public ResponseEntity<Term> create(@Valid @RequestBody CreateUpdateTerm request) {
		Term result = termService.save(request);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.PUT,
			value = "{id}")
	public ResponseEntity<Term> update(@PathVariable("id") Long id, @Valid @RequestBody CreateUpdateTerm request) {
		Term result = termService.update(id, request);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.DELETE,
			value = "{id}")
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		return ResponseEntity.ok(termService.remove(id));
	}

}
