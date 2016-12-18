<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../global/css_link.jsp" />
		<jsp:include page="../global/title.jsp" />
	</head>
	<body>
		<script type="text/javascript" src="<c:url value="resources/scripts/jquery-3.1.0.min.js" />"></script>
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
									<tr><td class="web-body"><jsp:include page="../body/admin/layout.jsp" /></td></tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<!-- Loading screen -->
		<jsp:include page="../global/loadingScreen.jsp" />
		
		<script type="text/javascript" src="resources/scripts/main.js"></script>
		<script type="text/javascript" src="resources/scripts/personal-validation.js"></script>
	</body>
</html>