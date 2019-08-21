package com.frapwise.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.frapwise.dao.LeaveDao;
import com.frapwise.db.DB;
import com.frapwise.entities.Leave;
import com.frapwise.entities.User;

public class LeaveModel implements LeaveDao{

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet result;
	

	// Setters
	private final static String ADD_LEAVE	 			= "INSERT INTO leaves(id,user_id,department_id,leave_type_id,leave_from,leave_to,applied_date,time_off_type,status,approval) VALUES (null,?,?,?,?,?,?,?,?,?)";
	private final static String REMOVE_LEAVE			= "DELETE FROM leaves WHERE id = ?";
	private final static String UPDATE_LEAVE			= "UPDATE leaves SET user_id = ?, department_id = ?, leave_type_id = ?, leave_from = ?, leave_to = ?, applied_date = ?, time_off_type = ?, status = ?, approval = ? WHERE id = ?";
	// getters
	private final static String GET_LEAVE_BY_ID 		= "SELECT * FROM leaves WHERE id = ?";
	private final static String GET_ALL_LEAVES			= "SELECT * FROM leaves";
	private final static String GET_LEAVE_BY_TODAY		= "SELECT * FROM leaves WHERE leave_from = ?"; 
	private final static String GET_LEAVE_BY_APPLIED 	= "SELECT * FROM leaves WHERE applied_from = ?";
	private final static String GET_LEAVE_BY_DATES 		= "SELECT * FROM leaves WHERE leave_from > ? and leave_to < ?";
	private final static String GET_LEAVE_BY_USER		= "SELECT * FROM leaves WHERE user_id = ?";
	private final static String GET_LEAVE_BY_DEPARTMENT	= "SELECT * FROM leaves WHERE department_id = ?";
	private final static String GET_LEAVE_BY_LEAVETYPE	= "SELECT * FROM leaves WHERE leave_type_id = ?";
	

	public LeaveModel(){
		DB db = new DB();
		this.conn = db.getConnection();
		
	}
	
