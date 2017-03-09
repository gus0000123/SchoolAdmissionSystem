<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
	<table>
		<thead>
			<tr style="cursor: pointer;">
				<th colspan="2">
					Personal information
					<form:input type="hidden" name="id" path="ID" />
				</th>
			</tr>
		</thead>
		<tbody id="person-body" style="width: 100%;">
			<tr>
				<td style="width: 150px;"><label for="p_first_name">* First name:</label></td>
				<td style="width: calc(100% - 150px);">
					<form:input id="p_first_name" type="text" placeholder="Enter first name" path="firstName" />
				</td>
			</tr>
			<tr>
				<td><label for="p_middle_name">Middle name:</label></td>
				<td><form:input id="p_middle_name" type="text" placeholder="Enter middle name" path="middleName" /></td>
			</tr>
			<tr>
				<td><label for="p_last_name">* Last name:</label></td>
				<td><form:input id="p_last_name" type="text" placeholder="Enter last name" path="lastName" /></td>
			</tr>
			<tr>
				<td><label for="p_street_address">Street address:</label></td>
				<td><form:input id="p_street_address" type="text" placeholder="Enter street address" path="address.streetAddress" /></td>
			</tr>
			<tr>
				<td><label for="p_city">City:</label></td>
				<td><form:input id="p_city" type="text" placeholder="Enter city name" path="address.city" /></td>
			</tr>
			<tr>
				<td><label for="p_state">State:</label></td>
				<td><form:input id="p_state" type="text" placeholder="Enter state" path="address.state" /></td>
			</tr>
			<tr>
				<td><label for="p_country">Country</label></td>
				<td>
					<jsp:include page="../../../global/countryDropdown.jsp" />
				</td>
			</tr>
			<tr>
				<td><label for="p_postal">Postal:</label></td>
				<td><form:input id="p_postal" type="text" placeholder="Enter postal code" path="address.postal" /></td>
			</tr>	
			<tr>
				<td><label for="p_tel_no">Telephone:</label></td>
				<td><form:input id="p_tel_no" type="text" placeholder="Enter telephone number" path="telNo" /></td>
			</tr>
			<tr>
				<td><label for="p_email">* E-mail:</label></td>
				<td><form:input id="p_email" type="text" placeholder="Enter email address" path="email" /></td>
			</tr>
			<tr>
				<td><label for="p_gender">Gender:</label></td>
				<td><form:input id="p_gender" type="text" placeholder="PLACEHOLDER" path="gender" /></td>
			</tr>
			<tr>
				<td><label for="p_sin">SIN:</label></td>
				<td><form:input id="p_sin" type="text" placeholder="Enter social insurance number" path="sin" /></td>
			</tr>
		</tbody>
	</table>
</div>