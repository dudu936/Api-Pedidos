package com.educandoweb.course.services.exeptions;

public class ConstraintException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
	public ConstraintException(String msg) {
		super(msg);
	}

}
