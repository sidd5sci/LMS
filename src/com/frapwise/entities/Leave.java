package com.frapwise.entities;

public class Leave {

	private int id;
	private int userId;
	private int departmentId;
	private int leaveTypeId;
	private String leaveFrom;
	private String leaveTo;
	private String appliedDate;
	private int timeOffType;
	private String status;
	private int approval;
	
	public Leave() {
		this.id = 0;
		this.userId = 0;
		this.departmentId = 0;
		this.leaveTypeId = 0;
		this.leaveFrom = "";
		this.leaveTo = "";
		this.appliedDate = "";
		this.timeOffType = 0;
		this.status = "";
		this.approval = 0;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the departmentId
	 */
	public int getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the leaveTypeId
	 */
	public int getLeaveTypeId() {
		return leaveTypeId;
	}

	/**
	 * @param leaveTypeId the leaveTypeId to set
	 */
	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	/**
	 * @return the leaveFrom
	 */
	public String getLeaveFrom() {
		return leaveFrom;
	}

	/**
	 * @param leaveFrom the leaveFrom to set
	 */
	public void setLeaveFrom(String leaveFrom) {
		this.leaveFrom = leaveFrom;
	}

	/**
	 * @return the leaveTo
	 */
	public String getLeaveTo() {
		return leaveTo;
	}

	/**
	 * @param leaveTo the leaveTo to set
	 */
	public void setLeaveTo(String leaveTo) {
		this.leaveTo = leaveTo;
	}

	/**
	 * @return the appliedDate
	 */
	public String getAppliedDate() {
		return appliedDate;
	}

	/**
	 * @param appliedDate the appliedDate to set
	 */
	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	/**
	 * @return the timeOffType
	 */
	public int getTimeOffType() {
		return timeOffType;
	}

	/**
	 * @param timeOffType the timeOffType to set
	 */
	public void setTimeOffType(int timeOffType) {
		this.timeOffType = timeOffType;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the approval
	 */
	public int getApproval() {
		return approval;
	}

	/**
	 * @param approval the approval to set
	 */
	public void setApproval(int approval) {
		this.approval = approval;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appliedDate == null) ? 0 : appliedDate.hashCode());
		result = prime * result + approval;
		result = prime * result + departmentId;
		result = prime * result + id;
		result = prime * result + ((leaveFrom == null) ? 0 : leaveFrom.hashCode());
		result = prime * result + ((leaveTo == null) ? 0 : leaveTo.hashCode());
		result = prime * result + leaveTypeId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + timeOffType;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Leave other = (Leave) obj;
		if (appliedDate == null) {
			if (other.appliedDate != null)
				return false;
		} else if (!appliedDate.equals(other.appliedDate))
			return false;
		if (approval != other.approval)
			return false;
		if (departmentId != other.departmentId)
			return false;
		if (id != other.id)
			return false;
		if (leaveFrom == null) {
			if (other.leaveFrom != null)
				return false;
		} else if (!leaveFrom.equals(other.leaveFrom))
			return false;
		if (leaveTo == null) {
			if (other.leaveTo != null)
				return false;
		} else if (!leaveTo.equals(other.leaveTo))
			return false;
		if (leaveTypeId != other.leaveTypeId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (timeOffType != other.timeOffType)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Leave [id=" + id + ", userId=" + userId + ", departmentId=" + departmentId + ", leaveTypeId="
				+ leaveTypeId + ", leaveFrom=" + leaveFrom + ", leaveTo=" + leaveTo + ", appliedDate=" + appliedDate
				+ ", timeOffType=" + timeOffType + ", status=" + status + ", approval=" + approval + "]";
	}

	
	
}
