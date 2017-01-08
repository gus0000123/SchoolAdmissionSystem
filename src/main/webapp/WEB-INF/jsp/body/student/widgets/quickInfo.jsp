<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/global/tableList.css" />">
<style>
	.table-selector tbody tr td {
		font-size: 75%;
	}
	.table-selector tbody tr td:first-child {
		 width: 150px;
		 text-align:right;
		 padding-right: 10px;
		 font-weight: 600;
	}
</style>
<article id="activity-article" style="margin-left: 20px;">
	<div class="article-header">
		STUDENT INFORMATION
	</div>
</article>
<div style="width: 100%;">
	<table class="table-selector">
		<tbody>
			<tr class="row-selector">
				<td>Department:</td>
				<td><c:out value="${ student.department.getCode().dept_name }" /></td>
			</tr>
			<tr class="row-selector">
				<td>Major:</td>
				<td><c:out value="${ student.major }" /></td>
			</tr>
			<tr class="row-selector">
				<td>Minor:</td>
				<td><c:out value="${ student.minor }" /></td>
			</tr>
			<tr class="row-selector">
				<td>Credit:</td>
				<td><c:out value="${ student.credit }" /></td>
			</tr>
			<tr class="row-selector">
				<td>Year:</td>
				<td><c:out value="${ student.year }" /></td>
			</tr>
			<tr class="row-selector">
				<td>Enrollment date:</td>
				<td><c:out value="${ student.getStartDate() }" /></td>
			</tr>
			<tr class="row-selector">
				<td>Admission status:</td>
				<td><c:out value="${ student.getAdmissionStatus().status }" /></td>
			</tr>
		</tbody>
	</table>
</div>