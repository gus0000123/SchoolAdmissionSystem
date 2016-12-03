<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${ not empty important_messages }">
	<article id="important-article" class="important">
		<div class="article-header">
			IMPORTANT MESSAGES
		</div>
		<div class="article-body">
			<c:forEach var="message" items="${ important_messages }">
				<div class="activity-row">
					<div class="activity-header"><c:out value="${ message.getHeadline() }" /></div>
					<div class="activity-date"><c:out value="${ fn:split(message.creation_time, ' ')[0] }" /></div>
					<div class="activity-description"><c:out value="${ message.getMessage() }" /></div>
				</div>
			</c:forEach>
		</div>
	</article>
</c:if>