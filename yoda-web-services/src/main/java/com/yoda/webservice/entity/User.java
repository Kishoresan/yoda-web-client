package com.yoda.webservice.entity;

import java.time.LocalDate;

public class User {
	
	private String username;
	private LocalDate dateOfBirth;
	private String nationality;
	private byte[] profilePic;
	
	private LocalDate signedUpOn;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}

	public LocalDate getSignedUpOn() {
		return signedUpOn;
	}

	public void setSignedUpOn(LocalDate signedUpOn) {
		this.signedUpOn = signedUpOn;
	}

}