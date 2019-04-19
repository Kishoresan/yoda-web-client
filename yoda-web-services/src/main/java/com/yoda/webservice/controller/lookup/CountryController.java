package com.yoda.webservice.controller.lookup;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.webservice.dto.lookup.CountryDto;
import com.yoda.webservice.service.lookup.CountryService;

@RestController
@RequestMapping("/country")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CountryDto>> find() {
		
		return ResponseEntity.ok().body(countryService.findAll());
	}
	
	@RequestMapping(path = "/id/{cd}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CountryDto> findById(@PathVariable("cd") Short code) {
		
		Optional<CountryDto> foundCountry = countryService.findById(code);
		
		if(foundCountry.isPresent()) {
			return ResponseEntity.ok().body(foundCountry.get());
		}
		
		return ResponseEntity.notFound().build();
	}
}