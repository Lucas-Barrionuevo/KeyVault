package com.keyVault.app.dto;

import java.util.Set;
import com.keyVault.app.entity.Password;
import com.keyVault.app.entity.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryDTO {
	private int id;
	
	@NotEmpty
	@Size(min=2, message = "The category name must have at least 2 characters")
	@Size(max=30, message = "The category name must be less than 30 characters")
	private String name;
	
	private String preview;
	
	private User user;
	
	public CategoryDTO() {
		super();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	
}
