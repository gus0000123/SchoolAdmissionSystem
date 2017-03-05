<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Tab content for prospective student -->
<div id="tab-container">
	<div class="tab">
		<div class="column" style="width: 100%">
			<c:choose>
				<c:when test="${ mode eq 'view' }">
					<jsp:include page="../widgets/studentTable.jsp" />
				</c:when>
				<c:otherwise>
					<jsp:include page="../widgets/studentModify.jsp" />
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>