package com.yoda.webservice.service.profile;

import org.springframework.stereotype.Component;

import com.yoda.webservice.dto.profile.MediaFileDto;
import com.yoda.webservice.entity.profile.MediaFile;
import com.yoda.webservice.service.JpaBackedService;

@Component
public interface MediaFileService extends JpaBackedService<MediaFileDto, MediaFile> {

}