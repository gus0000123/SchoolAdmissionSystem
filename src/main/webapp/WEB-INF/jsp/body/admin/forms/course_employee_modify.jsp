<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
	<table>
		<thead>
			<tr><th colspan="2">Employee-related information</th></tr>
		</thead>
		<tbody>
			<!-- Course instructor -->
			<tr>
				<td style="width: 150px;"><label for="c_instructor">* Instructor:</label></td>
				<td style="width: calc(100% - 150px);">
					<form:select name="c_instructor" path="instructor">
						<form:options items="${ instructor_list }" itemValue="id" itemLabel="person.firstName" />
					</form:select>
				</td>
			</tr>
		</tbody>
	</table>
</div>