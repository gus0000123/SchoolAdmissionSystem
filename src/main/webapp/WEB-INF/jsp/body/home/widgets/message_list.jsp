<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
	.row-selector {
		background-color: rgba(200, 200, 200, 0.3);
		border-bottom: 3px solid rgb(255, 255, 255);
		cursor: pointer;
	}

	.row-selector:hover {
		background-color: rgba(0, 200, 255, 0.1);
	}
	
	.row-selector:active {
		background-color: rgba(0, 255, 200, 0.3);
	}
	
	.bottom-button {
		background: none;
		outline: none;
		border: none;
		cursor: pointer;
		padding-top: 8px;
		border-top: 5px solid rgba(200, 200, 200, 0.0);
		width: 150px;
		font-weight: 700;
		padding-left: 0;
		margin-left: 0;
	}
	
	.bottom-button:hover {
		border-top: 5px solid rgba(0, 200, 255, 0.5);
	}
	
	.bottom-button:active {
		border-top: 5px solid rgba(0, 255, 200, 0.5);
	}
</style>
<div>
	<form id="messagesActionForm" action="TestMessage" method="post">
		<!-- Temporary refresh -->
		<input type="hidden" name="tab" value="messages" />
		<table style="border-collapse: collapse; width: 100%; margin-top: 0; margin-bottom: 0;">
			<thead>
				<tr>
					<th style="width: 13px;">
						<c:if test="${ sub_tab eq 'inbox' }">
							<c:if test="${ fn:length(all_messages) gt 0 }">
								<input id="select-all" type="checkbox" name="select_all" value="all" onclick="selectAll()" />
							</c:if>
						</c:if>
						<c:if test="${ sub_tab eq 'trash' }">
							<c:if test="${ fn:length(all_messages) gt 0 }">
								<input id="select-all" type="checkbox" name="select_all" value="all" onclick="selectAll()" />
							</c:if>
						</c:if>
					</th>
					<th style="width: 55%;">Title</th>
					<th style="width: 25%;">
						<c:choose>
							<c:when test="${ sub_tab eq 'inbox' }">
								Sender
							</c:when>
							<c:when test="${ sub_tab eq 'sent' }">
								Receiver
							</c:when>
							<c:when test="${ sub_tab eq 'trash' }">
								Sender
							</c:when>
						</c:choose>
					</th>
					<th style="width: 15%;">Date</th>
				</tr>
			</thead>
			<tbody style="font-size: 80%;">
				<c:set var="i" scope="page" value="0" />
				<c:choose>
					<c:when test="${ empty all_messages }">
						<tr><td colspan="4" style="text-align: center;">
								<span style="font-style: italic;">There is no message at this time</span>
						</td></tr>
					</c:when>
					<c:otherwise>
						<!-- action -->
						<input id="action_input" type="hidden" name="action" value="list" />
						<!-- view message -->
						<input id="select_message_input" type="hidden" name="select_message" value="" />
						<c:set var="i" scope="page" value="1" />
						<c:forEach var="message" items="${ all_messages }">
							<tr class="row-selector">
								<td>
									<input type="hidden" name="message_id" value="${ message.getId() }" />
									<c:if test="${ sub_tab eq 'inbox' }">
										<input id="row-${ i }" type="checkbox" name="selection" value="${ message.getId() }" onclick="selectRow(${ i })" />
									</c:if>
									<c:if test="${ sub_tab eq 'trash' }">
										<input id="row-${ i }" type="checkbox" name="selection" value="${ message.getId() }" onclick="selectRow(${ i })" />
									</c:if>
								</td>
								<td  onclick="viewMessage(event, ${ message.getId() })">
									<c:choose>
										<c:when test="${ message.important }">
											<span style="color: red;">
												<c:out value="${ message.headline }" />
											</span>
										</c:when>
										<c:otherwise>
											<c:out value="${ message.headline }" />
										</c:otherwise>
									</c:choose>
								</td>
								<td onclick="viewMessage(event, ${ message.getId() })">
									<c:out value="${ message.sender.firstName }" />&nbsp;
									<c:out value="${ message.sender.lastName }" />
								</td>
								<td onclick="viewMessage(event, ${ message.getId() })">
									<c:out value="${ fn:split(message.creation_time, ' ')[0] }" />
								</td>
							</tr>
							<!-- increase counter -->
							<c:set var="i" scope="page" value="${ i + 1 }" />
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
			<c:if test="${ sub_tab eq 'inbox' }">
				<c:if test="${ i gt 0 }">
					<tfoot><tr><td colspan="4">
						<input type="hidden" name="sub_tab" value="inbox" />
						<input class="bottom-button" type="submit" value="Delete selected" onclick="launchForm(event, 'messagesActionForm')" />
					</td></tr></tfoot>
				</c:if>
			</c:if>
			<c:if test="${ sub_tab eq 'trash' }">
				<c:if test="${ i gt 0 }">
					<tfoot><tr><td colspan="4">
						<input type="hidden" name="sub_tab" value="trash" />
						<input class="bottom-button" type="submit" value="Restore selected" onclick="launchForm(event, 'messagesActionForm')" />
					</td></tr></tfoot>
				</c:if>
			</c:if>
		</table>
	</form>
	<script>
		var counter = 0;
	
		function selectAll() {
			var count = 1;
			for (count = 1; count <= ${ i }; count++) {
				if ($('#select-all').is(':checked')) $('#row-' + count).prop('checked', true);
				else $('#row-' + count).prop('checked', false);
			}

			if ($('#select-all').is(':checked')) {
				counter = ${ i } - 1;
			} else {
				counter = 0;
			}
		}

		function selectRow(index) {
			if ($('#row-' + index).is(':checked')) {
				counter++;
			} else {
				counter--;
			}
			
			if (counter >= ${ i } - 1) {
				$('#select-all').prop('checked', true);
				counter = ${ i } - 1
			} else {
				$('#select-all').prop('checked', false);
			}
		}

		function viewMessage(e, message_id) {
			$('#messagesActionForm').attr("action", "TestTab");
			$('#select_message_input').val(message_id);
			$('#action_input').val("view");
			launchForm(e, 'messagesActionForm');
		}
	</script>
</div>