package com.frapwise.exceptions;

public class LeaveManagerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public LeaveManagerException() {
		
	}
	/**
	 * 
	 * @param message
	 */
	public LeaveManagerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public LeaveManagerException(String message,Throwable cause) {
		super(message,cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param cause
	 */
	public LeaveManagerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	

}
