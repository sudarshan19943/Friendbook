package com.macs.groupone.model;

import java.sql.Timestamp;

import com.macs.groupone.beans.MessageBean;
import com.macs.groupone.service.UserService;

public class Message {
	
	private long id;
    private Timestamp date;
    private User sender;
    private User recipient;
    private String body;

    private static Message from(MessageBean messageBean, User sender, User recipient) {

        Message message = new Message();
        message.id = messageBean.getId();
        message.date = messageBean.getDate();
        message.body = messageBean.getBody();
        message.sender = sender;
        message.recipient = recipient;

        return message;

    }

    public static Message from(MessageBean messageBean, UserService userDao) {
        User sender = userDao.getUserById(messageBean.getSender());
        User recipient = userDao.getUserById(messageBean.getRecipient());
        return from(messageBean, sender, recipient);
    }

}
