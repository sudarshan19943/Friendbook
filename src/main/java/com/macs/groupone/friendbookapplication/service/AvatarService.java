package com.macs.groupone.friendbookapplication.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.macs.groupone.friendbookapplication.common.Config;

@Service
public class AvatarService {

	private static final Logger log = LoggerFactory.getLogger(AvatarService.class);
	private static final ClassLoader loader = AvatarService.class.getClassLoader();
	private static final String AVATAR_FOLDER = Config.getProperty("avatarImages");
	private static final String AVATART_DEFAULT_IMAGE = "undefined.gif";
	private static final String AVATAR_IMAGE_EXTENSION = ".jpg";

	private AvatarService() {
	}

	public static String getProfileAvatar(int id) {
		final String path = AVATAR_FOLDER +"/"+ String.valueOf(id) + AVATAR_IMAGE_EXTENSION;
		if (null != loader.getResource(path)) {
			return path;
		}
		return AVATAR_FOLDER +"/"+ AVATART_DEFAULT_IMAGE;
	}

	public void uploadAvatarAndSave(MultipartFile uploadfile) {
		try {
			String filename = uploadfile.getOriginalFilename();
			String filepath = Paths.get(AVATAR_FOLDER, filename).toString();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			stream.write(uploadfile.getBytes());
			stream.close();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());

		} catch (IOException e) {
			log.error(e.getMessage());

		}
	}

}
