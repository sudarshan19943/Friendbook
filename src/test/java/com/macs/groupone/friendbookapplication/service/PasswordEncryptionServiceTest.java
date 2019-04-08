package com.macs.groupone.friendbookapplication.service;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordEncryptionServiceTest {

	private static final String SECRET = "secret";

	@Test
	public void testEncryptionAndDecryption() {
		String tobeEncrypted = "P@ssw0rd";
		String encryptedVal = PasswordEncryptionService.encrypt(tobeEncrypted, SECRET);
		String decryptedVal = PasswordEncryptionService.decrypt(encryptedVal, SECRET);
		assertEquals(tobeEncrypted, decryptedVal);
	}
}
