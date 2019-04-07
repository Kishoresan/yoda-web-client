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

import com.yoda.webservice.entity.lookup.DocumentType;
import com.yoda.webservice.repository.lookup.DocumentTypeRepository;

@RestController
@RequestMapping("/documentTypes")
public class DocumentTypeController {
	
	@Autowired
	private DocumentTypeRepository documentTypeRepository;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DocumentType>> find() {
		return new ResponseEntity<List<DocumentType>>(documentTypeRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/id/{cd}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DocumentType> findById(@PathVariable("cd") Short code) {
		
		Optional<DocumentType> foundType = documentTypeRepository.findById(code);
		
		if(foundType.isPresent()) {
			return new ResponseEntity<DocumentType>(foundType.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<DocumentType>(HttpStatus.NOT_FOUND);
	}
}