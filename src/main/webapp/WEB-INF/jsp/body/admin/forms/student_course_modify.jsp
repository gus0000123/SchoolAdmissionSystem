<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<c:forEach var="course" items="${ student.getEnrolled_courses() }">
				<tr>
					<td>
						<input type="checkbox" name="s_course_selection" value="${ course.course_code }" checked />
					</td>
					<td>
						<c:out value="${ course.course_code }" />:&nbsp;<c:out value="${ course.course_name }" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td><span id="limit-number">Limit: </span></td>
			</tr>
			<tr>
				<td colspan="2"><div onclick="togglePopup();" style="cursor: pointer; font-weight: 600; font-style: italic;">+ Add more course</div></td>
			</tr>
		</tfoot>
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