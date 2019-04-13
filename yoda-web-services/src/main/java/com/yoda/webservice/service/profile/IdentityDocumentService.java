package com.yoda.webservice.service.profile;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.yoda.webservice.dto.profile.IdentityDocumentDTO;

@Component
public interface IdentityDocumentService {

	public IdentityDocumentDTO save(IdentityDocumentDTO identityDocument, String documentName, MultipartFile document)
			throws IOException;
	
	public IdentityDocumentDTO update(IdentityDocumentDTO identityDocument);

	public void delete(UUID identityDocumentId);

}