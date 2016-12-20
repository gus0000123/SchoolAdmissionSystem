<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<table>
		<thead>
			<tr style="cursor: pointer;">
				<th colspan="2">
					Course Information
					<!-- Course code -->
					<c:if test="${ not empty course }">
						<input type="hidden" name="c_course_code" value="${ course.course_code }" />
					</c:if>
				</th>
			</tr>
		</thead>
		<tbody id="course-body" style="width: 100%;">
			<!-- Class level -->
			<tr>
				<td style="width: 150px;"><label for="c_class_level">* Class Level:</label></td>
				<td style="width: calc(100% - 150px);">
					<c:choose>
						<c:when test="${ empty course }">
							<input name="c_class_level" type="number" min="0" max="9" value="0" />
						</c:when>
						<c:otherwise>
							<input name="c_class_level" type="number" min="0" max="9" value="${ course.class_level }" />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<!-- Course number -->
			<tr>
				<td><label for="c_course_number">* Course number:</label></td>
				<td>
					<c:choose>
						<c:when test="${ empty course}">
							<input name="c_course_number" type="number" min="0" max="99" value="0" />
						</c:when>
						<c:otherwise>
							<input name="c_course_number" type="number" min="0" max="99" value="${ course.course_number }" />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<!-- Course section -->
			<tr>
				<td><label for="c_section">* Section:</label></td>
				<td>
					<c:choose>
						<c:when test="${ empty course}">
							<input name="c_section" type="number" min="0" max="9" value="0" />
						</c:when>
						<c:otherwise>
							<input name="c_section" type="number" min="0" max="9" value="${ course.section }" />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<!-- Course name -->
			<tr>
				<td><label for="c_course_name">* Course name:</label></td>
				<td>
					<c:choose>
						<c:when test="${ empty course}">
							<input name="c_course_name" type="text" />
						</c:when>
						<c:otherwise>
							<input name="c_course_name" type="text" value="${ course.course_name }" />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<!-- Course description -->
			<tr>
				<td><label for="c_course_description">Course description:</label></td>
				<td>
					<c:choose>
						<c:when test="${ empty course && empty course.course_name }">
							<input name="c_course_description" type="text" />
						</c:when>
						<c:otherwise>
							<input name="c_course_description" type="text" value="${ course.course_description }" />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<!-- Course capacity -->
			<tr>
				<td><label for="c_capacity">Capacity:</label></td>
				<td>
					<c:choose>
						<c:when test="${ empty course}">
							<input name="c_capacity" type="number" min="0" max="999" />
						</c:when>
						<c:otherwise>
							<input name="c_section" type="number" min="0" max="999" value="${ course.capacity }" />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<!-- Course credit -->
			<tr>
				<td><label for="c_credit">Credit:</label></td>
				<td>
					<c:choose>
						<c:when test="${ empty course}">
							<input name="c_credit" type="number" min="0" max="9" />
						</c:when>
						<c:otherwise>
							<input name="c_credit" type="number" min="0" max="9" value="${ course.credit }" />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<!-- Course isActive? -->
			<tr>
				<td><label for="c_active">Is open:</label></td>
				<td>
					<c:choose>
						<c:when test="${ empty course }">
							<input name="c_active" type="checkbox" checked />
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${ course.is_active eq true }">
									<input name="c_active" type="checkbox" checked />
								</c:when>
								<c:otherwise>
									<input name="c_active" type="checkbox" />
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<!-- Course department -->
			<tr>
				<td><label for="c_department">* Department:</label></td>
				<td>
					<select name="c_department">
						<c:forEach var="department" items="${ department_list }">
							<c:choose>
								<c:when test="${ empty course || empty course.getDepartment() || course.getDepartment().getDeptId() ne department.getDeptId() }">
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
			<!-- TODO: Course's pre-requisite (if time allowed) -->
		</tbody>
	</table>
</div>