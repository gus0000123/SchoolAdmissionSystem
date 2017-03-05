<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<article id="body-wrapper">
	<!-- Body content -->
	<div id="body-content">
		<jsp:include page="sections/header.jsp" />
		<jsp:include page="sections/tabSelector.jsp" />
		
		<c:choose>
			<c:when test="${ tab eq 'person' }">
				<jsp:include page="tabs/person_list.jsp" />
			</c:when>
			<c:when test="${ tab eq 'student' }">
				<jsp:include page="tabs/student_list.jsp" />
			</c:when>
			<c:when test="${ tab eq 'course' }">
				<jsp:include page="tabs/course_list.jsp" />
			</c:when>
			<c:when test="${ tab eq 'coursework' }">
				<jsp:include page="tabs/course_list.jsp" />
			</c:when>
			<c:otherwise>
				<jsp:include page="tabs/person_list.jsp" />
			</c:otherwise>
		</c:choose>
	</div>
</article>