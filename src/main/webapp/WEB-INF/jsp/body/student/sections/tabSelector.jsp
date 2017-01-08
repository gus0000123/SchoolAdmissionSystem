<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tab-selector">
	<c:choose>
		<c:when test="${ tab eq 'overview' }">
			<button class="active">Overview</button>
		</c:when>
		<c:otherwise>
			<button onclick="goToPage('overview')">Overview</button>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${ tab eq 'transcript' }">
			<button class="active">Transcript</button>
		</c:when>
		<c:otherwise>
			<button onclick="goToPage('transcript')">Transcript</button>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${ tab eq 'register' }">
			<button class="active">Registration</button>
		</c:when>
		<c:otherwise>
			<button onclick="goToPage('register')">Registration</button>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${ tab eq 'courses' }">
			<button class="active">Course Management</button>
		</c:when>
		<c:otherwise>
			<button onclick="goToPage('courses')">Course Management</button>
		</c:otherwise>
	</c:choose>
	<!-- hidden form -->
	<form id="tabSubmitForm" action="student" method="post">
		<input id="tabSubmitField" type="hidden" name="tab" value="" />
	</form>
	<script>
		function goToPage(tab)
		{
			$("#tabSubmitField").val(tab);
			launchForm(event, "tabSubmitForm");
		}
	</script>
</div>