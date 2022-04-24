package com.olx.entity;

import java.time.LocalDate;

import javax.persistence.Column;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="advertises")
public class AdvertisesDocument {
	@Id
	private int id;
	private String title;
	private String description;
	private double price;
	private String category;
	private LocalDate createdDate;
	private LocalDate modifiedDate;
	private String status;
	private String username;
	public AdvertisesDocument(int id, String title, String description, double price, String category,
			LocalDate createdDate, LocalDate modifiedDate, String status, String username) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.category = category;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.status = status;
		this.username = username;
	}
	public AdvertisesDocument() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDate getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "AdvertisesDocument [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", category=" + category + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", status=" + status + ", username=" + username + "]";
	}
	
	
	
}
