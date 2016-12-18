<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/global/tableList.css" />">
<div style="width: 100%;">
	<form id="delete-person-form" action="admin" method="post">
		<table class="table-selector">
			<thead>
				<tr>
					<th style="width: 13px;">
						<c:if test="${ fn:length(list) gt 1 }"> <!-- Only current user is shown -->
							<input id="select-all" type="checkbox" name="select_all" value="all" onclick="selectAll()" />
						</c:if>
					</th>
					<th style="width: 10%"><span>ID</span></th>
					<th style="wdith: 50%"><span>Name</span></th>
					<th><span>Roles</span></th>
				</tr>
			</thead>
			<tbody>
				<c:set var="i" scope="page" value="1" />
				<c:forEach var="obj" items="${ list }">
					<tr class="row-selector">
						<td>
							<c:if test="${ user.person.getID() ne obj.getID() }">
								<input id="row-${ i }" type="checkbox" name="selection" value="${ obj.getID() }" onclick="selectRow(${ i })" />
							</c:if>
						</td>
						<td onclick="viewPerson(event, 'edit-person-form', ${ obj.getID() })"><span><c:out value="${ obj.getID() }" /></span></td>
						<td onclick="viewPerson(event, 'edit-person-form', ${ obj.getID() })"><span><c:out value="${ obj.getFullName() }" /></span></td>
						<td onclick="viewPerson(event, 'edit-person-form', ${ obj.getID() })">
							<!-- Get if person has student/employee/user account -->
						</td>
					</tr>
					<c:set var="i" scope="page" value="${ i + 1 }" />
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						<button class="bottom-button" onclick="addPerson(event);">Add</button>
						<button class="bottom-button" onclick="launchForm(event, 'delete-person-form');">Remove</button>
					</td>
				</tr>
			</tfoot>
		</table>
		<input type="hidden" name="tab" value="person" />
		<input type="hidden" name="mode" value="delete" />
		
	</form>
	<form id="add-person-form" action="admin" method="post" style="display: none;">
		<input type="hidden" name="tab" value="person" />
		<input type="hidden" name="mode" value="insert" />
	</form>
	<form id="edit-person-form" action="admin" method="post" style="display: none;">
		<input type="hidden" name="tab" value="person" />
		<input type="hidden" name="mode" value="edit" />
		<input id="person_id" type="hidden" name="id" value="" />
	</form>
	<script>
		var counter = 0;
		
		function selectAll() {
			var count = 1;
			for (count = 1; count <= ${ i }; count++) {
				if ($('#select-all').is(':checked')) $('#row-' + count).prop('checked', true);
				else $('#row-' + count).prop('checked', false);
			}
			
			if ($('#select-all').is(':checked')) {
				counter = ${ i } - 1;
			} else {
				counter = 0;
			}
		}
	
		function selectRow(index) {
			if ($('#row-' + index).is(':checked')) {
				counter++;
			} else {
				counter--;
			}
			
			if (counter >= ${ i } - 1) {
				$('#select-all').prop('checked', true);
				counter = ${ i } - 1
			} else {
				$('#select-all').prop('checked', false);
			}
		}

		function addPerson(e) {
			launchForm(e, 'add-person-form');
		}

		function viewPerson(e, formName, id) {
			$('#person_id').val(id);
			launchForm(e, formName);
		}
	</script>
</div>