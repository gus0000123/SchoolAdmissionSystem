<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<form id="login-form" action="TestLogin" method="post">
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
						<input id="login-user" type="text" placeholder="Username" name="user">
					</td>
				</tr>
				<tr>
					<td>
						<c:if test="${ error eq 'password' }">
							<span id="login-password-error" class="error-text">Please input correct password.</span>
						</c:if>
						<input id="login-password" type="password" placeholder="Password" name="password">
					</td>
				</tr>
				<tr>
					<td>
						<c:if test="${ error eq 'unknown' }">
							<span id="login-password-error" class="error-text">Unknown error has occurred.</span>
						</c:if>
						<input type="hidden" name="action" value="login" />
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
	</form>
</div>