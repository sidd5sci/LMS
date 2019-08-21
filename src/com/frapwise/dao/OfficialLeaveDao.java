package com.frapwise.dao;

import com.frapwise.entities.OfficialLeave;

public interface OfficialLeaveDao extends Dao{
	
	/**
	 * get the Official leave by id
	 * @param id
	 * @return
	 */
	public OfficialLeave getOfficialLeaveById(int id);
	/**
	 * get the official leave by name
	 * @param name
	 * @return
	 */
	public OfficialLeave getOfficialLeaveByName(String name);
	
} 
