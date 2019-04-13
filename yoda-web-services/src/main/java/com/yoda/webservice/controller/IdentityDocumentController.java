package com.yoda.webservice.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yoda.webservice.dto.profile.IdentityDocumentDTO;
import com.yoda.webservice.service.profile.IdentityDocumentService;

@RestController
@RequestMapping("/identityDocument")
public class IdentityDocumentController {

	@Autowired
	private IdentityDocumentService identityDocumentService;
	
	@RequestMapping(path = "/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IdentityDocumentDTO> findById(@PathVariable("id") UUID id) {
		
		Optional<IdentityDocumentDTO> identityDocumentDTO = identityDocumentService.findById(id);
		
		if(identityDocumentDTO.isPresent()) {
			return new ResponseEntity<IdentityDocumentDTO>(identityDocumentDTO.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<IdentityDocumentDTO>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path = "/userId/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IdentityDocumentDTO> findByEmail(@PathVariable("userId") UUID userId) {
		
		Optional<IdentityDocumentDTO> identityDocumentDTO = identityDocumentService.findByUserId(userId);
		
		if(identityDocumentDTO.isPresent()) {
			return new ResponseEntity<IdentityDocumentDTO>(identityDocumentDTO.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<IdentityDocumentDTO>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IdentityDocumentDTO> save(
			@RequestParam(name = "documentTypeCode", required = true) Short documentTypeCode,
			@RequestParam(name = "documentName", required = true) String documentName,
			@RequestParam(name = "userId", required = true) UUID userId,
			@RequestPart(name = "document", required = true) MultipartFile document) {

		IdentityDocumentDTO identityDocumentDTO = IdentityDocumentDTO.unverified();

		identityDocumentDTO.setDocumentTypeCode(documentTypeCode);
		identityDocumentDTO.setUserId(userId);

		try {
			identityDocumentDTO = identityDocumentService.save(identityDocumentDTO, documentName, document);
			return new ResponseEntity<IdentityDocumentDTO>(identityDocumentDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: add logging
		}

		return new ResponseEntity<IdentityDocumentDTO>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody(required = true) IdentityDocumentDTO identityDocument) {
		identityDocumentService.update(identityDocument);
	}

	@RequestMapping(path = "/id/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") UUID id) {
		identityDocumentService.delete(id);
	}
}