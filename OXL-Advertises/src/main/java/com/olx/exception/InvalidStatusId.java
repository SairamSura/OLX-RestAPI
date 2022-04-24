package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class InvalidStatusId extends RuntimeException{
	private String message;

	public  InvalidStatusId(String message) {
		this.message = message;
	}
	public InvalidStatusId() {
		this.message = "";
	}
	public String toString() {
		return "Invalid StatusId : " + this.message;
	}

}
