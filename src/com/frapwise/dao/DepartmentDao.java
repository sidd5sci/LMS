package com.frapwise.dao;

import com.frapwise.entities.Department;
import com.frapwise.exceptions.DepartmentException;

public interface DepartmentDao extends Dao{
	
	/**
	 * Returns the department by id
	 * @param id
	 * @return
	 * @throws DepartmentException 
	 */
	public Department getDepartmentById(int id) throws DepartmentException;
	/**
	 * update the department by the department id
	 * @param id
	 * @param name
	 * @param description
	 * @return
	 */
	public int updateDepartmentById(int id,String name,String description);
}
