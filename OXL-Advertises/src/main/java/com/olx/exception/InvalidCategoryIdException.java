package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class InvalidCategoryIdException extends RuntimeException{
	private String message;

	public  InvalidCategoryIdException(String message) {
		this.message = message;
	}
	public InvalidCategoryIdException() {
		this.message = "";
	}
	public String toString() {
		return "Invalid CategoryId : " + this.message;
	}

}
