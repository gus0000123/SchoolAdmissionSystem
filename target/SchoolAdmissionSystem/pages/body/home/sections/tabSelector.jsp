<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tab-selector">
	<c:choose>
		<c:when test="${ tab eq 'overview' }">
			<button class="active">Overview</button>
		</c:when>
		<c:otherwise>
			<button>Overview</button>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${ tab eq 'info' }">
			<button class="active">Personal Info</button>
		</c:when>
		<c:otherwise>
			<button>Personal Info</button>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${ tab eq 'status' }">
			<button class="active">Application Status</button>
		</c:when>
		<c:otherwise>
			<button>Application Status</button>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${ tab eq 'history' }">
			<button class="active">History</button>
		</c:when>
		<c:otherwise>
			<button>History</button>
		</c:otherwise>
	</c:choose>
</div>