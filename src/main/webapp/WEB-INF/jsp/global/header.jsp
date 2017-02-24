<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<div id="header-wrapper">
		<table>
			<tr>
				<td>
					<div id="institution-name">&nbsp;&nbsp;Montreal College</div>
				</td>
				<td>
					<div id="header-menu">
						<div id="signout-btn" class="header-btn">
							<form id="signout" action="logout" method="post">
								<img src="<c:url value="resources/images/signout-icon.png" />"
									onclick="launchForm(event, 'signout');">
							</form>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
</header>