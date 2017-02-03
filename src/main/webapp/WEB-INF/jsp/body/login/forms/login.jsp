<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
	<form:form id="login-form" action="login" method="post" commandName="attempt">
		<table id="login-table" class="middle-table">
			<thead>
				<tr>
					<td>
						<span class="table-header">LOGIN PORTAL</span>
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<c:if test="${ error eq 'user' }">
							<span id="login-user-error" class="error-text">Please input correct username.</span>
						</c:if>
						<c:choose>
							<c:when test="${ empty account_name }">
								<form:input id="login-user" placeholder="Username" path="username" />
							</c:when>
							<c:otherwise>
								<form:input id="login-user" placeholder="Username" path="username" value="${ account_name }" />
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>
						<c:if test="${ error eq 'password' }">
							<span id="login-password-error" class="error-text">Please input correct password.</span>
						</c:if>
						<form:input id="login-password" type="password" placeholder="Password" path="password" />
					</td>
				</tr>
				<tr>
					<td>
						<c:if test="${ error eq 'unknown' }">
							<span id="login-password-error" class="error-text">Unknown error has occurred.</span>
						</c:if>
						<input id="login-submit" type="submit" value="Sign in" onclick="signin(event)">
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td>
						<span>
							Do not own an account?
							<span id="register-button" onclick="switchToRegister()">Register here</span>
						</span>
					</td>
				</tr>
			</tfoot>
		</table>
	</form:form>
</div>