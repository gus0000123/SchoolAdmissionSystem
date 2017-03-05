<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:if test="${ mode eq 'edit' }">
	<div>
		<table id="course-coursework-table">
			<thead>
				<tr><th>Course works</th></tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${ not empty all_courseworks }">
						<tr>
							<td>
								<spring:bind path="course_works">
									<form:checkboxes path="course_works" items="${ all_courseworks }" itemLabel="coursework_name" itemValue="coursework_id" onclick="selectCourseWork(event, this.value);" />
								</spring:bind>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr><td style="text-align:center; font-size: 75%; font-style:italic;">There is no available course work for this course.</td></tr>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr><td>
					<c:if test="${ mode eq 'edit' }">
						<button id="addCwBtn" class="bottom-button" onclick="launchForm(event, 'insert-coursework-form')">Add coursework</button>
						<button id="editCwBtn" class="bottom-button" onclick="launchForm(event, 'edit-coursework-form')" style="display:none;">Edit coursework</button>
						<input id="cw_text" type="text" value="" disabled style="width: 200px" />
					</c:if>
				</td></tr>
			</tfoot>
		</table>
	</div>
</c:if>