<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<article id="activity-article" style="margin-left: 20px;">
	<div class="article-header">
		UPCOMING ASSIGNMENT
	</div>
	<div class="article-body">
		<c:set var="assignment_counter" scope="page" value="${ 0 }" />
		<c:if test="${ not empty assignments }">
			<c:forEach var="assignment" items="${ assignments }">
				<c:if test="${ assignment.course.is_active eq true }">
					<div class="activity-row blue">
						<div class="activity-header"><c:out value="${ assignment.coursework_name }" /></div>
						<div class="activity-date"><c:out value="${ assignment.deadline }" /></div>
						<div class="activity-description"><c:out value="${ assignment.course.course_code }" />:&nbsp;<c:out value="${ assignment.course.course_name }" /></div>
					</div>
					<c:set var="assignment_counter" scope="page" value="${ assignment_counter + 1 }" />
				</c:if>
			</c:forEach>
		</c:if>
		<c:if test="${ assignment_counter eq 0 }">
			<div class="activity-row blue">
				<div class="activity-header">There is no upcoming assignment.</div>
			</div>
		</c:if>
	</div>
</article>