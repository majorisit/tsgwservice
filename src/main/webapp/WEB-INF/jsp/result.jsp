<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
}

.tg .tg-9pvc {
	font-family: Verdana, Geneva, sans-serif !important;;
	background-color: #9aff99;
	vertical-align: top
}

.tg .tg-sn89 {
	font-size: 12px;
	font-family: Verdana, Geneva, sans-serif !important;;
	vertical-align: top
}

.tg .tg-ejgj {
	font-family: Verdana, Geneva, sans-serif !important;;
	vertical-align: top
}
</style>

<table class="tg" width="100%">
	<tr>
		<td class="tg-9pvc" width="10%">Name</td>
		<td class="tg-ejgj" width="20%"><c:out
				value="${member.firstName}" /> <c:out value="${member.lastName}" /></td>
		<td class="tg-9pvc" width="15%">Veg Count</td>
		<td class="tg-ejgj" width="20%" align="center"><strong><c:out
					value="${member.veg}" /></strong></td>
		<td class="tg-9pvc" width="15%">Non-Veg Count</td>
		<td class="tg-ejgj" width="20%" align="center"><strong><c:out
					value="${member.nonVeg}" /></strong></td>
	</tr>
	<tr>
		<td class="tg-9pvc">Member Id</td>
		<td class="tg-ejgj"><strong><c:out
					value="${member.memeberId}" /></strong></td>
		<td class="tg-9pvc">Spouse</td>
		<td class="tg-ejgj"><c:out value="${member.spouseFirstName}" />
			<c:out value="${member.spouseLastName}" /></td>
		<td class="tg-9pvc">Kids</td>
		<td class="tg-ejgj"><c:out value="${member.kids}" /></td>
	</tr>
	<tr>
		<td class="tg-9pvc">Address</td>
		<td class="tg-ejgj" colspan="3"><c:out value="${member.address1}" />,<c:out
				value="${member.address2}" />,<c:out value="${member.city}" />,<c:out
				value="${member.state}" />,<c:out value="${member.zipCode}" /></td>
		<td class="tg-9pvc">Type</td>
		<td class="tg-ejgj"><c:out value="${member.type}" /> (Since <c:out
				value="${member.since}" />)</td>
	</tr>
	<tr>
		<td class="tg-9pvc">Phones</td>
		<td class="tg-ejgj"><c:out value="${member.phone1}" /> , <c:out
				value="${member.phone2}" /></td>
		<td class="tg-9pvc">Email Primary</td>
		<td class="tg-ejgj"><c:out value="${member.email1}" /></td>
		<td class="tg-9pvc">Email Secondary</td>
		<td class="tg-ejgj"><c:out value="${member.email2}" /></td>
	</tr>
	<tr>
		<td class="tg-9pvc">Wristbands</td>
		<td class="tg-ejgj"><input type="text" id="wristbands" size="5"
			maxlength="2"></td>
		<td class="tg-9pvc">Address Changed ?</td>
		<td class="tg-ejgj"><input type="checkbox" id="addressChanged" value="true"/></td>
		<td class="tg-9pvc">Thendral Mullai Issued ?</td>
		<td class="tg-ejgj"><input type="checkbox" id="thendralMullai" value="true"
			checked></td>
	</tr>
	<tr>
		<td colspan="6" align="center"><a href='#' id="link-checkin"
			class='button'><c:out value="${member.labelCheckIn}"/></a></td>
	</tr>
</table>

<input type="hidden" id="firstName" value="${member.firstName}" />
<input type="hidden" id="lastName" value="${member.lastName}" />

<script>
	jQuery(document).ready(function($) {

		$("#link-checkin").click(function(event) {
			$.ajax({
				type : "GET",
				//				cache : false,
				url : "${home}checkin",
				data : {
					memberId : $("#memberId").val(),
					firstName : $("#firstName").val(),
					lastName : $("#lastName").val(),
					wristbands : $("#wristbands").val(),
					addressChanged : document.getElementById('addressChanged').checked,
					thendralMullai : document.getElementById('thendralMullai').checked
				},
				success : function(response) {
					console.log("*********** response: ", response);
					$('#container').html(response);
				},
				error : function(e) {
					console.log("*** ERROR: ", e);
					$('#container').html(e);
				}
			});

		});

	});
</script>
