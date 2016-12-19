<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#course-popup { display:none; position: fixed; width: 100%; height: 100%; top: 0; left: 0; background-color: rgba(0, 0, 0, 0.4); }
	#course-popup-float { position: fixed; width: 400px; height: 100px; margin-top: calc(50vh - 50px); margin-left: calc(50vw - 200px); background-color: white; padding: 20px; }
	#course-popup-float table { width: 100%; height: 100%; }
</style>
<div id="course-popup">
	<div id="course-popup-float">
		<table>
			<thead>
				<tr><th colspan="2">Insert course</th></tr>
			</thead>
			<tbody>
				<tr>
					<td style="width: 100px;">Select one:</td>
					<td style="width: calc(100% - 100px);">
						<select id="new-course" name="new-course">
							<c:choose>
								<c:when test="${ empty all_courses }">
									<option value="0">Please have at least one course</option>
								</c:when>
								<c:otherwise>
									<c:forEach var="course" items="${ all_courses }">
										<option value="${ course.course_code }">
											<c:out value="${ course.course_name }" />
										</option>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<button onclick="addNewCourse();">Add</button>
						<button onclick="togglePopup();">Cancel</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</div>

<script>
	var limit = 3;

	$(document).ready(function() {
		$('#limit-number').html("Limit:" + limit);
	});
	
	function addNewCourse() {
		if (limit > 0) {
			var selectValue = $('#new-course').val();
			var selectText = $('#new-course :selected').text();
			var newRow = '<tr><td><input type="checkbox" name="s_course_selection" value="'
					+ selectValue + '" checked /></td><td>'
					+ selectValue + ':&nbsp;'
					+ selectText + '</td></tr>';
			$('#student-course-table tbody').append(newRow);
			limit--;
		}
		$('#limit-number').html("Limit:" + limit);
		$('#course-popup').css('display', 'none');
	}

	function togglePopup() {
		if ($('#course-popup').css('display') == 'none') {
			$('#course-popup').css('display', 'block');
		} else {
			$('#course-popup').css('display', 'none');
		}
	}
</script>