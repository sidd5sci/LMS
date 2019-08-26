package com.frapwise.entities;

public class Sheet {

	private int id;
	private Object sheet;
	private String createdAt;
	
	
	public Sheet() {
		
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
	 * @return the sheet
	 */
	public Object getSheet() {
		return sheet;
	}


	/**
	 * @param sheet the sheet to set
	 */
	public void setSheet(Object sheet) {
		this.sheet = sheet;
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
		result = prime * result + ((sheet == null) ? 0 : sheet.hashCode());
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
		Sheet other = (Sheet) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (id != other.id)
			return false;
		if (sheet == null) {
			if (other.sheet != null)
				return false;
		} else if (!sheet.equals(other.sheet))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Sheet [id=" + id + ", sheet=" + sheet + ", createdAt=" + createdAt + "]";
	}
	
	
	
}
