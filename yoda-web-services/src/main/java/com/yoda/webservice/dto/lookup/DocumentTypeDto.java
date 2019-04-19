package com.yoda.webservice.dto.lookup;

import com.yoda.webservice.dto.Dto;
import com.yoda.webservice.entity.lookup.DocumentType;

public class DocumentTypeDto implements Dto<DocumentType> {

	private static final long serialVersionUID = 1L;

	private Short code;
	private String description;

	public static DocumentTypeDto of(DocumentType entity) {
		DocumentTypeDto dto = new DocumentTypeDto();
		dto.synchronizeWithEntity(entity);
		return dto;
	}

	@Override
	public DocumentType createJPAEntity() {
		DocumentType entity = new DocumentType();

		entity.setCode(getCode());
		entity.setDescription(getDescription());

		return entity;
	}

	@Override
	public void synchronizeWithEntity(DocumentType entity) {
		this.code = entity.getCode();
		this.description = entity.getDescription();
	}

	public Short getCode() {
		return code;
	}

	public void setCode(Short code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "DocumentTypeDto [code=" + code + ", description=" + description + "]";
	}
}