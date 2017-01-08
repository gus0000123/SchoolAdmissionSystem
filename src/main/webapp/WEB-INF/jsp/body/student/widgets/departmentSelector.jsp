<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/global/tableList.css" />">
<article id="activity-article">
	<div class="article-header">
		COURSE REGISTRATION
	</div>
</article>
<div style="width: 100%;">
	<form id="departmentSelectorForm" action="student" method="post">
		<table>
			<tbody>
				<tr>
					<td style="width: 100px;">Department:</td>
					<td>
						<select id="department-dropdown" name="department_id" style="width: 100%;">
							<c:forEach var="dpt" items="${ department_list }">
								<c:choose>
									<c:when test="${ department.getDeptId() eq dpt.getDeptId() }">
										<option value="${ dpt.getDeptId() }" selected><c:out value="${ dpt.getCode().dept_code }" />:&nbsp;<c:out value="${ dpt.getCode().dept_name }" /></option>
									</c:when>
									<c:otherwise>
										<option value="${ dpt.getDeptId() }"><c:out value="${ dpt.getCode().dept_code }" />:&nbsp;<c:out value="${ dpt.getCode().dept_name }" /></option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<input type="hidden" name="tab" value="register" />
						<input type="hidden" name="mode" value="list" />
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	
	<script>
		$('#department-dropdown').change(function() {
			launchForm(event, 'departmentSelectorForm');
		});
	</script>
</div>