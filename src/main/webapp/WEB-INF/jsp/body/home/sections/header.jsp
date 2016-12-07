<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="body-header">
	<div id="welcome-block">
		<div class="title">
			<span id="welcome-message">Welcome back, <span id="owner-name">
				<c:out value="${ user.getPerson().getFirstName() }" />&nbsp;
				<c:if test="${ not empty user.getPerson().getMiddleName() }">
					<c:out value="${ user.getPerson().getMiddleName() }" />&nbsp;
				</c:if>
				<c:out value="${ user.getPerson().getLastName() }" />
			</span></span>
		</div>
		<div class="subtitle">
			<span id="department-name"></span>
		</div>
	</div>
</div>