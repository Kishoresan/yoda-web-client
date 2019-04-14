package com.yoda.webservice.service.profile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yoda.webservice.dto.profile.IdentityDocumentDto;
import com.yoda.webservice.entity.profile.IdentityDocument;
import com.yoda.webservice.entity.profile.MediaFile;
import com.yoda.webservice.repository.profile.IdentityDocumentRepository;
import com.yoda.webservice.repository.profile.MediaFileRepository;

@Service("identityDocumentService")
@Transactional(transactionManager = "profileTransactionManager")
public class IdentityDocumentServiceImpl implements IdentityDocumentService {

	@Autowired
	private IdentityDocumentRepository identityDocumentRepository;

	@Autowired
	private MediaFileRepository mediaFileRepository;

	@Override
	public Optional<IdentityDocumentDto> findById(UUID id) {

		Optional<IdentityDocument> foundEntity = identityDocumentRepository.findById(id);

		if (foundEntity.isPresent()) {

			return Optional.<IdentityDocumentDto>of(IdentityDocumentDto.of(foundEntity.get()));
		}

		return Optional.<IdentityDocumentDto>empty();
	}

	@Override
	public Optional<IdentityDocumentDto> findByUserId(UUID userId) {
		
		Optional<IdentityDocument> foundEntity = identityDocumentRepository.findByUserId(userId);

		if (foundEntity.isPresent()) {

			return Optional.<IdentityDocumentDto>of(IdentityDocumentDto.of(foundEntity.get()));
		}

		return Optional.<IdentityDocumentDto>empty();
	}

	@Override
	public IdentityDocumentDto save(IdentityDocumentDto identityDocument, String documentName, MultipartFile document)
			throws IOException {

		byte[] fileData = document.getBytes();

		MediaFile mediaFile = new MediaFile();
		mediaFile.setFileName(documentName);
		mediaFile.setFileType(document.getContentType());
		mediaFile.setFileData(fileData);

		mediaFile = mediaFileRepository.save(mediaFile);

		identityDocument.setMediaFileId(mediaFile.getId());

		IdentityDocument savedEntity = identityDocumentRepository.save(identityDocument.createJPAEntity());

		identityDocument.synchronizeWithEntity(savedEntity);

		return identityDocument;
	}

	@Override
	public IdentityDocumentDto update(IdentityDocumentDto identityDocument) {

		IdentityDocument updatedEntity = identityDocumentRepository.save(identityDocument.createJPAEntity());

		identityDocument.synchronizeWithEntity(updatedEntity);

		return identityDocument;
	}

	@Override
	public void delete(UUID identityDocumentId) {

		Optional<IdentityDocument> toBeDeleted = identityDocumentRepository.findById(identityDocumentId);

		if (toBeDeleted.isPresent()) {

			UUID mediaFileId = toBeDeleted.get().getMediaFileId();

			identityDocumentRepository.delete(toBeDeleted.get());
			mediaFileRepository.deleteById(mediaFileId);
		}
	}
}