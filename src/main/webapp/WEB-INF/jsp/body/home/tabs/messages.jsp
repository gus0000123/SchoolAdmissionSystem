<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tab-container">
	<div class="tab">
		<div class="column" style="width: 120px;">
			<jsp:include page="../sections/message_selector.jsp" />
		</div>
		<div class="column" style="width: calc(99% - 120px);">
			<c:choose>
				<c:when test="${ action eq 'compose' || action eq 'view' }">
					<jsp:include page="../widgets/message_view.jsp" />
				</c:when>
				<c:otherwise>
					<jsp:include page="../widgets/message_list.jsp" />
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
