package com.keyVault.app.dto;

import java.util.HashSet;
import java.util.Set;
import com.keyVault.app.entity.Password;

public class CategoryDTO {
	private int id;
	private String name;
	private String preview;
	private Set<Password> passwords = new HashSet<>();
	
	public CategoryDTO() {
		super();
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
	public Set<Password> getPasswords() {
		return passwords;
	}
	public void setPasswords(Set<Password> passwords) {
		this.passwords = passwords;
	}
	
}
