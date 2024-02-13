package com.ty.user.dto;

import java.time.LocalDateTime;

public class LoginInfo {
	private int id;
	private String userId;
	private String time = LocalDateTime.now().toString();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginUserId() {
		return userId;
	}
	public void setLoginUserId(String userId) {
		this.userId = userId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public String toString() {
		return String.format("| %-6s | %-9s | %-20s |", id, userId, time);
	}
}
