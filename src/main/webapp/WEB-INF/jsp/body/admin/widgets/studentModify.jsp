<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/global/tableList.css" />">
<div>
	<form id="student-admin-modify-form" action="admin" method="post">
		<jsp:include page="../forms/student_main_modify.jsp" />
		<jsp:include page="../forms/student_course.jsp" />
		<input type="hidden" name="tab" value="student" />
		<input type="hidden" name="studentAction" value="edit" />
		<button class="bottom-button" onclick="launchForm(event, 'student-admin-modify-form')">Edit</button>
		<button class="bottom-button" onclick="returnToView(event)">Back</button>
	</form>
	<form id="return-to-student-view" action="admin" method="post">
		<input type="hidden" name="tab" value="student" />
	</form>
	<script>
		function returnToView(e) { launchForm(e, 'return-to-student-view'); }
	</script>
</div>