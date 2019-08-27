package com.frapwise.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.frapwise.dao.LeaveTypeDao;
import com.frapwise.dao.Queries;
import com.frapwise.db.DB;
import com.frapwise.entities.Department;
import com.frapwise.entities.LeaveType;
import com.frapwise.exceptions.DepartmentException;
import com.frapwise.exceptions.LeaveTypeException;
import com.frapwise.utils.Util;

public class LeaveTypeModel implements LeaveTypeDao,Queries{

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet result;

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
				this.prep.setInt(2, dpt.getDefaultValue());
				this.prep.setString(3,dpt.getDescription());
				this.prep.setInt(4,dpt.getPeriod());
				
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
			this.prep.setInt(2, d.getDefaultValue());
			this.prep.setString(3, d.getDescription());
			this.prep.setInt(4, d.getPeriod());
			this.prep.setInt(5,d.getId());
			flag = this.prep.executeUpdate();
		}catch(SQLException e) {
			throw new LeaveTypeException("Leavetype exception occured");
		}
		catch(Exception e) {
			throw new LeaveTypeException("Leavetype exception occured");
		}
		return flag;
	}
	public void checkLeaveTypeRenew(int id) throws LeaveTypeException, ParseException {
		
		LeaveType l = this.getLeaveTypeById(id);
		
		
		if(Util.compareDatesWithPeriod(l.getLastRenewDate(),l.getPeriod()) == 1){
			this.updateStatus(l.getId(), "expired");
			System.out.println("CHECKED:EXPIRED");
		}
		
		
	}
	
	public int renewLeaveType(int id) throws LeaveTypeException, ParseException {
		LeaveType l = this.getLeaveTypeById(id);
		System.out.println(l.toString());
		SimpleDateFormat format = new SimpleDateFormat("yy-M-dd");
		Calendar c = Calendar.getInstance();
		
		java.util.Date newDate = format.parse(l.getLastRenewDate());

		c.setTime(newDate);
		c.add(Calendar.MONTH, l.getPeriod());
		
		
		java.sql.Date sqlNewDate = new java.sql.Date(c.getTimeInMillis());
		
		Integer flag = 0;
		try {
			this.prep = this.conn.prepareStatement(UPDATE_LEAVETYPE_RENEW);
			this.prep.setDate(1,sqlNewDate);
			this.prep.setInt(2,id);
			flag = this.prep.executeUpdate();
			this.updateStatus(id,"running");
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
				//
				d.setId(this.result.getInt("id"));
				d.setName(this.result.getString("name"));
				d.setDefaultValue(this.result.getInt("default_value"));
				d.setDescription(this.result.getString("description"));
				d.setPeriod(this.result.getInt("period"));
				d.setLastRenewDate(this.result.getString("last_renew_date"));
				d.setStatus(this.result.getString("status"));
				leavetypes.add(d);
				this.checkLeaveTypeRenew(d.getId());
			}
		}catch(SQLException e) {
			//e.printStackTrace();
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
		ResultSet result1 = null;
		LeaveType dpt = new LeaveType();
		try {
			this.prep = this.conn.prepareStatement(GET_LEAVETYPE_BY_ID);
			this.prep.setInt(1, id);
			result1 = this.prep.executeQuery();
			while(result1.next()) {
				dpt.setId(result1.getInt("id"));
				dpt.setName(result1.getString("name"));
				dpt.setDefaultValue(result1.getInt("default_value"));
				dpt.setDescription(result1.getString("description"));
				dpt.setPeriod(result1.getInt("period"));
				dpt.setLastRenewDate(result1.getString("last_renew_date"));
				dpt.setStatus(result1.getString("status"));
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
	
	public int updateStatus(int id,String status) throws LeaveTypeException {
		
		Integer flag = 0;
		try {
			this.prep = this.conn.prepareStatement(UPDATE_LEAVETYPE_STATUS);
			this.prep.setString(1,status);
			this.prep.setInt(2,id);
			flag = this.prep.executeUpdate();
		}catch(SQLException e) {
			throw new LeaveTypeException("Leavetype exception occured");
		}
		catch(Exception e) {
			throw new LeaveTypeException("Leavetype exception occured");
		}
		return flag;
		
	}
	
	
	public int getLeaveTypeIdByName(String name) throws LeaveTypeException {
		
		
		int id=0;
		try {
			this.prep = this.conn.prepareStatement(GET_LEAVETYPE_ID_BY_NAME);
			this.prep.setString(1, name);
			result = this.prep.executeQuery();
			while(result.next()) {
				id = result.getInt("id");
				
			}
		}catch(SQLException e) {
			throw new LeaveTypeException("Leavetype exception occured");
		}
		catch(Exception e) {
			throw new LeaveTypeException("Leavetype exception occured");
		}
		return id;
	}

}
