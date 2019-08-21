package com.frapwise.entities;

public class OfficialLeave {

	private int id;
	private String name;
	private String date;
	private String description;
	private String departmentIds;
	
	public OfficialLeave() {
		this.id = 0;
		this.name = "";
		this.date = "";
		this.description = "";
		this.departmentIds = "";
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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
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
	 * @return the departmentIds
	 */
	public String getDepartmentIds() {
		return departmentIds;
	}

	/**
	 * @param departmentIds the departmentIds to set
	 */
	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((departmentIds == null) ? 0 : departmentIds.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		OfficialLeave other = (OfficialLeave) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (departmentIds == null) {
			if (other.departmentIds != null)
				return false;
		} else if (!departmentIds.equals(other.departmentIds))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OfficialLeave [id=" + id + ", name=" + name + ", date=" + date + ", description=" + description
				+ ", departmentIds=" + departmentIds + "]";
	}
	
	
}
