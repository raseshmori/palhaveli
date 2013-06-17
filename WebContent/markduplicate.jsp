<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Pal Haveli</title>
<link href="css/google.css" rel="stylesheet" type="text/css"/>
<link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
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
					<li><a href="GetMembers?all=on">Show Members</a></li>
					<li class="current_page_item"><a href="markduplicate.jsp">Mark Duplicate Entries</a></li>
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
				<h2 class="title"><a href="#">Mark Duplicate</a></h2>				
				<div style="clear: both;">&nbsp;</div>				
				<div class="entry">
					<%
					String errorCode=request.getParameter("errorMsg");
					if(errorCode != null && errorCode.length() > 0){
						
						if(errorCode.equals("1")){
							out.println("<label style=\"color: green\">");
							out.println("Duplicate Addresses Marked. Please go ahead with the print.");
						}else if(errorCode.equals("2")){
							out.println("<label style=\"color: red\">");
							out.println("Error marking duplicate addresses. Please try again.");
						}
						out.println("</label><br/><br/><br/>");
					}
					%>
					By pressing this button, all the duplicate address will be marked and when you print them, only unique addresses get printed. <br/>
					So, use this every time before you print.
					<form action="MarkDuplicateServ" method="post">
						<table width="100%" border="0">							
							<tr>
								<td colspan="3" style="text-align: center"><input type="submit" value="Mark Duplicate Addresses"/></td>
							</tr>
						</table>
					</form>					
				</div>
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