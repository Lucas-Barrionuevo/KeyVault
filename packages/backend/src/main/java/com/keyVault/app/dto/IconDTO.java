package com.keyVault.app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class IconDTO {
	private int id;
	@NotEmpty
	@Size(min=2, message = "The icon domain must have at least 2 characters")
	private String domain;
	@NotEmpty
	@Size(min=2, message = "The url must have at least 2 characters")
	private String url;
	
	public IconDTO() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
