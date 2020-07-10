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

import com.mulyadime.backend.dto.SearchDTO;
import com.mulyadime.backend.payload.SearchRequest;
import com.mulyadime.backend.services.SearchService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SearchController {
	
	@Autowired
	private SearchService svcSearch;
	
	@RequestMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST,
			value = "")
	public void searchBy(@Valid @RequestBody SearchRequest request) {
		
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			value = "{search}")
	public ResponseEntity<List<SearchDTO>> getSearchResult(@PathVariable("search") String request) {
		log.info("Search String: {}", request);
		List<SearchDTO> search = svcSearch.getResult(request);
		log.debug("{}", search);
		
		return ResponseEntity.ok(search);
		
	}

}
