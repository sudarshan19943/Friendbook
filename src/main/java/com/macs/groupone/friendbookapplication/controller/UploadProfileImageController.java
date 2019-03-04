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


@Controller
public class UploadProfileImageController {
	
	private static final Logger log = LoggerFactory.getLogger(UploadProfileImageController.class);

  
 
/*  @Autowired
	AvatarService avatarService;*/
  
  
  @RequestMapping("/")
  public String index() {
    return "index.html";
  }
 
  @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> uploadFile(
      @RequestParam("uploadfile") MultipartFile uploadfile) {
	  try {
		//avatarService.uploadAvatarAndSave(uploadfile);
	} catch (Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
    return new ResponseEntity<>(HttpStatus.OK);
  } // method uploadFile

} // class MainController

