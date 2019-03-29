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
	private static final String AVATART_DEFAULT_IMAGE = "avatar.png";
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
	
	public static URL getResource(String email){
		String directory = Config.getProperty("avatarImagesForResourse");
	    String avatarImagesForResoursePath=directory+email+AVATAR_IMAGE_EXTENSION;
	    URL url = ResourceLoader.class.getResource(avatarImagesForResoursePath);
	    return url;
	}

    public static String getProfileAvatar(String email) {
        if( getResource(email)!=null)
        {
        	 String directory = Config.getProperty("avatarImagesForUrl");
             System.out.println("directory : "+directory);
             String filepath = Paths.get(directory, email+AVATAR_IMAGE_EXTENSION).toString();
             System.out.println("filepath : "+filepath);
         	String pathToReturn=directory+email+AVATAR_IMAGE_EXTENSION;
         	System.out.println("pathToReturn : "+pathToReturn);
             return pathToReturn;
        }else
        {
        	 String directory = Config.getProperty("avatarImagesForUrl");
             System.out.println("directory : "+directory);
             String filepath = Paths.get(directory, AVATART_DEFAULT_IMAGE).toString();
             System.out.println("filepath : "+filepath);
         	String pathToReturn=directory+AVATART_DEFAULT_IMAGE;
         	System.out.println("pathToReturn : "+pathToReturn);
             return pathToReturn;
        }
       
        	
    }



}
