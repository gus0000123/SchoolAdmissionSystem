<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<article id="activity-article" style="margin-left: 20px; margin-top: 20px;">
	<div class="article-header">
		CURRENTLY ENROLLED COURSE
	</div>
	<div class="article-body">
		<c:set var="course_counter" scope="page" value="${ 0 }" />
		<c:if test="${ not empty student.getEnrolled_courses() }">
			<c:forEach var="course" items="${ student.getEnrolled_courses() }">
				<c:if test="${ course.is_active eq true }">
					<div class="activity-row blue">
						<div class="activity-header"><c:out value="${ course.course_code }" /></div>
						<div class="activity-date"><c:out value="${ course.course_name }" /></div>
					</div>
					<c:set var="course_counter" scope="page" value="${ course_counter + 1 }" />
				</c:if>
			</c:forEach>
		</c:if>
		<c:if test="${ course_counter eq 0 }">
			<div class="activity-row blue">
				<div class="activity-header">Student has not enrolled into any course.</div>
			</div>
		</c:if>
	</div>
</article>
