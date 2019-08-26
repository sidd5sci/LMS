package com.frapwise.dao;

/**
 * 
 * @author galac
 *
 */
public interface Queries {
	
		/**
		 * Department model
		 */
		// Setters
		public final static String ADD_DEPARTMENT			= "INSERT INTO departments(id,name,description) VALUES(null,?,?)";
		public final static String REMOVE_DEPARTMENT		= "DELETE FROM departments where id = ?";
		public final static String UPDATE_DEPARTMENT		= "UPDATE departments SET name = ? , description = ? WHERE id = ?";
		// getters
		public final static String GET_DEPARTMENT_BY_ID 	= "SELECT * FROM departments WHERE id = ?";
		public final static String GET_ALL_DEPARTMENTS 		= "SELECT * FROM departments";

		/**
		 * Leave Model
		 */
		// Setters
		public final static String ADD_LEAVE	 			= "INSERT INTO leaves(id,user_id,department_id,leave_type_id,leave_from,leave_to,applied_date,time_off_type,status,approval) VALUES (null,?,?,?,?,?,?,?,?,?)";
		public final static String REMOVE_LEAVE				= "DELETE FROM leaves WHERE id = ?";
		public final static String UPDATE_LEAVE				= "UPDATE leaves SET user_id = ?, department_id = ?, leave_type_id = ?, leave_from = ?, leave_to = ?, applied_date = ?, time_off_type = ?, status = ?, approval = ? WHERE id = ?";
		public final static String APPROVE_LEAVE			= "UPDATE leaves SET status = ? WHERE id = ?";
		public final static String REJECT_LEAVE				= "UPDATE leaves SET status = ? WHERE id = ?";
		// getters
		public final static String GET_LEAVE_BY_ID 			= "SELECT * FROM leaves WHERE id = ?";
		public final static String GET_ALL_LEAVES			= "SELECT * FROM leaves";
		public final static String GET_LEAVE_BY_TODAY		= "SELECT * FROM leaves WHERE leave_from = ?"; 
		public final static String GET_LEAVE_BY_APPLIED 	= "SELECT * FROM leaves WHERE applied_from = ?";
		public final static String GET_LEAVE_BY_DATES 		= "SELECT * FROM leaves WHERE leave_from > ? and leave_to < ?";
		public final static String GET_LEAVE_BY_USER		= "SELECT * FROM leaves WHERE user_id = ?";
		public final static String GET_LEAVE_BY_USER_DATE	= "SELECT * FROM leaves WHERE user_id = ? and leave_from = ? or leave_to ";
		public final static String GET_LEAVE_BY_DEPARTMENT	= "SELECT * FROM leaves WHERE department_id = ?";
		public final static String GET_LEAVE_BY_LEAVETYPE	= "SELECT * FROM leaves WHERE leave_type_id = ?";
		
		/**
		 * LeaveType Model
		 */
		// Setters
		public final static String ADD_LEAVETYPE			= "INSERT INTO leave_types(id,name,description) VALUES(null,?,?)";
		public final static String REMOVE_LEAVETYPE			= "DELETE FROM leave_types where id = ?";
		public final static String UPDATE_LEAVETYPE			= "UPDATE leave_types SET name = ? , description = ? WHERE id = ?";
		// getters
		public final static String GET_LEAVETYPE_BY_ID 		= "SELECT * FROM leave_types WHERE id = ?";
		public final static String GET_ALL_LEAVETYPE 		= "SELECT * FROM leave_types";
		
		/**
		 * Session Model
		 */
		// Setters
		public final static String ADD_SESSION				= "INSERT INTO sessions(id,uid,ip_address,user_agent,payload) VALUES(?,?,?,?,?)";
		public final static String UPDATE_PAYLOAD			= "";
		public final static String REMOVE_SESSION			= "DELETE session where id = ?";
		// getters
		public final static String GET_SESSION_BY_SSID 		= "SELECT * FROM users WHERE id = ?";
		public final static String GET_ALL_SESSION 			= "SELECT * FROM users";

	
		/**
		 * User Model
		 */

