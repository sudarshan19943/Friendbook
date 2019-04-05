package com.macs.groupone.friendbookapplication.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

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
import com.macs.groupone.friendbookapplication.service.AvatarService;
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


	@RequestMapping(value = "/newpost", method = RequestMethod.GET) 
	public String showNewpostPage(Model model, User user, HttpServletRequest request ) {
		if(request.getSession().getAttribute("email")==null)
			 return "redirect:login";
		
		/* Map md = model.asMap();
		    for (Object modelKey : md.keySet()) {
		        Object modelValue = md.get(modelKey);
		        System.out.println(modelKey + " -- " + modelValue);
		    }*/
		
		String emailfromsession=(String) request.getSession().getAttribute("email");
		User userFromSession=userService.getUserByEmail(emailfromsession);
		if(userFromSession.getUserImage()==null)
		{//show default image
			//model.addAttribute("avatarpic",AvatarService.getDefaultAvatarImage());
		}else
		{
			System.out.println("image found in new post :"+userFromSession.getUserImage());
			model.addAttribute("avatarpic",userFromSession.getUserImage());
		}
		return "newpost"; 
	}


	@RequestMapping(value = "/newpost", params="post", method = RequestMethod.POST) 
	public String processPost(Model model, HttpServletRequest request, RedirectAttributes redir, @RequestParam("post") String post, @Valid Post message,RedirectAttributes redirect) 
	{ 
		HttpSession session=request.getSession();
		String emailfromsession=(String) session.getAttribute("email");
		        if(emailfromsession==null)
		        	return "redirect:login";
		User user=userService.getUserByEmail(emailfromsession);
		messageService.addNewPost(user, post);
		//now check where to redirect
		model.addAttribute("postVal",post);
		model.addAttribute("successMessage","Message has been posted successfully");
		redirect.addFlashAttribute("successMessage","Message has been posted successfully");
		return "redirect:/newpost"; 
	}
	
	



}
