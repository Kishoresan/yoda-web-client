package com.yoda.webservice.service.profile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.yoda.webservice.dto.profile.IdentityDocumentDto;
import com.yoda.webservice.entity.profile.IdentityDocument;
import com.yoda.webservice.service.JpaBackedService;

@Component
public interface IdentityDocumentService extends JpaBackedService<IdentityDocumentDto, IdentityDocument, UUID> {
	
	public Optional<IdentityDocumentDto> findByUserId(UUID userId);

	public IdentityDocumentDto save(IdentityDocumentDto identityDocument, String documentName, MultipartFile document)
			throws IOException;

}