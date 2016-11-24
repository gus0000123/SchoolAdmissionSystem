<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/main.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/importTool.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/global/sidebar.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/global/header.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/global/body.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/personal.css">
		<title>Home - Montreal College</title>
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
									<c:choose>
										<c:when test="${ tab eq 'overview' }">
											<tr><td class="web-body"><jsp:include page="../../pages/body/home/overview.jsp" /></td></tr>
										</c:when>
										<c:when test="${ tab eq 'info' }">
											<tr><td class="web-body"><jsp:include page="../../pages/body/home/personalInfo.jsp" /></td></tr>
										</c:when>
									</c:choose>
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