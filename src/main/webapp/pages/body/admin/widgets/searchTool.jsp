<div>
	<form>
		<table class="search-table">
			<thead>
				<tr>
					<th>
						Filter result(s)
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td style="padding-top: 15px;">
						<input type="text" name="filter" placeholder="Matching word...">
					</td>
				</tr>
				<tr>
					<td id="advance-filter" style="display: none;">
						<input type="text" name="category" placeholder="dropdown_placeholder">
					</td>
				</tr>
				<tr>
					<td>
						<button id="filter-toggle" onclick="toggleFilter(event)">Advanced Filter</button>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td>
						<input type="submit" value="Search">
						<input type="reset" value="Clear">
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
</div>