package com.frapwise.dao;

import java.util.HashMap;
import java.util.List;

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
	
	/**
	 * returns user by user id
	 * @param id
	 * @return
	 * @throws UserException
	 */
	public User getUserById(int id) throws UserException;
	
	/**
	 * returns user by role
	 * @param role
	 * @return
	 * @throws UserException
	 */
	public List<User> getUserByRole(String role) throws UserException;
	
	/**
	 * returns user by status
	 * @param status
	 * @return
	 * @throws UserException
	 */
	public List<User> getUserByStatus(String status) throws UserException;
	/**
	 * get th users by department id
	 * @param departmentId
	 * @return
	 * @throws UserException
	 */
	public List<User> getUserByDepartmentId(int departmentId) throws UserException;
	
	
	
	
}
