package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class UserNameDoesNotExistException extends RuntimeException {
	private String message;

	public  UserNameDoesNotExistException(String message) {
		this.message = message;
	}
	public UserNameDoesNotExistException() {
		this.message = "";
	}
	public String toString() {
		return "Invalid User Name: " + this.message;
	}
}
