package com.keyVault.app.dto;
import java.util.Date;
import com.keyVault.app.entity.Category;
import com.keyVault.app.entity.Icon;
import com.keyVault.app.entity.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
public class PasswordDTO {
	private int id;
	@NotEmpty
	@Size(min=2, message = "The password content must have at least 2 characters")
	@Size(max=30, message = "The password content must be less than 30 characters")
	private String content;
	@Size(min=2, message = "The password name must have at least 2 characters")
	@Size(max=30, message = "The name must be less than 30 characters")
	private String name;
	@Size(min=2, message = "The password name must have at least 2 characters")
	@Size(max=30, message = "The user or mail must be less than 30 characters")
	private String userOrMail;
	private Date createdAt;
	private int seenqty;//number of times password seen
	
	private String categoryName;
	
	private Category category;
	
	private Icon icon;
	
	private User user;
	
	private String url;
	
	public PasswordDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getSeenqty() {
		return seenqty;
	}

	public void setSeenqty(int seenqty) {
		this.seenqty = seenqty;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserOrMail() {
		return userOrMail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserOrMail(String userOrMail) {
		this.userOrMail = userOrMail;
	}

}
