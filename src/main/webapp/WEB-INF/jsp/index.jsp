<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="global/css_link.jsp" />
		<title>Sign in - Montreal College</title>
	</head>
	<body>
		<div id="loginBody">
			<div id="login-bg"></div>
			<div id="login-div">
				<jsp:include page="body/login/forms/login.jsp" />
				<jsp:include page="body/login/forms/register.jsp" />
			</div>
		</div>
		<jsp:include page="global/loadingScreen.jsp" />
		<jsp:include page="global/footer.jsp" />
		
		<!-- java script -->
		<script type="text/javascript" src="<c:url value="resources/scripts/jquery-3.1.0.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/scripts/main.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/scripts/login-validation.js" />"></script>
		
		<!-- switching page -->
		<c:choose>
			<c:when test="${ page eq 'login' }">
				<!-- Do nothing -->
			</c:when>
			<c:when test="${ page eq 'register' }">
				<!-- call javascript -->
				<script type="text/javascript">
					$(document).ready( function(){ switchToRegister(); });
				</script>
			</c:when>
			<c:when test="${ page eq 'confirmation' }">
				<!-- call javascript -->
				<script type="text/javascript">
					$(document).ready( function(){ countdownConfirmation(); });
				</script>
			</c:when>
		</c:choose>
	</body>
</html>