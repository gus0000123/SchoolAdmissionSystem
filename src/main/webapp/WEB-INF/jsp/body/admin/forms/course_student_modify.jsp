<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:if test="${ mode eq 'edit' }">
	<div>
		<table>
			<thead>
				<tr><th>Enrolled students</th></tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${ not empty all_students }">
						<tr>
							<td>
								<spring:bind path="students">
									<form:checkboxes path="students" items="${ all_students }" itemLabel="person.firstName" itemValue="id" />
								</spring:bind>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td style="text-align:center; font-size: 75%; font-style:italic;">There is no student in this course.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</c:if>