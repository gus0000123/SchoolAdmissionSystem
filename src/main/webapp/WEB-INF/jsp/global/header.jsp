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
						<div id="mail-btn" class="header-btn">
							<form id="goToMessageIcon" action="TestTab" method="post">
								<input type="hidden" name="tab" value="messages" />
								<img src="<c:url value="resources/images/mail-icon.jpg" />" onclick="launchForm(event, 'goToMessageIcon');">
							</form>
						</div>
						<div id="announcement-btn" class="header-btn">
							<img src="<c:url value="resources/images/announcement-icon.png" />">
						</div>
						<div id="help-btn" class="header-btn">
							<img src="<c:url value="resources/images/help-icon.png" />">
						</div>
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