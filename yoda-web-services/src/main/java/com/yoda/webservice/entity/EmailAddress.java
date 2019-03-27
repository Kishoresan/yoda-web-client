package com.yoda.webservice.entity;

public class EmailAddress extends ContactInformation{
	
	private String emailAddress;
	private boolean isVerified;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

}