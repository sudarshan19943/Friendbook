package com.macs.groupone.friendbookapplication.model;

import java.io.Serializable;

public class User implements Serializable{

	private int id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private boolean enabled;
	private String confirmation_token;
	private String stateId;
	private String countryId;
	private String cityId;
	private String passwordConfirm;
	private int friend_token;
	private int friend_confirm_token;
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getConfirmationToken() {
		return confirmation_token;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmation_token = confirmationToken;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean value) {
		this.enabled = value;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	
	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	
	public int getFriendToken()
	{
		return friend_token;
	}
	
	public void setFriendToken(int token)
	{
		this.friend_token = token;
	}
	
	public int getFriendConfirmationToken()
	{
		return friend_confirm_token;
	}
	
	public void setFriendConfirmationToken(int token)
	{
		this.friend_confirm_token = token;
	}
	
}