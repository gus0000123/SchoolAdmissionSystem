<div>
	<form id="register-form" action="register" method="post">
		<table id="register-table" class="middle-table">
			<thead>
				<tr>
					<td>
						<span class="table-header">REGISTRATION</span>
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<span id="register-user-error" class="error-text">Please input correct information.</span>
						<input id="register-user" type="text" placeholder="Username" name="user">
					</td>
				</tr>
				<tr>
					<td>
						<span id="register-first-name-error" class="error-text">Please input correct information.</span>
						<input id="register-first-name" type="text" placeholder="First name" name="firstName">
					</td>
				</tr>
				<tr>
					<td>
						<span id="register-last-name-error" class="error-text">Please input correct information.</span>
						<input id="register-last-name" type="text" placeholder="Last name" name="lastName">
					</td>
				</tr>
				<tr>
					<td>
						<span id="register-email-error" class="error-text">Please input correct information.</span>
						<input id="register-email" type="email" placeholder="E-mail address" name="email">
					</td>
				</tr>
				<tr>
					<td>
						<span id="register-password-error" class="error-text">Please input correct information.</span>
						<input id="register-password" type="password" placeholder="Password" name="password">
					</td>
				</tr>
				<tr>
					<td>
						<span id="register-confirm-password-error" class="error-text">Please put matching password.</span>
						<input id="register-confirm-password" type="password" placeholder="Confirm password" name="confirm-password">
					</td>
				</tr>
				<tr>
					<td>
						<span style="font-size: 80%">By clicking register, you agree to the <a href="#">privacy policy</a> of the institution.</span>
					</td>
				</tr>
				<tr>
					<td>
						<input id="register-submit" type="submit" value="Register" onclick="register(event)">
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td>
						<span>
							Already own an account?
							<span id="register-button" onclick="switchToLogin()">Login here</span>
						</span>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
</div>