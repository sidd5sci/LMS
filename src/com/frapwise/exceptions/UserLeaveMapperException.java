package com.frapwise.exceptions;

public class UserLeaveMapperException extends LeaveManagerException {

	/**
	 * 
	 */
	public UserLeaveMapperException() {
		
	}
	/**
	 * 
	 * @param message
	 */
	public UserLeaveMapperException(String message) {
		super(message);
	}
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public UserLeaveMapperException(String message,Throwable cause) {
		super(message,cause);
	}
	/**
	 * 
	 * @param cause
	 */
	public UserLeaveMapperException(Throwable cause) {
		super(cause);
	}
}
