package com.macs.groupone.friendbookapplication.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordEncryptionServiceTest {

	
   @Test	
   public void testEncryptionAndDecryption()
   {
	   String tobeEncrypted="P@ssw0rd";
	   String encryptedVal=PasswordEncryptionService.encrypt(tobeEncrypted, "SECRET");
	   String decryptedVal=PasswordEncryptionService.decrypt(encryptedVal, "SECRET");
	   assertEquals(tobeEncrypted, decryptedVal);
	   
   }
}
