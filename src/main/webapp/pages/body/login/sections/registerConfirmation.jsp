<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="confirmation-wrapper" onclick="closeConfirmation();">
	<div class="confirmation-text">
		ACCOUNT CREATED
	</div>
	<div class="confirmation-subtitle-text">
		<span>Registration is completed</span><br />
		<c:if test="${ not empty account_name }">
			<span>Your account is: <c:out value="${ account_name }" /></span>
		</c:if>
	</div>
</div>