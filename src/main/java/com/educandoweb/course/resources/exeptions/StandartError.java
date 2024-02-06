package com.educandoweb.course.resources.exeptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandartError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant timestap;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
	public StandartError() {
		
	}

	public StandartError(Instant timestap, Integer status, String error, String message, String path) {
		super();
		this.timestap = timestap;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public Instant getTimestap() {
		return timestap;
	}

	public Integer getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	
	
	
}
