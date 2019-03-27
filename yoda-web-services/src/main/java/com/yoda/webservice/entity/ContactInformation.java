package com.yoda.webservice.entity;

public abstract class ContactInformation {

	private boolean isPrimary;

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
}