package com.macs.groupone.friendbookapplication.validator;

import com.macs.groupone.friendbookapplication.validator.ForgetPasswordValidator;
import com.macs.groupone.friendbookapplication.validator.LoginValidator;
import com.macs.groupone.friendbookapplication.validator.RegistrationValidator;
import com.macs.groupone.friendbookapplication.validator.ResetPasswordValidator;

public interface IFormValidatorFactory {
	
	ForgetPasswordValidator getForgetPasswordValidator();

	LoginValidator getLoginValidator();

	RegistrationValidator getRegistrationValidator();

	ResetPasswordValidator getResetPasswordValidator();

}
