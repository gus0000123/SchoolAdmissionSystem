<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<table>
		<thead>
			<tr style="cursor: pointer;">
				<th colspan="2">
					Student Information
					<c:if test="${ not empty p_id }">
						<input type="hidden" name="id" value="${ p_id }" />
					</c:if>
				</th>
			</tr>
		</thead>
		<tbody id="student-body" style="width: 100%;">
			<tr>
				<td style="width: 150px;"><label for="s_major">Major:</label></td>
				<td style="width: calc(100% - 150px);">
					<c:choose>
						<c:when test="${ empty s_major }">
							<input name="s_major" type="text" placeholder="Enter major" />
						</c:when>
						<c:otherwise>
							<input name="s_major" type="text" placeholder="Enter major" value="${ s_major }" />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td style="width: 150px;"><label for="s_minor">Minor:</label></td>
				<td style="width: calc(100% - 150px);">
					<c:choose>
						<c:when test="${ empty s_minor }">
							<input name="s_minor" type="text" placeholder="Enter minor" />
						</c:when>
						<c:otherwise>
							<input name="s_minor" type="text" placeholder="Enter minor" value="${ s_minor }" />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td style="width: 150px;"><label for="s_start_date">Enrollment date:</label></td>
				<td style="width: calc(100% - 150px);">
					<c:choose>
						<c:when test="${ empty s_start_date }">
							<input name="s_start_date" type="text" placeholder="Enter enrollment date" />
						</c:when>
						<c:otherwise>
							<input name="s_start_date" type="text" placeholder="Enter enrollment date" value="${ s_start_date }" />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td style="width: 150px;"><label for="s_admission_status">* Status:</label></td>
				<td style="width: calc(100% - 150px);">
					<select name="s_admission_status">
						<c:forEach var="status" items="${ student_status_list }">
							<c:choose>
								<c:when test="${ empty student || empty student.getAdmissionStatus() || student.getAdmissionStatus().getStatus() ne status.getStatus() }">
									<option value="${ status.getStatus() }">
										<c:out value="${ status.getStatus() }" />
									</option>
								</c:when>
								<c:otherwise>
									<option value="${ status.getStatus() }" selected>
										<c:out value="${ status.getStatus() }" />
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td style="width: 150px;"><label for="s_department">* Department:</label></td>
				<td style="width: calc(100% - 150px);">
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
				</td>
			</tr>
		</tbody>
	</table>
</div>