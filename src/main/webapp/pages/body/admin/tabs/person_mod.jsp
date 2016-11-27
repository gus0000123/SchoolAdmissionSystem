<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Tab content for prospective student -->
<div id="tab-container">
	<div class="tab">
		<div class="column" style="width: 300px;">
			<jsp:include page="../widgets/searchTool.jsp" />
		</div>
		<div class="column">
			<table>
				<thead>
					<tr>
						<c:forEach var="title" items="${ tableHeader }">
							<td>
								<span><c:out value="${ title }"/></span>
							</td>
						</c:forEach>
						<td>
							<!-- Empty header for action -->
						</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="obj" items="${ list }">
						<tr>
							<c:forTokens var="col" items="${ obj.toString() }" delims=";">
								<td>
									<span><c:out value="${ col }"/></span>
								</td>
							</c:forTokens>
							<td>
								<form action="dataModification" method="post" style="display: inline-block;">
									<input type="hidden" name="object" value="${ obj }">
									<input type="hidden" name="redirect" value="modify">
									<input type="hidden" name="action" value="edit">
									<input type="submit" value="Edit">
								</form>
								<form action="dataModification" method="post" style="display: inline-block;">
									<input type="hidden" name="object" value="${ obj }">
									<input type="hidden" name="redirect" value="none">
									<input type="hidden" name="action" value="delete">
									<input type="submit" value="Delete">
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
				</tfoot>
			</table>
		</div>
	</div>
</div>