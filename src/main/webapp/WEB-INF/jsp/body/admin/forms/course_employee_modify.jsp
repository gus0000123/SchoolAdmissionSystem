<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
	<table>
		<thead>
			<tr><th colspan="2">Employee-related information</th></tr>
		</thead>
		<tbody>
			<!-- Course instructor -->
			<tr>
				<td style="width: 150px;"><label for="c_instructor">* Instructor:</label></td>
				<td style="width: calc(100% - 150px);">
					<form:select name="c_instructor" path="instructor">
						<c:forEach var="instructor" items="${ instructor_list }">
							<form:option value="${ instructor.id }"><c:out value="${ instructor.person.getFullName() }" /></form:option>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<!-- TODO: Course's teaching assistance if time allowed -->
		</tbody>
	</table>
</div>