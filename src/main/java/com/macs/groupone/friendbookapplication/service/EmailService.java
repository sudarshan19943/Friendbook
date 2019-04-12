package com.macs.groupone.friendbookapplication.service;

import java.util.Date;
import java.util.Properties;

import com.macs.groupone.friendbookapplication.config.ApplicationConfigPropertyConfigurator;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

//References:
//https://www.tutorialspoint.com/java/java_sending_email.htm
//https://stackoverflow.com/questions/3649014/send-email-using-java

public class EmailService implements IService 
{

	final Logger logger = Logger.getLogger(EmailService.class);
	public static final String PROTOCOL = "mail.transport.protocol";
	public static final String PORT = "mail.smtp.port";
	public static final String HOST = "mail.smtp.host";
	public static final String ENABLED = "mail.smtp.starttls.enable";
	public static final String AUTH = "mail.smtp.auth";
	public static final String MAIL_USERNAME = "mail.smtp.username";
	public static final String MAIL_PASSWORD = "mail.smtp.password";
	private static IService emailService;

	public static IService getEmailServiceInstance() 
	{
		if (emailService == null) 
		{
			return new EmailService();
		} 
		else 
		{
			return emailService;
		}
	}

	private Properties properties;
	private Session session;
	private InternetAddress fromAddress;

	private void createFromAddress(String userName) throws AddressException 
	{
		fromAddress = new InternetAddress(userName);
	}

	private void createSession() 
	{
		final String userName = ApplicationConfigPropertyConfigurator.getProperty(MAIL_USERNAME);
		final String password = ApplicationConfigPropertyConfigurator.getProperty(MAIL_PASSWORD);
		Authenticator auth = new Authenticator() 
		{
			public PasswordAuthentication getPasswordAuthentication() 
			{
				return new PasswordAuthentication(userName, password);
			}
		};
		session = Session.getInstance(properties, auth);
	}

	private void createProperties() 
	{
		properties = new Properties();
		properties.put(HOST, ApplicationConfigPropertyConfigurator.getProperty(HOST));
		properties.put(PORT, ApplicationConfigPropertyConfigurator.getProperty(PORT));
		properties.put(AUTH, ApplicationConfigPropertyConfigurator.getProperty(AUTH));
		properties.put(ENABLED, ApplicationConfigPropertyConfigurator.getProperty(ENABLED));
	}

	public void sendEmail(String toAddress, String subject, String message)
			throws AddressException, MessagingException 
	{
		createProperties();
		createSession();
		try 
		{
			createFromAddress(ApplicationConfigPropertyConfigurator.getProperty(MAIL_USERNAME));
			Message msg = new MimeMessage(session);
			msg.setFrom(fromAddress);
			InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setContent(message, "text/html");
			Transport.send(msg);
		} 
		catch (AddressException e) 
		{
			logger.error(e.getMessage());
			throw new AddressException("[sendEmail]: Incorrect email address");
		} 
		catch (MessagingException e) 
		{
			logger.error(e.getMessage());
			throw new MessagingException("[sendEmail]: Unable to send email");
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage());
		}

	}
}
