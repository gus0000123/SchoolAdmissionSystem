<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
	<c:when test="${ tab eq 'overview' }">
		<title>Overview</title>
	</c:when>
	<c:when test="${ tab eq 'info' }">
		<title>Personal information</title>
	</c:when>
	<c:when test="${ tab eq 'message' }">
		<c:choose>
			<c:when test="${ sub_tab eq 'inbox' }">
				<title>Inbox</title>
			</c:when>
			<c:when test="${ sub_tab eq 'sent' }">
				<title>Sent messages</title>
			</c:when>
			<c:when test="${ sub_tab eq 'compose' }">
				<title>New message</title>
			</c:when>
			<c:when test="${ sub_tab eq 'trash' }">
				<title>Trash</title>
			</c:when>
		</c:choose>
	</c:when>
	<c:when test="${ tab eq 'person' }">
		<title>Person management</title>
	</c:when>
	<c:when test="${ tab eq 'student' }">
		<title>Student management</title>
	</c:when>
</c:choose>