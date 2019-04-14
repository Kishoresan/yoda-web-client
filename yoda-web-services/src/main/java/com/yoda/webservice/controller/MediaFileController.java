package com.yoda.webservice.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.webservice.dto.profile.MediaFileDto;
import com.yoda.webservice.service.profile.MediaFileService;

@RestController
@RequestMapping("/mediaFile")
public class MediaFileController {

	@Autowired
	private MediaFileService mediaFileService;

	@RequestMapping(path = "/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<Resource> findById(@PathVariable("id") UUID id) {

		Optional<MediaFileDto> foundFile = mediaFileService.findById(id);

		if (foundFile.isPresent()) {

			MediaFileDto file = foundFile.get();

			return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getFileType()))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
					.body(new ByteArrayResource(file.getFileData()));
		}

		return ResponseEntity.notFound().build();
	}

	@RequestMapping(path = "/id/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") UUID id) {
		mediaFileService.delete(id);
	}
}