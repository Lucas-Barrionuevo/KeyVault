package com.keyVault.app.dto;
import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
public class UserDTO {
	private int id;
	@NotEmpty
	@Email(message= "The email entered is invalid")
	private String email;
	@NotEmpty
	@Size(min=2, message = "The password must have at least 2 characters")
	@Size(max=30, message = "The password must be less than 30 characters")
	private String password;
	private Date createdAt;
	private boolean enabled;
	
	public UserDTO() {
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
