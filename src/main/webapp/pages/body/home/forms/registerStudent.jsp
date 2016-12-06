<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<div style="width: 100%; padding-bottom: 10px; border-bottom: 1px solid rgba(0,0,0,0.2);">
		<button onclick="toggleStudentApplication();">Student Application</button>
	</div>
	<div id="registerStudentDiv" style="display:none; width: 100%;">
		<c:choose>
			<c:when test="${ empty student }">
				<!-- Has not applied yet -->
				<form>
					<table>
						<thead>
							<tr><th colspan="2">Fill the following information</th></tr>
						</thead>
						<tbody>
							<tr>
								<td>Major:</td>
								<td>
									<!-- Major option -->
									<select name="major" required>
										
									</select>
								</td>
							</tr>
							<tr>
								<td>Minor:</td>
								<td>
									<!-- Minor option -->
									<select name="minor">
										<option value="none">None</option>
									</select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr><td colspan="2"></td></tr>
						</tfoot>
					</table>
				</form>
			</c:when>
			<c:otherwise>
				<!-- Already applied -->
				<table style="width: 50%;">
					<thead>
						<tr><th colspan="2" style="text-align: left;">Application Status</th></tr>
					</thead>
					<tbody>
						<tr>
							<td style="width: 150px; text-align:right;">Admission status:</td>
							<td style="width: calc(100% - 150px);"><c:out value="${ student.getAdmissionStatus().getStatus() }" /></td>
						</tr>
						<tr>
							<td style="text-align:right;">Detail:</td>
							<td><c:out value="${ student.getAdmissionStatus().getDescription() }" /></td>
						</tr>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
	<script>
		function toggleStudentApplication()
		{
			if ($('#registerStudentDiv').css("display") == "none")
				$('#registerStudentDiv').css("display", "block");
			else
				$('#registerStudentDiv').css("display", "none");
		}
	</script>
</div>