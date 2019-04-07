package com.yoda.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.webservice.entity.profile.IdentityDocument;
import com.yoda.webservice.repository.profile.IdentityDocumentRepository;

@RestController
@RequestMapping("/identityDocument")
public class IdentityDocumentController {

	@Autowired
	private IdentityDocumentRepository identityDocumentRepository;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IdentityDocument> save(@RequestBody(required = true) IdentityDocument document) {
		
		IdentityDocument createdEntry = identityDocumentRepository.saveAndFlush(document);
		
		return new ResponseEntity<IdentityDocument>(createdEntry, HttpStatus.CREATED);
	}
}