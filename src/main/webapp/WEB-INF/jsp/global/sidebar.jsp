<aside id="side-panel">
	<form id="homeSideButtonForm" action="TestTab" method="post">
		<input type="hidden" name="tab" value="overview" />
		<button onclick="launchForm(event, 'homeSideButtonForm');">
			<img src="${pageContext.servletContext.contextPath}/images/home-icon.png">
		</button>
	</form>
	<form action="toolTransition" method="post">
		<input type="hidden" name="page" value="User" />
		<button>
			<img src="${pageContext.servletContext.contextPath}/images/application-icon.png">
		</button>
	</form>
</aside>