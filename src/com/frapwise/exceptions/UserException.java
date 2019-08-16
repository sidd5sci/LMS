package com.frapwise.exceptions;

public class UserException extends LeaveManagerException{

	/**
	 * 
	 */
	public UserException() {
		
	}
	/**
	 * 
	 * @param message
	 */
	public UserException(String message) {
		super(message);
	}
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public UserException(String message,Throwable cause) {
		super(message,cause);
	}
	/**
	 * 
	 * @param cause
	 */
	public UserException(Throwable cause) {
		super(cause);
	}
	
}
