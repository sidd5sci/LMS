package com.frapwise.dao;

import com.frapwise.entities.LeaveType;
import com.frapwise.exceptions.LeaveTypeException;

public interface LeaveTypeDao extends Dao{

	/**
	 * returns the leave type by providing leave type id
	 * @param id
	 * @return
	 * @throws LeaveTypeException 
	 */
	public LeaveType getLeaveTypeById(int id) throws LeaveTypeException;
	/**
	 * update the leave type by id
	 * @param id
	 * @param name
	 * @param description
	 * @return
	 */
	public int updateLeaveTypeById(int id, String name, String description);
}
