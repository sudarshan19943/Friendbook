/*package com.macs.groupone.friendbookapplication.service;



import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.macs.groupone.friendbookapplication.common.Config;



@Service
public class AvatarService {
	
	  private static final Logger log = LoggerFactory.getLogger(AvatarService.class);
	  private static final ClassLoader loader = AvatarService.class.getClassLoader();
	  
	  private static final String AVATAR_FOLDER=Config.getProperty("uploadedImages");
	  public static String API_URL="";
	
	 private AvatarService() {
	        try {
	            final File folder = new File(loader.getResource(AVATAR_FOLDER).getFile());
	            if (folder.isDirectory()) {
	                log.info("Avatar folder {} was found", AVATAR_FOLDER);
	            } else {
	                log.error("Avatar folder {} was not found", AVATAR_FOLDER);
	            }
	        } catch (NullPointerException ex) {
	            log.error("Avatar folder is not found: {}", ex);
	        }
	    }

	    public static String getDeafultProfileAvatar(int id) {
	        final String path = AVATAR_FOLDER + String.valueOf(id) + ".jpg";

	        if (null != loader.getResource(path)) {
	            return API_URL + "/" + path;
	        }
	        return API_URL + "/" + AVATAR_FOLDER + "undefined.gif";
	    }
	    
	    public void uploadAvatarAndSave(MultipartFile uploadfile) throws Exception
	    {
	    	 try {
	    	      // Get the filename and build the local file path
	    	      String filename = uploadfile.getOriginalFilename();
	    	     // String directory = env.getProperty("netgloo.paths.uploadedFiles");
	    	      String filepath = Paths.get(AVATAR_FOLDER, filename).toString();
	    	      
	    	      // Save the file locally
	    	      BufferedOutputStream stream =
	    	          new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	    	      stream.write(uploadfile.getBytes());
	    	      stream.close();
	    	    }
	    	    catch (Exception e) {
	    	      System.out.println(e.getMessage());
	    	     
	    	    }
	    }

	   
}
*/