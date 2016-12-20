<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${ not empty course }">
	<div>
		<table id="course-coursework-table">
			<thead>
				<tr><th colspan="4">Course works</th></tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${ empty course.getCourse_works() }">
						<tr><td colspan="4" style="text-align:center; font-size: 75%; font-style:italic;">There is no available course work for this course.</td></tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="coursework" items="${ course.getCourse_works() }">
							<tr>
								<td style="width: 20px;"><input type="checkbox" name="coursework_selection" value="${ coursework.coursework_id }" checked /></td>
								<td style="width: calc(100% - 270px);"><c:out value="${ coursework.coursework_name }" /></td>
								<td style="width: 50px;"><c:out value="${ coursework.contribution * 100 }" /></td>
								<td style="width: 200px;"><c:out value="${ coursework.deadline }" /></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						<div onclick="togglePopup();" style="font-size: 75%; font-style: italic; font-weight: 600;">+ Add new coursework</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</c:if>