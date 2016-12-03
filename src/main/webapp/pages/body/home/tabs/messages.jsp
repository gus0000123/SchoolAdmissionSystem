<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tab-container">
	<div class="tab">
		<div class="column" style="width: 120px;">
			<jsp:include page="../sections/message_selector.jsp" />
		</div>
		<div class="column" style="width: calc(99% - 120px);">
			<jsp:include page="../widgets/message_list.jsp" />
		</div>
	</div>
</div>
