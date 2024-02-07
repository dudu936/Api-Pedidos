package com.educandoweb.course.resources.exeptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exeptions.ConstraintException;
import com.educandoweb.course.services.exeptions.DatabaseException;
import com.educandoweb.course.services.exeptions.IllegalArgument;
import com.educandoweb.course.services.exeptions.ResourceNotFoundExeption;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundExeption.class)
	public ResponseEntity<StandartError> resourceNotFound(ResourceNotFoundExeption errorClass, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandartError errorResponse = new StandartError(Instant.now(), status.value(), error, errorClass.getMessage(), request.getServletPath());
		return ResponseEntity.status(status).body(errorResponse);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandartError> databaseError(DatabaseException errorClass, HttpServletRequest request){
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandartError errorResponse = new StandartError(Instant.now(), status.value(), error, errorClass.getMessage(), request.getServletPath());
		return ResponseEntity.status(status).body(errorResponse);
	}
	
	@ExceptionHandler(ConstraintException.class)
	public ResponseEntity<StandartError> ConstraintViolationException(ConstraintException errorClass, HttpServletRequest request){
		String error = "A validate error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandartError errorResponse = new StandartError(Instant.now(), status.value(), error, errorClass.getMessage(), request.getServletPath());
		return ResponseEntity.status(status).body(errorResponse);
	}
	
	@ExceptionHandler(IllegalArgument.class)
	public ResponseEntity<StandartError> IllegalArgument(IllegalArgument errorClass, HttpServletRequest request){
		String error = "A argument error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandartError errorResponse = new StandartError(Instant.now(), status.value(), error, errorClass.getMessage(), request.getServletPath());
		return ResponseEntity.status(status).body(errorResponse);
	}
}
