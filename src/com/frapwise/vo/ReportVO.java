package com.frapwise.vo;

public class ReportVO {

	private String leaveRequest;
	private String employeeName;
	private String homeOffice;
	private String leaveType;
	private String departmentCode;
	private String typeOfTimeOfF;
	private String dates;
	private String createdBy;
	private String createdWhen;
	private String approvalStatus;
	private String duration;
	
	public ReportVO() {
		
		this.leaveRequest = "";
		this.employeeName = "";
		this.homeOffice = "";
		this.leaveType = "";
		this.departmentCode = "";
		this.typeOfTimeOfF = "";
		this.dates = "";
		this.createdWhen = "";
		this.approvalStatus = "";
		this.duration = "";
	}

	/**
	 * @return the leaveRequest
	 */
	public String getLeaveRequest() {
		return leaveRequest;
	}

	/**
	 * @param leaveRequest the leaveRequest to set
	 */
	public void setLeaveRequest(String leaveRequest) {
		this.leaveRequest = leaveRequest;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the homeOffice
	 */
	public String getHomeOffice() {
		return homeOffice;
	}

	/**
	 * @param homeOffice the homeOffice to set
	 */
	public void setHomeOffice(String homeOffice) {
		this.homeOffice = homeOffice;
	}

	/**
	 * @return the leaveType
	 */
	public String getLeaveType() {
		return leaveType;
	}

	/**
	 * @param leaveType the leaveType to set
	 */
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	/**
	 * @return the departmentCode
	 */
	public String getDepartmentCode() {
		return departmentCode;
	}

	/**
	 * @param departmentCode the departmentCode to set
	 */
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	/**
	 * @return the typeOfTimeOfF
	 */
	public String getTypeOfTimeOfF() {
		return typeOfTimeOfF;
	}

	/**
	 * @param typeOfTimeOfF the typeOfTimeOfF to set
	 */
	public void setTypeOfTimeOfF(String typeOfTimeOfF) {
		this.typeOfTimeOfF = typeOfTimeOfF;
	}

	/**
	 * @return the dates
	 */
	public String getDates() {
		return dates;
	}

	/**
	 * @param dates the dates to set
	 */
	public void setDates(String dates) {
		this.dates = dates;
	}

	/**
	 * @return the createdWhen
	 */
	public String getCreatedWhen() {
		return createdWhen;
	}

	/**
	 * @param createdWhen the createdWhen to set
	 */
	public void setCreatedWhen(String createdWhen) {
		this.createdWhen = createdWhen;
	}

	/**
	 * @return the approvalStatus
	 */
	public String getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * @param approvalStatus the approvalStatus to set
	 */
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approvalStatus == null) ? 0 : approvalStatus.hashCode());
		result = prime * result + ((createdWhen == null) ? 0 : createdWhen.hashCode());
		result = prime * result + ((dates == null) ? 0 : dates.hashCode());
		result = prime * result + ((departmentCode == null) ? 0 : departmentCode.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		result = prime * result + ((homeOffice == null) ? 0 : homeOffice.hashCode());
		result = prime * result + ((leaveRequest == null) ? 0 : leaveRequest.hashCode());
		result = prime * result + ((leaveType == null) ? 0 : leaveType.hashCode());
		result = prime * result + ((typeOfTimeOfF == null) ? 0 : typeOfTimeOfF.hashCode());
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
		ReportVO other = (ReportVO) obj;
		if (approvalStatus == null) {
			if (other.approvalStatus != null)
				return false;
		} else if (!approvalStatus.equals(other.approvalStatus))
			return false;
		if (createdWhen == null) {
			if (other.createdWhen != null)
				return false;
		} else if (!createdWhen.equals(other.createdWhen))
			return false;
		if (dates == null) {
			if (other.dates != null)
				return false;
		} else if (!dates.equals(other.dates))
			return false;
		if (departmentCode == null) {
			if (other.departmentCode != null)
				return false;
		} else if (!departmentCode.equals(other.departmentCode))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (employeeName == null) {
			if (other.employeeName != null)
				return false;
		} else if (!employeeName.equals(other.employeeName))
			return false;
		if (homeOffice == null) {
			if (other.homeOffice != null)
				return false;
		} else if (!homeOffice.equals(other.homeOffice))
			return false;
		if (leaveRequest == null) {
			if (other.leaveRequest != null)
				return false;
		} else if (!leaveRequest.equals(other.leaveRequest))
			return false;
		if (leaveType == null) {
			if (other.leaveType != null)
				return false;
		} else if (!leaveType.equals(other.leaveType))
			return false;
		if (typeOfTimeOfF == null) {
			if (other.typeOfTimeOfF != null)
				return false;
		} else if (!typeOfTimeOfF.equals(other.typeOfTimeOfF))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReportVO [leaveRequest=" + leaveRequest + ", employeeName=" + employeeName + ", homeOffice="
				+ homeOffice + ", leaveType=" + leaveType + ", departmentCode=" + departmentCode + ", typeOfTimeOfF="
				+ typeOfTimeOfF + ", dates=" + dates + ", createdWhen=" + createdWhen + ", approvalStatus="
				+ approvalStatus + ", duration=" + duration + "]";
	}





	
}
