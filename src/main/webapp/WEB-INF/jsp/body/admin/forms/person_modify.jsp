<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
	<thead>
		<tr style="cursor: pointer;">
			<th colspan="2">
				Personal information
				<c:if test="${ not empty p_id }">
					<input type="hidden" name="id" value="${ p_id }" />
				</c:if>
			</th>
		</tr>
	</thead>
	<tbody id="person-body" style="width: 100%;">
		<tr>
			<td style="width: 150px;"><label for="p_first_name">* First name:</label></td>
			<td style="width: calc(100% - 150px);">
				<c:choose>
					<c:when test="${ empty p_first_name }">
						<input name="p_first_name" type="text" placeholder="Enter first name" required />
					</c:when>
					<c:otherwise>
						<input name="p_first_name" type="text" placeholder="Enter first name" value="${ p_first_name }" required />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td><label for="p_middle_name">Middle name:</label></td>
			<td>
				<c:choose>
					<c:when test="${ empty p_middle_name }">
						<input name="p_middle_name" type="text" placeholder="Enter middle name" />
					</c:when>
					<c:otherwise>
						<input name="p_middle_name" type="text" placeholder="Enter middle name" value="${ p_middle_name }" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td><label for="p_last_name">* Last name:</label></td>
			<td>
				<c:choose>
					<c:when test="${ empty p_last_name }">
						<input name="p_last_name" type="text" placeholder="Enter last name" required />
					</c:when>
					<c:otherwise>
						<input name="p_last_name" type="text" placeholder="Enter last name" value="${ p_last_name }" required />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td><label for="p_street_address">Street address:</label></td>
			<td>
				<c:choose>
					<c:when test="${ empty p_street_address }">
						<input name="p_street_address" type="text" placeholder="Enter street address" />
					</c:when>
					<c:otherwise>
						<input name="p_street_address" type="text" placeholder="Enter street address" value="${ p_street_address }" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td><label for="p_city">City:</label></td>
			<td>
				<c:choose>
					<c:when test="${ empty p_city }">
						<input name="p_city" type="text" placeholder="Enter city name" />
					</c:when>
					<c:otherwise>
						<input name="p_city" type="text" placeholder="Enter city name" value="${ p_city }" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td><label for="p_state">State:</label></td>
			<td>
				<c:choose>
					<c:when test="${ empty p_state }">
						<input name="p_state" type="text" placeholder="Enter state" />
					</c:when>
					<c:otherwise>
						<input name="p_state" type="text" placeholder="Enter state" value="${ p_state }" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td><label for="p_country">Country</label></td>
			<td>
				<select name="p_country">
					<jsp:include page="../../../global/countryDropdown.jsp" />
				</select>
			</td>
		</tr>
		<tr>
			<td><label for="p_postal">Postal:</label></td>
			<td>
				<c:choose>
					<c:when test="${ empty p_postal }">
						<input name="p_postal" type="text" placeholder="Enter postal code" />
					</c:when>
					<c:otherwise>
						<input name="p_postal" type="text" placeholder="Enter postal code" value="${ p_postal }" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>	
		<tr>
			<td><label for="p_tel_no">Telephone:</label></td>
			<td>
				<c:choose>
					<c:when test="${ empty p_tel_no }">
						<input name="p_tel_no" type="text" placeholder="Enter telephone number" />
					</c:when>
					<c:otherwise>
						<input name="p_tel_no" type="text" placeholder="Enter telephone number" value="${ p_tel_no }" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td><label for="p_email">* E-mail:</label></td>
			<td>
				<c:choose>
					<c:when test="${ empty p_email }">
						<input name="p_email" type="text" placeholder="Enter email address" required />
					</c:when>
					<c:otherwise>
						<input name="p_email" type="text" placeholder="Enter email address" value="${ p_email }" required />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td><label for="p_gender">Gender:</label></td>
			<td>
				<input name="p_gender" type="text" placeholder="PLACEHOLDER" />
			</td>
		</tr>
		<tr>
			<td><label for="p_sin">SIN:</label></td>
			<td>
				<c:choose>
					<c:when test="${ empty p_sin }">
						<input name="p_sin" type="text" placeholder="Enter social insurance number" />
					</c:when>
					<c:otherwise>
						<input name="p_sin" type="text" placeholder="Enter social insurance number" value="${ p_sin }" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</tbody>
</table>