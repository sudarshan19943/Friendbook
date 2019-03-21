package com.macs.groupone.friendbookapplication.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.dao.impl.FriendsDaoImpl;
import com.macs.groupone.friendbookapplication.dao.impl.MessageDaoImpl;
import com.macs.groupone.friendbookapplication.model.Message;
import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.FriendsService;
import com.macs.groupone.friendbookapplication.service.MessageService;

@Controller
class TimelineController {
	@Autowired
	FriendsService friendsService;

	@Autowired
	MessageService messageService;
	
	@Autowired
	FriendsDaoImpl friendsDaoimpl;

	@Autowired
	MessageDaoImpl messagedaoimpl;

	private Date timestamp;

	@RequestMapping(value = "/timeline", method = RequestMethod.GET) 
	public ModelAndView showNewpostPage(ModelAndView modelAndView, User user) {
		modelAndView.setViewName(Constants.TIMELINE_VIEW);
		modelAndView.addObject("friends", friendsDaoimpl.getFriendList(user));
		//modelAndView.addObject("message", messagedaoimpl.getMessage(user));
		modelAndView.setViewName("timeline");
		return modelAndView; 
	}


	@RequestMapping(value = "/timeline", params="post", method = RequestMethod.POST) 
	public ModelAndView processPost(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redir, @RequestParam("post") String post, @Valid Message message) { 

		return modelAndView; 
	}

}
