package com.macs.groupone.friendbookapplication.model;

import java.util.ArrayList;

public class User {

	private int id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private boolean enabled;
	private String confirmationToken;
	private String stateId;
	private String countryId;
	private String cityId;
	private String passwordConfirm;
    private boolean friendToken;
	private boolean friendConfirmToken;
	private ArrayList<Post> posts;
	private String userImage;

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public ArrayList<Post> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
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
	
	public boolean getFriendToken()
	{
		return friendToken;
	}
	
	public void setFriendToken(boolean token)
	{
		this.friendToken = token;
	}
	
	public boolean getFriendConfirmationToken()
	{
		return friendConfirmToken;
	}
	
	public void setFriendConfirmationToken(boolean token)
	{
		this.friendConfirmToken = token;
	}

}