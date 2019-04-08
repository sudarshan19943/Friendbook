package com.macs.groupone.friendbookapplication.model;

import java.sql.Timestamp;
import java.util.List;

public class Post implements Comparable<Post>{

	private int id;
	private Timestamp timeStamp;
	private int sender;
	private String text;
	private List<Comment> comments;

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTimeStamp() {
		return  timeStamp.toString();
	}

	public void setTimeStamp(Timestamp timestamp) {
		this.timeStamp = timestamp;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int senderId) {
		this.sender=senderId;
	}

	public String getBody() {
		return text;
	}

	public void setBody(String text) {
		this.text= text;
	}
	
	@Override
	  public int compareTo(Post o) {
	    return getTimeStamp().compareTo(o.getTimeStamp());
	  }
}
