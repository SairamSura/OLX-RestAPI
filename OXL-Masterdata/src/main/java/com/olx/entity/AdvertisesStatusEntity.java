package com.olx.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Advertises")
public class AdvertisesStatusEntity {
	@Id
	@GeneratedValue
	private int id;
	private String status;
	public AdvertisesStatusEntity(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	public AdvertisesStatusEntity() {
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
		return "AdvertisesEntity [id=" + id + ", status=" + status + "]";
	}
	

}
