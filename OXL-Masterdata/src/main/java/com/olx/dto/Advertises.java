package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(value="Olx Advertises Status DTo")
public class Advertises {
	@ApiModelProperty(value="Advertise Id")
	private int id;
	@ApiModelProperty(value="Advertise Status")
	private String status;
	public Advertises(int id, String status) {
		super();
		this.id = id;
		this.status = status;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "AdvertisesStatus [id=" + id + ", status=" + status + "]";
	}

}
