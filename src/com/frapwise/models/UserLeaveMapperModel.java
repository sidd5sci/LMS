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

import com.frapwise.dao.UserLeaveMapperDao;
import com.frapwise.db.DB;
import com.frapwise.entities.Leave;
import com.frapwise.entities.UserLeaveMapper;

public class UserLeaveMapperModel implements UserLeaveMapperDao{

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet result;
	


	// Setters
	private final static String ADD_LEAVE	 			= "INSERT INTO user_leave_maper(id,uid,leave_type_id,leave_max,leave_taken,leave_availible,time_duration,assigned_from,assigned_to) VALUES (null,?,?,?,?,?,?,?,?)";
	private final static String REMOVE_LEAVE			= "DELETE FROM user_leave_maper WHERE id = ?";
	private final static String UPDATE_LEAVE			= "UPDATE user_leave_maper SET uid = ?, leave_type_id = ?, leave_max = ?, leave_taken = ?, leave_availible = ?, time_duration = ?, assigned_from,assigned_to = ? WHERE id = ?";
	private final static String REMOVE_LEAVE_BY_UID		= "DELETE FROM user_leave_maper WHERE uid = ?";
	private final static String UPDATE_LEAVE_N_BY_UID_L = "UPDATE user_leave_maper SET leave_taken = leave_taken - ?, leave_availible = leave_availible + ? WHERE uid = ? and leave_type_id = ? ";
	private final static String UPDATE_LEAVE_P_BY_UID_L	= "UPDATE user_leave_maper SET leave_taken = leave_taken + ?, leave_availible = leave_availible - ? WHERE uid = ? and leave_type_id = ? ";
	// getters
	private final static String GET_LEAVE_BY_ID 		= "SELECT * FROM user_leave_maper WHERE id = ?";
	private final static String GET_ALL_LEAVES			= "SELECT * FROM user_leave_maper";
	private final static String GET_LEAVE_BY_TODAY		= "SELECT * FROM user_leave_maper WHERE assigned_from = ?"; 
	private final static String GET_LEAVE_BY_APPLIED 	= "SELECT * FROM user_leave_maper WHERE assigned_from = ?";
	private final static String GET_LEAVE_BY_DATES 		= "SELECT * FROM user_leave_maper WHERE assigned_from > ? and assigned_to < ?";
	private final static String GET_LEAVE_BY_USER		= "SELECT * FROM user_leave_maper WHERE uid = ?";
	private final static String GET_LEAVE_BY_DEPARTMENT	= "SELECT * FROM user_leave_maper WHERE department_id = ?";
	private final static String GET_LEAVE_BY_LEAVETYPE	= "SELECT * FROM user_leave_maper WHERE leave_type_id = ?";
	

