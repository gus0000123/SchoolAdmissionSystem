<div>
	<form id="register-form" action="registerUser" method="post">
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
						<span id="register-first-name-error" class="error-text">Please input correct information.</span>
						<input id="register-first-name" type="text" placeholder="First name" name="first_name" required>
					</td>
				</tr>
				<tr>
					<td>
						<input id="register-middle-name" type="text" placeholder="Middle name" name="middle_name" required>
					</td>
				</tr>
				<tr>
					<td>
						<span id="register-last-name-error" class="error-text">Please input correct information.</span>
						<input id="register-last-name" type="text" placeholder="Last name" name="last_name" required>
					</td>
				</tr>
				<tr>
					<td>
						<span id="register-email-error" class="error-text">Please input correct information.</span>
						<input id="register-email" type="email" placeholder="E-mail address" name="email" required>
					</td>
				</tr>
				<tr>
					<td>
						<span id="register-password-error" class="error-text">Please input correct information.</span>
						<input id="register-password" type="password" placeholder="Password" name="password" required>
					</td>
				</tr>
				<tr>
					<td>
						<span id="register-confirm-password-error" class="error-text">Please put matching password.</span>
						<input id="register-confirm-password" type="password" placeholder="Confirm password" name="cpassword" required>
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