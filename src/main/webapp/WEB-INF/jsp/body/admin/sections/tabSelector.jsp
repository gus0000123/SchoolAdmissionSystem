<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tab-selector">
	<c:choose>
		<c:when test="${ tab eq 'person' }">
			<button class="active">Person</button>
		</c:when>
		<c:otherwise>
			<button onclick="goToPage('person')">Person</button>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${ tab eq 'student' or tab eq 'coursemark' }">
			<button class="active">Student</button>
		</c:when>
		<c:otherwise>
			<button onclick="goToPage('student')">Student</button>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${ tab eq 'course' or tab eq 'coursework' }">
			<button class="active">Course</button>
		</c:when>
		<c:otherwise>
			<button onclick="goToPage('course')">Course</button>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${ tab eq 'department' }">
			<button class="active">Department</button>
		</c:when>
		<c:otherwise>
			<button onclick="goToPage('department')">Department</button>
		</c:otherwise>
	</c:choose>
	<!-- hidden form -->
	<form id="tabSubmitForm" action="admin" method="post">
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