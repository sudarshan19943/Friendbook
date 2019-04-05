package com.macs.groupone.friendbookapplication.service;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import java.io.File;
import java.net.URL;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.macs.groupone.friendbookapplication.config.Config;
import com.macs.groupone.friendbookapplication.dao.impl.UserDaoImpl;

@Service
public class AvatarService implements IService {

	private static final Logger log = LoggerFactory.getLogger(AvatarService.class);
	private static final String AVATAR_FOLDER = Config.getProperty("image.upload.uploadedFiles");
	private static final String AVATART_DEFAULT_IMAGE = "default.jpg";
	private static final String WELCOME_PAGE_IMAGE = "promo.jpg";
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
	
	
	
	public void saveDefaultAvatar(String email)
	{
		String defaultImageFIle=AVATAR_FOLDER+AVATART_DEFAULT_IMAGE;
		File fBlob = new File(defaultImageFIle);
		try {
			FileInputStream is = new FileInputStream ( fBlob );
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int count;
			while ((count = is.read(buffer)) != -1)
			    output.write(buffer, 0, count);
			byte[] contents = output.toByteArray();
			Blob userImageBlob = new javax.sql.rowset.serial.SerialBlob(contents);
			UserDaoImpl dao = new UserDaoImpl();
			dao.uploadAvatarAndSaveBLOB(userImageBlob, email);
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
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

	} 
	
	/*public void setDefaultImage()
	{
		    File imgfile = new File("src/test/resources/avatarImages/default.jpg");
		    try {
				FileInputStream fin = new FileInputStream(imgfile);
				DiskFileItemFactory factory = new DiskFileItemFactory();
				FileItem fileItem = factory.createItem("formFieldName", "application/zip", false,
				    "/var/temp/somefile.zip");
				MultipartFile uploadfile=new CommonsMultipartFile(fileItem);
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	*/
	public static String  getResource()
	{
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		String path="avatarImages/avatar.png";
		File file = new File(classLoader.getResource(path).getFile());
		System.out.println("FIle: "+file.getPath());
		System.out.println("FIle: "+file.getAbsolutePath());
		
		return "C:/QA_25th/Project Backup_2/Group1/Group1/target/classes/avatarImages/avatar.png";
	}

	public static String getProfileAvatar(String email) {
		if (getResource() != null) {
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
