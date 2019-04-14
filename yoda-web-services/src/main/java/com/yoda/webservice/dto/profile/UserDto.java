package com.yoda.webservice.dto.profile;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import com.yoda.webservice.dto.Dto;
import com.yoda.webservice.entity.profile.User;

public class UserDto implements Dto<User> {

	private static final long serialVersionUID = 1L;

	private UUID id;
	private String email;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date dateOfBirth;
	private Short nationality;
	private Timestamp signedUpOn;
	
	public static UserDto of(User entity) {
		UserDto user = new UserDto();
		user.synchronizeWithEntity(entity);
		return user;
	}

	@Override
	public User createJPAEntity() {

		User user = new User();
		
		user.setDateOfBirth(getDateOfBirth());
		user.setEmail(getEmail());
		user.setFirstName(getFirstName());
		user.setId(getId());
		user.setLastName(getLastName());
		user.setMiddleName(getMiddleName());
		user.setNationality(getNationality());
		user.setSignedUpOn(getSignedUpOn());
		
		return user;
	}

	@Override
	public void synchronizeWithEntity(User entity) {
		
		this.dateOfBirth = entity.getDateOfBirth();
		this.email = entity.getEmail();
		this.firstName = entity.getFirstName();
		this.id = entity.getId();
		this.lastName = entity.getLastName();
		this.middleName = entity.getMiddleName();
		this.nationality = entity.getNationality();
		this.signedUpOn = entity.getSignedUpOn();

	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Short getNationality() {
		return nationality;
	}

	public void setNationality(Short nationality) {
		this.nationality = nationality;
	}

	public Timestamp getSignedUpOn() {
		return signedUpOn;
	}

	public void setSignedUpOn(Timestamp signedUpOn) {
		this.signedUpOn = signedUpOn;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", email=" + email + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", nationality=" + nationality
				+ ", signedUpOn=" + signedUpOn + "]";
	}
}