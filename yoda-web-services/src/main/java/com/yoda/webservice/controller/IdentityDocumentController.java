package com.yoda.webservice.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yoda.webservice.entity.profile.IdentityDocument;
import com.yoda.webservice.entity.profile.MediaFile;
import com.yoda.webservice.repository.profile.IdentityDocumentRepository;
import com.yoda.webservice.repository.profile.MediaFileRepository;

@RestController
@RequestMapping("/identityDocument")
public class IdentityDocumentController {

	@Autowired
	private IdentityDocumentRepository identityDocumentRepository;
	
	@Autowired
	private MediaFileRepository mediaFileRepository;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IdentityDocument> save(
			@RequestParam(name = "documentTypeCode", required = true) Short documentTypeCode,
			@RequestParam(name = "documentType", required = true) String documentType,
			@RequestParam(name = "userId", required = true) UUID userId,
			@RequestPart(name = "document", required = true) MultipartFile document) {
		
		byte[] fileData = null;
		
		try {
			fileData = document.getBytes();
		} catch (IOException e) {
			return ResponseEntity.badRequest().build();
		}
		
		
		MediaFile mediaFile = new MediaFile();
		mediaFile.setFileName(documentType);
		mediaFile.setFileType(document.getContentType());
		mediaFile.setFileData(fileData);
		
		mediaFile = mediaFileRepository.save(mediaFile);
		
		IdentityDocument identityDocument = IdentityDocument.unverified();
		identityDocument.setDocumentTypeCode(documentTypeCode);
		identityDocument.setUserId(userId);
		identityDocument.setMediaFileId(mediaFile.getId());
		
		identityDocument = identityDocumentRepository.save(identityDocument);
		
		return new ResponseEntity<IdentityDocument>(identityDocument, HttpStatus.CREATED);
	}
}