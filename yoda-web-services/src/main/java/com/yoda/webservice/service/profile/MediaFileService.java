package com.yoda.webservice.service.profile;

import org.springframework.stereotype.Component;

import com.yoda.webservice.dto.profile.MediaFileDTO;
import com.yoda.webservice.entity.profile.MediaFile;
import com.yoda.webservice.service.JPABackedService;

@Component
public interface MediaFileService extends JPABackedService<MediaFileDTO, MediaFile> {

}