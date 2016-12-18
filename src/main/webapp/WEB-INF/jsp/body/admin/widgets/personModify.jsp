<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/global/tableList.css" />">
<div>
	<form id="person-admin-modify-form" action="admin" method="post">
		<jsp:include page="../forms/person_modify.jsp" />
		<jsp:include page="../forms/user_modify.jsp" />
		<jsp:include page="../forms/student_modify.jsp" />
		<jsp:include page="../forms/employee_modify.jsp" />
		<input type="hidden" name="actionPerformed" value=true />
		<input type="hidden" name="mode" value="${ mode }" />
		<button class="bottom-button" onclick="launchForm(event, 'person-admin-modify-form')">
			<c:choose>
				<c:when test="${ mode eq 'insert' }">Insert</c:when>
				<c:otherwise>Edit</c:otherwise>
			</c:choose>
		</button>
		<button class="bottom-button">Back</button>
	</form>
</div>