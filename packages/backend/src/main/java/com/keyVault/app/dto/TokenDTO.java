package com.keyVault.app.dto;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class TokenDTO {
	private String token;
	private Date expirationToken;
	private int id;
	@NotEmpty
	@Email(message= "The email entered is invalid")
	private String email;
	private Date createdAt;
	

	public TokenDTO(String token, Date expirationToken) {
		super();
		this.token = token;
		this.expirationToken = expirationToken;
	}
	public TokenDTO() {
		super();
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
	public Date getExpirationToken() {
		return expirationToken;
	}
	public void setExpirationToken(Date expirationToken) {
		this.expirationToken = expirationToken;
	}
	
}
