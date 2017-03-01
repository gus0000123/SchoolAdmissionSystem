<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
	<table>
		<thead>
			<tr style="cursor: pointer;">
				<th colspan="2">
					Student Information
					<form:input type="hidden" name="id" path="id" />
					<form:input type="hidden" name="person" path="person" />
				</th>
			</tr>
		</thead>
		<tbody id="student-body" style="width: 100%;">
			<tr>
				<td style="width: 150px;"><label for="s_major">Major:</label></td>
				<td style="width: calc(100% - 150px);"><form:input id="s_major" type="text" placeholder="Enter Major" path="major" /></td>
			</tr>
			<tr>
				<td><label for="s_minor">Minor:</label></td>
				<td><form:input id="s_minor" type="text" placeholder="Enter Minor" path="minor" /></td>
			</tr>
			<tr>
				<td><label for="s_start_date">Enrollment date:</label></td>
				<td><form:input id="s_start_date" type="date" placeholder="Enter enrollment date" path="start_date" /></td>
			</tr>
			<tr>
				<td><label for="s_admission_status">* Status:</label></td>
				<td>
					<form:select name="s_admission_status" path="admission_status">
						<form:options items="${ student_status_list }" itemValue="status" itemLabel="status" />
					</form:select>
				</td>
			</tr>
			<tr>
				<td><label for="s_department">* Department:</label></td>
				<td>
					<form:select name="s_department" path="department">
						<form:options items="${ department_list }" itemValue="dept_code.dept_name" itemLabel="dept_code.dept_name" />
					</form:select>
				</td>
			</tr>
		</tbody>
	</table>
</div>