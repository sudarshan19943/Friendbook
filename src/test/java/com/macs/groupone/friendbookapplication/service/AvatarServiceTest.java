package com.macs.groupone.friendbookapplication.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvatarServiceTest {
	
	
   @Test	
   public void testgetDefaultProfileAvatar()
   {
	   String avatarExistsPath=AvatarService.getProfileAvatar("abc");
	   assertTrue(avatarExistsPath.contains("avatar"));
   }
   
   
   @Test	
   public void testgetProfileAvatar()
   {
	   String avatarExists=AvatarService.getProfileAvatar("smn.singh444@gmail.com");
	   assertNotNull(avatarExists);
	   
   }
	
	

}
