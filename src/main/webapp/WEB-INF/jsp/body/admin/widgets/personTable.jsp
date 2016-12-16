<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/global/tableList.css">
<div style="width: 100%;">
	<form>
		<table class="table-selector">
			<thead>
				<tr>
					<th style="width: 13px;"><input id="select-all" type="checkbox" name="select_all" value="all" onclick="selectAll()" /></th>
					<th style="width: 10%"><span>ID</span></th>
					<th style="wdith: 50%"><span>Name</span></th>
					<th><span>Status</span></th>
				</tr>
			</thead>
			<tbody>
				<c:set var="i" scope="page" value="1" />
				<c:forEach var="obj" items="${ list }">
					<tr class="row-selector">
						<td><input id="row-${ i }" type="checkbox" name="selection" value="${ message.getId() }" onclick="selectRow(${ i })" /></td>
						<td><span><c:out value="${ obj.getID() }" /></span></td>
						<td><span><c:out value="${ obj.getFullName() }" /></span></td>
						<td>
							<!-- Get if person has student/employee/user account -->
						</td>
					</tr>
					<c:set var="i" scope="page" value="${ i + 1 }" />
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						<button class="bottom-button">Add</button>
						<button class="bottom-button">Remove</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
	<form action="TestAdmin" method="post">
		<input type="hidden" name="tab" value="person" />
		<input type="hidden" name="mode" value="insert" />
		<input type="submit" value="test" />
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
	</script>
</div>