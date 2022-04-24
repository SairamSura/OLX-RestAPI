package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class InvalidPageIdException extends RuntimeException{
	private String message;

	public  InvalidPageIdException(String message) {
		this.message = message;
	}
	public InvalidPageIdException() {
		this.message = "";
	}
	public String toString() {
		return "Invalid PageId : " + this.message;
	}

}
