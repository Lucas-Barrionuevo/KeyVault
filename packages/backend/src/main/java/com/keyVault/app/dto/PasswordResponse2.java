package com.keyVault.app.dto;

import java.util.Date;

import com.keyVault.app.entity.Category;
import com.keyVault.app.entity.Icon;

public class PasswordResponse2 {
	private int id;
	private String name;
	private String userOrMail;
	private Date createdAt;
	private int seenqty;//number of times password seen
	
	private Category category;
	
	private Icon icon;
	
	private String url;
	
	public PasswordResponse2() {
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

	public void setUserOrMail(String userOrMail) {
		this.userOrMail = userOrMail;
	}

}
