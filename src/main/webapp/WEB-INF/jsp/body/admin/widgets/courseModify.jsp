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
		<c:if test="${ mode eq 'edit' }">
			<button class="bottom-button" onclick="launchForm(event, 'insert-coursework-form')">Add coursework</button>
		</c:if>
		<button class="bottom-button" onclick="launchForm(event, 'course-admin-modify-form')">
			<c:choose>
				<c:when test="${ mode eq 'insert' }">Insert</c:when>
				<c:otherwise>Edit</c:otherwise>
			</c:choose>
		</button>
		<button class="bottom-button" onclick="launchForm(event, 'return-to-course-view')">Back</button>
	</form:form>
	<form id="return-to-course-view" action="admin" method="post">
		<input type="hidden" name="tab" value="course" />
	</form>
	<form id="insert-coursework-form" action="admin" method="post">
		<input type="hidden" name="tab" value="coursework" />
		<input type="hidden" name="mode" value="insert" />
		<input type="hidden" name="course_code" value="${ course.course_code }" />
	</form>
</div>