<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
	<table id="student-course-table">
		<thead>
			<tr style="cursor: pointer;">
				<th>
					Enrolled Courses
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<spring:bind path="enrolled_courses">
						<form:checkboxes path="enrolled_courses" items="${ all_courses }" itemLabel="course_code" itemValue="course_code" />
					</spring:bind>
				</td>
			</tr>
		</tbody>
	</table>
</div>