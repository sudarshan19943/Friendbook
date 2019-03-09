package com.macs.groupone.friendbookapplication.model;

import java.util.Date;

public class Message {

	private long id;
	private Date date;
	private User sender;
	private User recipient;
	private String text;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return  new Date();
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(int sender_id) {
		sender.setId(sender_id);
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(int recipient_id) {
		recipient.setId(recipient_id);
	}

	public String getBody() {
		return text;
	}

	public void setBody(String text) {
		this.text= text;
	}



	private static Message from(Message messageBean, User sender, User recipient) {

		Message message = new Message();
		message.id = messageBean.getId();
		message.text = messageBean.getBody();
		message.sender = sender;
		message.recipient = recipient;

		return message;

	}
}
