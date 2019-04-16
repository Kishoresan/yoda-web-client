package com.yoda.webservice.service.profile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yoda.webservice.dto.profile.IdentityDocumentDto;
import com.yoda.webservice.entity.profile.IdentityDocument;
import com.yoda.webservice.entity.profile.MediaFile;
import com.yoda.webservice.repository.profile.IdentityDocumentRepository;
import com.yoda.webservice.repository.profile.MediaFileRepository;
import com.yoda.webservice.service.BaseJpaBackedService;

@Service("identityDocumentService")
@Transactional(transactionManager = "profileTransactionManager")
public class IdentityDocumentServiceImpl extends BaseJpaBackedService<IdentityDocumentDto, IdentityDocument>
		implements IdentityDocumentService {

	@Autowired
	private IdentityDocumentRepository identityDocumentRepository;

	@Autowired
	private MediaFileRepository mediaFileRepository;

	@Override
	public Optional<IdentityDocumentDto> findByUserId(UUID userId) {
		return extractDtoFromOptional(identityDocumentRepository.findByUserId(userId));
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
	public void delete(UUID identityDocumentId) {

		Optional<IdentityDocument> toBeDeleted = identityDocumentRepository.findById(identityDocumentId);

		if (toBeDeleted.isPresent()) {

			UUID mediaFileId = toBeDeleted.get().getMediaFileId();

			identityDocumentRepository.delete(toBeDeleted.get());
			mediaFileRepository.deleteById(mediaFileId);
		}
	}

	@Override
	protected IdentityDocumentDto buildDto(IdentityDocument entity) {
		return IdentityDocumentDto.of(entity);
	}

	@Override
	protected JpaRepository<IdentityDocument, UUID> getJpaRepository() {
		return identityDocumentRepository;
	}
}