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
		<td class="tg-ejgj" width="25%"><c:out
				value="${member.firstName}" /> <c:out value="${member.lastName}" /></td>
		<td class="tg-9pvc" width="10%">Member Id</td>
		<td class="tg-ejgj" width="25%" align="center"><strong><c:out
					value="${member.memeberId}" /></strong></td>
		<td class="tg-9pvc" width="15%">Breakfast Tokens</td>
		<td class="tg-ejgj" width="15%"><input type="text"
			id="breakfastIssued" size="5" maxlength="2"
			value="${member.breakfastIssued}">&nbsp;&nbsp;&nbsp;[ <strong><c:out
					value="${member.breakfastTotal}" /></strong> ]</td>
	</tr>
	<tr>
		<td class="tg-9pvc">Membership Year</td>
		<td class="tg-ejgj"><c:out value="${member.year}" /></td>
		<td class="tg-9pvc">Type</td>
		<td class="tg-ejgj" align="center"><c:out value="${member.type}" /></td>
		<td class="tg-9pvc">Lunch Veg</td>
		<td class="tg-ejgj"><input type="text" id="lunchVegIssued"
			size="5" maxlength="2" value="${member.lunchVegIssued}">&nbsp;&nbsp;&nbsp;
			[ <strong><c:out value="${member.lunchVegTotal}" /></strong> ]</td>
	</tr>
	<tr>
		<td class="tg-9pvc">Emails</td>
		<td class="tg-ejgj"><c:out value="${member.email1}" /><br /> <c:out
				value="${member.email2}" /></td>
		<td class="tg-9pvc">Spouse</td>
		<td class="tg-ejgj"><c:out value="${member.spouseFirstName}" />
			<c:out value="${member.spouseLastName}" /></td>
		<td class="tg-9pvc">Lunch Non-Veg</td>
		<td class="tg-ejgj"><input type="text" id="lunchNonVegIssued"
			size="5" maxlength="2" value="${member.lunchNonVegIssued}">&nbsp;&nbsp;&nbsp;[
			<strong><c:out value="${member.lunchNonVegTotal}" /></strong> ]</td>
	</tr>
	<tr>
		<td class="tg-9pvc">Phones</td>
		<td class="tg-ejgj"><c:out value="${member.phone1}" />, <c:out
				value="${member.phone2}" /></td>
		<td class="tg-9pvc">Kids</td>
		<td class="tg-ejgj"><c:out value="${member.kids}" /></td>
		<td class="tg-9pvc">Dinner Veg</td>
		<td class="tg-ejgj"><input type="text" id="dinnerVegIssued"
			size="5" maxlength="2" value="${member.dinnerVegIssued}">&nbsp;&nbsp;&nbsp;[
			<strong><c:out value="${member.dinnerVegTotal}" /></strong> ]</td>
	</tr>
	<tr>
		<td class="tg-9pvc">Address</td>
		<td class="tg-ejgj" colspan="3"><c:out value="${member.address1}" />,<c:out
				value="${member.address2}" />,<c:out value="${member.city}" />,<c:out
				value="${member.state}" />,<c:out value="${member.zipCode}" /></td>
		<td class="tg-9pvc">Dinner Non-Veg</td>
		<td class="tg-ejgj"><input type="text" id="dinnerNonVegIssued"
			size="5" maxlength="2" value="${member.dinnerNonVegIssued}">&nbsp;&nbsp;&nbsp;[
			<strong><c:out value="${member.dinnerNonVegTotal}" /></strong> ]</td>
	</tr>
	<tr>
		<td colspan="6" align="center"><a href='#' id="link-checkin"
			class='button'><c:out value="${member.checkedOut}" /></a></td>
	</tr>
</table>

<input type="hidden" id="firstName" value="${member.firstName}" />
<input type="hidden" id="lastName" value="${member.lastName}" />
<input type="hidden" id="breakfastTotal"
	value="${member.breakfastTotal}" />
<input type="hidden" id="lunchVegTotal" value="${member.lunchVegTotal}" />
<input type="hidden" id="lunchNonVegTotal"
	value="${member.lunchNonVegTotal}" />
<input type="hidden" id="dinnerVegTotal"
	value="${member.dinnerVegTotal}" />
<input type="hidden" id="dinnerNonVegTotal"
	value="${member.dinnerNonVegTotal}" />

