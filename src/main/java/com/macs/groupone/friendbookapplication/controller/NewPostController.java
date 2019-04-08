package com.macs.groupone.friendbookapplication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.Post;
import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.MessageService;
import com.macs.groupone.friendbookapplication.service.ServiceFactory;
import com.macs.groupone.friendbookapplication.service.UserService;

@Controller
public class NewPostController {
	private static final Logger logger = Logger.getLogger(NewPostController.class);

	private UserService userService = (UserService) ServiceFactory.getInstance().getUserService();
	private MessageService messageService = (MessageService) ServiceFactory.getInstance().getMessageService();

	@GetMapping(value = "/newpost")
	public String displayNewpostPage(Model model, User user, HttpServletRequest request) {
		if (request.getSession().getAttribute("email") == null)
			return Constants.REDIRECT_LOGIN;

		String emailfromsession = (String) request.getSession().getAttribute("email");
		User userFromSession = userService.getUserByEmail(emailfromsession);
		model.addAttribute("avatarpic", userFromSession.getUserImage());
		return Constants.NEW_POST_VIEW;
	}

	@PostMapping(value = "/newpost", params = "post")
	public String processNewPost(Model model, HttpServletRequest request, RedirectAttributes redir,
			@RequestParam("post") String post, @Valid Post message, RedirectAttributes redirect) {
		HttpSession session = request.getSession();
		String emailfromsession = (String) session.getAttribute("email");
		if (emailfromsession == null)
			return Constants.REDIRECT_LOGIN;
		User user = userService.getUserByEmail(emailfromsession);
		messageService.addNewPost(user, post);
		model.addAttribute("postVal", post);
		model.addAttribute(Constants.SUCCESSMESSAGE, Constants.MESSAGE_POSTED_SUCCESSFULLY);
		redirect.addFlashAttribute(Constants.SUCCESSMESSAGE, Constants.MESSAGE_POSTED_SUCCESSFULLY);
		logger.debug("Mesaage posted Successfully by user : "+emailfromsession);
		return Constants.REDIRECT_NEWPOST;
	}

}
