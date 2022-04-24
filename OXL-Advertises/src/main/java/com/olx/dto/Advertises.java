package com.olx.dto;


import java.io.File;
import java.sql.Date;
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(value="OlX Advertise DTO")
public class Advertises {
	
	@ApiModelProperty(value="Advertise Id")
	private int id;
	@ApiModelProperty(value="Advertise Title")
	private String title;
	@ApiModelProperty(value="Advertise CategoryId")
	private int categoryId;
	@ApiModelProperty(value="Advertise StatusId")
	private int statusId;
	@ApiModelProperty(value="Advertise Price")
	private double price;
	@ApiModelProperty(value="Advertise Description")
	private String description;
	@ApiModelProperty(value="Advertise CreatedDate")
	private LocalDate createdDate;
	@ApiModelProperty(value="Advertise ModifiedDate")
	private LocalDate modifiedDate;
	private String category;
	private String status;
	private String userName;

	public Advertises( int id,String title, int categoryId, int statusId, double price, String description,
			LocalDate createdDate, LocalDate modifiedDate, String category, String status, String userName) {
		super();
		this.id = id;
		this.title = title;
		this.categoryId = categoryId;
		this.statusId = statusId;
		this.price = price;
		this.description = description;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.category = category;
		this.status = status;
		this.userName = userName;
	}

	public Advertises() {
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

	@Override
	public String toString() {
		return "Advertises [ title=" + title + ", categoryId=" + categoryId
				+ ", statusId=" + statusId + ", price=" + price + ", description=" + description + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", category=" + category + ", status=" + status
				+ ", userName=" + userName + "]";
	}

	@Override
	public boolean equals(Object obj) {
	Advertises advertiseDto = (Advertises)obj;
	if(this.title.equals(advertiseDto.getTitle()))
	return true;
	return false;
	}
	
}
