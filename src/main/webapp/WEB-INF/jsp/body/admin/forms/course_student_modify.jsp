<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${ not empty course }">
	<div>
		<table>
			<thead>
				<tr><th colspan="3">Enrolled students</th></tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${ empty course.getStudents() }">
						<tr><td colspan="3" style="text-align:center; font-size: 75%; font-style:italic;">There is no available student for this course.</td></tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="student" items="${ course.getStudents() }">
							<tr>
								<td style="width: 20px;"><input type="checkbox" name="student_selection" value="${ student.getPerson().getID() }" checked /></td>
								<td style="width: 130px;"><c:out value="${ student.getPerson().getID() }" /></td>
								<td style="width: calc(100% - 150px);"><c:out value="${ student.getPerson().getFullName() }" /></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</c:if>