	public UserLeaveMapperModel(){
		DB db = new DB();
		this.conn = db.getConnection();
		
	}
	@Override
	public Object add(Object o) throws Exception {
		// TODO Auto-generated method stub
		UserLeaveMapper l = (UserLeaveMapper) o;
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
	public int removeByUid(int uid) {
		
		Integer flag = 0;
		try {
			this.prep = this.conn.prepareStatement(REMOVE_LEAVE_BY_UID);
			this.prep.setInt(1, uid);
			flag = this.prep.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public Object update(Object o) throws Exception {
		// TODO Auto-generated method stub
		UserLeaveMapper l = (UserLeaveMapper) o;
		Integer flag = 0;
		try {
			this.prep = this.conn.prepareStatement(UPDATE_LEAVE);
			this.setParam(this.prep,l);
			this.prep.setInt(9,l.getId());
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
				UserLeaveMapper l = new UserLeaveMapper();
				this.setLeave(l, this.result);
				leaves.add(l);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return leaves;
	}

	@Override
	public int setLeaveMaxByUser(int uid, int maxValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setLeaveTakenByUser(int uid, int takenValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setLeaveAvailibleByUser(int uid, int availibleValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setTimeDurationByUser(int uid, int duration) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setTimeDurationByLeaveType(int leaveType, int duration) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setAssignedTimes(int uid, String from, String to) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateLeaveByUserAndLeaveType(int uid,int leaveType,int duration,String opration) {
		
		Integer flag = 0;
		try {
			if(opration.equals("+")) {
				this.prep = this.conn.prepareStatement(UPDATE_LEAVE_P_BY_UID_L);
			}else {
				this.prep = this.conn.prepareStatement(UPDATE_LEAVE_N_BY_UID_L);
			}
			
			this.prep.setInt(1, duration);
			this.prep.setInt(2, duration);
			this.prep.setInt(3, uid);
			this.prep.setInt(4, leaveType);
			
			flag = this.prep.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	@Override
	public List<UserLeaveMapper> getLeaveMaxByUser(int uid) {
		// TODO Auto-generated method stub
		List<UserLeaveMapper> leaves = new ArrayList<UserLeaveMapper>();
		try {
			this.prep = this.conn.prepareStatement(GET_LEAVE_BY_USER);
			this.prep.setInt(1, uid);
			this.result = this.prep.executeQuery();
			while(this.result.next()) {
				UserLeaveMapper l = new UserLeaveMapper();
				this.setLeave(l, this.result);
				leaves.add(l);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return leaves;
	}
	
	@Override
	public List<UserLeaveMapper> getLeaveByUser(int uid) {
		// TODO Auto-generated method stub
		List<UserLeaveMapper> leaves = new ArrayList<UserLeaveMapper>();
		try {
			this.prep = this.conn.prepareStatement(GET_LEAVE_BY_USER);
			this.prep.setInt(1, uid);
			this.result = this.prep.executeQuery();
			while(this.result.next()) {
				UserLeaveMapper l = new UserLeaveMapper();
				this.setLeave(l, this.result);
				leaves.add(l);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return leaves;
	}
	
	@Override
	public List<UserLeaveMapper> getLeaveTakenByUser(int uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserLeaveMapper> getLeaveAvailibleByUser(int uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserLeaveMapper> getLeaveMaxByUserAndLeaveType(int uid, int leaveType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserLeaveMapper> getLeaveTakenByUserAndLeaveType(int uid, int leaveType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserLeaveMapper> getLeaveAvailibleByUserAndLeaveType(int uid, int leaveType) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void setParam(PreparedStatement stmt,UserLeaveMapper l) throws SQLException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat( "dd-M-yy" );
		
//		SimpleDateFormat formatTime = new SimpleDateFormat( "yy-mm-dd HH:mm:ss" );
		stmt.setInt(1, l.getUid());
		stmt.setInt(2, l.getLeaveTypeId());
		stmt.setInt(3, l.getLeaveMax());
		stmt.setInt(4, l.getLeaveTaken());
		stmt.setInt(5, l.getLeaveAvailible());
		stmt.setInt(6, l.getTimeDuration());
		java.util.Date fromDate = format.parse( l.getLeaveFrom() );
		java.sql.Date sqlFromDate = new java.sql.Date( fromDate.getTime() );
		stmt.setDate(7, sqlFromDate);
		java.util.Date toDate = format.parse( l.getLeaveTo() );
		java.sql.Date sqlToDate = new java.sql.Date( toDate.getTime() );
		stmt.setDate(8, sqlToDate);
		
		
		System.out.println(stmt.toString());
	}
	
	protected void setLeave(UserLeaveMapper l,ResultSet rs) throws SQLException {
		l.setId(rs.getInt("id"));
		l.setUid(rs.getInt("uid"));
		l.setLeaveTypeId(rs.getInt("leave_type_id"));
		l.setLeaveMax(rs.getInt("leave_max"));
		l.setLeaveTaken(rs.getInt("leave_taken"));
		l.setLeaveAvailible(rs.getInt("leave_availible"));
		l.setTimeDuration(rs.getInt("time_duration"));
		l.setLeaveFrom(rs.getString("assigned_from"));
		l.setLeaveTo(rs.getString("assigned_to"));
		l.setCreatedAt(rs.getString("created_at"));
	}
	
}
