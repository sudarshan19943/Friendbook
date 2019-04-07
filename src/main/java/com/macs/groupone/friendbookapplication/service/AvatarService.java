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

import com.macs.groupone.friendbookapplication.config.Config;
import com.macs.groupone.friendbookapplication.dao.impl.DAOFactory;
import com.macs.groupone.friendbookapplication.dao.impl.UserDaoImpl;

public class AvatarService implements IService {

	final static Logger logger = Logger.getLogger(AvatarService.class);
	private static final String AVATAR_FOLDER = Config.getProperty("image.upload.uploadedFiles");
	private static final String AVATART_DEFAULT_IMAGE = "default.jpg";

	private static IService avatarService;

	public static IService getAvatarServiceInstance() {
		if (avatarService == null) {
			return new AvatarService();
		} else {
			return avatarService;
		}
	}

	

	public static void uploadAvatarAndSaveBLOB(MultipartFile uploadfile, String emailID) {
		byte[] bytes;
		try {
			bytes = uploadfile.getBytes();
			Blob userImageBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
			UserDaoImpl uerDao = (UserDaoImpl) DAOFactory.getInstance().getUserDao();
			uerDao.uploadAvatarAndSaveBLOB(userImageBlob, emailID);
		} catch (IOException e) {
			logger.error("IOException has Occured for User : " + emailID);
		} catch (SerialException e) {
			logger.error("Image to Byte Conversion has Occured for User : " + emailID);
		} catch (SQLException e) {
			logger.error("Image to Byte Conversion has Occured for User : " + emailID);
		}

	} // method uploadFile

	public void saveDefaultAvatar(String email) {
		String defaultImageFIle = AVATAR_FOLDER + AVATART_DEFAULT_IMAGE;
		File fBlob = new File(defaultImageFIle);
		FileInputStream fis;
		try {
			fis = new FileInputStream(fBlob);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int count;
			while ((count = fis.read(buffer)) != -1)
				output.write(buffer, 0, count);
			byte[] contents = output.toByteArray();
			Blob userImageBlob = new javax.sql.rowset.serial.SerialBlob(contents);
			UserDaoImpl dao = new UserDaoImpl();
			dao.uploadAvatarAndSaveBLOB(userImageBlob, email);
		} catch (FileNotFoundException e) {
			logger.error("Could not find default avatar image : " + defaultImageFIle);
		} catch (IOException e) {
			logger.error("Could not read image: " + defaultImageFIle);
		} catch (SerialException e) {
			logger.error("Image to Byte Conversion has Occured for User : " + defaultImageFIle);
		} catch (SQLException e) {
			logger.error("Database communication error : " + defaultImageFIle);
		}
	}

}
