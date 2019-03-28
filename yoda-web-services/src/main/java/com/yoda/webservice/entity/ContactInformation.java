package com.yoda.webservice.entity;

import com.yoda.webservice.entity.lookup.ContactType;

public abstract class ContactInformation {

	private boolean isVerified;
	private ContactType contactType;

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}
	
}