package com.yoda.webservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.webservice.entity.lookup.Country;
import com.yoda.webservice.repository.lookup.CountryRepository;

@RestController
@RequestMapping("/countries")
public class CountryController {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Country>> find() {
		return new ResponseEntity<List<Country>>(countryRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{cd}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Country> findById(@PathVariable("cd") Short code) {
		
		Optional<Country> foundCountry = countryRepository.findById(code);
		
		if(foundCountry.isPresent()) {
			return new ResponseEntity<Country>(foundCountry.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
	}
}