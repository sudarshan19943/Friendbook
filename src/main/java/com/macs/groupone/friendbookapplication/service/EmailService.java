package com.macs.groupone.friendbookapplication.service;

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

import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.common.Config;

@Service
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
		final String userName = Config.getProperty(ServiceConstants.MAIL_USERNAME);
		final String password = Config.getProperty(ServiceConstants.MAIL_PASSWORD);
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		session = Session.getInstance(properties, auth);
	}

	private void createProperties() {
		properties = new Properties();
		properties.put(ServiceConstants.HOST, Config.getProperty(ServiceConstants.HOST));
		properties.put(ServiceConstants.PORT, Config.getProperty(ServiceConstants.PORT));
		properties.put(ServiceConstants.AUTH, Config.getProperty(ServiceConstants.AUTH));
		properties.put(ServiceConstants.ENABLED, Config.getProperty(ServiceConstants.ENABLED));
	}

	public void sendEmail(String toAddress, String subject, String message) throws MessagingException {

		createProperties();
		createSession();
		createFromAddress(Config.getProperty(ServiceConstants.MAIL_USERNAME));

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
