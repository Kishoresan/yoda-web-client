package com.yoda.webservice.dto.profile;

import java.io.Serializable;
import java.util.UUID;

import com.yoda.webservice.dto.DTO;
import com.yoda.webservice.entity.profile.IdentityDocument;

public class IdentityDocumentDTO implements DTO<IdentityDocument>, Serializable {

	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private Short documentTypeCode;
	private UUID userId;
	private UUID mediaFileId;
	private boolean isVerified;
	
	public static IdentityDocumentDTO unverified() {
		IdentityDocumentDTO dto = new IdentityDocumentDTO();
		dto.setIsVerified(false);
		return dto;
	}

	@Override
	public IdentityDocument createJPAEntity() {
		
		IdentityDocument jpaEntity = new IdentityDocument();
		
		jpaEntity.setId(getId());
		jpaEntity.setDocumentTypeCode(getDocumentTypeCode());
		jpaEntity.setUserId(getUserId());
		jpaEntity.setMediaFileId(getMediaFileId());
		jpaEntity.setVerified(getIsVerified());
		
		return jpaEntity;
	}

	@Override
	public void synchronizeWithEntity(IdentityDocument entity) {
		setId(entity.getId());
		setDocumentTypeCode(entity.getDocumentTypeCode());
		setUserId(entity.getUserId());
		setMediaFileId(entity.getMediaFileId());
		setIsVerified(entity.isVerified());
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Short getDocumentTypeCode() {
		return documentTypeCode;
	}

	public void setDocumentTypeCode(Short documentTypeCode) {
		this.documentTypeCode = documentTypeCode;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public UUID getMediaFileId() {
		return mediaFileId;
	}

	public void setMediaFileId(UUID mediaFileId) {
		this.mediaFileId = mediaFileId;
	}

	/**
	 * IMPORTANT:
	 * Jackson needs get in the beginning of method name. If this object is manipulated to/from JSON, make sure you 
	 * keep it in mind. 
	 */
	public boolean getIsVerified() {
		return isVerified;
	}

	/**
	 * IMPORTANT:
	 * Jackson needs set in the beginning of method name. If this object is manipulated to/from JSON, make sure you 
	 * keep it in mind. 
	 */
	public void setIsVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	@Override
	public String toString() {
		return "IdentityDocumentDTO [id=" + id + ", documentTypeCode=" + documentTypeCode + ", userId=" + userId
				+ ", mediaFileId=" + mediaFileId + ", isVerified=" + isVerified + "]";
	}
}