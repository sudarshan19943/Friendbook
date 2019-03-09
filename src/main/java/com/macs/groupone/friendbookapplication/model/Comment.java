package com.macs.groupone.friendbookapplication.model;

import java.util.Date;

public class Comment {

	private long comment_id;
	private Date timestamp;
	private User sender;
	private User recipient;
	private String comment;

	public long getId() {
		return comment_id;
	}

	public void setId(long id) {
		this.comment_id = id;
	}

	public Date getDate() {
		return  new Date();
	}

	public void setDate(Date date) {
		this.timestamp = date;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(int sender_id) {
		sender.setId(sender_id);
	}

	public int getRecipient() {
		return recipient.getId();
	}

	public void setRecipient(int recipient_id) {
		recipient.setId(recipient_id);
	}

	public String getBody() {
		return comment;
	}

	public void setBody(String text) {
		this.comment= text;
	}
}

