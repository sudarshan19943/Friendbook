package com.macs.groupone.friendbookapplication.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.Post;
import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.FriendsService;
import com.macs.groupone.friendbookapplication.service.MessageService;
import com.macs.groupone.friendbookapplication.service.UserService;


@Controller
public class NewPostController {
	private static final Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	FriendsService friendsService;
	
	@Autowired
	UserService userService;

	@Autowired
	MessageService messageService;

	private Date timestamp;

	@RequestMapping(value = "/newpost", method = RequestMethod.GET) 
	public ModelAndView showNewpostPage(ModelAndView modelAndView, User user, Model model) {
		modelAndView.setViewName(Constants.NEW_POST_VIEW);
		return modelAndView; 
	}


	@RequestMapping(value = "/newpost", params="post", method = RequestMethod.POST) 
	public ModelAndView processPost(ModelAndView modelAndView, HttpServletRequest request, RedirectAttributes redir, @RequestParam("post") String post, @Valid Post message) 
	{ 
		HttpSession session=request.getSession();
		String emailfromsession=(String) session.getAttribute("email");
		User user=userService.getUserByEmail(emailfromsession);
		messageService.addNewPost(user, post);
		//now check where to redirect
		modelAndView.addObject("postVal",post);
		modelAndView.addObject("successMessage","Message has been posted successfully");
		return modelAndView; 
	}
	


}
