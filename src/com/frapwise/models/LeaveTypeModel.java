package com.frapwise.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frapwise.dao.LeaveTypeDao;
import com.frapwise.db.DB;
import com.frapwise.entities.Department;
import com.frapwise.entities.LeaveType;
import com.frapwise.exceptions.DepartmentException;
import com.frapwise.exceptions.LeaveTypeException;

public class LeaveTypeModel implements LeaveTypeDao{

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet result;

	// Setters
	private final static String ADD_LEAVETYPE			= "INSERT INTO leave_types(id,name,description) VALUES(null,?,?)";
	private final static String REMOVE_LEAVETYPE		= "DELETE FROM leave_types where id = ?";
	private final static String UPDATE_LEAVETYPE		= "UPDATE leave_types SET name = ? , description = ? WHERE id = ?";
	// getters
	private final static String GET_LEAVETYPE_BY_ID 	= "SELECT * FROM leave_types WHERE id = ?";
	private final static String GET_ALL_LEAVETYPE 		= "SELECT * FROM leave_types";
	

	
	public LeaveTypeModel(){
		DB db = new DB();
		this.conn = db.getConnection();
	}
	@Override
	public Object add(Object o) throws Exception {
		// TODO Auto-generated method stub
		LeaveType dpt = (LeaveType)o;
		Integer flag = 0;
		try {
			if(dpt.getName().equals("")) {
				throw new LeaveTypeException("LeaveType name should not be empty or null");
			}else {
				this.prep = this.conn.prepareStatement(ADD_LEAVETYPE);
				this.prep.setString(1, dpt.getName());
				this.prep.setString(2,dpt.getDescription());
				flag = this.prep.executeUpdate();
			}
			
		}catch(SQLException e) {
			throw new LeaveTypeException("Leavetype exception occured");
		}
		catch(Exception e) {
			throw new LeaveTypeException("Leavetype exception occured");
		}
		
		return flag;
	}

	@Override
	public Object remove(Object o) throws Exception {
		// TODO Auto-generated method stub
		Integer id = (Integer) o;
		Integer flag = 0;
		try {
			this.prep = this.conn.prepareStatement(REMOVE_LEAVETYPE);
			this.prep.setInt(1, id);
			flag = this.prep.executeUpdate();
		}catch(SQLException e) {
			throw new LeaveTypeException("Leavetype exception occured");
		}
		catch(Exception e) {
			throw new LeaveTypeException("Leavetype exception occured");
		}
		return flag;
	}

	@Override
	public Object update(Object o) throws Exception {
		// TODO Auto-generated method stub
		LeaveType d = (LeaveType) o;
		Integer flag = 0;
		try {
			this.prep = this.conn.prepareStatement(UPDATE_LEAVETYPE);
			this.prep.setString(1,d.getName());
			this.prep.setString(2, d.getDescription());
			this.prep.setInt(3,d.getId());
			flag = this.prep.executeUpdate();
		}catch(SQLException e) {
			throw new LeaveTypeException("Leavetype exception occured");
		}
		catch(Exception e) {
			throw new LeaveTypeException("Leavetype exception occured");
		}
		return flag;
	}

	@Override
	public List<Object> getAll() throws Exception {
		// TODO Auto-generated method stub
		List<Object> leavetypes = new ArrayList<Object>();
		try {
			this.prep = this.conn.prepareStatement(GET_ALL_LEAVETYPE);
			this.result = this.prep.executeQuery();
			while(this.result.next()) {
				LeaveType d = new LeaveType();
				d.setId(this.result.getInt("id"));
				d.setName(this.result.getString("name"));
				d.setDescription(this.result.getString("description"));
				leavetypes.add(d);
			}
		}catch(SQLException e) {
			throw new LeaveTypeException("Leavetype exception occured");
		}
		catch(Exception e) {
			throw new LeaveTypeException("Leavetype exception occured");
		}
		return leavetypes;
	}

	@Override
	public LeaveType getLeaveTypeById(int id) throws LeaveTypeException {
		// TODO Auto-generated method stub
		LeaveType dpt = new LeaveType();
		try {
			this.prep = this.conn.prepareStatement(GET_LEAVETYPE_BY_ID);
			this.prep.setInt(1, id);
			this.result = this.prep.executeQuery();
			while(this.result.next()) {
				dpt.setId(this.result.getInt("id"));
				dpt.setName(this.result.getString("name"));
				dpt.setDescription(this.result.getString("description"));
			}
		}catch(SQLException e) {
			throw new LeaveTypeException("Leavetype exception occured");
		}
		catch(Exception e) {
			throw new LeaveTypeException("Leavetype exception occured");
		}
		return dpt;
	}

	@Override
	public int updateLeaveTypeById(int id, String name, String description) {
		// TODO Auto-generated method stub
		return 0;
	}

}
