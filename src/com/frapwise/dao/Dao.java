package com.frapwise.dao;

import java.util.List;

/**
 * Base interface of all the DAO interfaces  
 * @author galac
 *
 */
public interface Dao {
	/**
	 * To the data to the MYSQL tables by mapping the entity
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Object add(Object o) throws Exception;
	/**
	 * To remove the single row data from the MYSQL table using 
	 * using entity mapping 
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Object remove(Object o) throws Exception;
	
	/**
	 * To update the data of the MYSQL table by mapping the entity
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Object update(Object o) throws Exception;
	
	/**
	 * To retrive all the rows of the MYSQL table 
	 * @return
	 * @throws Exception
	 */
	public List<Object> getAll() throws Exception;
}
