package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class FromDateMissingException extends RuntimeException{
	private String message;

	public  FromDateMissingException(String message) {
		this.message = message;
	}
	public FromDateMissingException() {
		this.message = "";
	}
	public String toString() {
		return "From Date Missing : " + this.message;
	}
}