<script>
	jQuery(document).ready(function($) {

		$("#link-checkin").click(function(event) {
			$.ajax({
				type : "GET",
				//				cache : false,
				url : "${home}checkin",
				data : {
					memberId : $("#memberId").val(),
					breakfastIssued : $("#breakfastIssued").val(),
					lunchVegIssued : $("#lunchVegIssued").val(),
					lunchNonVegIssued : $("#lunchNonVegIssued").val(),
					dinnerVegIssued : $("#dinnerVegIssued").val(),
					dinnerNonVegIssued : $("#dinnerNonVegIssued").val()
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

	jQuery(document).ready(function($) {
		bfTotal = parseInt($("#breakfastTotal").val());
		if(isNaN(bfTotal)) bfTotal = 0;
		bfIssued = parseInt($("#breakfastIssued").val());
		if(isNaN(bfIssued)) bfIssued = 0;
		if (bfTotal < 1 || bfIssued >= bfTotal) {
			$("#breakfastIssued").attr("disabled", "disabled");
		}
		
		lvTotal = parseInt($("#lunchVegTotal").val());
		if(isNaN(lvTotal)) lvTotal = 0;
		lvIssued = parseInt($("#lunchVegIssued").val());
		if(isNaN(lvIssued)) lvIssued = 0;		
		if (lvTotal < 1 || lvIssued >= lvTotal) {
			$("#lunchVegIssued").attr("disabled", "disabled");
		}		
		
		lnvTotal = parseInt($("#lunchNonVegTotal").val());
		if(isNaN(lnvTotal)) lnvTotal = 0;
		lnvIssued = parseInt($("#lunchNonVegIssued").val());
		if(isNaN(lnvIssued)) lnvIssued = 0;		
		if (lnvTotal < 1 || lnvIssued >= lnvTotal) {
			$("#lunchNonVegIssued").attr("disabled", "disabled");
		}
		
		dvTotal = parseInt($("#dinnerVegTotal").val());
		if(isNaN(dvTotal)) dvTotal = 0;
		dvIssued = parseInt($("#dinnerVegIssued").val());
		if(isNaN(dvIssued)) dvIssued = 0;			
		if (dvTotal < 1 || dvIssued >= dvTotal) {
			$("#dinnerVegIssued").attr("disabled", "disabled");
		}		
		
		dnvTotal = parseInt($("#dinnerNonVegTotal").val());
		if(isNaN(dnvTotal)) dnvTotal = 0;
		dnvIssued = parseInt($("#dinnerNonVegIssued").val());
		if(isNaN(dnvIssued)) dnvIssued = 0;
		if (dnvTotal < 1 || dnvIssued >= dnvTotal) {
			$("#dinnerNonVegIssued").attr("disabled", "disabled");
		}		
	});
	
	$("#breakfastIssued").on("change",function() {
		bfIssued = this.value;
		if(isNaN(bfIssued)) bfIssued = 0;
		bfTotal = parseInt($("#breakfastTotal").val());
		if(isNaN(bfTotal)) bfTotal = 0;
		if (bfIssued > bfTotal) {
			alert('Breakfast tokens cannot be greater than ' + bfTotal);
			$("#breakfastIssued").focus();			
		}		
	});	
	
	$("#lunchVegIssued").on("change",function() {
		lvIssued = this.value;
		if(isNaN(lvIssued)) lvIssued = 0;
		lvTotal = parseInt($("#lunchVegTotal").val());
		if(isNaN(lvTotal)) lvTotal = 0;
		if (lvIssued > lvTotal) {
			alert('Lunch Veg tokens cannot be greater than ' + lvTotal);
			$("#lunchVegIssued").focus();
		}		
	});		
	
	$("#lunchNonVegIssued").on("change",function() {
		lnvIssued = this.value;
		if(isNaN(lnvIssued)) lnvIssued = 0;
		lnvTotal = parseInt($("#lunchNonVegTotal").val());
		if(isNaN(lnvTotal)) lnvTotal = 0;
		if (lnvIssued > lnvTotal) {
			alert('Lunch Non Veg tokens cannot be greater than ' + lnvTotal);
			$("#lunchNonVegIssued").focus();				
		}		
	});	
	
	$("#dinnerVegIssued").on("change",function() {
		dvIssued = this.value;
		if(isNaN(dvIssued)) dvIssued = 0;
		dvTotal = parseInt($("#dinnerVegTotal").val());
		if(isNaN(dvTotal)) dvTotal = 0;
		if (dvIssued > dvTotal) {
			alert('Dinner Veg tokens cannot be greater than ' + dvTotal);
			$("#dinnerVegIssued").focus();			
		}		
	});		
	
	$("#dinnerNonVegIssued").on("change",function() {
		dnvIssued = this.value;
		if(isNaN(dnvIssued)) dnvIssued = 0;
		dnvTotal = parseInt($("#dinnerNonVegTotal").val());
		if(isNaN(dnvTotal)) dnvTotal = 0;
		if (dnvIssued > dnvTotal) {
			alert('Dinner Non Veg tokens cannot be greater than ' + dnvTotal);
			$("#dinnerNonVegIssued").focus();				
		}		
	});	
</script>
