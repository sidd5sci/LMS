package com.frapwise.dao;

import java.util.List;

import com.frapwise.entities.UserLeaveMapper;

public interface UserLeaveMapperDao extends Dao{

	/**
	 * set the max leave by user id
	 * @param uid
	 * @param maxValue
	 * @return
	 */
	public int setLeaveMaxByUser(int uid,int maxValue);
	/**
	 * set the taken leave by user id
	 * @param uid
	 * @param takenValue
	 * @return
	 */
	public int setLeaveTakenByUser(int uid,int takenValue);
	/**
	 * set the available leave of user by user id
	 * @param uid
	 * @param availibleValue
	 * @return
	 */
	public int setLeaveAvailibleByUser(int uid,int availibleValue);
	/**
	 * set the leave duration by user id
	 * @param uid
	 * @param duration
	 * @return
	 */
	public int setTimeDurationByUser(int uid,int duration);
	/**
	 * set the leave duration by leave type 
	 * @param leaveType
	 * @param duration
	 * @return
	 */
	public int setTimeDurationByLeaveType(int leaveType,int duration);
	/**
	 * set the leave assigned date 
	 * @param uid
	 * @param from
	 * @param to
	 * @return
	 */
	public int setAssignedTimes(int uid,String from,String to);
	
	/**
	 * returns the max leave of all leave types 
	 * @param uid
	 * @return
	 */
	public List<UserLeaveMapper> getLeaveMaxByUser(int uid);
	/**
	 * returns the Taken leaves of all leave types
	 * @param uid
	 * @return
	 */
	public List<UserLeaveMapper> getLeaveTakenByUser(int uid);
	/**
	 * returns the available leaves of all leave types 
	 * @param uid
	 * @return
	 */
	public List<UserLeaveMapper> getLeaveAvailibleByUser(int uid);
	/**
	 * returns the max leave of 1 leave types 
	 * @param uid
	 * @return
	 */
	public List<UserLeaveMapper> getLeaveMaxByUserAndLeaveType(int uid,int leaveType);
	/**
	 * returns the Taken leaves of 1 leave types
	 * @param uid
	 * @return
	 */
	public List<UserLeaveMapper> getLeaveTakenByUserAndLeaveType(int uid,int leaveType);
	/**
	 * returns the available leaves of 1 leave types 
	 * @param uid
	 * @return
	 */
	public List<UserLeaveMapper> getLeaveAvailibleByUserAndLeaveType(int uid,int leaveType);
	/**
	 * 
	 * @param uid
	 * @return
	 */
	public List<UserLeaveMapper> getLeaveByUser(int uid);
	
}
