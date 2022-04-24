package com.olx.entity;

import java.time.LocalDate;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="blacklisted_tokens")
public class BlacklistedTokensDocument {
	//@Id
	//private int id;
	private String token;
	private LocalDate date;
	public BlacklistedTokensDocument(String token, LocalDate date) {
		super();
		this.token = token;
		this.date = date;
	}
	public BlacklistedTokensDocument() {
		super();
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "BlacklistedTokensDocument [token=" + token + ", date=" + date + "]";
	}
	
	

}
