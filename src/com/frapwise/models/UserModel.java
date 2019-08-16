package com.frapwise.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.frapwise.dao.UserDao;
import com.frapwise.db.DB;
import com.frapwise.entities.User;
import com.frapwise.exceptions.UserException;

public class UserModel implements UserDao{
	
	private Connection conn;
	private PreparedStatement prep;
	private ResultSet result;

	// Setters
	private final static String AUTHENTICATE 			= "SELECT * FROM users WHERE email = ? and password = ?";
	private final static String REGISTER_USER 			= "INSERT INTO users(id,fname,lanme,username,email,password,role,status) VALUES (null,?,?,?,?,?,?,?)";
	private final static String ENABLE_USER 			= "UPDATE users SET status = ? , updated_at = ? WHERE id = ?";
	private final static String DISABLE_USER 			= "UPDATE users SET status = ? , updated_at = ? WHERE id = ?";
	private final static String UPDATE_PASSWORD 		= "UPDATE users SET password = ? , updated_at = ? WHERE id =?";
	private final static String REMOVE_USER				= "DELETE users WHERE id = ?";
	private final static String UPDATE_USER				= "";
	// getters
	private final static String GET_USER_BY_ID 			= "SELECT * FROM users WHERE id = ?";
	private final static String GET_ALL_USER 			= "SELECT * FROM users";
	private final static String GET_USER_BY_ROLE 		= "SELECT * FROM users WHERE role = ?";
	private final static String GET_ENABLED_USER 		= "SELECT * FROM users WHERE status = 'enabled'";
	private final static String GET_DISABLED_USER 		= "SELECT * FROM users WHERE status = 'disbaled'";
	

	
	public UserModel(){
		DB db = new DB();
		this.conn = db.getConnection();
	}
	
	@Override
	public Object add(Object o) throws Exception {
		// TODO Auto-generated method stub
		User u = (User) o;
		Integer flag = 0;
		try {
			this.prep = this.conn.prepareStatement(REGISTER_USER,Statement.RETURN_GENERATED_KEYS);
			this.setParam(this.prep, u);
			flag = this.prep.executeUpdate();
	
		}catch(SQLException e) {
			return "E2001";
		}
		return flag;
	}

	@Override
	public Object remove(Object o) throws Exception {
		// TODO Auto-generated method stub
		User u = (User) o;
		Integer flag = 0;
		try {
			this.prep = this.conn.prepareStatement(REMOVE_USER);
			this.prep.setInt(1, u.getId());
			flag = this.prep.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Object update(Object o) throws Exception {
		// TODO Auto-generated method stub
		User u = (User) o;
		Integer flag = 0;
		try {
			this.prep = this.conn.prepareStatement(UPDATE_USER);
			this.setParam(this.prep,u);
			flag = this.prep.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Object> getAll() throws Exception {
		// TODO Auto-generated method stub
		List<Object> users = new ArrayList<Object>();
		try {
			this.prep = this.conn.prepareStatement(GET_ALL_USER);
			this.result = this.prep.executeQuery();
			while(this.result.next()) {
				User u = new User();
				this.setUser(u, this.result);
				users.add(u);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User Authenticate(String email, String password) throws UserException {
		// TODO Auto-generated method stub
		User u = new User();
		try {
			this.prep = this.conn.prepareStatement(AUTHENTICATE);
			this.prep.setString(1, email);
			this.prep.setString(2, password);
			this.result = this.prep.executeQuery();
			
			if(result.next() == false) {
				u = null;
				System.out.println("ResultSet in empty"); 
				throw new UserException("User not found");
			}else {
				this.setUser(u, this.result);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public int setSession(int uid, HashMap<String, Object> session) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSession(int uid, HashMap<String, Object> session) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePassword(int uid, String password) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int enable(int uid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int disable(int uid) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	protected void setParam(PreparedStatement stmt,User u) throws SQLException {
		stmt.setString(1, u.getFname());
		stmt.setString(2, u.getLname());
		stmt.setString(3, u.getUsername());
		stmt.setString(4, u.getEmail());
		stmt.setString(5, u.getPassword());
		stmt.setString(6, u.getRole());
		stmt.setString(7, u.getStatus());
		
	}
	
	protected void setUser(User u,ResultSet rs) throws SQLException {
		u.setId(rs.getInt("id"));
		u.setFname(rs.getString("fname"));
		u.setLname(rs.getString("lname"));
		u.setUsername(rs.getString("username"));
		u.setEmail(rs.getString("email"));
		u.setPassword(rs.getString("password"));
		u.setRole(rs.getString("role"));
		u.setStatus(rs.getString("status"));
		u.setRememberToken(rs.getString("remember_token"));
		u.setUpdatedAt(rs.getString("updated_at"));
		u.setCreatedAt(rs.getString("created_at"));
	}

}
