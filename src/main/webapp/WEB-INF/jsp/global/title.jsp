<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
	<c:when test="${ tab eq 'person' }">
		<title>Person management</title>
	</c:when>
	<c:when test="${ tab eq 'student' }">
		<title>Student management</title>
	</c:when>
	<c:when test="${ tab eq 'course' or tab eq 'coursework' }">
		<title>Course management</title>
	</c:when>
</c:choose>