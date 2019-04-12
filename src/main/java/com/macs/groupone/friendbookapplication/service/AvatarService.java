package com.macs.groupone.friendbookapplication.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.macs.groupone.friendbookapplication.config.ApplicationConfigPropertyConfigurator;

public class AvatarService implements IService {

	private final static Logger logger = Logger.getLogger(AvatarService.class);
	private static final String AVATAR_FOLDER = ApplicationConfigPropertyConfigurator.getProperty("image.upload.uploadedFiles");
	private static final String AVATART_DEFAULT_IMAGE = "default.jpg";

	private UserService userService = (UserService) ServiceFactory.getInstance().getUserService();

	private static IService avatarService;

	public static IService getAvatarServiceInstance() {
		if (avatarService == null) {
			return new AvatarService();
		} else {
			return avatarService;
		}
	}

	public void uploadAvatarAndSaveBLOB(MultipartFile uploadfile, String emailID) {
		byte[] imageBytes;
		try {
			imageBytes = uploadfile.getBytes();
			Blob userImageBlob = new javax.sql.rowset.serial.SerialBlob(imageBytes);
			userService.updateUserImage(userImageBlob, emailID);
		} catch (IOException e) {
			logger.error("IOException has Occured for User : " + emailID);
		} catch (SerialException e) {
			logger.error("Image to Byte Conversion has Occured for User : " + emailID);
		} catch (SQLException e) {
			logger.error("SQL exception occured while converting Image to Byte : " + emailID);
			e.printStackTrace();
		}

	}

	public void saveDefaultAvatar(String emailID) {
		String defaultImageFIle = AVATAR_FOLDER + AVATART_DEFAULT_IMAGE;
		File imageFile = new File(defaultImageFIle);
		FileInputStream imageFileInputStream;
		try {
			imageFileInputStream = new FileInputStream(imageFile);
			ByteArrayOutputStream imageByteArrayOutPut = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int count;
			while ((count = imageFileInputStream.read(buffer)) != -1)
				imageByteArrayOutPut.write(buffer, 0, count);
			byte[] imageContent = imageByteArrayOutPut.toByteArray();
			Blob userImageBlob = new javax.sql.rowset.serial.SerialBlob(imageContent);
			userService.updateUserImage(userImageBlob, emailID);
		} catch (FileNotFoundException e) {
			logger.error("Could not find default avatar image : " + defaultImageFIle);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Could not read image: " + defaultImageFIle);
			e.printStackTrace();
		} catch (SerialException e) {
			logger.error("Image to Byte Conversion has Occured for User : " + emailID);
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error("Database communication error : " + defaultImageFIle);
			e.printStackTrace();
		}
	}

}
