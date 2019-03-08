package com.macs.groupone.friendbookapplication.beans;

import java.sql.Date;

public class Message {

	private int id;
	private Date date;
	private int sender;
	private int recipient;
	private String body;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getRecipient() {
		return recipient;
	}

	public void setRecipient(int recipient) {
		this.recipient = recipient;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int setId(int id) {
		return this.id = id;
	}

	public int getId() {
		return 0;
	}

}
