<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside id="side-panel">
	<form id="homeSideButtonForm" action="home" method="post">
		<input type="hidden" name="tab" value="overview" />
		<button onclick="launchForm(event, 'homeSideButtonForm');">
			<img src="<c:url value="resources/images/home-icon.png" />">
		</button>
	</form>
	<form id="adminSideButtonForm" action="admin" method="post">
		<input type="hidden" name="tab" value="person" />
		<button onclick="launchForm(event, 'adminSideButtonForm');">
			<img src="<c:url value="resources/images/application-icon.png" />">
		</button>
	</form>
</aside>