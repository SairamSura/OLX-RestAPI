package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(value="OLX Category DTO")
public class Categories {
	@ApiModelProperty(value="Category Id")
	private int id;
	@ApiModelProperty(value="Category Status")
	private String category;
	public Categories(int id, String category) {
		super();
		this.id = id;
		this.category = category;
	}
	public Categories() {
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
		return "Categories [id=" + id + ", category=" + category + "]";
	}

}
