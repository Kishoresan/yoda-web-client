package com.yoda.webservice.entity.lookup;

public enum PhoneType {

	HOME ((short) 1),
	MOBILE((short) 2),
	OFFICE((short) 3);
	
	private short phoneType;

	private PhoneType(short phoneType) {
		this.phoneType = phoneType;
	}
	
	public short getPhoneType() {
		return phoneType;
	}
	
}