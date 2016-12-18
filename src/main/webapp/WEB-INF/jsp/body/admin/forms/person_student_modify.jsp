<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<table>
		<thead>
			<tr style="cursor: pointer;">
				<th colspan="2">Student Information</th>
			</tr>
		</thead>
		<tbody id="student-body" style="width: 100%;">
			<tr>
				<td style="text-align:right; width: 150px;">
					<input id="studentCheckBox" type="checkbox" name="attachStudent" value="attach" onclick="toggleForm('student')" />
					<input id="studentAction" type="hidden" name="studentAction" value="delete" />
				</td>
				<td style="width: calc(100% - 150px);">
					<span style="font-style: italic; font-size: 75%;">Click on the checkbox to attach/remove student to this person.</span>
				</td>
			</tr>
		</tbody>
		<tbody id="studentDetail">
			<tr>
				<td style="width: 150px;"><label for="s_major">* Department:</label></td>
				<td style="width: calc(100% - 150px);">
					<c:choose>
						<c:when test="${ not empty department_list }">
							<select name="s_department">
								<c:forEach var="department" items="${ department_list }">
									<c:choose>
										<c:when test="${ empty student || empty student.getDepartment() || student.getDepartment().getDeptId() ne department.getDeptId() }">
											<option value="${ department.getDeptId() }">
												<c:out value="${ department.getCode().getDept_name() }" />
											</option>
										</c:when>
										<c:otherwise>
											<option value="${ department.getDeptId() }" selected>
												<c:out value="${ department.getCode().getDept_name() }" />
											</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</c:when>
						<c:otherwise>
							<span style="color:red;">There is no department available. Please create at least one department first.</span>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</tbody>
	</table>
	
	<script>
		$(document).ready(function(){
			if (${ not empty student }) {
				$('#studentCheckBox').prop('checked', true);
			}
			toggleForm('student');
		});
	</script>
</div>