<div>
	<form id="login-form" action="login" method="post">
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
						<span id="login-user-error" class="error-text">Please input correct information.</span>
						<input id="login-user" type="text" placeholder="Username" name="user">
					</td>
				</tr>
				<tr>
					<td>
						<span id="login-password-error" class="error-text">Please input correct information.</span>
						<input id="login-password" type="password" placeholder="Password" name="password">
					</td>
				</tr>
				<tr>
					<td>
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