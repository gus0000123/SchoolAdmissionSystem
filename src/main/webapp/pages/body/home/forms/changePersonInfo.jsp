<form method="post">
	<table>
		<thead>
			<tr>
				<th colspan="2">
					General information
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="name-column">
					<label for="firstname">
						First name:
					</label>
				</td>
				<td class="input-column">
					<input id="firstname" name="firstname" type="text" disabled>
					<button onclick="unlockInput(event, '#firstname')">e</button>
				</td>
			</tr>
			<tr>
				<td>
					<label for="lastname">
						Last name:
					</label>
				</td>
				<td>
					<input id="lastname" name="lastname" type="text" disabled>
					<button onclick="unlockInput(event, '#lastname')">e</button>
				</td>
			</tr>
			<tr>
				<td>
					<label for="apartment">
						Apartment #:
					</label>
				</td>
				<td>
					<input id="apartment" name="apartment" type="text" disabled>
					<button onclick="unlockInput(event, '#apartment')">e</button>
				</td>
			</tr>
			<tr>
				<td>
					<label for="address">
						Street Address:
					</label>
				</td>
				<td>
					<input id="address" name="address" type="text" disabled>
					<button onclick="unlockInput(event, '#address')">e</button>
				</td>
			</tr>
			<tr>
				<td>
					<label for="city">
						City:
					</label>
				</td>
				<td>
					<input id="city" name="city" type="text" disabled>
					<button onclick="unlockInput(event, '#city')">e</button>
				</td>
			</tr>
			<tr>
				<td>
					<label for="province">
						Province/State:
					</label>
				</td>
				<td>
					<input id="province" name="province" type="text" disabled>
					<button onclick="unlockInput(event, '#province')">e</button>
				</td>
			</tr>
			<tr>
				<td>
					<label for="country">
						Country:
					</label>
				</td>
				<td>
					<input id="country" name="country" type="text" disabled>
					<button onclick="unlockInput(event, '#country')">e</button>
				</td>
			</tr>
			<tr>
				<td>
					<label for="postal">
						Postal Code:
					</label>
				</td>
				<td>
					<input id="postal" name="postal" type="text" disabled>
					<button onclick="unlockInput(event, '#postal')">e</button>
				</td>
			</tr>
			<tr>
				<td>
					<label for="home_no">
						Home phone:
					</label>
				</td>
				<td>
					<input id="home_no" name="home_no" type="text" disabled>
					<button onclick="unlockInput(event, '#home_no')">e</button>
				</td>
			</tr>
			<tr>
				<td>
					<label for="work_no">
						Work phone:
					</label>
				</td>
				<td>
					<input id="work_no" name="work_no" type="text" disabled>
					<button onclick="unlockInput(event, '#work_no')">e</button>
				</td>
			</tr>
			<tr>
				<td>
					<label for="mobile_no">
						Mobile phone:
					</label>
				</td>
				<td>
					<input id="mobile_no" name="mobile_no" type="text" disabled>
					<button onclick="unlockInput(event, '#mobile_no')">e</button>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td></td>
				<td>
					<input id="submit_info" type="submit" value="Apply changes" onclick="submitInfo(event)">
					<input id="discard_info" type="button" value="Discard change" onclick="lockResetForm(event)">
				</td>
			</tr>
		</tfoot>
	</table>
</form>