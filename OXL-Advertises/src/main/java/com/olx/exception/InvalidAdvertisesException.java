package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class InvalidAdvertisesException extends RuntimeException {
	private String message;

	public  InvalidAdvertisesException(String message) {
		this.message = message;
	}
	public InvalidAdvertisesException() {
		this.message = "";
	}
	public String toString() {
		return "Invalid AdvertisesId : " + this.message;
	}
}
