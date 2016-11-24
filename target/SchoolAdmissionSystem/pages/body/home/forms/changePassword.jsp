<form method="post">
	<table>
		<thead>
			<tr>
				<th colspan="2">
					Change password
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="name-column">
					<label for="cur-password">
						Current password:
					</label>
				</td>
				<td class="input-column">
					<input id="cur-password" name="curPassword" type="password">
					<input id="cur-password-txt" name="curPasswordText" type="text">
					<button id="cur-password-btn" onclick="showHideText('#cur-password', event)">Show password</button>
				</td>
			</tr>
			<tr>
				<td>
					<label for="new-password">
						New password:
					</label>
				</td>
				<td>
					<input id="new-password" name="newPassword" type="password">
					<input id="new-password-txt" name="newPasswordText" type="text">
					<button id="new-password-btn" onclick="showHideText('#new-password', event)">Show password</button>
				</td>
			</tr>
			<tr>
				<td>
					<label for="cfm-password">
						Confirm password:
					</label>
				</td>
				<td>
					<input id="cfm-password" name="confirmPassword" type="password">
					<input id="cfm-password-txt" name="confirmPasswordText" type="text">
					<button id="cfm-password-btn" onclick="showHideText('#cfm-password', event)">Show password</button>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td></td>
				<td>
					<input id="password-submit" type="submit" value="Change password" onclick="submitPassword(event)">
				</td>
			</tr>
		</tfoot>
	</table>
</form>