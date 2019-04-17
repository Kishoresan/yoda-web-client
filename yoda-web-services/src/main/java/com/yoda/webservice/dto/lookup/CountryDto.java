package com.yoda.webservice.dto.lookup;

import com.yoda.webservice.dto.Dto;
import com.yoda.webservice.entity.lookup.Country;

public class CountryDto implements Dto<Country> {

	private static final long serialVersionUID = 1L;

	private Short code;
	private String name;
	private String phoneCode;
	
	public static CountryDto of(Country entity) {
		CountryDto dto = new CountryDto();
		dto.synchronizeWithEntity(entity);
		return dto;
	}

	@Override
	public Country createJPAEntity() {

		Country entity = new Country();

		entity.setCode(getCode());
		entity.setName(getName());
		entity.setPhoneCode(getPhoneCode());

		return entity;
	}

	@Override
	public void synchronizeWithEntity(Country entity) {

		this.code = entity.getCode();
		this.name = entity.getName();
		this.phoneCode = entity.getPhoneCode();
	}

	public Short getCode() {
		return code;
	}

	public void setCode(Short code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	@Override
	public String toString() {
		return "CountryDto [code=" + code + ", name=" + name + ", phoneCode=" + phoneCode + "]";
	}
}