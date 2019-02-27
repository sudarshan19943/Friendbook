package com.macs.groupone.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.macs.groupone.common.Config;
import com.macs.groupone.friendbookapplication.constants.Constants;

public class EmailService {
	public EmailService() {

	}

	private Properties properties;
	private Session session;
	private InternetAddress fromAddress;

	private void createFromAddress(String userName) throws AddressException {
		fromAddress = new InternetAddress(userName);
	}

	private void createSession() {
		final String userName = Config.getProperty(Constants.MAIL_USERNAME);
		final String password = Config.getProperty(Constants.MAIL_PASSWORD);
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		session = Session.getInstance(properties, auth);
	}

	private void createProperties() {
		properties = new Properties();
		properties.put(Constants.HOST, Config.getProperty(Constants.HOST));
		properties.put(Constants.PORT, Config.getProperty(Constants.PORT));
		properties.put(Constants.AUTH, Config.getProperty(Constants.AUTH));
		properties.put(Constants.ENABLED, Config.getProperty(Constants.ENABLED));
	}

	public void sendEmail(String toAddress, String subject, String message) throws MessagingException {

		createProperties();
		createSession();
		createFromAddress(Config.getProperty(Constants.MAIL_USERNAME));

		Message msg = new MimeMessage(session);
		msg.setFrom(fromAddress);
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setContent(message, "text/html");

		Transport.send(msg);
	}

}
