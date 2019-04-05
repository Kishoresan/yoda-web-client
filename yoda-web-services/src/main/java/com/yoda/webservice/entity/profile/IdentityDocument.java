package com.yoda.webservice.entity.profile;

import com.yoda.webservice.entity.lookup.DocumentType;

public class IdentityDocument {
	
	private DocumentType documentType;
	
	private byte[] documentCopy;
	
	private boolean isVerified;

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}
	
	public DocumentType getDocumentType() {
		return documentType;
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