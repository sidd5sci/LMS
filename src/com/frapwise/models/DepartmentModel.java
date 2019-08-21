package com.frapwise.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frapwise.dao.DepartmentDao;
import com.frapwise.db.DB;
import com.frapwise.entities.Department;
import com.frapwise.entities.User;
import com.frapwise.exceptions.DepartmentException;

public class DepartmentModel implements DepartmentDao{

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet result;

	// Setters
	private final static String ADD_DEPARTMENT			= "INSERT INTO departments(id,name,description) VALUES(null,?,?)";
	private final static String REMOVE_DEPARTMENT		= "DELETE FROM departments where id = ?";
	private final static String UPDATE_DEPARTMENT		= "UPDATE departments SET name = ? , description = ? WHERE id = ?";
	// getters
	private final static String GET_DEPARTMENT_BY_ID 	= "SELECT * FROM departments WHERE id = ?";
	private final static String GET_ALL_DEPARTMENTS 	= "SELECT * FROM departments";
	

	
	public DepartmentModel(){
		DB db = new DB();
		this.conn = db.getConnection();
	}
	
	@Override
	public Object add(Object o) throws Exception {
		// TODO Auto-generated method stub
		Department dpt = (Department)o;
		Integer flag = 0;
		try {
			if(dpt.getName().equals("")) {
				throw new DepartmentException("Department name should not be empty or null");
			}else {
				this.prep = this.conn.prepareStatement(ADD_DEPARTMENT);
				this.prep.setString(1, dpt.getName());
				this.prep.setString(2,dpt.getDescription());
				flag = this.prep.executeUpdate();
			}
			
		}catch(Exception e) {
			
		}
		
		return flag;
	}

	@Override
	public Object remove(Object o) throws Exception {
		// TODO Auto-generated method stub
		Integer id = (Integer) o;
		Integer flag = 0;
		try {
			this.prep = this.conn.prepareStatement(REMOVE_DEPARTMENT);
			this.prep.setInt(1, id);
			flag = this.prep.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Object update(Object o) throws Exception {
		// TODO Auto-generated method stub
		Department d = (Department) o;
		Integer flag = 0;
		try {
			this.prep = this.conn.prepareStatement(UPDATE_DEPARTMENT);
			this.prep.setString(1,d.getName());
			this.prep.setString(2, d.getDescription());
			this.prep.setInt(3,d.getId());
			flag = this.prep.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Object> getAll() throws Exception {
		// TODO Auto-generated method stub
		List<Object> departments = new ArrayList<Object>();
		try {
			this.prep = this.conn.prepareStatement(GET_ALL_DEPARTMENTS);
			this.result = this.prep.executeQuery();
			while(this.result.next()) {
				Department d = new Department();
				d.setId(this.result.getInt("id"));
				d.setName(this.result.getString("name"));
				d.setDescription(this.result.getString("description"));
				departments.add(d);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return departments;
	}

	@Override
	public Department getDepartmentById(int id) {
		// TODO Auto-generated method stub
		Department dpt = new Department();
		try {
			this.prep = this.conn.prepareStatement(GET_DEPARTMENT_BY_ID);
			this.prep.setInt(1, id);
			this.result = this.prep.executeQuery();
			while(this.result.next()) {
				dpt.setId(this.result.getInt("id"));
				dpt.setName(this.result.getString("name"));
				dpt.setDescription(this.result.getString("description"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dpt;
	}

	@Override
	public int updateDepartmentById(int id, String name, String description) {
		// TODO Auto-generated method stub
		return 0;
	}

}
