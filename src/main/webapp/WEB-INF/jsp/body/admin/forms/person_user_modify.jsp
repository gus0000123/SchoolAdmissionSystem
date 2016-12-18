<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<table>
		<thead>
			<tr style="cursor: pointer;">
				<th colspan="2">User Information</th>
			</tr>
		</thead>
		<tbody id="userDetail" style="width: 100%;">
			<tr>
				<td style="text-align:right; width: 150px;"><label for="u_password">* Password:</label></td>
				<td style="width: calc(100% - 150px);"><input id="u_password" name="u_password" type="password" placeholder="Enter password" /></td>
			</tr>
			<tr>
				<td><label for="u_cpassword">* Confirm password:</label></td>
				<td><input id="u_cpassword" name="u_cpassword" type="text" placeholder="Enter password again" /></td>
			</tr>
			<tr>
				<td><label for="u_username">* Authority:</label></td>
				<td>
					<select name="authority">
						<option value="1">User</option>
						<option value="2">Moderator</option>
						<option value="3">Administrator</option>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
</div>