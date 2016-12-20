<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${ not empty course }">
	<div>
		<table>
			<thead>
				<tr><th colspan="3">Course works</th></tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${ empty course.getCourse_works() }">
						<tr><td colspan="3" style="text-align:center; font-size: 75%; font-style:italic;">There is no available course work for this course.</td></tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="coursework" items="${ course.getCourse_works() }">
							<tr>
								<td style="width: 20px;"><input type="checkbox" name="coursework_selection" value="${ coursework.coursework_id }" checked /></td>
								<td style="width: 150px;"><c:out value="${ coursework.coursework_id }" /></td>
								<td style="width: calc(100% - 170px);"><c:out value="${ coursework.coursework_name }" /></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</c:if>