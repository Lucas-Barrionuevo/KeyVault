package com.keyVault.app.dto;
import java.util.Date;
import com.keyVault.app.entity.Category;
import com.keyVault.app.entity.Icon;
public class PasswordDTO {
	private int id;
	private String content;
	private Date createdAt;
	private int seenqty;//number of times password seen
	private Category category;
	private Icon icon;
	private String url;
	
	public PasswordDTO() {
		super();
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

}
