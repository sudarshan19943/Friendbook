package com.macs.groupone.friendbookapplication;



import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.macs.groupone.interfaces.UserDao;
import com.macs.groupone.model.User;

@Controller
public class LoginController {

	@Autowired
	UserDao userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "login";
	}

	// Process form input data
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user,
			BindingResult bindingResult, HttpServletRequest request) {
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
	    User userVal = userService.getUserByEmailPassword(user.getEmail(), user.getPassword());
		if(userVal==null)
		{
			System.out.println(" user does not exists in database..");
			modelAndView.setViewName("registration");
		}
		return modelAndView;
	}

	// Process form input data
	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public ModelAndView processResetPassword(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult,
			HttpServletRequest request) {
		modelAndView.setViewName("resetpassword");
		return modelAndView;
	}

	// Process form input data
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public ModelAndView processForgetPassword(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult,
			HttpServletRequest request) {
		modelAndView.setViewName("forgotpassword");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView showSignUpPage(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

}
