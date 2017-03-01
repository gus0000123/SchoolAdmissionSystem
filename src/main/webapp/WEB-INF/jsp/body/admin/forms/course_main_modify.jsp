<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
	<table>
		<thead>
			<tr style="cursor: pointer;">
				<th colspan="2">
					Course Information
					<form:input type="hidden" name="c_course_code" path="course_code" />
				</th>
			</tr>
		</thead>
		<tbody id="course-body" style="width: 100%;">
			<!-- Class level -->
			<tr>
				<td style="width: 150px;"><label for="c_class_level">* Class Level:</label></td>
				<td style="width: calc(100% - 150px);"><form:input type="number" name="c_class_level" min="0" max="9" path="class_level" /></td>
			</tr>
			<!-- Course number -->
			<tr>
				<td><label for="c_course_number">* Course number:</label></td>
				<td><form:input type="number" name="c_course_number" min="0" max="9" path="course_number" /></td>
			</tr>
			<!-- Course section -->
			<tr>
				<td><label for="c_section">* Section:</label></td>
				<td><form:input type="number" name="c_section" min="0" max="9" path="section" /></td>
			</tr>
			<!-- Course name -->
			<tr>
				<td><label for="c_course_name">* Course name:</label></td>
				<td><form:input type="text" name="c_course_name" path="course_name" /></td>
			</tr>
			<!-- Course description -->
			<tr>
				<td><label for="c_course_description">Course description:</label></td>
				<td><form:input type="text" name="c_course_description" path="course_description" /></td>
			</tr>
			<!-- Course capacity -->
			<tr>
				<td><label for="c_capacity">Capacity:</label></td>
				<td><form:input type="number" name="c_capacity" min="0" max="999" path="capacity" /></td>
			</tr>
			<!-- Course credit -->
			<tr>
				<td><label for="c_credit">Credit:</label></td>
				<td><form:input type="number" name="c_credit" min="0" max="9" path="credit" /></td>
			</tr>
			<!-- Course isActive? -->
			<tr>
				<td><label for="c_active">Is open:</label></td>
				<td>
					<spring:bind path="is_active">
						<form:checkbox name="c_active" path="is_active" />
					</spring:bind>
				</td>
			</tr>
			<!-- Course department -->
			<tr>
				<td><label for="c_department">* Department:</label></td>
				<td>
					<form:select name="c_department" path="department">
						<form:options items="${ department_list }" itemValue="dept_code.dept_name" itemLabel="dept_code.dept_name" />
					</form:select>
				</td>
			</tr>
		</tbody>
	</table>
</div>