package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class OnDateMissingException extends RuntimeException {
	private String message;

	public  OnDateMissingException(String message) {
		this.message = message;
	}
	public OnDateMissingException() {
		this.message = "";
	}
	public String toString() {
		return "On Date Missing: " + this.message;
	}
}
