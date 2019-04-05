package com.yoda.webservice.entity.profile;

public class EmailAddress extends ContactInformation{
	
	private String emailAddress;
	private boolean isPrimary;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

}