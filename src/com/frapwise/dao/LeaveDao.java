package com.frapwise.dao;

import java.text.ParseException;
import java.util.List;

import com.frapwise.entities.Leave;
import com.frapwise.exceptions.LeaveException;

public interface LeaveDao extends Dao{

	/**
	 * get leaves by id
	 * @param id
	 * @return
	 * @throws LeaveException 
	 */
	public Leave getLeaveById(int id) throws LeaveException;
	/**
	 * returns the leaves by leave type id
	 * @param leaveTypeId
	 * @return
	 */
	public List<Leave> getLeavesByLeaveType(int leaveTypeId);
	/**
	 * returns leaves by department id
	 * @param departmentId
	 * @return
	 */
	public List<Leave> getLeavesByDepartment(int departmentId);
	/**
	 * returns the leaves by user user id
	 * @param uid
	 * @return
	 * @throws LeaveException 
	 */
	public List<Leave> getLeavesByUser(int uid) throws LeaveException;
	/**
	 * returns the leaves by applied date
	 * @param date
	 * @return
	 * @throws ParseException 
	 * @throws LeaveException 
	 */
	public List<Leave> getLeavesByAppliedDate(String date) throws ParseException, LeaveException;
	/**
	 * returns the leaves by date range
	 * @param start
	 * @param end
	 * @return
	 * @throws ParseException 
	 * @throws LeaveException 
	 */
	public List<Leave> getLeavesInDates(String start, String end) throws ParseException, LeaveException;
	/**
	 * returns the leaves by status 
	 * @param status
	 * @return
	 */
	public List<Leave> getLeavesByStatus(String status);
	/**
	 * returns the leaves by approval
	 * @param approval
	 * @return
	 */
	public List<Leave> getLeavesByApproval(int approval);
	
}
