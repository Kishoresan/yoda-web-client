package com.yoda.webservice.dto.profile;

import java.util.UUID;

import com.yoda.webservice.dto.Dto;
import com.yoda.webservice.entity.profile.MediaFile;

public class MediaFileDto implements Dto<MediaFile> {

	private static final long serialVersionUID = 1L;

	private UUID id;
	private String fileName;
	private String fileType;
	private byte[] fileData;
	
	public static MediaFileDto of(MediaFile entity) {
		MediaFileDto dto = new MediaFileDto();
		dto.synchronizeWithEntity(entity);
		return dto;
	}

	@Override
	public MediaFile createJPAEntity() {
		
		MediaFile file = new MediaFile();
		
		file.setFileData(getFileData());
		file.setFileName(getFileName());
		file.setFileType(getFileType());
		file.setId(getId());
		
		return file;
	}

	@Override
	public void synchronizeWithEntity(MediaFile entity) {
		
		this.fileData = entity.getFileData();
		this.fileName = entity.getFileName();
		this.fileType = entity.getFileType();
		this.id = entity.getId();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	@Override
	public String toString() {
		return "MediaFileDTO [id=" + id + ", fileName=" + fileName + ", fileType=" + fileType + "]";
	}
}