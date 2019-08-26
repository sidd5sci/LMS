<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<h2>Excel File header</h2>
<table id="dataTable">

	<thead>
		<th>Leave Request</th>
		<th>Employee name</th>
		<th>Home office</th>
		<th>Leave Type</th>
		<th>Department code</th>
		<th>Type of Time Off</th>
		<th>Dates</th>
		<th>Created by</th>
		<th>Created when</th>
		<th>Approval status</th>
		<th>Duration</th>

	</thead>

	<tr>
		<td>Jhon - 2019-08-30 - 2019-08-31</td>
		<td>Jhon</td>
		<td>Banglore</td>
		<td>Vacation leave</td>
		<td>Backend</td>
		<td>Full day</td>
		<td>2019-08-30 - 2019-08-31</td>
		<td>Jhon</td>
		<td>2019-08-23 00:00:00</td>
		<td>pending</td>
		<td>1</td>

	</tr>

</table>
<br>
<br>


<div class="row">

	<div class="col-md-3"></div>
	<div class="col-md-6">

		<form action="admin-upload-file.api" enctype="multipart/form-data" method="post">
			Enter File Name 
			<input type="text" class="form-control"	name="file_name"><br> 
			Select 
			<input type="file"	class="form-control" name="file2" accept=".xls,.xlsx"/><br> 
			<input type="submit" class="form-btn" value="upload" />
		</form>	

	</div>
	<div class="col-md-3"></div>
</div>
