package com.frapwise.vo;

public class LeaveMapperVO {

	private String userName;
	private String leaveTypeName;
	private int taken;
	private int availible;
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the leaveTypeName
	 */
	public String getLeaveTypeName() {
		return leaveTypeName;
	}
	/**
	 * @param leaveTypeName the leaveTypeName to set
	 */
	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}
	/**
	 * @return the taken
	 */
	public int getTaken() {
		return taken;
	}
	/**
	 * @param taken the taken to set
	 */
	public void setTaken(int taken) {
		this.taken = taken;
	}
	/**
	 * @return the availible
	 */
	public int getAvailible() {
		return availible;
	}
	/**
	 * @param availible the availible to set
	 */
	public void setAvailible(int availible) {
		this.availible = availible;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + availible;
		result = prime * result + ((leaveTypeName == null) ? 0 : leaveTypeName.hashCode());
		result = prime * result + taken;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		LeaveMapperVO other = (LeaveMapperVO) obj;
		if (availible != other.availible)
			return false;
		if (leaveTypeName == null) {
			if (other.leaveTypeName != null)
				return false;
		} else if (!leaveTypeName.equals(other.leaveTypeName))
			return false;
		if (taken != other.taken)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "LeaveMapperVO [userName=" + userName + ", leaveTypeName=" + leaveTypeName + ", taken=" + taken
				+ ", availible=" + availible + "]";
	}

	
}
