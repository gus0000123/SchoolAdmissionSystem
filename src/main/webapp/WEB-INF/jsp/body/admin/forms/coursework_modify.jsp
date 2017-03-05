<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form action="admin" method="post" commandName="coursework">
	<input type="hidden" name="tab" value="coursework" />
	<input type="hidden" name="mode" value="${ mode }" />
	<input type="hidden" name="course_code" value="${ course.course_code }" /> <!-- this name required for forwarding to course controller -->
	<input type="hidden" name="actionPerformed" value="true" />
	<form:input type="hidden" name="number" path="coursework_id" />
	<table style="width: 100%;">
		<thead>
			<tr><th colspan="2">Information</th></tr>
		</thead>
		<tbody>
			<tr>
				<td style="width: 100px;">* Title</td>
				<td style="width: calc(100% - 100px);"><form:input type="text" name="cw_coursework_name" path="coursework_name" /></td>
			</tr>
			<tr>
				<td>* Description</td>
				<td><form:input type="text" name="cw_coursework_description" path="coursework_description" /></td>
			</tr>
			<tr>
				<td>* Contribution</td>
				<td><form:input type="number" name="cw_contribution" min="0" max="1" path="contribution" step="0.01" /></td>
			</tr>
			<tr>
				<td>* Mark</td>
				<td><form:input type="number" name="cw_max_mark" min="1" value="10" path="max_mark" /></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<c:choose>
						<c:when test="${ mode eq 'insert' }">
							<input class="bottom-button" type="submit" value="Insert" />
						</c:when>
						<c:otherwise>
							<input class="bottom-button" type="submit" value="Edit" />
						</c:otherwise>
					</c:choose>
					<button class="bottom-button" onclick="launchForm(event, 'return-to-course-view')">Back</button>
				</td>
			</tr>
		</tfoot>
	</table>
</form:form>
<form id="return-to-course-view" action="admin" method="post">
	<input type="hidden" name="tab" value="course" />
	<input type="hidden" name="mode" value="edit" />
	<input type="hidden" name="course_code" value="${ course.course_code }" />
</form>