<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside id="side-panel">
	<form id="adminSideButtonForm" action="admin" method="post">
		<input type="hidden" name="tab" value="person" />
		<button onclick="launchForm(event, 'adminSideButtonForm');">
			<img src="<c:url value="resources/images/application-icon.png" />">
		</button>
	</form>
</aside>