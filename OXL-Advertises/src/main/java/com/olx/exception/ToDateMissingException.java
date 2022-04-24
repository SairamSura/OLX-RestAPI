package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class ToDateMissingException extends RuntimeException{
	private String message;

	public  ToDateMissingException(String message) {
		this.message = message;
	}
	public ToDateMissingException() {
		this.message = "";
	}
	public String toString() {
		return "To Date Misssing : " + this.message;
	}

}
