<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	.message_input {
		background: none;
		outline: none;
		border: none;
		background: rgba(200, 200, 200, 0.5);
	}
	
	.message_title {
		font-size: 150%;
	}
	
	textarea {
		resize: none;
		height: 50vh;
		width: 100%;
	}
</style>
<div>
	<form>
		<table style="width: 100%;">
			<thead>
				<tr><th>
					<c:choose>
						<c:when test="${ action eq 'view' }">
							<span class="message_title"><c:out value="${ message.headline }" /></span>
						</c:when>
						<c:when test="${ action eq 'compose' }">
							<input type="text" name="headline" placeholder="Message header" class="message_input message_title" style="width: 100%;" required />
						</c:when>
					</c:choose>
				</th></tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${ action eq 'view' }">
						<tr><td>
							From:&nbsp;<c:out value="${ message.receiver.firstName }" />&nbsp;
							<c:out value="${ message.receiver.lastName }" />
						</td></tr>
						<tr><td>
							To:&nbsp;<c:out value="${ message.sender.firstName }" />&nbsp;
							<c:out value="${ message.sender.lastName }" />
						</td></tr>
						<tr><td>
							<textarea disabled><c:out value="${ message.message }" /></textarea>
						</td></tr>
					</c:when>
					<c:otherwise>
						<tr><td>
							 <input type="text" name="receiver" placeholder="Receiver user name" class="message_input" style="width: 100%;" required />
						</td></tr>
						<tr><td>
							<input type="checkbox" name="isImportant" style="display:inline-block;" />
							<label for="isImportant"  style="display:inline-block;">&nbsp;Important message</label>
						</td></tr>
						<tr><td>
							<textarea name="message" required></textarea>
						</td></tr>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr><td>
					<c:choose>
						<c:when test="${ action eq 'view' }">
							<input type="hidden" name="action" value="" />
							<input type="submit" value="Reply" onclick="" />
							<input type="submit" value="Delete" onclick="" />
						</c:when>
						<c:otherwise>
							<input type="hidden" name="action" value="send" />
							<input type="submit" value="Send" />
						</c:otherwise>
					</c:choose>
				</td></tr>
			</tfoot>
		</table>
	</form>
</div>