package com.macs.groupone.friendbookapplication.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import java.io.File;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.macs.groupone.friendbookapplication.config.Config;
import com.macs.groupone.friendbookapplication.dao.impl.UserDaoImpl;

@Service
public class AvatarService implements IService {

	private static final Logger log = LoggerFactory.getLogger(AvatarService.class);
	private static final String AVATAR_FOLDER = Config.getProperty("avatarImages");
	private static final String AVATART_DEFAULT_IMAGE = "avatar.png";
	private static final String AVATAR_IMAGE_EXTENSION = ".JPG";
	private static final ClassLoader loader = AvatarService.class.getClassLoader();

	public AvatarService() {

	}

	public static void uploadAvatarAndSaveBLOB(MultipartFile uploadfile, String emailID) {
		String originalFileName =uploadfile.getOriginalFilename();
		byte[] bytes;
		try {
			try {
				bytes = uploadfile.getBytes();
				Blob userImageBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
				System.out.println("user Image : "+userImageBlob);
				UserDaoImpl dao = new UserDaoImpl();
				dao.uploadAvatarAndSaveBLOB(userImageBlob, emailID);
				System.out.println("user Image : "+userImageBlob);
			} catch (IOException e) {
				e.printStackTrace();
				log.error("IOException has Occured for User : "+emailID);
			}
			
		} catch (SerialException e) {
			log.error("Image to Byte Conversion has Occured for User : "+emailID);
			e.printStackTrace();
		} catch (SQLException e) {
			log.error("Image to Byte Conversion has Occured for User : "+emailID);
			e.printStackTrace();
		}

	} // method uploadFile

	public static void uploadAvatarAndSave(MultipartFile uploadfile, String emailID) {
		try {
			// Get the filename and build the local file path
			String filename = uploadfile.getOriginalFilename();
			filename = emailID + AVATAR_IMAGE_EXTENSION;
			String directory = Config.getProperty("image.upload.uploadedFiles");
			String filepath = Paths.get(directory, filename).toString();
			// Save the file locally
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			stream.write(uploadfile.getBytes());
			stream.close();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());

		} catch (IOException e) {
			log.error(e.getMessage());

		}

	} // method uploadFile

	public static URL getResource(String email) {
		String directory = Config.getProperty("avatarImagesForResourse");
		String avatarImagesForResoursePath = directory + email + AVATAR_IMAGE_EXTENSION;
		URL url = ResourceLoader.class.getResource(avatarImagesForResoursePath);
		return url;
	}
	
	public static String  getDefaultAvatarImage()
	{
		/*String directory = Config.getProperty("avatarImagesForUrl");
		System.out.println("directory : " + directory);
		String filepath = Paths.get(directory, AVATART_DEFAULT_IMAGE).toString();
		System.out.println("filepath : " + filepath);
		String pathToReturn = directory + AVATART_DEFAULT_IMAGE;
		System.out.println("pathToReturn : " + pathToReturn);
		return pathToReturn;*/
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		String path="avatarImages/avatar.png";
		File file = new File(classLoader.getResource(path).getFile());
		System.out.println("FIle: "+file.getPath());
		System.out.println("FIle: "+file.getAbsolutePath());
		
		return "C:/QA_25th/Project Backup_2/Group1/Group1/target/classes/avatarImages/avatar.png";
	}

	public static String getProfileAvatar(String email) {
		if (getResource(email) != null) {
			String directory = Config.getProperty("avatarImagesForUrl");
			System.out.println("directory : " + directory);
			String filepath = Paths.get(directory, email + AVATAR_IMAGE_EXTENSION).toString();
			System.out.println("filepath : " + filepath);
			String pathToReturn = directory + email + AVATAR_IMAGE_EXTENSION;
			System.out.println("pathToReturn : " + pathToReturn);
			return pathToReturn;
		} else {
			String directory = Config.getProperty("avatarImagesForUrl").trim();
			System.out.println("directory : " + directory.trim());
			String filepath = Paths.get(directory.trim(), AVATART_DEFAULT_IMAGE.trim()).toString();
			System.out.println("filepath : " + filepath.trim());
			String pathToReturn = directory.trim() + AVATART_DEFAULT_IMAGE.trim();
			System.out.println("pathToReturn : " + pathToReturn);
			return pathToReturn.trim();
		}

	}

}
