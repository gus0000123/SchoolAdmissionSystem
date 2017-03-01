<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
	<table id="student-course-table">
		<thead>
			<tr style="cursor: pointer;">
				<th colspan="2">
					Enrolled Courses
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td style="width: 15px;"></td>
				<td style="width: calc(100%-15px);"></td>
			</tr>
			<tr>
				<td rowspan="2" style="width: 100%;">
					<spring:bind path="enrolled_courses">
						<form:checkboxes path="enrolled_courses" items="${ all_courses }" itemLabel="course_code" itemValue="course_code" />
					</spring:bind>
				</td>
			</tr>
		</tbody>
	</table>
	
	<script>
		var course_dropdown_counter = 0;
		var limit = 3;
		
		function addDropdown() {
			if (couse_dropdown_counter < limit){
				
				course_dropdown_counter++;
			}
		}
	</script>
</div>