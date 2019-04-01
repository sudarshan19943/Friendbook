package com.macs.groupone.friendbookapplication.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.UserService;
import com.macs.groupone.friendbookapplication.validator.LoginValidator;

@Controller
public class LoginController {

	private static final Logger log = Logger.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	@Autowired
	private LoginValidator loginValidator;

	@GetMapping("/login")
	public String registration(Model model,HttpSession session) {
		model.addAttribute("loginForm", new User());
		return "login";
	}

	@PostMapping("/login")
	public String registration(Model model, @ModelAttribute("loginForm") User loginForm, BindingResult bindingResult,
			HttpServletRequest request, RedirectAttributes redirect) {
		loginValidator.validate(loginForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "login";
		} else {
			User userByEmailAndPassword = userService.getUserByEmailPassword(loginForm.getEmail(),
					loginForm.getPassword());
			if (userByEmailAndPassword != null) {
				HttpSession session=request.getSession();
				session.setAttribute("email", loginForm.getEmail());
				session.setAttribute("password", loginForm.getPassword());
				session.setAttribute("user", userByEmailAndPassword);
				return "redirect:/profile";
			} else {
				model.addAttribute(Constants.ERRORMESSAGE, Constants.PASSWORD_DOES_NOT_MATCH);
				return "login";
			}
		}

	}
	
	@RequestMapping(value="/logout",method = RequestMethod.GET) 
    public String signOut(HttpServletRequest request){
           HttpSession session=request.getSession(); 
           System.out.println("session id before invalidating it:"+session.getId());
         // UserDTO userDTO=(UserDTO)session.getAttribute("UserDTO");
         // System.out.println("userDTO obje"+userDTO.getFirst_name());
          session.removeAttribute("user");   
          session.invalidate(); 
         System.out.println("session id after invalidating session is:"+session.getId()); 
         return "redirect:/login";
}
}
