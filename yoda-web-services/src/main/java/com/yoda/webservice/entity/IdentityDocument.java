package com.yoda.webservice.entity;

public class IdentityDocument {
	
	private IdentityDocumentType identityDocumentType;
	
	private boolean isVerified;

	public IdentityDocumentType getIdentityDocumentType() {
		return identityDocumentType;
	}

	public void setIdentityDocumentType(IdentityDocumentType identityDocumentType) {
		this.identityDocumentType = identityDocumentType;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	
}