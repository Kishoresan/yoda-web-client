package com.yoda.webservice.service.profile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.yoda.webservice.dto.profile.IdentityDocumentDto;

@Component
public interface IdentityDocumentService {
	
	public Optional<IdentityDocumentDto> findById(UUID id);
	
	public Optional<IdentityDocumentDto> findByUserId(UUID userId);

	public IdentityDocumentDto save(IdentityDocumentDto identityDocument, String documentName, MultipartFile document)
			throws IOException;
	
	public IdentityDocumentDto update(IdentityDocumentDto identityDocument);

	public void delete(UUID identityDocumentId);

}