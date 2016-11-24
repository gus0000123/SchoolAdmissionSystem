<article id="body-wrapper">
	<!-- Body content -->
	<div id="body-content">
		<jsp:include page="../../body/home/sections/header.jsp" />
		<jsp:include page="../../body/home/sections/tabSelector.jsp" />
					
		<!-- Tab content for prospective student -->
		<div id="tab-container">
			<div class="tab">
				<div class="column">
					<jsp:include page="../../body/home/forms/changePersonInfo.jsp" />
				</div>
				<div class="column">
					<jsp:include page="../../body/home/forms/changePassword.jsp" />
					<jsp:include page="../../body/home/forms/changeEmail.jsp" />
				</div>
			</div>
		</div>
	</div>
</article>