<table>
	<thead>
		<tr onclick="toggleForm('employee-body');" style="cursor: pointer;">
			<th colspan="2">Employee Information</th>
		</tr>
	</thead>
	<tbody id="employee-body" style="width: 100%; display: none;">
		<tr>
			<td colspan="2" style="text-align: center;">
				<span style="font-style: italic;">This person does not contain employee record. Click to create one.</span>
			</td>
		</tr>
		<tr>
			<td style="width: 150px;"><label for="e_department">* Department:</label></td>
			<td style="width: calc(100% - 150px);">
				<select name="e_department">
					<option>PLACEHOLDER</option>
				</select>
			</td>
		</tr>
		<tr>
			<td><label for="e_instructor">Instructor:</label></td>
			<td><input type="checkbox" name="e_instructor" value="true" /></td>
		</tr>
	</tbody>
</table>