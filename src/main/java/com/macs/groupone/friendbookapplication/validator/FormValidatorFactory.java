package com.macs.groupone.friendbookapplication.validator;


public class FormValidatorFactory implements IFormValidatorFactory {
	
	private static FormValidatorFactory formValidatorFactory;
	
	public static FormValidatorFactory getInstance()
	{
		if(formValidatorFactory==null)
		{
			formValidatorFactory=new FormValidatorFactory();
			return  formValidatorFactory;
		}
		return formValidatorFactory;
	}

	@Override
	public ForgetPasswordValidator getForgetPasswordValidator() {
		return new ForgetPasswordValidator();
	}

	@Override
	public LoginValidator getLoginValidator() {
		return new LoginValidator() ;
	}

	@Override
	public RegistrationValidator getRegistrationValidator() {
		return new RegistrationValidator();
	}

	@Override
	public ResetPasswordValidator getResetPasswordValidator() {
		return new ResetPasswordValidator();
	}

}
