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

import com.frapwise.dao.Queries;
import com.frapwise.dao.UserLeaveMapperDao;
import com.frapwise.db.DB;
import com.frapwise.entities.Leave;
import com.frapwise.entities.UserLeaveMapper;
import com.frapwise.utils.Util;

public class UserLeaveMapperModel implements UserLeaveMapperDao,Queries{

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet result;
	


	

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
			this.prep = this.conn.prepareStatement(ADD_LEAVEMAPPER,Statement.RETURN_GENERATED_KEYS);
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
			this.prep = this.conn.prepareStatement(REMOVE_LEAVEMAPPER);
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
			this.prep = this.conn.prepareStatement(REMOVE_LEAVEMAPPER_BY_UID);
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
			this.prep = this.conn.prepareStatement(UPDATE_LEAVEMAPPER);
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
			this.prep = this.conn.prepareStatement(GET_ALL_LEAVEMAPPERS);
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
	public int setLeaveMaxById(int id,UserLeaveMapper ulm) throws ParseException {
		// TODO Auto-generated method stub
		UserLeaveMapper l = new UserLeaveMapper();
		Integer flag = 0;
		try {
			this.prep = this.conn.prepareStatement(UPDATE_LEAVEMAPPER_BY_ID);
			this.prep.setDate(1,Util.jqueryToSql(ulm.getLeaveFrom()));
			this.prep.setDate(2,Util.jqueryToSql(ulm.getLeaveTo()));
			this.prep.setInt(3, ulm.getLeaveMax());
			this.prep.setInt(4, id);
			flag = this.prep.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
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
	public int updateMaxLeavesByLeaveType(int lid,int inc) {
		Integer flag = 0;
		try {
			// increase the leaves by @param inc
			this.prep = this.conn.prepareStatement(UPDATE_LEAVEMAPPER_M_BY_LID);
			this.prep.setInt(1, inc);
			this.prep.setInt(2, lid);
			
			flag = this.prep.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
		
	}
	public int updateLeaveByUserAndLeaveType(int uid,int leaveType,int duration,String opration) {
		
		Integer flag = 0;
		try {
			if(opration.equals("+")) {
				this.prep = this.conn.prepareStatement(UPDATE_LEAVEMAPPER_P_BY_UID_L);
			}else {
				this.prep = this.conn.prepareStatement(UPDATE_LEAVEMAPPER_N_BY_UID_L);
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
	
	public UserLeaveMapper getLeavemapperByLeaveTypeAndUser(int uid,int leaveTypeId) {
		
		UserLeaveMapper ulm = new UserLeaveMapper();
		try {
			this.prep = this.conn.prepareStatement(GET_LEAVEMAPPERS_BY_UID_LEAVETYPE);
			this.prep.setInt(1, uid);
			this.prep.setInt(2,leaveTypeId);
			this.result = this.prep.executeQuery();
			while(this.result.next()) {
				this.setLeave(ulm, this.result);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ulm;
	}
	
	@Override
	public List<UserLeaveMapper> getLeaveMaxByUser(int uid) {
		// TODO Auto-generated method stub
		List<UserLeaveMapper> leaves = new ArrayList<UserLeaveMapper>();
		try {
			this.prep = this.conn.prepareStatement(GET_LEAVEMAPPER_BY_USER);
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
			this.prep = this.conn.prepareStatement(GET_LEAVEMAPPER_BY_USER);
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
	@Override
	public int setLeaveMaxByUser(int uid, int maxValue) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
