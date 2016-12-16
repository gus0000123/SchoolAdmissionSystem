<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Tab content for prospective student -->
<div id="tab-container">
	<div class="tab">
		<div class="column" style="width: 300px;">
			<jsp:include page="../widgets/searchTool.jsp" />
		</div>
		<div class="column" style="width: calc(99% - 300px);">
			<jsp:include page="../widgets/personTable.jsp" />
		</div>
	</div>
</div>