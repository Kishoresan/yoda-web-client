package com.yoda.webservice.entity;

import com.yoda.webservice.entity.lookup.Country;
import com.yoda.webservice.entity.lookup.PhoneType;

public class PhoneNumber extends ContactInformation{
	
	private Country country;
	private String number;
	private PhoneType phoneType;
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}
		
}