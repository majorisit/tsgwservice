<table width="100%" cellpadding="0.5" style="border:0.5px"> 
<thead>
<tbody>
<tr bgcolor="#ffffff">
	<th width="100%" colspan="2">
		<img src="cid:tsgw_banner"/>
	</th>
</tr>
</thead> 
<tbody>
	<tr bgcolor="#9aff99">
	    <td width="20%" align="center">
    		<img src="cid:member_barcode" width="175" height="30"/>
    		<br><font size="3"><b>${memberId}</b></font>
	    </td>	
	    <td width="80%" align="right">
	    	<b>
	    	<font size="6" color="#ef0707">
	    		Children's Day 2017
	    	</font>
			<br>
			<i>
			<font size="4" color="#ef0707">
	   		 <#if firstName??>
	    		${firstName}
	    	 <#else>
	    	 </#if>	
	    	 &nbsp;
	   		 <#if lastName??>
	    		${lastName}&nbsp;&nbsp;
	    	 <#else>
	    	 </#if>		    	 
	    	 </font>
	    	 </i>
	    	 </b>	    	 
	    </td> 
	</tr>
	</table>
	
	<table width="100%" cellpadding="0.5" style="border:0.5px"> 	
	<tr>
		<td align="center">
			<font size="3">
				<b>Food Reserved:</b>&nbsp;&nbsp;&nbsp;&nbsp;Vegetarian&nbsp;-
				 <#if veg??>
		    		<b>${veg}</b>
		    	 <#else>
		    	 </#if>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				Non-Vegetarian&nbsp;-
				 <#if veg??>
		    		<b>${nonVeg}</b>
		    	 <#else>
		    	 </#if>
	    	 </font>
		</td>			
	</tr>
	<tr>
		<td align="center" colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td align="center">
			<font size="3">
				<b>Chief Guest</b><br/><b>Mr. Jonathan Ripley,</b>&nbsp;&nbsp;Tamil Preceptor at Harvard University&nbsp;
			</font>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2">&nbsp;</td>
	</tr>	
	<tr>
		<td align="center">
			<font size="3">
				<b>Location</b><br/>Walter Johnson High School<br/>6400 Rock Spring Drive<br/>Bethesda, MD 20814
			</font> 
		</td>
	</tr>	
	<tr>
		<td align="center" colspan="2">&nbsp;</td>
	</tr>	
	<tr>
		<td align="center">
			<font size="3">
				<b>Date:</b>&nbsp;&nbsp;&nbsp;Saturday, November 11, 2017
			</font>
		</td>
	</tr>
	<tr>
		<td align="center">
			<font size="3">
				<b>Time:</b>&nbsp;&nbsp;&nbsp;3:00 PM - 9:00 PM
			</font>
		</td>
	</tr>
	</tbody>
	</table>
			
	<table width="100%" bgcolor="green" cellpadding="0.5" style="border:0.5px">
	<tr>
		<td width="80%"><font color="white" size="4"><strong>Tamil Sangam of Greater Washington (TSGW)</font><br>
					<font color="white" size="6">Goes Green</strong></font></td>
		<td align="right" width="20%"><img alt="tsgw_green"
			src="cid:gogreen" height="100"/></td>
	</tr>
</table>
<BR><BR><BR><BR>
<#--
<img src="cid:daily_bargraph">
<BR><BR><BR><BR>
-->