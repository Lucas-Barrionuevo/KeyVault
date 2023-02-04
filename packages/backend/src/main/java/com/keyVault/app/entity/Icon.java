package com.keyVault.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "icons")
public class Icon {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	
	private int id;
	@Column(nullable = false)
	private String domain;
	@Column(nullable = false)
	private String url;
	
	public Icon(String domain, String url) {
		super();
		this.domain = domain;
		this.url = url;
	}
	public Icon() {
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
