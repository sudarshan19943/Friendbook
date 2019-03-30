package com.macs.groupone.friendbookapplication.model;

import java.sql.Timestamp;

public class Message {

	private long id;
	private Timestamp time;
	private User sender = new User();
	private User recipient = new User();
	private String text;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return  time.toString();
	}

	public void setDate(Timestamp timestamp) {
		this.time = timestamp;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(int senderId) {
		sender.setId(senderId);
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(int recipientId) {
		recipient.setId(recipientId);
	}

	public String getBody() {
		return text;
	}

	public void setBody(String text) {
		this.text= text;
	}

}
