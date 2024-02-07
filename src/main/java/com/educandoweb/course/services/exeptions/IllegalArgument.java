package com.educandoweb.course.services.exeptions;

public class IllegalArgument extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public IllegalArgument() {
		super("Provide a valid argument.");
	}

}
