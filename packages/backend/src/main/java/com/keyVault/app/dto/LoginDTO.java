package com.keyVault.app.dto;

import java.util.Date;

public class LoginDTO {
	private int id;
	private String email;
	private Date createdAt;
	private String token;
	private Date expirationDate;
	
	public LoginDTO() {
		super();
	}
	
	public LoginDTO(int id, String email, Date createdAt, String token, Date expirationDate) {
		super();
		this.id = id;
		this.email = email;
		this.createdAt = createdAt;
		this.token = token;
		this.expirationDate = expirationDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
}
