package com.company.house.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {

	public int cid;
	public String message;
	public int gid;
	public int likes;
	public String createdDate;
	public String user;
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		this.createdDate = dateFormat.format(createdDate!=null?createdDate:new Date());
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
}
