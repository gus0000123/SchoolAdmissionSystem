<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../../pages/global/css_link.jsp" />
		<c:choose>
			<c:when test="${ tab eq 'overview' }">
				<title>Overview</title>
			</c:when>
			<c:when test="${ tab eq 'info' }">
				<title>Personal information</title>
			</c:when>
		</c:choose>
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
									<tr><td class="web-body"><jsp:include page="../body/home/layout.jsp" /></td></tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/scripts/jquery-3.1.0.min.js"></script>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/scripts/main.js"></script>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/scripts/personal-validation.js"></script>
	</body>
</html>