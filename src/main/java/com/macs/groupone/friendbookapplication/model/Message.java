package com.macs.groupone.friendbookapplication.model;

import java.sql.Date;

public class Message {
	
	private long id;
    private Date date;
    private User sender;
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	private User recipient;
    private String body;

    private static Message from(Message messageBean, User sender, User recipient) {

        Message message = new Message();
        message.id = messageBean.getId();
        message.date = messageBean.getDate();
        message.body = messageBean.getBody();
        message.sender = sender;
        message.recipient = recipient;

        return message;

    }

   /* public static Message from(Message messageBean, UserDaoImpl userDao) {
        User sender = userDao.getUserById(messageBean.getSender());
        User recipient = userDao.getUserById(messageBean.getRecipient());
        return from(messageBean, sender, recipient);
    }
*/
}
