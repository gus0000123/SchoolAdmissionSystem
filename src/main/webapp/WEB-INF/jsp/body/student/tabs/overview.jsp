<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Tab content for prospective student -->
<div id="tab-container">
	<div class="tab">
		<div class="column">
			<jsp:include page="../widgets/quickInfo.jsp" />
			<jsp:include page="../widgets/coursesList.jsp" />
		</div>
		<div class="column">
			<jsp:include page="../widgets/upcomingAssignment.jsp" />
		</div>
	</div>
</div>