		// Setters
		public final static String AUTHENTICATE 			= "SELECT * FROM users WHERE email = ? and password = ?";
		public final static String REGISTER_USER 			= "INSERT INTO users(id,fname,lname,username,home_office,department_id,email,password,role,status) VALUES (null,?,?,?,?,?,?,?,?,?)";
		public final static String ENABLE_USER 				= "UPDATE users SET status = ? , updated_at = ? WHERE id = ?";
		public final static String DISABLE_USER 			= "UPDATE users SET status = ? , updated_at = ? WHERE id = ?";
		public final static String UPDATE_PASSWORD 			= "UPDATE users SET password = ? , updated_at = ? WHERE id =?";
		public final static String REMOVE_USER				= "DELETE FROM users WHERE id = ?";
		public final static String UPDATE_USER				= "UPDATE users SET fname = ?, lname = ?, username = ?, home_office = ?, department_id = ?, email = ?, password = ?, role = ?, status = ? WHERE id = ?";
		// getters
		public final static String GET_USER_BY_ID 			= "SELECT * FROM users WHERE id = ?";
		public final static String GET_ALL_USER 			= "SELECT * FROM users";
		public final static String GET_USER_BY_ROLE 		= "SELECT * FROM users WHERE role = ?";
		public final static String GET_USER_BY_STATUS 		= "SELECT * FROM users WHERE status = ?";
		public final static String GET_USER_BY_DEPARTMENT	= "SELECT * FROM users WHERE department_id = ?";
		public final static String GET_DISABLED_USER 		= "SELECT * FROM users WHERE status = 'disbaled'";
		
		/**
		 * UserLeaveMapper Model
		 */
		// Setters
		public final static String ADD_LEAVEMAPPER	 				= "INSERT INTO user_leave_maper(id,uid,leave_type_id,leave_max,leave_taken,leave_availible,time_duration,assigned_from,assigned_to) VALUES (null,?,?,?,?,?,?,?,?)";
		public final static String REMOVE_LEAVEMAPPER				= "DELETE FROM user_leave_maper WHERE id = ?";
		public final static String UPDATE_LEAVEMAPPER				= "UPDATE user_leave_maper SET uid = ?, leave_type_id = ?, leave_max = ?, leave_taken = ?, leave_availible = ?, time_duration = ?, assigned_from,assigned_to = ? WHERE id = ?";
		public final static String REMOVE_LEAVEMAPPER_BY_UID		= "DELETE FROM user_leave_maper WHERE uid = ?";
		public final static String UPDATE_LEAVEMAPPER_N_BY_UID_L 	= "UPDATE user_leave_maper SET leave_taken = leave_taken - ?, leave_availible = leave_availible + ? WHERE uid = ? and leave_type_id = ? ";
		public final static String UPDATE_LEAVEMAPPER_P_BY_UID_L	= "UPDATE user_leave_maper SET leave_taken = leave_taken + ?, leave_availible = leave_availible - ? WHERE uid = ? and leave_type_id = ? ";
		public final static String UPDATE_LEAVEMAPPER_BY_ID			= "UPDATE user_leave_maper SET assigned_from = ?, assigned_to = ?, leave_max = ? WHERE id = ?";
		// getters
		public final static String GET_LEAVEMAPPER_BY_ID 			= "SELECT * FROM user_leave_maper WHERE id = ?";
		public final static String GET_ALL_LEAVEMAPPERS				= "SELECT * FROM user_leave_maper";
		public final static String GET_LEAVEMAPPER_BY_TODAY			= "SELECT * FROM user_leave_maper WHERE assigned_from = ?"; 
		public final static String GET_LEAVEMAPPER_BY_APPLIED 		= "SELECT * FROM user_leave_maper WHERE assigned_from = ?";
		public final static String GET_LEAVEMAPPER_BY_DATES 		= "SELECT * FROM user_leave_maper WHERE assigned_from > ? and assigned_to < ?";
		public final static String GET_LEAVEMAPPER_BY_USER			= "SELECT * FROM user_leave_maper WHERE uid = ?";
		public final static String GET_LEAVEMAPPER_BY_DEPARTMENT	= "SELECT * FROM user_leave_maper WHERE department_id = ?";
		public final static String GET_LEAVEMAPPER_BY_LEAVETYPE		= "SELECT * FROM user_leave_maper WHERE leave_type_id = ?";
		public final static String GET_LEAVEMAPPERS_BY_UID_LEAVETYPE= "SELECT * FROM user_leave_maper WHERE uid = ? and leave_type_id = ?";
		
}