	@Override
	public Object add(Object o) throws Exception {
		// TODO Auto-generated method stub
		Leave l = (Leave) o;
		Integer flag = 0;
		try {
			this.prep = this.conn.prepareStatement(ADD_LEAVE,Statement.RETURN_GENERATED_KEYS);
			this.setParam(this.prep, l);
			flag = this.prep.executeUpdate();
	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Object remove(Object o) throws Exception {
		// TODO Auto-generated method stub
		Integer id = (Integer) o;
		Integer flag = 0;
		try {
			this.prep = this.conn.prepareStatement(REMOVE_LEAVE);
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
		Leave l = (Leave) o;
		Integer flag = 0;
		try {
			this.prep = this.conn.prepareStatement(UPDATE_LEAVE);
			this.setParam(this.prep,l);
			this.prep.setInt(10,l.getId());
			flag = this.prep.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Object> getAll() throws Exception {
		// TODO Auto-generated method stub
		
		List<Object> leaves = new ArrayList<Object>();
		try {
			this.prep = this.conn.prepareStatement(GET_ALL_LEAVES);
			this.result = this.prep.executeQuery();
			while(this.result.next()) {
				Leave l = new Leave();
				this.setLeave(l, this.result);
				leaves.add(l);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return leaves;
	}
	
	@Override
	public Leave getLeaveById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Leave> getLeavesByLeaveType(int leaveTypeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Leave> getLeavesByDepartment(int departmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Leave> getLeavesByUser(int uid) {
		// TODO Auto-generated method stub
		
		List<Leave> leaves = new ArrayList<Leave>();
		try {
			this.prep = this.conn.prepareStatement(GET_LEAVE_BY_USER);
		
			this.prep.setInt(1, uid);
			
			this.result = this.prep.executeQuery();
			while(this.result.next()) {
				Leave l = new Leave();
				this.setLeave(l, this.result);
				leaves.add(l);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return leaves;
	}

	
	@Override
	public List<Leave> getLeavesByAppliedDate(String date) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat( "yy-M-dd" );
		List<Leave> leaves = new ArrayList<Leave>();
		try {
			this.prep = this.conn.prepareStatement(GET_LEAVE_BY_APPLIED);
			
			java.util.Date fromDate = format.parse( date );
			java.sql.Date sqlFromDate = new java.sql.Date( fromDate.getTime() );
			this.prep.setDate(1, sqlFromDate);
			
			this.result = this.prep.executeQuery();
			while(this.result.next()) {
				Leave l = new Leave();
				this.setLeave(l, this.result);
				leaves.add(l);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return leaves;
	}

	@Override
	public List<Leave> getLeavesInDates(String start, String end) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat( "yy-M-dd" );
		List<Leave> leaves = new ArrayList<Leave>();
		try {
			this.prep = this.conn.prepareStatement(GET_LEAVE_BY_DATES);
			
			java.util.Date fromDate = format.parse( start );
			java.sql.Date sqlFromDate = new java.sql.Date( fromDate.getTime() );
			this.prep.setDate(1, sqlFromDate);
			java.util.Date toDate = format.parse( end );
			java.sql.Date sqlToDate = new java.sql.Date( toDate.getTime() );
			this.prep.setDate(2, sqlToDate);
			this.result = this.prep.executeQuery();
			while(this.result.next()) {
				Leave l = new Leave();
				this.setLeave(l, this.result);
				leaves.add(l);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return leaves;
	}

	
	public List<Leave> getLeavesToday(String start) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println(start);
		SimpleDateFormat format = new SimpleDateFormat( "yy-M-dd" );
		List<Leave> leaves = new ArrayList<Leave>();
		try {
			this.prep = this.conn.prepareStatement(GET_LEAVE_BY_TODAY);
			
			java.util.Date fromDate = format.parse( start );
			java.sql.Date sqlFromDate = new java.sql.Date( fromDate.getTime() );
			this.prep.setDate(1, sqlFromDate);
			System.out.println(this.prep.toString());
			this.result = this.prep.executeQuery();
			while(this.result.next()) {
				Leave l = new Leave();
				this.setLeave(l, this.result);
				leaves.add(l);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return leaves;
	}
	@Override
	public List<Leave> getLeavesByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Leave> getLeavesByApproval(int approval) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void setParam(PreparedStatement stmt,Leave l) throws SQLException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat( "dd-M-yy" );
//		SimpleDateFormat formatTime = new SimpleDateFormat( "yy-mm-dd HH:mm:ss" );
		stmt.setInt(1, l.getUserId());
		stmt.setInt(2, l.getDepartmentId());
		stmt.setInt(3, l.getLeaveTypeId());
		java.util.Date fromDate = format.parse( l.getLeaveFrom() );
		java.sql.Date sqlFromDate = new java.sql.Date( fromDate.getTime() );
		stmt.setDate(4, sqlFromDate);
		java.util.Date toDate = format.parse( l.getLeaveTo() );
		java.sql.Date sqlToDate = new java.sql.Date( toDate.getTime() );
		stmt.setDate(5, sqlToDate);
		java.util.Date appliedDate = new java.util.Date();
		java.sql.Date sqlAppliedDate = new java.sql.Date( appliedDate.getTime() );
		stmt.setDate(6, sqlAppliedDate);
		stmt.setInt(7, l.getTimeOffType());
		stmt.setString(8, "pending");
		stmt.setInt(9, 0);
		
		System.out.println(stmt.toString());
	}
	
	protected void setLeave(Leave l,ResultSet rs) throws SQLException {
		l.setId(rs.getInt("id"));
		l.setUserId(rs.getInt("user_id"));
		l.setLeaveTypeId(rs.getInt("leave_type_id"));
		l.setDepartmentId(rs.getInt("department_id"));
		l.setLeaveFrom(rs.getString("leave_from"));
		l.setLeaveTo(rs.getString("leave_to"));
		l.setAppliedDate(rs.getString("applied_date"));
		l.setTimeOffType(rs.getInt("time_off_type"));
		l.setStatus(rs.getString("status"));
		l.setApproval(rs.getInt("approval"));
		
	}


}
