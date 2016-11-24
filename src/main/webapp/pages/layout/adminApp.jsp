<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/main.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/importTool.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/global/sidebar.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/global/header.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/global/body.css">
		<title>Management Tool - Montreal College</title>
	</head>
	<body>
		<div id="page-wrapper">
			<table class="table-no-border fill-page">
				<tbody>
					<tr>
						<td class="web-side">
							<jsp:include page="../../pages/global/sidebar.jsp" />
						</td>
						<td>
							<table class="table-no-border fill-page">
								<tbody>
									<tr><td class="web-body" style="height: 1%;"><jsp:include page="../../pages/global/header.jsp" /></td></tr>
									<tr><td class="web-body">
									
			<!-- TODO: Move tool -->						
<div id="body-wrapper">
				<!-- Body content -->
				<div id="body-content">
					<!-- header at the top of body -->
					<div id="body-header">
						<div id="welcome-block">
							<div class="title">
								<span id="welcome-message">ADMIN MANAGEMENT TOOL</span>
							</div>
						</div>
					</div>
					
					<!-- Tab content selector -->
					<div id="tab-selector">
						<form action="toolTransition" method="post" style="display:inline-block;">
							<input type="hidden" name="page" value="User">
							<c:choose>
								<c:when test="${ tab eq 'User' }">
									<button class="active">User</button>
								</c:when>
								<c:otherwise>
									<button>User</button>
								</c:otherwise>
							</c:choose>
						</form>
						<form action="toolTransition" method="post" style="display:inline-block;">
							<input type="hidden" name="page" value="Person">
							<c:choose>
								<c:when test="${ tab eq 'Person' }">
									<button class="active">Person</button>
								</c:when>
								<c:otherwise>
									<button>Person</button>
								</c:otherwise>
							</c:choose>
						</form>
						<button>Prospect</button>
						<form action="toolTransition" method="post" style="display:inline-block;">
							<input type="hidden" name="page" value="Student">
							<c:choose>
								<c:when test="${ tab eq 'Student' }">
									<button class="active">Student</button>
								</c:when>
								<c:otherwise>
									<button>Student</button>
								</c:otherwise>
							</c:choose>
						</form>
						<button>Employee</button>
						<button>Department</button>
						<button>Course</button>
					</div>
					
					<!-- Tab content for prospective student -->
					<div id="tab-container">
						<div class="tab">
							<div class="column" style="width: 300px;">
								<form>
									<table class="search-table">
										<thead>
											<tr>
												<th>
													Filter result(s)
												</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td style="padding-top: 15px;">
													<input type="text" name="filter" placeholder="Matching word...">
												</td>
											</tr>
											<tr>
												<td id="advance-filter" style="display: none;">
													<input type="text" name="category" placeholder="dropdown_placeholder">
												</td>
											</tr>
											<tr>
												<td>
													<button id="filter-toggle" onclick="toggleFilter(event)">Advanced Filter</button>
												</td>
											</tr>
										</tbody>
										<tfoot>
											<tr>
												<td>
													<input type="submit" value="Search">
													<input type="reset" value="Clear">
												</td>
											</tr>
										</tfoot>
									</table>
								</form>
							</div>
							<div class="column">
								<table>
									<thead>
										<tr>
											<c:forEach var="title" items="${ tableHeader }">
												<td>
													<span><c:out value="${ title }"/></span>
												</td>
											</c:forEach>
											<td>
												<!-- Empty header for action -->
											</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="obj" items="${ list }">
											<tr>
												<c:forTokens var="col" items="${ obj.toString() }" delims=";">
													<td>
														<span><c:out value="${ col }"/></span>
													</td>
												</c:forTokens>
												<td>
													<form action="dataModification" method="post" style="display: inline-block;">
														<input type="hidden" name="object" value="${ obj }">
														<input type="hidden" name="redirect" value="modify">
														<input type="hidden" name="action" value="edit">
														<input type="submit" value="Edit">
													</form>
													<form action="dataModification" method="post" style="display: inline-block;">
														<input type="hidden" name="object" value="${ obj }">
														<input type="hidden" name="redirect" value="none">
														<input type="hidden" name="action" value="delete">
														<input type="submit" value="Delete">
													</form>
												</td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
									</tfoot>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
					<!--  -->				
									</td></tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<!-- Loading screen -->
<!-- 	<div id="black-div">
			<div class="loading-wrapper">
				<script type="image/svg+xml">
					<svg class="hourglass" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 120 206" preserveAspectRatio="none">
						<path class="middle" d="M120 0H0v206h120V0zM77.1 133.2C87.5 140.9 92 145 92 152.6V178H28v-25.4c0-7.6 4.5-11.7 14.9-19.4 6-4.5 13-9.6 17.1-17 4.1 7.4 11.1 12.6 17.1 17zM60 89.7c-4.1-7.3-11.1-12.5-17.1-17C32.5 65.1 28 61 28 53.4V28h64v25.4c0 7.6-4.5 11.7-14.9 19.4-6 4.4-13 9.6-17.1 16.9z"/>
						<path class="outer" d="M93.7 95.3c10.5-7.7 26.3-19.4 26.3-41.9V0H0v53.4c0 22.5 15.8 34.2 26.3 41.9 3 2.2 7.9 5.8 9 7.7-1.1 1.9-6 5.5-9 7.7C15.8 118.4 0 130.1 0 152.6V206h120v-53.4c0-22.5-15.8-34.2-26.3-41.9-3-2.2-7.9-5.8-9-7.7 1.1-2 6-5.5 9-7.7zM70.6 103c0 18 35.4 21.8 35.4 49.6V192H14v-39.4c0-27.9 35.4-31.6 35.4-49.6S14 81.2 14 53.4V14h92v39.4C106 81.2 70.6 85 70.6 103z"/>
					</svg>
				</script>
				<div class="loading-text">
					...THINKING...
				</div>
			</div>
			<div class="confirmation-wrapper" onclick="closeConfirmation()">
				<div class="confirmation-text">
					INFORMATION HAS BEEN CHANGED
				</div>
				<div class="confirmation-subtitle-text">
					<span>Click to return</span>
				</div>
			</div>
		</div>
-->		
		<script type="text/javascript" src="${ pageContext.request.contextPath }/scripts/jquery-3.1.0.min.js"></script>
		<script type="text/javascript" src="${ pageContext.request.contextPath }/scripts/main.js"></script>
		<script type="text/javascript" src="${ pageContext.request.contextPath }/scripts/personal-validation.js"></script>
	</body>
</html>