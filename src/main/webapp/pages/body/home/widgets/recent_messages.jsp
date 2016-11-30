<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<article id="activity-article">
	<div class="article-header">
		RECENT MESSAGES
	</div>
	<div class="article-body">
		<c:choose>
			<c:when test="${ not empty messages }">
				<c:forEach var="message" items="${ messages }">
					<div class="activity-row blue">
						<div class="activity-header"><c:out value="${ message.getHeadline() }" /></div>
						<div class="activity-date"><c:out value="${ message.getCreation_time() }" /></div>
						<div class="activity-description"><c:out value="${ message.getMessage() }" /></div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="activity-row blue">
					<div class="activity-header">There is no message for this users.</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</article>