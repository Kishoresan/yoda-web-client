package com.yoda.webservice.entity;

import com.yoda.webservice.entity.lookup.IdentityDocumentType;

public class IdentityDocument {
	
	private IdentityDocumentType identityDocumentType;
	
	private byte[] documentCopy;
	
	private boolean isVerified;

	public IdentityDocumentType getIdentityDocumentType() {
		return identityDocumentType;
	}

	public void setIdentityDocumentType(IdentityDocumentType identityDocumentType) {
		this.identityDocumentType = identityDocumentType;
	}

	public byte[] getDocumentCopy() {
		return documentCopy;
	}

	public void setDocumentCopy(byte[] documentCopy) {
		this.documentCopy = documentCopy;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	
}