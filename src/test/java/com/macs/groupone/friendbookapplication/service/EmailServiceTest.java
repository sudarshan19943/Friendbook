package com.macs.groupone.friendbookapplication.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTest {

	@Autowired
	EmailService emailService;

	private GreenMail testSmtp;

	@Before
	public void setUp() throws Exception {
             //to-do
	}

	@After
	public void tearDown() throws Exception {
		//to-do
	}

	@Test
	public void testEmail() throws MessagingException, IOException {
		
		//yet to be fixed 
		testSmtp = new GreenMail(ServerSetupTest.SMTP);
		testSmtp.start();
		emailService.sendEmail("smn.singh666@gmail.com", "Test Subject", "Test mail");
		testSmtp.waitForIncomingEmail(1);
		Message[] messages = testSmtp.getReceivedMessages();
		Assert.assertTrue(messages.length == 1);
		String message = messages[0].getSubject();
		Multipart part = (Multipart) messages[0].getContent();
		assertEquals(part.getCount(), 1);
		assertEquals("Test Subject", message);
		String body = GreenMailUtil.getBody(messages[0]).replaceAll("=\r?\n", "");
		assertTrue(body.contains("Test mail"));
		testSmtp.stop();
	}


}

//reference-https://github.com/abburi03/spring-boot-email/blob/master/src/test/java/org/common/email/EmailServiceTest.java
