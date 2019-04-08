package com.macs.groupone.friendbookapplication.controller;

public class Constants {
	

	//EMAIL Title
	public static final String RESET_PASSWORD_TITLE="Reset Password";
	public static final String REGISTRATION_CONFIRMATION_MESSAGE="You have been registered with Friend Book.";
	public static final String REGISTRATION_CONFIRMATION_TITLE="Registration Confirmation.";
	
	
	//Error Messages
	public static final String ERRORMESSAGE="errorMessage";
	public static final String INVALID_PASSWORD_LINK="Oops!  This is an invalid password reset link.";
	public static final String ACCOUNT_NOT_FOUND="We didn't find an account for that e-mail address.";
	
	//success messages
	public static final String SUCCESSMESSAGE="successMessage";
	public static final String RESET_PASSWORD_LINK_SENT="A password reset link has been sent to your Registered E-mail ID.";
	public static final String PASSWORD_DOES_NOT_MATCH="Unable to Authenticate User, Please recheck Password.";
	public static final String PASSWORD_RESET_SUCCESS="You have successfully reset your password.  You may now login.";
	public static final String REGISTRATIONSUCCESS="You have been sucessfully registered, and email has been sent to your registered email.";
	public static final String MESSAGE_POSTED_SUCCESSFULLY="Message has been Posted Successfully.";
	
	//Profile Pic Size Exceed Error
	public static final String IMAGE_SIZE_EXCEEDED="Image Size exceeded, chose image less than 20 KB";
	
	
	//JSP Views
	public static final String LOGIN_VIEW="login";
	public static final String PROFILE_VIEW="profile";
	public static final String REGISTER_VIEW="registration";
	public static final String RESET_VIEW="resetpassword";
	public static final String FORGOTPASSWORD_VIEW="forgotpassword";
	public static final String NEW_POST_VIEW="newpost";
	public static final String TIMELINE_VIEW="timeline";
	
	//redirect attribute
	public static final String REDIRECT_TIMELINE="redirect:/timeline";
	public static final String REDIRECT_LOGIN="redirect:/login";
	public static final String REDIRECT_PROFILE="redirect:/profile";
	public static final String REDIRECT_NEWPOST="redirect:/newpost";
	public static final String REDIRECT_RESETPASSWORD="redirect:/resetpassword";
	public static final String REDIRECT_FRIENDS="redirect:/friends";
	
	//Session Attributes
	public static final String EMAIL="email";
	
	//Validation Forms
	public static final String LOGIN_FORM="loginForm";
	public static final String FORGOTPASSWORD_FORM="forgotPasswordForm";
	
	
}
