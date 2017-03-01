<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/global/tableList.css" />">
<div>
	<form:form id="course-admin-modify-form" action="admin" method="post" commandName="course">
		<jsp:include page="../forms/course_main_modify.jsp" />
		<jsp:include page="../forms/course_employee_modify.jsp" />
		<jsp:include page="../forms/course_student_modify.jsp" />
		<jsp:include page="../forms/course_coursework_modify.jsp" />
		<input type="hidden" name="actionPerformed" value=true />
		<input type="hidden" name="mode" value="${ mode }" />
		<input type="hidden" name="tab" value="course" />
		<button class="bottom-button" onclick="launchForm(event, 'course-admin-modify-form')">
			<c:choose>
				<c:when test="${ mode eq 'insert' }">Insert</c:when>
				<c:otherwise>Edit</c:otherwise>
			</c:choose>
		</button>
		<button class="bottom-button" onclick="returnToView(event)">Back</button>
	</form:form>
	<jsp:include page="courseCourseworkInsertion.jsp" />
	<form id="return-to-course-view" action="admin" method="post">
		<input type="hidden" name="tab" value="course" />
	</form>
	<script>
		function returnToView(e) { launchForm(e, 'return-to-course-view'); }
	</script>
</div>