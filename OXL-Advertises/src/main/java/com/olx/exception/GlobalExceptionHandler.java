package com.olx.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.micrometer.core.instrument.config.validate.Validated.Invalid;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value=InvalidAuthTokenException.class)
	public ResponseEntity<Object> handleAuthTokenException(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \""+ exception.toString()+"\"}";
		ResponseEntity<Object> response = handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	
	@ExceptionHandler(value=InvalidAdvertisesException.class)
	public ResponseEntity<Object> handleAdvertisesIdException(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \""+ exception.toString()+"\"}";
		ResponseEntity<Object> response = handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	@ExceptionHandler(value=UserNameDoesNotExistException.class)
	public ResponseEntity<Object> handleUserNameNotFoundException(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \""+ exception.toString()+"\"}";
		ResponseEntity<Object> response = handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	@ExceptionHandler(value=OnDateMissingException.class)
	public ResponseEntity<Object> handleOnDateMissingException(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \""+ exception.toString()+"\"}";
		ResponseEntity<Object> response = handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	@ExceptionHandler(value=FromDateMissingException.class)
	public ResponseEntity<Object> handleFromDateMissingException(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \""+ exception.toString()+"\"}";
		ResponseEntity<Object> response = handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	@ExceptionHandler(value=ToDateMissingException.class)
	public ResponseEntity<Object> handleToDateMissingException(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \""+ exception.toString()+"\"}";
		ResponseEntity<Object> response = handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	@ExceptionHandler(value=InvalidPageIdException.class)
	public ResponseEntity<Object> handlePageIdException(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \""+ exception.toString()+"\"}";
		ResponseEntity<Object> response = handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	@ExceptionHandler(value=InvalidCategoryIdException.class)
	public ResponseEntity<Object> handleCategoryIdException(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \""+ exception.toString()+"\"}";
		ResponseEntity<Object> response = handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	@ExceptionHandler(value=InvalidStatusId.class)
	public ResponseEntity<Object> handleStatusIdException(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \""+ exception.toString()+"\"}";
		ResponseEntity<Object> response = handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	

}
