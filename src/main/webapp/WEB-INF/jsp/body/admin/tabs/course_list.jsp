<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Tab content for prospective student -->
<div id="tab-container">
	<div class="tab">
		<div class="column" style="width: 100%">
			<c:choose>
				<c:when test="${ tab eq  'course' }">
					<c:choose>
						<c:when test="${ mode eq 'view' }">
							<jsp:include page="../widgets/courseTable.jsp" />
						</c:when>
						<c:otherwise>
							<jsp:include page="../widgets/courseModify.jsp" />
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when test="${ tab eq 'coursework' }">
					<jsp:include page="../widgets/courseWorkModify.jsp" />
				</c:when>
			</c:choose>
		</div>
	</div>
</div>