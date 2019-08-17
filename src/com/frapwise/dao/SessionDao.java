package com.frapwise.dao;

import com.frapwise.entities.Session;

/**
 * Session dao
 * @author galac
 *
 */
public interface SessionDao extends Dao{
	
	/**
	 * Get the session from  database by ssid
	 * @param ssid
	 * @return
	 */
	public Session getSession(String ssid);
	/**
	 * set the session into database 
	 * @param session
	 * @return
	 */
	public int setSession(Session session);
}
