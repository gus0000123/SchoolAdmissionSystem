<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title><spring:message code="transcript.title" text="Unofficial transcript for student "/> id <c:out value="${ student.id }" /></title>
		<jsp:include page="../global/css_link.jsp" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/global/tableList.css" />">
		<style>
			body { background-color: rgba(255, 198, 94, 0.25); }
			header { padding-top: 50px; padding-bottom: 50px; font-size: 200%; }
			table { border-collapse: collapse; border: 0; width: 100%; }
			div { margin: 0 auto; width: 80%; }
			th { padding-bottom: 15px; border-bottom: 1px solid rgba(0, 0, 0, 0.25); }
			td { padding: 5px; }
		</style>
	</head>
	<body>
		<div style="text-align: right; text-size: 75%;">
			Language : <a href="?language=en">EN</a>|<a href="?language=fr">FR</a>
		</div>
		<header>
			<spring:message code="transcript.header" text="Unofficial transcript for "/> <c:out value="${ student.person.getFullName() }" />
		</header>
		<div>
			<table>
				<thead>
					<tr>
						<th style="text-align: left;"><spring:message code="transcript.ID" text="ID" /> </th>
						<th style="text-align: left;"><spring:message code="transcript.name" text="Course" /> </th>
						<th><spring:message code="transcript.credit" text="Credit" /> </th>
						<th><spring:message code="transcript.marks" text="Marks" /> </th>
						<th><spring:message code="transcript.grade" text="Grade" /> </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="coursemark" items="${ coursemarks }">
						<tr class="row-selector">
							<td><c:out value="${ coursemark.coursework.course.course_code }" /></td>
							<td><c:out value="${ coursemark.coursework.course.course_name }" /></td>
							<td style="text-align: center;"><c:out value="${ coursemark.coursework.course.credit }" /></td>
							<td style="text-align: center;"><c:out value="${ coursemark.mark }" /></td>
							<td style="text-align: center; font-weight: 700;">
								<c:choose>
									<c:when test="${ coursemark.mark gt 95 }">A+</c:when>
									<c:when test="${ coursemark.mark gt 90 }">A</c:when>
									<c:when test="${ coursemark.mark gt 85 }">A-</c:when>
									<c:when test="${ coursemark.mark gt 80 }">B+</c:when>
									<c:when test="${ coursemark.mark gt 75 }">B</c:when>
									<c:when test="${ coursemark.mark gt 70 }">B-</c:when>
									<c:when test="${ coursemark.mark gt 65 }">C+</c:when>
									<c:when test="${ coursemark.mark gt 60 }">C</c:when>
									<c:when test="${ coursemark.mark gt 55 }">C-</c:when>
									<c:otherwise>F</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>