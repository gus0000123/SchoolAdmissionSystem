<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/global/tableList.css" />">
<div>
	<form:form id="student-admin-modify-form" action="admin" method="post" commandName="student">
	 	<jsp:include page="../forms/student_main_modify.jsp" />
		<jsp:include page="../forms/student_course_modify.jsp" />
		<input type="hidden" name="tab" value="student" />
		<input type="hidden" name="studentAction" value="edit" />
		<button class="bottom-button" onclick="launchForm(event, 'student-admin-modify-form')">Edit</button>
		<button class="bottom-button" onclick="returnToView(event)">Back</button>
	</form:form>
	<jsp:include page="studentCourseInsertion.jsp" />
	<form id="return-to-student-view" action="admin" method="post">
		<input type="hidden" name="tab" value="student" />
	</form>
	<form id="go-to-edit-mark" action="admin" method="post">
		<input type="hidden" name="tab" value="coursemark" />
		<input type="hidden" name="student_id" value="${ student.id }" />
	</form>
	<script>
		function returnToView(e) { launchForm(e, 'return-to-student-view'); }
	</script>
</div>