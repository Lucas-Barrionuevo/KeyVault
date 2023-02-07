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
	@NotEmpty
	private String name;
	private String userOrMail;
	private Date createdAt;
	private int seenQty;//number of times password seen
	
	private String categoryName;
	
	private Category category;
	
	private Icon icon;
	
	private User user;
	
	private String url;
	
	public PasswordDTO() {
		super();
	}

	public PasswordDTO(
			@NotEmpty @Size(min = 2, message = "The password content must have at least 2 characters") @Size(max = 30, message = "The password content must be less than 30 characters") String content,
			@NotEmpty String name, String userOrMail, Date createdAt, String categoryName, String url) {
		super();
		this.content = content;
		this.name = name;
		this.userOrMail = userOrMail;
		this.createdAt = createdAt;
		this.categoryName = categoryName;
		this.url = url;
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

	public int getSeenQty() {
		return seenQty;
	}

	public void setSeenQty(int seenQty) {
		this.seenQty = seenQty;
	}

	public Category getCategory() {
		return category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
