package com.macs.groupone.friendbookapplication.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger log = LoggerFactory.getLogger(UploadProfileImageController.class);

  
 
  @Autowired
	AvatarService avatarService;
  
  *//**
   * Show the index page containing the form for uploading a file.
   *//*
  @RequestMapping("/")
  public String index() {
    return "index.html";
  }
  
  *//**
   * POST /uploadFile -> receive and locally save a file.
   * 
   * @param uploadfile The uploaded file as Multipart file parameter in the 
   * HTTP request. The RequestParam name must be the same of the attribute 
   * "name" in the input tag with type file.
   * 
   * @return An http OK status in case of success, an http 4xx status in case 
   * of errors.
   *//*
  @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> uploadFile(
      @RequestParam("uploadfile") MultipartFile uploadfile) {
	  try {
		avatarService.uploadAvatarAndSave(uploadfile);
	} catch (Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
    return new ResponseEntity<>(HttpStatus.OK);
  } // method uploadFile

} // class MainController

