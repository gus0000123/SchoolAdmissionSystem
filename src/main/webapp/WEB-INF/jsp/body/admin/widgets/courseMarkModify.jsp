<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/global/tableList.css" />">
<div>
	<jsp:include page="../forms/course_mark_modify.jsp" />
	<form id="return-to-student-view" action="admin" method="post">
		<input type="hidden" name="tab" value="student" />
	</form>
</div>