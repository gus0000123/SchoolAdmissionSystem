<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="width: 95%;">
	<form>
		<table class="table-selector">
			<thead>
				<tr>
					<th>Department</th>
					<th>Course ID</th>
					<th>Course Name</th>
					<th>Capacity</th>
					<th>Credit</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${ not empty course_list }">
						<c:forEach var="course" items="${ course_list }">
							<tr class="row-selector">
								<td><c:out value="${ course.getDepartment().getCode().dept_name }" /></td>
								<td><c:out value="${ course.course_code }" /></td>
								<td><c:out value="${ course.course_name }" /></td>
								<td><c:out value="${ course.getStudents().size() }" />/<c:out value="${ course.capacity }" /></td>
								<td><c:out value="${ course.credit }" /></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr	class="row-selector">
							<td colspan="5">There is no available course at the moment.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</form>
</div>