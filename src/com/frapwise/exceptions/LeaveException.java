package com.frapwise.exceptions;

public class LeaveException extends LeaveManagerException{

	/**
	 * 
	 */
	public LeaveException() {
		
	}
	/**
	 * 
	 * @param message
	 */
	public LeaveException(String message) {
		super(message);
	}
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public LeaveException(String message,Throwable cause) {
		super(message,cause);
	}
	/**
	 * 
	 * @param cause
	 */
	public LeaveException(Throwable cause) {
		super(cause);
	}
}
