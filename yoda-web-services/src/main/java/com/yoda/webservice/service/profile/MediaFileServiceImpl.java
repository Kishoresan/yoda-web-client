package com.yoda.webservice.service.profile;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoda.webservice.dto.profile.MediaFileDTO;
import com.yoda.webservice.entity.profile.MediaFile;
import com.yoda.webservice.repository.profile.MediaFileRepository;
import com.yoda.webservice.service.BaseJPABackedService;

@Service("mediaFileService")
@Transactional(transactionManager = "profileTransactionManager")
public class MediaFileServiceImpl extends BaseJPABackedService<MediaFileDTO, MediaFile> implements MediaFileService {

	@Autowired
	private MediaFileRepository mediaFileRepository;


	@Override
	protected MediaFileDTO buildDto(MediaFile entity) {
		return MediaFileDTO.of(entity);
	}

	@Override
	protected JpaRepository<MediaFile, UUID> getJpaRepository() {
		return mediaFileRepository;
	}
}