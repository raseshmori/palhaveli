<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page import="org.palhaveli.model.Member"%>
<%@page import="java.util.List"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Pal Haveli</title>
<style type="text/css" title="currentStyle">					
			@import "css/jquery.dataTables_themeroller.css";			
			@import "css/demo_table.css";
			@import "css/TableTools.css";
</style>
<script type="text/javascript" charset="utf-8" src="js/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="js/jquery.dataTables.js"></script>

<script type="text/javascript" charset="utf-8" src="js/ZeroClipboard.js"></script>
<script type="text/javascript" charset="utf-8" src="js/TableTools.min.js"></script>

<link href="css/google.css" rel="stylesheet" type="text/css"/>
<link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />


<script type="text/javascript">

(function($) {
	/*
	 * Function: fnGetColumnData
	 * Purpose:  Return an array of table values from a particular column.
	 * Returns:  array string: 1d data array 
	 * Inputs:   object:oSettings - dataTable settings object. This is always the last argument past to the function
	 *           int:iColumn - the id of the column to extract the data from
	 *           bool:bUnique - optional - if set to false duplicated values are not filtered out
	 *           bool:bFiltered - optional - if set to false all the table data is used (not only the filtered)
	 *           bool:bIgnoreEmpty - optional - if set to false empty values are not filtered from the result array
	 * Author:   Benedikt Forchhammer <b.forchhammer /AT\ mind2.de>
	 */
	$.fn.dataTableExt.oApi.fnGetColumnData = function ( oSettings, iColumn, bUnique, bFiltered, bIgnoreEmpty ) {
		// check that we have a column id
		if ( typeof iColumn == "undefined" ) return new Array();
		
		// by default we only wany unique data
		if ( typeof bUnique == "undefined" ) bUnique = true;
		
		// by default we do want to only look at filtered data
		if ( typeof bFiltered == "undefined" ) bFiltered = true;
		
		// by default we do not wany to include empty values
		if ( typeof bIgnoreEmpty == "undefined" ) bIgnoreEmpty = true;
		
		// list of rows which we're going to loop through
		var aiRows;
		
		// use only filtered rows
		if (bFiltered == true) aiRows = oSettings.aiDisplay; 
		// use all rows
		else aiRows = oSettings.aiDisplayMaster; // all row numbers

		// set up data array	
		var asResultData = new Array();
		
		for (var i=0,c=aiRows.length; i<c; i++) {
			iRow = aiRows[i];
			var aData = this.fnGetData(iRow);
			var sValue = aData[iColumn];
			
			// ignore empty values?
			if (bIgnoreEmpty == true && sValue.length == 0) continue;

			// ignore unique values?
			else if (bUnique == true && jQuery.inArray(sValue, asResultData) > -1) continue;
			
			// else push the value onto the result data array
			else asResultData.push(sValue);
		}
		
		return asResultData;
	}}(jQuery));


	function fnCreateSelect( aData )
	{
		var r='<select style="width: 60px"><option value=""></option>', i, iLen=aData.length;
		for ( i=0 ; i<iLen ; i++ )
		{
			r += '<option value="'+aData[i]+'">'+aData[i]+'</option>';
		}
		return r+'</select>';
	}

	function fnShowHide( iCol )
    {
            /* Get the DataTables object again - this is not a recreation, just a get of the object */
            var oTable = $('#data').dataTable();

            var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
            oTable.fnSetColumnVis( iCol, bVis ? false : true );
    }

	$(document).ready(function() {
		/* Initialise the DataTable */
		var oTable = $('#data').dataTable();
		
		/* Add a select menu for each TH element in the table footer */
		$("tfoot th").each( function ( i ) {
			this.innerHTML = fnCreateSelect( oTable.fnGetColumnData(i) );
			$('select', this).change( function () {
				oTable.fnFilter( $(this).val(), i );
			} );
		} );
		
		fnShowHide(6);
		fnShowHide(7);
		fnShowHide(10);
		fnShowHide(11);
	} );
	
	function printaddress(){
		
		var oTable=$("#data").dataTable();
		var oSettings = oTable.fnSettings();
	    oSettings._iDisplayLength = -1;
	    oTable.fnDraw();
	    
		var selector = $("#data tr");
		
		var count=0;
		var idsToPrint="";
		
		selector.each(function (index, element) {
		    count++;
		    var id=$(this).find("td:first").html();
		    
		    if(!isNaN(id))
		    	idsToPrint+=id+",";
		    
		});
		
		if(idsToPrint != ""){
			idsToPrint=idsToPrint.substring(0, idsToPrint.length - 1);
			
			if($("#idsToPrint")){
				$("#idsToPrint").remove();
			}
			
			$("#printform").append('<input type="hidden" id="idsToPrint" name="idsToPrint" value="'+idsToPrint+'">');			
			
			alert($('#idsToPrint').val());
			
			$("#printform").submit();
		}
	}
		
</script>

</head>
<body>
<%
	if(session.getAttribute("user") == null){
		response.sendRedirect("login.jsp");
	}
