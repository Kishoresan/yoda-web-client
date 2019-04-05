package com.yoda.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.webservice.entity.lookup.DocumentType;
import com.yoda.webservice.repository.lookup.DocumentTypeRepository;

@RestController
@RequestMapping("/documentType")
public class DocumentTypeController {
	
	@Autowired
	private DocumentTypeRepository documentTypeRepository;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DocumentType> find() {
		return documentTypeRepository.findAll();
	}
}