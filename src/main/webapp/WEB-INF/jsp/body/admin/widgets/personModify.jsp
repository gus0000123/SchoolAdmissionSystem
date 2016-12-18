<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/global/tableList.css" />">
<div>
	<form id="person-admin-modify-form" action="admin" method="post">
		<jsp:include page="../forms/person_main_modify.jsp" />
		<c:if test="${ mode ne 'insert' }">
			<jsp:include page="../forms/person_user_modify.jsp" />
			<jsp:include page="../forms/person_student_modify.jsp" />
			<jsp:include page="../forms/person_employee_modify.jsp" />
		</c:if>
		<input type="hidden" name="actionPerformed" value=true />
		<input type="hidden" name="mode" value="${ mode }" />
		<button class="bottom-button" onclick="launchForm(event, 'person-admin-modify-form')">
			<c:choose>
				<c:when test="${ mode eq 'insert' }">Insert</c:when>
				<c:otherwise>Edit</c:otherwise>
			</c:choose>
		</button>
		<button class="bottom-button" onclick="returnToView(event)">Back</button>
	</form>
	<form id="return-to-person-view" action="admin" method="post">
		<input type="hidden" name="tab" value="person" />
	</form>
	<script>
		function returnToView(e) { launchForm(e, 'return-to-person-view'); }
		
		function toggleForm(name) {
			var form_name = '#' + name + 'Detail';
			var checkbox = '#' + name + 'CheckBox';
			var action = '#' + name + 'Action';
			if ($(checkbox).is(':checked')) {
				$(form_name).css('display', '');
				$(action).val('insert');
			} else {
				$(form_name).css('display', 'none');
				$(action).val('delete');
			}
		}
	</script>
</div>