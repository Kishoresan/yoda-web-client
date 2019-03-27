package com.yoda.webservice.entity.lookup;

public enum ContactType {
	
	HOME ((short) 1),
	PHONE_NUMBER ((short) 2),
	EMAIL((short) 3);
	
	private short contactType;

	private ContactType(short contactType) {
		this.contactType = contactType;
	}
	
	public short getContactType() {
		return contactType;
	}
}