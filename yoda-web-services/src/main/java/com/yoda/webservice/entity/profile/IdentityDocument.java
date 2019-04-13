package com.yoda.webservice.entity.profile;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(schema = "PROFILE", name = "T_IDENTITY_DOCUMENT")
public class IdentityDocument implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "BINARY(16)")
	private UUID id;
	
	@Column(name = "FK_DOCUMENT_TYPE_CD", nullable = false, updatable = false)
	private Short documentTypeCode;
	
	@Column(name = "FK_USER_ID", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
	private UUID userId;
	
	@Column(name = "FK_MEDIA_FILE_ID", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
	private UUID mediaFileId;
	
	@Column(name = "IS_VERIFIED", columnDefinition = "BIT")
	private boolean isVerified;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdentityDocument other = (IdentityDocument) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	@Override
	public String toString() {
		return "IdentityDocument [id=" + id + ", documentTypeCode=" + documentTypeCode + ", userId=" + userId
				+ ", mediaFileId=" + mediaFileId + ", isVerified=" + isVerified + "]";
	}
	
}