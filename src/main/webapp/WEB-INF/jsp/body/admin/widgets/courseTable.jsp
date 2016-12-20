<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/global/tableList.css" />">
<div style="width: 100%;">
	<form id="delete-course-form" action="admin" method="post">
		<table class="table-selector">
			<thead>
				<tr>
					<th style="width: 13px;">
						<c:if test="${ fn:length(list) gt 0 }">
							<input id="select-all" type="checkbox" name="select_all" value="all" onclick="selectAll()" />
						</c:if>
					</th>
					<th style="width: 20%;"><span>Course ID</span></th>
					<th><span>Course name</span></th>
				</tr>
			</thead>
			<tbody>
				<c:set var="i" scope="page" value="1" />
				<c:forEach var="obj" items="${ list }">
					<tr class="row-selector">
						<td><input id="row-${ i }" type="checkbox" name="selection" value="${ obj.course_code }" onclick="selectRow(${ i })" /></td>
						<td onclick="viewCourse(event, 'edit-course-form', '${ obj.course_code }')"><span><c:out value="${ obj.course_code }" /></span></td>
						<td onclick="viewCourse(event, 'edit-course-form', '${ obj.course_code }')"><span><c:out value="${ obj.course_name }" /></span></td>
					</tr>
					<c:set var="i" scope="page" value="${ i + 1 }" />
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="3">
						<button class="bottom-button" onclick="addCourse(event);">Add</button>
						<button class="bottom-button" onclick="launchForm(event, 'delete-course-form');">Remove</button>
					</td>
				</tr>
			</tfoot>
		</table>
		<input type="hidden" name="tab" value="course" />
		<input type="hidden" name="mode" value="delete" />
	</form>
	<form id="add-course-form" action="admin" method="post" style="display: none;">
		<input type="hidden" name="tab" value="course" />
		<input type="hidden" name="mode" value="insert" />
	</form>
	<form id="edit-course-form" action="admin" method="post" style="display: none;">
		<input type="hidden" name="tab" value="course" />
		<input type="hidden" name="mode" value="edit" />
		<input id="course_id" type="hidden" name="course_code" value="" />
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
		
		function addCourse(e) {
			launchForm(e, 'add-course-form');
		}
		
		function viewCourse(e, formName, id) {
			$('#course_id').val(id);
			launchForm(e, formName);
		}
	</script>
</div>