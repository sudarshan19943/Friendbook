package com.macs.groupone.friendbookapplication.controller;

public class Constants {
	

	//EMAIL Title
	public static final String EMAIL_TITLE="Reset Password";
	
	
	//Error Messages
	public static final String ERRORMESSAGE="errorMessage";
	public static final String ALREADY_REGISTERED="alreadyRegisteredMessage";
	public static final String ALREADY_REGISTERED_ERROR= "Oops!  There is already a user registered with the email provided.";
	public static final String USER_DOES_NOT_EXISTS="Oops user does not exist.";
	public static final String INVALID_PASSWORD_LINK="Oops!  This is an invalid password reset link.";
	public static final String ACCOUNT_NOT_FOUND="We didn't find an account for that e-mail address.";
	
	//success messages
	public static final String SUCCESSMESSAGE="successMessage";
	public static final String PASSWORD_LINK_SENT="A password reset link has been sent to";
	public static final String PASSWORD_RESET_SUCCESS="You have successfully reset your password.  You may now login.";
	
	
	//JSP Views
	public static final String LOGIN_VIEW="login";
	public static final String PROFILE_VIEW="profile";
	public static final String REGISTER_VIEW="register";
	public static final String RESET_VIEW="reset";
	public static final String FORGOTPASSWORD_VIEW="forgotpassword";
	
}
