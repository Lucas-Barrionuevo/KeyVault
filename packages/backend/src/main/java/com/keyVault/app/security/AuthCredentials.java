package com.keyVault.app.security;

public class AuthCredentials {
	private String mail;
	private String Password;
	
	public AuthCredentials() {
		super();
	}
	public AuthCredentials(String mail, String password) {
		super();
		this.mail = mail;
		Password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
}
