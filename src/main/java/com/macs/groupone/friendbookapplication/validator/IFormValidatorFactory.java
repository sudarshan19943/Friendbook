package com.macs.groupone.friendbookapplication.validator;


public interface IFormValidatorFactory {
	
	ForgetPasswordValidator getForgetPasswordValidator();

	LoginValidator getLoginValidator();

	RegistrationValidator getRegistrationValidator();

	ResetPasswordValidator getResetPasswordValidator();

}
