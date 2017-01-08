<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/global/tableList.css" />">
<style>
	.table-selector tbody tr td {
		font-size: 75%;
		text-align: center;
	}
	.table-selector tbody tr td:first-child {
		font-weight: 600;
	}
</style>
<article id="activity-article" style="margin-left: 20px;">
	<div class="article-header">
		COURSE INFORMATION
	</div>
</article>
<div style="width: 100%;">
	<table class="table-selector" style="width: 95%; margin: 0 auto;">
		<thead>
			<tr>
				<th style="width: 100px;">ID</th>
				<th>Name</th>
				<th style="width: 100px;">Credit</th>
				<th style="width: 100px;">Grade</th>
				<th style="width: 200px;">Enroll date</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${ not empty grades }">
					<c:forEach var="grade" items="${ grades }">
						<tr class="row-selector">
							<td><c:out value="${ grade.course.course_code }" /></td>
							<td><c:out value="${ grade.course.course_name }" /></td>
							<td><c:out value="${ grade.course.credit }" /></td>
							<td>
								<c:if test="${ grade.course.is_active eq false }">
									<!-- TODO: Add letter grade -->
									<c:out value="${ letter[grade.course.course_code] }" />
								</c:if>
							</td>
							<td><c:out value="${ grade.start_date }" /></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="row-selector">
						<td colspan="4" style="font-style:italic; text-align: center;">
							This student has not registered any course.
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
		<!-- TODO: ADD CUMULATIVE GPA -->
	</table>
</div>