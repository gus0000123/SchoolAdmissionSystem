<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<article id="body-wrapper">
	<!-- Body content -->
	<div id="body-content">
		<jsp:include page="sections/header.jsp" />
		<jsp:include page="sections/tabSelector.jsp" />
		
		<c:choose>
			<c:when test="${ tab eq 'overview' }">
				<jsp:include page="tabs/overview.jsp" />
			</c:when>
			<c:when test="${ tab eq 'info' }">
				<jsp:include page="tabs/personalInfo.jsp" />
			</c:when>
			<c:when test="${ tab eq 'messages' }">
				<jsp:include page="tabs/messages.jsp" />
			</c:when>
		</c:choose>
	</div>
</article>