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

tr.even { background: white; }
tr.odd { background: #eaf9ea; }
</style>

<table class="tg" width="100%">
	<tr>
		<td class="tg-9pvc"><strong>ID</strong></td>
		<td class="tg-9pvc"><strong>Name</strong></td>
		<td class="tg-9pvc"><strong>Membership</strong></td>
		<td class="tg-9pvc"><strong>Email</strong></td>
		<td class="tg-9pvc"><strong>Phone</strong></td>
		<td class="tg-9pvc"><strong>Address</strong></td>
	</tr>
	<c:forEach items="${members}" var="member" varStatus="loop">
		<tr class="${loop.index % 2 == 0 ? 'even' : 'odd'}">
			<td><c:out value="${member.memeberId}" /></td>
			<td><c:out value="${member.firstName}" /> <c:out
					value="${member.lastName}" /></td>
			<td><c:out value="${member.year}" />,&nbsp;&nbsp;<c:out
					value="${member.type}" /></td>
			<td><c:out value="${member.email1}" /></td>
			<td><c:out value="${member.phone1}" />,<c:out
					value="${member.phone2}" /></td>
			<td><c:out value="${member.state}" />,&nbsp;&nbsp;<c:out
					value="${member.zipCode}" /></td>
		</tr>
		<tr class="${loop.index % 2 == 0 ? 'even' : 'odd'}">
			<td>&nbsp;</td>			
			<td><c:out value="${member.breakfastIssued}" />&nbsp; [ <strong><c:out
						value="${member.breakfastTotal}" /></strong> ]</td>
			<td><c:out value="${member.lunchVegIssued}" />&nbsp; [ <strong><c:out
						value="${member.lunchVegTotal}" /></strong> ]</td>
			<td><c:out value="${member.lunchNonVegIssued}" />&nbsp;[ <strong><c:out
						value="${member.lunchNonVegTotal}" /></strong> ]</td>
			<td><c:out value="${member.dinnerVegIssued}" />&nbsp;[ <strong><c:out
						value="${member.dinnerVegTotal}" /></strong> ]</td>
			<td><c:out value="${member.dinnerNonVegIssued}" />&nbsp;&nbsp;&nbsp;[
				<strong><c:out value="${member.dinnerNonVegTotal}" /></strong> ]</td>
		</tr>

	</c:forEach>
</table>
