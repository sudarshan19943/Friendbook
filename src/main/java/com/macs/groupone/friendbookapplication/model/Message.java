package com.macs.groupone.friendbookapplication.model;

import java.sql.Timestamp;



public class Message {
	
	private long id;
    private Timestamp date;
    private User sender;
    private User recipient;
    private String body;

    /*private static Message from(Message messageBean, User sender, User recipient) {

        Message message = new Message();
        message.id = messageBean.getId();
        message.date = messageBean.getDate();
        message.body = messageBean.getBody();
        message.sender = sender;
        message.recipient = recipient;

        return message;

    }

    public static Message from(Message messageBean, UserService userDao) {
        User sender = userDao.getUserById(messageBean.getSender());
        User recipient = userDao.getUserById(messageBean.getRecipient());
        return from(messageBean, sender, recipient);
    }
*/
}
