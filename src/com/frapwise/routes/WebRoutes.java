package com.frapwise.routes;

public interface WebRoutes {

public final static String HOME 					= "home.htm";
	
	// auth routes
	public final static String LOGIN 					= "login.htm";
	public final static String AUHENTICATE 				= "login-db.htm";
	public final static String LOGOUT 					= "logout.htm";
	
	// Employee routes
	public final static String EMPLOYEE_DASHBOARD 		= "employee-dashboard.htm";
		// leaves
		public final static String EMPLOYEE_ADD_LEAVE		 	= "employee-add-leave.htm";
		public final static String EMPLOYEE_ADD_LEAVE_DB	 	= "employee-add-leave-db.htm";
		public final static String EMPLOYEE_ALL_LEAVES		 	= "employee-all-leaves.htm";
		public final static String EMPLOYEE_STATUS_LEAVE	 	= "employee-status-leave.htm";
		public final static String EMPLOYEE_EDIT_LEAVE		 	= "employee-edit-leave.htm";
		public final static String EMPLOYEE_EDIT_LEAVE_DB	 	= "employee-edit-leave-db.htm";
		public final static String EMPLOYEE_REMOVE_LEAVE	 	= "employee-remove-leave.htm";
		public final static String EMPLOYEE_PENDING_LEAVE	 	= "employee-pending-leave.htm";
		public final static String EMPLOYEE_APROVED_LEAVE	 	= "employee-aproved-leave.htm";
		public final static String EMPLOYEE_APPLY_FILTERS	 	= "employee-appy-filter.htm";
	
	
	// Admin routes
	public final static String ADMIN_DASHBOARD 			= "admin-dashboard.htm";
		// department
		public final static String ADMIN_ADD_DEPARTMENT	 		= "admin-add-department.htm";
	    public final static String ADMIN_ADD_DEPARTMENT_DB	 	= "admin-add-department-db.htm";
		public final static String ADMIN_ALL_DEPARTMENT	 		= "admin-all-department.htm";
		public final static String ADMIN_EDIT_DEPARTMENT	 	= "admin-edit-department.htm";
		public final static String ADMIN_EDIT_DEPARTMENT_DB 	= "admin-edit-department-db.htm";
		public final static String ADMIN_REMOVE_DEPARTMENT	 	= "admin-remove-department.htm";
		// leave types
		public final static String ADMIN_ADD_LEAVETYPE		 	= "admin-add-leavetype.htm";
		public final static String ADMIN_ADD_LEAVETYPE_DB	 	= "admin-add-leavetype-db.htm";
		public final static String ADMIN_ALL_LEAVETYPE		 	= "admin-all-leavetype.htm";
		public final static String ADMIN_EDIT_LEAVETYPE	 		= "admin-edit-leavetype.htm";
		public final static String ADMIN_EDIT_LEAVETYPE_DB	 	= "admin-edit-leavetype-db.htm";
		public final static String ADMIN_REMOVE_LEAVETYPE   	= "admin-remove-leavetype.htm";
		// employees
		public final static String ADMIN_ADD_EMPLOYEE	    	= "admin-add-employee.htm";
		public final static String ADMIN_ADD_EMPLOYEE_DB	 	= "admin-add-employee-db.htm";
		public final static String ADMIN_ALL_EMPLOYEE		 	= "admin-all-employee.htm";
		public final static String ADMIN_EDIT_EMPLOYEE		 	= "admin-edit-employee.htm";
		public final static String ADMIN_EDIT_EMPLOYEE_DB	 	= "admin-edit-employee-db.htm";
		public final static String ADMIN_REMOVE_EMPLOYEE	 	= "admin-remove-employee.htm";
		public final static String ADMIN_EMPLOYEE_FILTER	 	= "";
		// leave
		public final static String ADMIN_ADD_LEAVE 		 	 	= "admin-add-leave.htm";
		public final static String ADMIN_ADD_LEAVE_DB 		 	= "admin-add-leave-db.htm";
		public final static String ADMIN_ALL_LEAVE			 	= "admin-all-leaves.htm";
		public final static String ADMIN_TODAYS_LEAVES		 	= "admin-today-leaves.htm";
		public final static String ADMIN_LEAVE_APPROVAL 	 	= "admin-leave-approve.htm";
		public final static String ADMIN_LEAVE_REJECT		 	= "admin-reject-leave.htm";
		public final static String ADMIN_ASSIGN_LEAVES		 	= "admin-assign-leaves.htm";
		public final static String ADMIN_ASSIGN_LEAVES_DB	 	= "admin-assign-leave-db.htm";
		public final static String ADMIN_STATUS_LEAVE		 	= "admin-max-leaves-status.htm";
		public final static String ADMIN_APPLY_FILTERS		 	= "admin-apply-filter.htm";
		public final static String ADMIN_UPLOAD_FILE		 	= "admin-upload-data.htm";
}
