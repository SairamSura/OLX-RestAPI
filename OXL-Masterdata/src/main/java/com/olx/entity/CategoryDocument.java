package com.olx.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value="category")
public class CategoryDocument {
	@Id
	private int id;
	private String category;
	public CategoryDocument(int id, String category) {
		super();
		this.id = id;
		this.category = category;
	}
	public CategoryDocument() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "CategoryDocument [id=" + id + ", category=" + category + "]";
	}
	
}
