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
				<td class="name-column"><label for="cur-password">Current password:</label></td>
				<td class="input-column"><input id="cur-password" name="curPassword" type="password" /></td>
			</tr>
			<tr>
				<td><label for="new-password">New password:</label></td>
				<td><input id="new-password" name="newPassword" type="password" /></td>
			</tr>
			<tr>
				<td><label for="cfm-password">Confirm password:</label></td>
				<td><input id="cfm-password" name="confirmPassword" type="password" /></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td></td>
				<td><input id="password-submit" type="submit" value="Change password" onclick="submitPassword(event)"></td>
			</tr>
		</tfoot>
	</table>
</form>