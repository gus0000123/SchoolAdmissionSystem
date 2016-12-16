<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<form>
		<jsp:include page="../forms/person_modify.jsp" />
		<jsp:include page="../forms/user_modify.jsp" />
		<jsp:include page="../forms/student_modify.jsp" />
		<jsp:include page="../forms/employee_modify.jsp" />
		<input type="submit" value="Add" />
		<input type="submit" value="Back" />
	</form>
	<script>
		function toggleForm(name) {
			var form_id = '#' + name;
			if ($(form_id).css('display') == 'none') {
				$(form_id).css('display', 'table');
			} else {
				$(form_id).css('display', 'none');
			}
		}
	</script>
</div>