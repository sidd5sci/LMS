package com.frapwise.entities;

public class LeaveType {

	private int id;
	private String name;
	private int defaultValue;
	private String description;
	private int period;
	private String lastRenewDate;
	private String status;
	
	public LeaveType() {
		this.id = 0;
		this.name = "";
		this.defaultValue = 0;
		this.description = "";
		this.period = 0;
		this.lastRenewDate = "";
		this.status = "";
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the defaultValue
	 */
	public int getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(int defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the period
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * @param period the period to set
	 */
	public void setPeriod(int period) {
		this.period = period;
	}

	/**
	 * @return the lastRenewDate
	 */
	public String getLastRenewDate() {
		return lastRenewDate;
	}

	/**
	 * @param lastRenewDate the lastRenewDate to set
	 */
	public void setLastRenewDate(String lastRenewDate) {
		this.lastRenewDate = lastRenewDate;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + defaultValue;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastRenewDate == null) ? 0 : lastRenewDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + period;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		LeaveType other = (LeaveType) obj;
		if (defaultValue != other.defaultValue)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (lastRenewDate == null) {
			if (other.lastRenewDate != null)
				return false;
		} else if (!lastRenewDate.equals(other.lastRenewDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (period != other.period)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LeaveType [id=" + id + ", name=" + name + ", defaultValue=" + defaultValue + ", description="
				+ description + ", period=" + period + ", lastRenewDate=" + lastRenewDate + ", status=" + status + "]";
	}


	
	
}
