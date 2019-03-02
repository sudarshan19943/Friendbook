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
	
	public String PORT="mail.smtp.port";
	public String HOST="mail.smtp.host";
	public String ENABLED="mail.smtp.starttls.enable";
	public String AUTH="mail.smtp.auth";
	public String MAIL_USERNAME="mail.smtp.username";
	public String MAIL_PASSWORD="mail.smtp.password";

	private void createFromAddress(String userName) throws AddressException {
		fromAddress = new InternetAddress(userName);
	}

	private void createSession() {
		final String userName = Config.getProperty(MAIL_USERNAME);
		final String password = Config.getProperty(MAIL_PASSWORD);
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		session = Session.getInstance(properties, auth);
	}

	private void createProperties() {
		properties = new Properties();
		properties.put(HOST, Config.getProperty(HOST));
		properties.put(PORT, Config.getProperty(PORT));
		properties.put(AUTH, Config.getProperty(AUTH));
		properties.put(ENABLED, Config.getProperty(ENABLED));
	}

	public void sendEmail(String toAddress, String subject, String message) throws MessagingException {

		createProperties();
		createSession();
		createFromAddress(Config.getProperty(MAIL_USERNAME));

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
