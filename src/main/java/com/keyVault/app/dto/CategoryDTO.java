package com.keyVault.app.dto;

import java.util.HashSet;
import java.util.Set;
import com.keyVault.app.entity.Password;

public class CategoryDTO {

	private int id;
	private String name;
	private String preview;
	private Set<Password> passwords = new HashSet<>();
	
}
