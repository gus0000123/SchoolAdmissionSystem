<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	.selection {
		outline: none;
		border: none;
		background: none;
		padding-top: 8px;
		padding-bottom: 8px;
		border-right: 5px solid rgba(0, 0, 0, 0);
		width: 95%;
		cursor: pointer;
		font-weight: 700;
		text-align: left;	
	}
	
	.selection:hover {
		background-color: rgba(0, 200, 255, 0.1);
		border-right: 5px solid rgba(0, 255, 200, 0.8);
	}
	
	.selection:active {
		background-color: rgba(0, 200, 255, 0.3);
	}
</style>
<div>
	<table style="width: 100%;">
		<tbody>
			<tr><td>
				<form method="post" action="TestTab">
					<input type="hidden" name="tab" value="messages" />
					<input type="hidden" name="sub_tab" value="compose" />
					<input type="submit" value="Compose" class="selection" />
				</form>
			</td></tr>
			<tr><td>
				<form method="post" action="TestTab">
					<input type="hidden" name="tab" value="messages" />
					<input type="hidden" name="sub_tab" value="inbox" />
					<input type="submit" value="Inbox" class="selection" />
				</form>
			</td></tr>
			<tr><td>
				<form method="post" action="TestTab">
					<input type="hidden" name="tab" value="messages" />
					<input type="hidden" name="sub_tab" value="sent" />
					<input type="submit" value="Sent" class="selection" />
				</form>
			</td></tr>
			<tr><td>
				<form method="post" action="TestTab">
					<input type="hidden" name="tab" value="messages" />
					<input type="hidden" name="sub_tab" value="trash" />
					<input type="submit" value="Trash" class="selection" />
				</form>
			</td></tr>
		</tbody>
	</table>
</div>