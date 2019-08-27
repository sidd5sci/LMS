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
import com.frapwise.dao.Queries;
import com.frapwise.db.DB;
import com.frapwise.entities.Leave;
import com.frapwise.entities.User;
import com.frapwise.exceptions.LeaveException;
import com.frapwise.exceptions.UserException;
import com.frapwise.utils.Util;

public class LeaveModel implements LeaveDao,Queries{

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet result;
	private Statement smt;

	

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
			this.result = this.prep.getGeneratedKeys();
			if(result.next()) {
				flag = result.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			throw new LeaveException("Leave error occured");
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
		}catch(Exception e) {
			throw new LeaveException("Leave error occured");
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
		}catch(Exception e) {
			throw new LeaveException("Leave error occured");
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
		}catch(Exception e) {
			throw new LeaveException("Leave error occured");
		}
		return leaves;
	}
	
	public List<Leave> getLeaveByFilter(String from,String to,String status,Integer uid) throws ParseException, LeaveException{
	
		SimpleDateFormat format = new SimpleDateFormat("dd-M-yy");
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM leaves WHERE 0=0");
		
		
		if( !from.equals("") && from != null) {
			java.util.Date dfrom = format.parse(from);
			java.sql.Date sqlFrom = new java.sql.Date(dfrom.getTime());
			query.append(" AND leave_from >= '"+sqlFrom+"'");	
		}
		if( !to.equals("") && to != null) {
			java.util.Date dto = format.parse(to);
			java.sql.Date sqlTo = new java.sql.Date(dto.getTime());
			query.append(" AND leave_to <= '"+sqlTo+"'");
		}
		if( !status.equals("") && status != null ) {
			query.append(" AND status = '"+status+"'");
		}
		if( uid != 0 && uid != null) {
			query.append(" AND user_id = "+uid);
		}
		
		
		System.out.println("F:"+from+"\n"+query);
		List<Leave> leaves = new ArrayList<Leave>();
		try {
			this.smt = this.conn.createStatement();
			this.result = this.smt.executeQuery(query.toString());
			while(this.result.next()) {
				Leave l = new Leave();
				this.setLeave(l, this.result);
				leaves.add(l);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			throw new LeaveException("Leave error occured");
		}
		return leaves;
		
		
	}
	
	public int approve(int id) throws LeaveException {
		try {
			this.prep = this.conn.prepareStatement(APPROVE_LEAVE);
			this.prep.setString(1, "approved");
			this.prep.setInt(2, id);
			
			id = this.prep.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			throw new LeaveException("Leave error occured");
		}
		return id;
	}
	
	public int reject(int id) throws LeaveException {
		try {
			this.prep = this.conn.prepareStatement(REJECT_LEAVE);
			this.prep.setString(1, "rejected");
			this.prep.setInt(2, id);
			
			id = this.prep.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			throw new LeaveException("Leave error occured");
		}
		return id;
	}
	
	@Override
	public Leave getLeaveById(int id) throws LeaveException {
		// TODO Auto-generated method stub
		Leave leave = new Leave();
		try {
			this.prep = this.conn.prepareStatement(GET_LEAVE_BY_ID);
			this.prep.setInt(1, id);
			
			this.result = this.prep.executeQuery();
			while(this.result.next()) {
				this.setLeave(leave, this.result);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			throw new LeaveException("Leave error occured");
		}
		return leave;
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
	public List<Leave> getLeavesByUser(int uid) throws LeaveException {
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
		}catch(Exception e) {
			throw new LeaveException("Leave error occured");
		}
		return leaves;
	}
	public List<Leave> getLeavesByUserAndDate(int uid) throws LeaveException {
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
		}catch(Exception e) {
			throw new LeaveException("Leave error occured");
		}
		return leaves;
	}
	
	@Override
	public List<Leave> getLeavesByAppliedDate(String date) throws ParseException, LeaveException {
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
		}catch(Exception e) {
			throw new LeaveException("Leave error occured");
		}
		return leaves;
	}

	@Override
	public List<Leave> getLeavesInDates(String start, String end) throws ParseException, LeaveException {
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
		}catch(Exception e) {
			throw new LeaveException("Leave error occured");
		}
		return leaves;
	}

	
	public List<Leave> getLeavesToday(String start) throws ParseException, LeaveException {
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
		}catch(Exception e) {
			throw new LeaveException("Leave error occured");
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

	
	public int addBulkLeaves(List<Leave> leaves) throws LeaveException {
		try {
			
			for(Leave l :leaves) {
				
				
				this.prep = this.conn.prepareStatement(ADD_LEAVE);
				this.setParam(this.prep,l);
				this.prep.executeUpdate();
			}
			
		}catch(Exception e) {
			throw new LeaveException("Leave Exception occured "); 
		}
		
		return 0;
	}
	public int isExist(Leave l) {
		int flag = 1;
		try {
			this.prep = this.conn.prepareStatement(CHECH_IF_EXIST);
			this.prep.setInt(1, l.getUserId());
			this.prep.setDate(2, Util.jquery1ToSql(l.getLeaveFrom()) );
			this.prep.setDate(3, Util.jquery1ToSql(l.getLeaveTo()) );
			this.result = this.prep.executeQuery();
			System.out.println(this.prep.toString());
			if(result.next() == false) {
				
				System.out.println("ResultSet in empty"); 
				flag = 0;
			}
		
		}catch(SQLException e) {
			
		}
		return flag;
		
		
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
