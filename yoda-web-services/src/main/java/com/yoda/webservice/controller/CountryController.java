package com.yoda.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	public List<Country> find() {
		return countryRepository.findAll();
	}
	
	@RequestMapping(path = "/{cd}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Country findById(@PathVariable("cd") Short code) {
		return countryRepository.findById(code).orElse(null);
	}
}