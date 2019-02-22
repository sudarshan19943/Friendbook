package com.macs.groupone.friendbookapplication.constants;

public class Constants {
	
	
	//Datasource connection properties
	public static final String URL="spring.datasource.url";
	public static final String USERNAME="spring.datasource.username";
	public static final String PASSWORD="spring.datasource.password";
	
	//Application FIle path
	public static final String APPLICATION_PROPERTIES="src/main/resources/application.properties";
	
	//mail properties
	public static final String PROTOCOL="mail.transport.protocol";
	public static final String PORT="mail.smtp.port";
	public static final String HOST="mail.smtp.host";
	public static final String ENABLED="mail.smtp.starttls.enable";
	public static final String AUTH="mail.smtp.auth";
	public static final String MAIL_USERNAME="mail.smtp.username";
	public static final String MAIL_PASSWORD="mail.smtp.password";
	
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
	public static final String REGISTER_VIEW="registration";
	public static final String RESET_VIEW="resetpassword";
	public static final String FORGOTPASSWORD_VIEW="forgotpassword";
	
	
	
	
	
	
	
	
	
	
	
	

}
