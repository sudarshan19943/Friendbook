package com.macs.groupone.friendbookapplication.service;

import java.util.Date;
import java.util.Properties;


import com.macs.groupone.friendbookapplication.config.Config;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IService {

	private static final Logger log = LoggerFactory.getLogger(EmailService.class);
	
	    //mail properties
		public static final String PROTOCOL="mail.transport.protocol";
		public static final String PORT="mail.smtp.port";
		public static final String HOST="mail.smtp.host";
		public static final String ENABLED="mail.smtp.starttls.enable";
		public static final String AUTH="mail.smtp.auth";
		public static final String MAIL_USERNAME="mail.smtp.username";
		public static final String MAIL_PASSWORD="mail.smtp.password";
	
	public EmailService() {

	}

	private Properties properties;
	private Session session;
	private InternetAddress fromAddress;

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

	public boolean sendEmail(String toAddress, String subject, String message) throws AddressException, MessagingException {

		createProperties();
		createSession();
		boolean exceptionOccured=false;
		try
		{
			createFromAddress(Config.getProperty(MAIL_USERNAME));
			Message msg = new MimeMessage(session);
			msg.setFrom(fromAddress);
			InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setContent(message, "text/html");
			Transport.send(msg);
		}catch (AddressException e) {
			log.error(e.getMessage());
			exceptionOccured=true;
            throw new AddressException("[sendEmail]: Incorrect email address");

        } catch (MessagingException e) {
        	log.error(e.getMessage());
        	exceptionOccured=true;
            throw new MessagingException("[sendEmail]: Unable to send email");
        } catch (Exception e) {
        	exceptionOccured=true;
        	log.error(e.getMessage());
        }
		return exceptionOccured;
		
	}
}
