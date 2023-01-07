package com.keyVault.app.dto;

import java.util.Date;

public class TokenDTO {
	private String token;
	private Date expirationToken;
	
	public TokenDTO(String token, Date expirationToken) {
		super();
		this.token = token;
		this.expirationToken = expirationToken;
	}
	public TokenDTO() {
		super();
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
