package com.yoda.webservice.entity;

public class PhoneNumber extends ContactInformation{
	
	private String countryCode;
	private String number;
	
	public String getCountryCode() {
		return countryCode;
	}
	
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
}