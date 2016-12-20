<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					<select name="c_instructor">
						<c:forEach var="instructor" items="${ instructor_list }">
							<c:choose>
								<c:when test="${ empty course || empty course.getInstructor() }">
									<option value="${ instructor.getPerson().getID() }">
										<c:out value="${ instructor.getPerson().getFullName() }" /><!-- need to change this to better format -->
									</option>
								</c:when>
								<c:otherwise>
									<option value="${ instructor.getPerson().getID() }" selected>	
										<c:out value="${ instructor.getPerson().getFullName() }" />
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
			<!-- TODO: Course's teaching assistance if time allowed -->
		</tbody>
	</table>
</div>