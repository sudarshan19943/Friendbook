package com.macs.groupone.friendbookapplication.model;

import java.util.Date;

public class Comment {

	private long commentId;
	private Date timestamp;
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	private int recipient;
	private String comment;
	private int sender;

	
	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public Date getDate() {
		return  new Date();
	}

	public void setDate(Date date) {
		this.timestamp = date;
	}


	public int getRecipient() {
		return recipient;
	}

	public void setRecipient(int recipient) {
		this.recipient=recipient;
	}

	public String getBody() {
		return comment;
	}

	public void setBody(String text) {
		this.comment= text;
	}
	
	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

}

