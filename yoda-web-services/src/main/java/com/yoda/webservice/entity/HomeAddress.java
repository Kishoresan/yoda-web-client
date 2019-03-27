package com.yoda.webservice.entity;

import com.yoda.webservice.entity.lookup.Country;

public class HomeAddress extends ContactInformation {
	
	private Short houseNumber;
	private String street;
	private String zipCode;
	private String city;
	private String state;
	private Country country;
	
	public Short getHouseNumber() {
		return houseNumber;
	}
	
	public void setHouseNumber(Short houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}
}