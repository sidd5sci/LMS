package com.frapwise.exceptions;

public class LeaveTypeException extends LeaveManagerException{

	/**
	 * 
	 */
	public LeaveTypeException() {
		
	}
	/**
	 * 
	 * @param message
	 */
	public LeaveTypeException(String message) {
		super(message);
	}
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public LeaveTypeException(String message,Throwable cause) {
		super(message,cause);
	}
	/**
	 * 
	 * @param cause
	 */
	public LeaveTypeException(Throwable cause) {
		super(cause);
	}
}
