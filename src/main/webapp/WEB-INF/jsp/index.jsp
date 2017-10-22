<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TSGW Goes Green</title>

<spring:url value="/js/jquery-3.2.1.min.js" var="jqueryJs" />
<script src="${jqueryJs}"></script>
<c:url var="home" value="/" scope="request" />
</head>
<body onload="">
	<center>
		<img alt="tsgw_banner" src="/images/tsgw_banner.jpg">
	</center>
	<br/>
	<form id="checkinForm">
		<table width="100%">
			<tr>
				<td width="100%" align="left"><font color="#000000"><b>TSGW Member Id </b></font><input type="text"
					id="memberId" size="10" />&nbsp;&nbsp;&nbsp; <a href='#'
					id="bth-search" class='button'>Find</a>
				</td>
			</tr>
		</table>
	</form>
	<br/><br/>
	<div id="container" style="height: 250px; background: white"></div>

	<div id="gogreen">
		<table width="100%" height="50px" bgcolor="green" cellspacing="0"
			cellpadding="0" style="border-spacing: 0px">
			<tr>
				<td width="80%"><font color="white" size="10"><strong>TSGW
							Goes Green</strong></font></td>
				<td align="right" width="20%"><img alt="tsgw_green"
					src="/images/gogreen1.jpg" height="100"/></td>
			</tr>
		</table>
	</div>
	<script>
		jQuery(document).ready(function($) {
			$('#memberId').focus();
			
			$("#bth-search").click(function(event) {
				$.ajax({
					type : "GET",
					//				cache : false,
					url : "${home}search",
					data : {
						memberId : $("#memberId").val()
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
				$('#memberId').focus();
			});

		});
	</script>

</body>
</html>

<style type="text/css">
.button {
	border: 0px solid #0a3c59;
	background: #9c3e3e;
	background: -webkit-gradient(linear, left top, left bottom, from(#ff0000),
		to(#9c3e3e));
	background: -webkit-linear-gradient(top, #ff0000, #9c3e3e);
	background: -moz-linear-gradient(top, #ff0000, #9c3e3e);
	background: -ms-linear-gradient(top, #ff0000, #9c3e3e);
	background: -o-linear-gradient(top, #ff0000, #9c3e3e);
	background-image: -ms-linear-gradient(top, #ff0000 0%, #9c3e3e 100%);
	padding: 10px 20px;
	-webkit-border-radius: 0px;
	-moz-border-radius: 0px;
	border-radius: 0px;
	-webkit-box-shadow: rgba(255, 255, 255, 0.4) 0 1px 0, inset
		rgba(255, 255, 255, 0.4) 0 0px 0;
	-moz-box-shadow: rgba(255, 255, 255, 0.4) 0 1px 0, inset
		rgba(255, 255, 255, 0.4) 0 0px 0;
	box-shadow: rgba(255, 255, 255, 0.4) 0 1px 0, inset
		rgba(255, 255, 255, 0.4) 0 0px 0;
	text-shadow: #ffffff 0 1px 0;
	color: #ffffff;
	font-size: 17px;
	font-family: helvetica, serif;
	text-decoration: none;
	vertical-align: middle;
}

.button:hover {
	border: 0px solid #0a3c59;
	text-shadow: #ffffff 0 1px 0;
	background: #9e2b2b;
	background: -webkit-gradient(linear, left top, left bottom, from(#6060db),
		to(#9e2b2b));
	background: -webkit-linear-gradient(top, #6060db, #9e2b2b);
	background: -moz-linear-gradient(top, #6060db, #9e2b2b);
	background: -ms-linear-gradient(top, #6060db, #9e2b2b);
	background: -o-linear-gradient(top, #6060db, #9e2b2b);
	background-image: -ms-linear-gradient(top, #6060db 0%, #9e2b2b 100%);
	color: #ffffff;
}

.button:active {
	text-shadow: #1e4158 0 1px 0;
	border: 0px solid #0a3c59;
	background: #65a9d7;
	background: -webkit-gradient(linear, left top, left bottom, from(#3e779d),
		to(#9e2b2b));
	background: -webkit-linear-gradient(top, #3e779d, #65a9d7);
	background: -moz-linear-gradient(top, #3e779d, #65a9d7);
	background: -ms-linear-gradient(top, #3e779d, #65a9d7);
	background: -o-linear-gradient(top, #3e779d, #65a9d7);
	background-image: -ms-linear-gradient(top, #3e779d 0%, #65a9d7 100%);
	color: #fff;
}
</style>
