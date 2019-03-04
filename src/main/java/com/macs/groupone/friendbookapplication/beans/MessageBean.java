package com.macs.groupone.friendbookapplication.beans;

import java.sql.Timestamp;

public class MessageBean {

	private int id;
	private Timestamp date;
	private int sender;
	private int recipient;
	private String body;

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
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
