<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<table>
		<thead style="width: 100%;">
			<tr style="cursor: pointer;">
				<th colspan="2">Employee Information</th>
			</tr>
		</thead>
		<tbody id="employee-body" style="width: 100%;">
			<tr>
				<td style="width: 150px; text-align:right;">
					<input id="employeeCheckBox" type="checkbox" name="e_attach" value="attach" onclick="toggleForm('employee')" />
					<input id="userAction" type="hidden" name="employeeAction" value="" />
				</td>
				<td style="width: calc(100% - 150px);">
					<span style="font-style: italic; font-size: 75%;">Click on the checkbox to attach/remove employee to this person.</span>
				</td>
			</tr>
		</tbody>
		<tbody id="employeeDetail" style="width: 100%;">
			<tr>
				<td style="width: 150px;"><label for="e_department">* Department:</label></td>
				<td style="width: calc(100% - 150px);">
					<c:choose>
						<c:when test="${ not empty department_list }">
							<select name="e_department">
								<c:forEach var="department" items="${ department_list }">
									<c:choose>
										<c:when test="${ empty employee || empty employee.getDepartment() || employee.getDepartment().getDeptId() ne department.getDeptId() }">
											<option value="${ department.getDeptId() }">
												<c:out value="${ department.getDept_code().getDept_name() }" />
											</option>
										</c:when>
										<c:otherwise>
											<option value="${ department.getDeptId() }" selected>
												<c:out value="${ department.getDept_code().getDept_name() }" />
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
			if (${ not empty employee }) {
				$('#employeeCheckBox').prop('checked', true);
			}
			toggleForm('employee');
		});
	</script>
</div>