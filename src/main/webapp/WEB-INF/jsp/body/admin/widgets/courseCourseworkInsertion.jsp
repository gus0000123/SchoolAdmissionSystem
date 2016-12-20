<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#coursework-popup { display:none; position: fixed; width: 100%; height: 100%; top: 0; left: 0; background-color: rgba(0, 0, 0, 0.4); }
	#coursework-popup-float { position: fixed; width: 400px; margin-left: calc(50vw - 200px); background-color: white; padding: 20px; }
	#coursework-popup-float table { width: 100%; height: 100%; }
</style>
<c:if test="${ not empty course }">
	<div id="coursework-popup">
		<div id="coursework-popup-float">
			<form action="admin" method="post">
				<input type="hidden" name="tab" value="coursework" />
				<input type="hidden" name="mode" value="insert" />
				<input type="hidden" name="course_code" value="${ course.course_code }" /> <!-- this name required for forwarding to course controller -->
				<table style="width: 100%;">
					<thead>
						<tr><th colspan="2">Insert course work</th></tr>
					</thead>
					<tbody>
						<tr>
							<td style="width: 100px;">* Title</td>
							<td style="width: calc(100% - 100px);"><input type="text" name="cw_coursework_name" /></td>
						</tr>
						<tr>
							<td>* Description</td>
							<td><input type="text" name="cw_coursework_description" /></td>
						</tr>
						<tr>
							<td>* Contribution</td>
							<td><input type="number" name="cw_contribution" min="1" max="100" value="1" /></td>
						</tr>
						<tr>
							<td>* Mark</td>
							<td><input type="number" name="cw_max_mark" min="1" value="10" /></td>
						</tr>
						<tr>
							<td>* Deadline date</td>
							<td><input type="date" name="cw_deadline" /></td>
						</tr>
						<tr>
							<td>* Deadline time</td>
							<td><input type="text" name="cw_deadline_time" /></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<input type="submit" value="Add" />
								<div onclick="togglePopup();">Cancel</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</form>
		</div>
	</div>
	
	<script>	
		function togglePopup() {
			if ($('#coursework-popup').css('display') == 'none') {
				$('#coursework-popup').css('display', 'block');
			} else {
				$('#coursework-popup').css('display', 'none');
			}
		}
	</script>
</c:if>