%>
<div id="wrapper">
	<div id="header-wrapper">
		<div id="header" class="container">			
			<div id="menu">
				<ul>
					<li><a href="HomeServ">Home</a></li>
					<li><a href="addmember.jsp">Add Member</a></li>
					<li><a href="addgroup.jsp">Add Group</a></li>
					<li class="current_page_item"><a href="GetMembers?all=on">Show Members</a></li>
					<li><a href="markduplicate.jsp">Mark Duplicate Entries</a></li>
					<li><a href="logout.jsp">Logout</a></li>					
				</ul>
			</div>
		</div>
		<div id="banner">
			<div class="content"><img src="images/img02.jpg" width="1000" height="300" alt="" /></div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="content">
			<div class="post">
				<h2 class="title"><a href="#">Members</a></h2>								
				<div style="clear: both;">&nbsp;</div>
				<div style="text-align: left">
					<table width="100%">
						<tr>
							<td><a href="javascript:void(0);" onclick="fnShowHide(1);">Show/Hide Name</a></td>					
							<td><a href="javascript:void(0);" onclick="fnShowHide(2);">Show/Hide Region</a></td>
							<td><a href="javascript:void(0);" onclick="fnShowHide(3);">Show/Hide City</a></td>					
							<td><a href="javascript:void(0);" onclick="fnShowHide(4);">Show/Hide Pin Code</a></td>
							<td><a href="javascript:void(0);" onclick="fnShowHide(5);">Show/Hide Mobile</a></td>					
							<td><a href="javascript:void(0);" onclick="fnShowHide(6);">Show/Hide Home No.</a></td>
						</tr>
						<tr>
							<td><a href="javascript:void(0);" onclick="fnShowHide(7);">Show/Hide Email</a></td>					
							<td><a href="javascript:void(0);" onclick="fnShowHide(8);">Show/Hide Caste</a></td>
							<td><a href="javascript:void(0);" onclick="fnShowHide(9);">Show/Hide Home Town</a></td>					
							<td><a href="javascript:void(0);" onclick="fnShowHide(10);">Show/Hide Profession</a></td>
							<td><a href="javascript:void(0);" onclick="fnShowHide(11);">Show/Hide Members</a></td>					
							<td><a href="javascript:void(0);" onclick="fnShowHide(12);">Show/Hide Brahmsambandh?</a></td>
						</tr>
						<tr>
							<td><a href="javascript:void(0);" onclick="fnShowHide(13);">Show/Hide Thakorji?</a></td>
							<td><a href="javascript:void(0);" onclick="fnShowHide(14);">Show/Hide Edit</a></td>
						</tr>
					</table>
				</div><br/>
				<div>
					<table width="100%">
						<tr>
							<td style="text-align: right"><input type="submit" onclick="printaddress()" value="Sticker Print" /></td>
						</tr>
					</table>					
				</div>
				<!--div class="entry"-->
					<form id="printform" action="PrintServ" method="post">
						<table id="data" border="1" style="table-layout: fixed">
							<thead>
						        <tr>
						            <th>Id</th>
						            <th>Name</th>
						            <th>Region</th>
						            <th>City</th>
						            <th>Pin Code</th>
						            <th>Mobile</th>
						            <th>Home No.</th>
						            <th>Email</th>
						            <th>Caste</th>
						            <th>Home Town</th>
						            <th>Profession</th>
						            <th>Members</th>
						            <th>Brahm sambandh?</th>
						            <th>Thakorji Pusht?</th>
						            <th>View/Edit</th>						            
						        </tr>
						    </thead>
						    <tbody>
						    	<%
						    		List<Member> members=(List<Member>)request.getAttribute("members");
						    		for(Member member : members){
						    	%>
						    			<tr>
						    				<td><%=member.getMemberId() %></td>
						    				<td><%=member.getFirstName()%> <%=member.getMiddleName() %> <%=member.getLastName() %></td>
						    				<td><%=member.getRegion() %></td>
						    				<td><%=member.getCity() %></td>
						    				<td><%=member.getPincode() %></td>
						    				<td><%=member.getMobile() %></td>
						    				<td><%=member.getHome() %></td>
						    				<td><%=member.getEmail() %></td>
						    				<td><%=member.getCaste() %></td>
						    				<td><%=member.getHomeTown() %></td>
						    				<td><%=member.getProfession() %></td>
						    				<td><%=member.getFamilyMembers() %></td>
						    				<td><%=member.isBramsambandh()?"Yes":"No" %></td>
						    				<td><%=member.isThakorji()?"Yes":"No" %></td>
						    				<td><a href="GetEditMember?memberid=<%=member.getMemberId()%>">Edit</a></td>
						    			</tr>
						    	<% } %>						        
							</tbody>
							<tfoot style="table-layout: fixed">
								<tr>									
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
							</tfoot>
						</table>
					</form>					
				<!-- /div-->				
			</div>			
			<div style="clear: both;">&nbsp;</div>
		</div>
		<div style="clear: both;">&nbsp;</div>
	</div>
	<!-- end #page --> 
</div>
<div id="footer">
	<p>Design by <a href="#" rel="nofollow">Rasesh Mori</a>.</p>
</div>
<!-- end #footer -->
</body>
</html>