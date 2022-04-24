package com.olx.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="AdvertisesStatus")
public class AdvertisesStatusDocument {
	@Id
	private int id;
	private String status;
	public AdvertisesStatusDocument(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	public AdvertisesStatusDocument() {
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
		return "AdvertisesStatusDocument [id=" + id + ", status=" + status + "]";
	}
	

}
