package com.macs.groupone.friendbookapplication.validator;

public enum ValidationCode {
	
	    //Login Validation 
        EMAIL_IN_USE("AlreadyInUSe.loginForm.email"),
        EMAIL_NOT_FOUND("NotFound.loginForm.email"),
        EMIAL_NOT_VALID("Invalid.loginForm.email"),
        AUTHENTICATION_ERROR("Authentication.loginForm.password"),
        
        //Registration Error
        DUPLICATE_EMAIL("Duplicate.registrationForm.email"),
        PASSWORD_DOES_NOT_MATCH("Diff.registrationForm.passwordConfirm"),
        PASSWORD_POLICY_DOES_NOT_SATISFY("PasswordPolicy.registrationForm.passwordConfirm"),
        PASSWORD_SIZE_POLICY("Size.registrationForm.password"),
	
	    //Profile Image Size
	     PROFILE_MAX__SIZE("MaxSize.profileForm.userImage");

        private final String propertyName;

        ValidationCode(String propertyName) {
            this.propertyName = propertyName;
        }

        public String getPropertyName() {
			return propertyName;
		}

		
}

