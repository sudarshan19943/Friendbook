package com.macs.groupone.friendbookapplication.controller;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.Comment;
import com.macs.groupone.friendbookapplication.model.Post;
import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.CommentService;
import com.macs.groupone.friendbookapplication.service.FriendsService;
import com.macs.groupone.friendbookapplication.service.MessageService;
import com.macs.groupone.friendbookapplication.service.ServiceFactory;
import com.macs.groupone.friendbookapplication.service.UserService;

@Controller
class TimelineController {
	
	private static final Logger logger = Logger.getLogger(RegistrationController.class);

	private FriendsService friendsService = (FriendsService) ServiceFactory.getInstance().getFriendService();
	private UserService userService = (UserService) ServiceFactory.getInstance().getUserService();
	private CommentService commentService = (CommentService) ServiceFactory.getInstance().getCommentService();
	private MessageService messageService = (MessageService) ServiceFactory.getInstance().getMessageService();

	@GetMapping(value = "/timeline")
	public String displayTimelinePage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String currentUseremail = (String) session.getAttribute("email");
		if (currentUseremail == null)
			return Constants.REDIRECT_LOGIN;

		User findUserFromEmail = userService.getUserByEmail(currentUseremail);
		Collection<User> listOfFriends = friendsService.findFriends(findUserFromEmail);
		Map<String, Post> listOfPostsFromAllMyFriendsSorted = messageService
				.GetPostsSortedByTimeStamp(findUserFromEmail, listOfFriends);
		model.addAttribute("types", listOfPostsFromAllMyFriendsSorted);
		String emailfromsession = (String) request.getSession().getAttribute("email");
		User userFromSession = userService.getUserByEmail(emailfromsession);
		model.addAttribute("avatarpic", userFromSession.getUserImage());
		return Constants.TIMELINE_VIEW;
	}

	@PostMapping(value = "/comment")
	public String processComments(ModelAndView modelAndView, HttpServletRequest request, RedirectAttributes redir,
			@RequestParam("comment") String comment, @RequestParam("postId") int postID) {
		String email = (String) request.getSession().getAttribute("email");
		User userByEmailAndPassword = userService.getUserByEmail(email);
		Comment commentBean = new Comment();
		commentBean.setSender(userByEmailAndPassword.getId());
		int recipient=messageService.getPostCreator(postID).get(0).getSender();
		commentBean.setRecipient(recipient);// creator of post
		commentBean.setBody(comment);
		commentService.addNewComment(commentBean, postID);
		logger.debug("Comment has been posted by user : "+userByEmailAndPassword.getEmail()+" on "+recipient +" Timeline Post.");
		return Constants.REDIRECT_TIMELINE;
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		return new ModelAndView(Constants.REDIRECT_LOGIN);
	}

}
