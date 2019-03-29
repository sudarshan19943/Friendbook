package com.macs.groupone.friendbookapplication.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.io.File;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.macs.groupone.friendbookapplication.config.Config;

@Service
public class AvatarService implements IService {

	private static final Logger log = LoggerFactory.getLogger(AvatarService.class);
	private static final String AVATAR_FOLDER = Config.getProperty("avatarImages");
	private static final String AVATART_DEFAULT_IMAGE = "default.JPG";
	private static final String AVATAR_IMAGE_EXTENSION = ".JPG";
	
	private static final ClassLoader loader = AvatarService.class.getClassLoader();

	
	public AvatarService() {
      
    }
	

	public static void uploadAvatarAndSave(MultipartFile uploadfile,String emailID) {
		try {
			String uniqueFileName=emailID+AVATAR_IMAGE_EXTENSION;
			String filepath = Paths.get(AVATAR_FOLDER, uniqueFileName).toString();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			stream.write(uploadfile.getBytes());
			stream.close();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());

		} catch (IOException e) {
			log.error(e.getMessage());

		}
	}
	
	public static URL getResource(String path){
		 String directory = Config.getProperty("avatarImages");
		 
	    URL url = ResourceLoader.class.getResource(path);
	    if (url==null){
	        System.out.println(path);
	    }
	    return url;
	}

    public static String getProfileAvatar(String email) {
    	
    	//getResource();
        String directory = Config.getProperty("avatarImages");
        System.out.println("directory : "+directory);
        String filepath = Paths.get(directory, email+AVATAR_IMAGE_EXTENSION).toString();
        /*File f = new File(filepath);
        final File folder = new File(loader.getResource(AVATAR_FOLDER).getFile());
        File f1 = new File("../../avatarImages/smn.singh666@gmail.com.JPG");
        if(f1.exists() && !f1.isDirectory()) { 
            System.out.println("exists..");
        }else
        {
        	 System.out.println("does not exists..");
        }
        
        File f2 = new File("../../avatarImages/smn.singh666@gmail.com");
        if(f2.exists() && !f2.isDirectory()) { 
            System.out.println("exists..");
        }else
        {
        	 System.out.println("does not exists..");
        }
        File f3 = new File("../../avatarImages/smn.singh666@gmail.com");
        if(f.exists() && !f3.isDirectory()) { 
            System.out.println("exists..");
        }else
        {
        	 System.out.println("does not exists..");
        }
        URL fileUrl = loader.getResource("../../avatarImages/smn.singh666@gmail.com.JPG");
        //File fileR= new File(fileUrl.getFile());
        
        URL fileUrl1 = loader.getResource("C:\\QA_25th\\Project_backup_before_security\\Group1\\src\\main\\webapp\\avatarImages\\smn.singh666@gmail.com.JPG");
        //File fileR1= new File(fileUrl1.getFile());
        
        URL fileUrl2 = loader.getResource("C:/QA_25th/Project_backup_before_security/Group1/src/main/webapp/avatarImages/smn.singh666@gmail.com.JPG");
        File fileR2= new File(fileUrl2.getFile());*/
        
        System.out.println("filepath : "+filepath);
    	String pathToReturn=directory+"/"+email+AVATAR_IMAGE_EXTENSION;
    	System.out.println("pathToReturn : "+pathToReturn);
        return pathToReturn;
    }


}
