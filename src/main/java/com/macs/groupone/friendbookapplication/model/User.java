package com.macs.groupone.friendbookapplication.model;

public class User {

	private int id;
	private String email;
	private String password;
	private String first_name;
	private String last_name;
	private boolean enabled;
	private String confirmation_token;
	private String province;
	private String country;
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
		return first_name;
	}

	public void setFirstName(String firstname) {
		this.first_name = firstname;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String lastname) {
		this.last_name = lastname;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}