package com.yoda.webservice.entity.lookup;

public enum IdentityDocumentType {

	PASSPORT ((short)1),
	DRIVERS_LICENCE ((short)2),
	RESIDENCE_CARD ((short)3);
	
	private short documentType;

	private IdentityDocumentType(short documentType) {
		this.documentType = documentType;
	}
	
	public short getDocumentType() {
		return documentType;
	}

}