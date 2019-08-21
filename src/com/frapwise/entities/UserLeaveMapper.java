package com.frapwise.entities;

public class UserLeaveMapper {

	private int id;
	private int uid;
	private int leaveTypeId;
	private int leaveMax;
	private int leaveTaken;
	private int leaveAvailible;
	private int timeDuration;
	private String leaveFrom;
	private String leaveTo;
	private String createdAt;
	
	public UserLeaveMapper() {
		
		this.id = 0;
		this.uid = 0;
		this.leaveTypeId = 0;
		this.leaveMax = 0;
		this.leaveTaken = 0;
		this.leaveAvailible = 0;
		this.timeDuration = 0;
		this.leaveFrom = "";
		this.leaveTo = "";
		this.createdAt = "";
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
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
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
	 * @return the leaveMax
	 */
	public int getLeaveMax() {
		return leaveMax;
	}

	/**
	 * @param leaveMax the leaveMax to set
	 */
	public void setLeaveMax(int leaveMax) {
		this.leaveMax = leaveMax;
	}

	/**
	 * @return the leaveTaken
	 */
	public int getLeaveTaken() {
		return leaveTaken;
	}

	/**
	 * @param leaveTaken the leaveTaken to set
	 */
	public void setLeaveTaken(int leaveTaken) {
		this.leaveTaken = leaveTaken;
	}

	/**
	 * @return the leaveAvailible
	 */
	public int getLeaveAvailible() {
		return leaveAvailible;
	}

	/**
	 * @param leaveAvailible the leaveAvailible to set
	 */
	public void setLeaveAvailible(int leaveAvailible) {
		this.leaveAvailible = leaveAvailible;
	}

	/**
	 * @return the timeDuration
	 */
	public int getTimeDuration() {
		return timeDuration;
	}

	/**
	 * @param timeDuration the timeDuration to set
	 */
	public void setTimeDuration(int timeDuration) {
		this.timeDuration = timeDuration;
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
	 * @return the createdAt
	 */
	public String getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + id;
		result = prime * result + leaveAvailible;
		result = prime * result + ((leaveFrom == null) ? 0 : leaveFrom.hashCode());
		result = prime * result + leaveMax;
		result = prime * result + leaveTaken;
		result = prime * result + ((leaveTo == null) ? 0 : leaveTo.hashCode());
		result = prime * result + leaveTypeId;
		result = prime * result + timeDuration;
		result = prime * result + uid;
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
		UserLeaveMapper other = (UserLeaveMapper) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (id != other.id)
			return false;
		if (leaveAvailible != other.leaveAvailible)
			return false;
		if (leaveFrom == null) {
			if (other.leaveFrom != null)
				return false;
		} else if (!leaveFrom.equals(other.leaveFrom))
			return false;
		if (leaveMax != other.leaveMax)
			return false;
		if (leaveTaken != other.leaveTaken)
			return false;
		if (leaveTo == null) {
			if (other.leaveTo != null)
				return false;
		} else if (!leaveTo.equals(other.leaveTo))
			return false;
		if (leaveTypeId != other.leaveTypeId)
			return false;
		if (timeDuration != other.timeDuration)
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserLeaveMapper [id=" + id + ", uid=" + uid + ", leaveTypeId=" + leaveTypeId + ", leaveMax=" + leaveMax
				+ ", leaveTaken=" + leaveTaken + ", leaveAvailible=" + leaveAvailible + ", timeDuration=" + timeDuration
				+ ", leaveFrom=" + leaveFrom + ", leaveTo=" + leaveTo + ", createdAt=" + createdAt + "]";
	}
	
	
	
}
