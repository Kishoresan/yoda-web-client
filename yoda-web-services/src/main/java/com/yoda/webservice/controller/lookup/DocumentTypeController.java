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

import com.yoda.webservice.dto.lookup.DocumentTypeDto;
import com.yoda.webservice.service.lookup.DocumentTypeService;

@RestController
@RequestMapping("/documentType")
public class DocumentTypeController {
	
	@Autowired
	private DocumentTypeService documentTypeService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DocumentTypeDto>> find() {
		return ResponseEntity.ok().body(documentTypeService.findAll());
	}
	
	@RequestMapping(path = "/id/{cd}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DocumentTypeDto> findById(@PathVariable("cd") Short code) {
		
		Optional<DocumentTypeDto> foundType = documentTypeService.findById(code);
		
		if(foundType.isPresent()) {
			return ResponseEntity.ok().body(foundType.get());
		}
		
		return ResponseEntity.notFound().build();
	}
}