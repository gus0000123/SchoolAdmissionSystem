<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form method="post">
	<table>
		<thead>
			<tr>
				<th colspan="2">
					Change e-mail address
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="name-column"><label for="cur-email">Current e-mail address:</label></td>
				<td class="input-column"><input id="cur-email" name="curEmail" type="text" value="${ user.person.email }" required disabled /></td>
			</tr>
			<tr>
				<td><label for="new-email">New e-mail address:</label></td>
				<td><input id="new-email" name="newEmail" type="text" required /></td>
			</tr>
			<tr>
				<td><label for="cfm-email">Confirm e-mail address:</label></td>
				<td><input id="cfm-email" name="cfmEmail" type="text" required /></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td></td>
				<td>
					<input id="email-btn" type="submit" value="Send confirmation e-mail" onclick="submitEmail">
				</td>
			</tr>
		</tfoot>
	</table>
</form>