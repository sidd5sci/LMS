package com.frapwise.dao;

import com.frapwise.entities.LeaveType;

public interface LeaveTypeDao extends Dao{

	/**
	 * returns the leave type by providing leave type id
	 * @param id
	 * @return
	 */
	public LeaveType getLeaveTypeById(int id);
	/**
	 * update the leave type by id
	 * @param id
	 * @param name
	 * @param description
	 * @return
	 */
	public int updateLeaveTypeById(int id, String name, String description);
}
