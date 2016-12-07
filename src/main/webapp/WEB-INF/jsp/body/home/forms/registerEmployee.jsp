<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<div style="width: 100%; padding-bottom: 10px; border-bottom: 1px solid rgba(0,0,0,0.2);">
		<button onclick="toggleEmployeeApplication();">Employee Application</button>
	</div>
	<div id="registerEmployeeDiv" style="display:none; width: 100%;">
		<c:choose>
			<c:when test="${ empty employee }">
				<!-- Has not applied yet -->
				<form>
					
				</form>
			</c:when>
			<c:otherwise>
				<!-- Already applied -->
				<table style="width: 50%;">
					
				</table>
			</c:otherwise>
		</c:choose>
	</div>
	<script>
		function toggleEmployeeApplication()
		{
			if ($('#registerEmployeeDiv').css("display") == "none")
				$('#registerEmployeeDiv').css("display", "block");
			else
				$('#registerEmployeeDiv').css("display", "none");
		}
	</script>
</div>