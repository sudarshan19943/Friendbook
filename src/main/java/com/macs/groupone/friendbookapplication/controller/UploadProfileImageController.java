package com.macs.groupone.friendbookapplication.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.macs.groupone.friendbookapplication.service.AvatarService;

@Controller
public class UploadProfileImageController {

	@Autowired AvatarService avatarService;
	
	private static final Logger log = Logger.getLogger(UploadProfileImageController.class);

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("uploadImage") MultipartFile uploadfile) {
		try {
			avatarService.uploadAvatarAndSave(uploadfile);
		} catch (Exception e) {
			log.error("Error in uploading image" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	} 

	
} 
