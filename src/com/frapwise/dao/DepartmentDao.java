package com.frapwise.dao;

import com.frapwise.entities.Department;

public interface DepartmentDao extends Dao{
	
	/**
	 * Returns the department by id
	 * @param id
	 * @return
	 */
	public Department getDepartmentById(int id);
	/**
	 * update the department by the department id
	 * @param id
	 * @param name
	 * @param description
	 * @return
	 */
	public int updateDepartmentById(int id,String name,String description);
}
