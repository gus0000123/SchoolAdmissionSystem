<table>
	<thead>
		<tr onclick="toggleForm('student-body');" style="cursor: pointer;">
			<th colspan="2">Student Information</th>
		</tr>
	</thead>
	<tbody id="student-body" style="width: 100%; display: none;">
		<tr>
			<td colspan="2" style="text-align: center;">
				<span style="font-style: italic;">This person does not contain student record. Click to create one.</span>
			</td>
		</tr>
		<tr>
			<td style="width: 150px;"><label for="s_major">* Major:</label></td>
			<td style="width: calc(100% - 150px);">
				<select name="s_major">
					<option>PLACEHOLDER</option>
				</select>
			</td>
		</tr>
		<tr>
			<td><label for="s_major">* Minor:</label></td>
			<td>
				<select name="s_minor">
					<option>PLACEHOLDER</option>
				</select>
			</td>
		</tr>
	</tbody>
</table>