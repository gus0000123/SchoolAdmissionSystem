<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../global/css_link.jsp" />
		<c:choose>
			<c:when test="${ tab eq 'overview' }">
				<title>Overview</title>
			</c:when>
			<c:when test="${ tab eq 'info' }">
				<title>Personal information</title>
			</c:when>
			<c:when test="${ tab eq 'messages' }">
				<title>Messages</title>
			</c:when>
		</c:choose>
	</head>
	<body>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/scripts/jquery-3.1.0.min.js"></script>
		<div id="page-wrapper">
			<table class="table-no-border fill-page">
				<tbody>
					<tr>
						<td class="web-side">
							<jsp:include page="../global/sidebar.jsp" />
						</td>
						<td>
							<table class="table-no-border fill-page">
								<tbody>
									<tr><td class="web-body" style="height: 1%;"><jsp:include page="../global/header.jsp" /></td></tr>
									<tr><td class="web-body"><jsp:include page="../body/home/layout.jsp" /></td></tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<jsp:include page="../global/loadingScreen.jsp" />
		
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/scripts/main.js"></script>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/scripts/personal-validation.js"></script>
	</body>
</html>