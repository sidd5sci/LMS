package com.frapwise.dao;

import java.util.HashMap;

import com.frapwise.entities.User;
import com.frapwise.exceptions.UserException;

public interface UserDao extends Dao{

	/**
	 * Authenticate the user if credentials are true
	 * @param email
	 * @param password
	 * @return
	 * @throws UserException 
	 */
	public User Authenticate(String email,String password) throws UserException;

	/**
	 * Set the session in database
	 * @param uid
	 * @param session
	 * @return
	 * @throws UserException 
	 */
	public int setSession(int uid,HashMap<String,Object> session) throws UserException;
	
	/**
	 * Get the session data from database
	 * @param uid
	 * @param session
	 * @return
	 * @throws UserException
	 */
	public int getSession(int uid,HashMap<String,Object> session) throws UserException;
	
	/**
	 * Update the password, forgot password case
	 * @param uid
	 * @param password
	 * @return
	 * @throws UserException
	 */
	public int updatePassword(int uid, String password) throws UserException;
	
	/**
	 * Enable user to authenticate
	 * @param uid
	 * @return
	 * @throws UserException
	 */
	public int enable(int uid) throws UserException;
	
	/**
	 * disable user to authenticate
	 * @param uid
	 * @return
	 * @throws UserException
	 */
	public int disable(int uid) throws UserException;
}
