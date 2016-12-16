<table>
	<thead>
		<tr onclick="toggleForm('person-body');" style="cursor: pointer;"><th colspan="2">Personal information</th></tr>
	</thead>
	<tbody id="person-body" style="width: 100%; display: none;">
		<tr>
			<td style="width: 150px;"><label for="p_first_name">* First name:</label></td>
			<td style="width: calc(100% - 150px);"><input name="p_first_name" type="text" placeholder="Enter first name" /></td>
		</tr>
		<tr>
			<td><label for="p_middle_name">Middle name:</label></td>
			<td><input name="p_middle_name" type="text" placeholder="Enter middle name" /></td>
		</tr>
		<tr>
			<td><label for="p_last_name">* Last name:</label></td>
			<td><input name="p_last_name" type="text" placeholder="Enter last name" /></td>
		</tr>
		<tr>
			<td><label for="p_street_address">Street address:</label></td>
			<td><input name="p_street_address" type="text" placeholder="Enter street address" /></td>
		</tr>
		<tr>
			<td><label for="p_city">City:</label></td>
			<td><input name="p_city" type="text" placeholder="Enter city name" /></td>
		</tr>
		<tr>
			<td><label for="p_state">State:</label></td>
			<td><input name="p_state" type="text" placeholder="Enter state" /></td>
		</tr>
		<tr>
			<td><label for="p_country">Country</label></td>
			<td>
				<select name="p_country">
					<option>Placeholder</option>
				</select>
			</td>
		</tr>
		<tr>
			<td><label for="p_postal">Postal:</label></td>
			<td><input name="p_postal" type="text" placeholder="Enter postal code" /></td>
		</tr>	
		<tr>
			<td><label for="p_tel_no">Telephone:</label></td>
			<td><input name="p_tel_no" type="text" placeholder="Enter telephone number" /></td>
		</tr>
		<tr>
			<td><label for="p_email">* E-mail:</label></td>
			<td><input name="p_email" type="text" placeholder="Enter email address" /></td>
		</tr>
		<tr>
			<td><label for="p_gender">Gender:</label></td>
			<td><input name="p_gender" type="text" placeholder="PLACEHOLDER" /></td>
		</tr>
		<tr>
			<td><label for="p_sin">SIN:</label></td>
			<td><input name="p_sin" type="text" placeholder="Enter social insurance number" /></td>
		</tr>
	</tbody>
</table>