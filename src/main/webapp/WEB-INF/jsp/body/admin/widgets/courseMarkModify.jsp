<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/global/tableList.css" />">
<div>
	<form:form id="student-coursemark-modify-form" method="post" action="admin" commandName="coursemarks">
		<table>
			<thead>
				<tr style="cursor: pointer;">
					<th colspan="3">
						Course Marks
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Course ID</td>
					<td>Assignment name</td>
					<td>Mark</td>
				</tr>
				<c:forEach var="coursemark" items = "${ coursemarks.list }" varStatus="status">
					<tr>
						<td><c:out value="${ coursemark.coursework.course.course_code }" /></td>
						<td><c:out value="${ coursemark.coursework.coursework_name }" /></td>
						<td><form:input path="list[${ status.index }].mark" type="number" min="0" max="${ coursemark.coursework.max_mark }" value="${ coursemark.mark }" />&nbsp;/&nbsp;<c:out value="${ coursemark.coursework.max_mark }" /></td>
						<td>
							<!-- Hidden values for spring -->
							<form:input path="list[${ status.index }].coursemark_id" type="hidden" value="${ coursemark.coursemark_id }" />
							<form:input path="list[${ status.index }].coursework" type="hidden" value="${ coursemark.coursework.coursework_id }" />
							<form:input path="list[${ status.index }].student" type="hidden" value="${ coursemark.student.id }" />
						</td>
					</tr> 
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="3">
						<input class="bottom-button" type="submit" value="Submit" onclick="launchForm(event, 'student-coursemark-modify-form')" />
						<button class="bottom-button" onclick="launchForm(event, 'return-to-course-view')">Back</button>
						<!-- Hidden value for controller -->
						<input type="hidden" name="tab" value="coursemark" />
						<input type="hidden" name="mode" value="edit" />
						<input type="hidden" name="student_id" value="${ student.id }" />
						<input type="hidden" name="actionPerformed" value="true" />
					</td>
				</tr>
			</tfoot>
		</table>
	</form:form>
	<form id="return-to-student-view" action="admin" method="post">
		<input type="hidden" name="tab" value="student" />
	</form>
</div>