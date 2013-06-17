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
					<li class="current_page_item"><a href="addgroup.jsp">Add Group</a></li>
					<li><a href="GetMembers?all=on">Show Members</a></li>
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
				<h2 class="title"><a href="#">Add Group</a></h2>				
				<div style="clear: both;">&nbsp;</div>				
				<div class="entry">
					<%
					String errorCode=request.getParameter("errorMsg");
					if(errorCode != null && errorCode.length() > 0){
						out.println("<label style=\"color: red\">");
						if(errorCode.equals("1")){
							out.println("Please enter the group name");
						}else if(errorCode.equals("2")){
							out.println("Some error occured at server. Please try again.");
						}
						out.println("</label>");
					}
					%>
					<form action="GroupServ" method="post">
						<table width="100%" border="0">
							<tr>
								<td></td>
								<td>Group Name</td>
								<td>:</td>
								<td><input type="text" name="groupname" /></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td colspan="9"></td>								
							</tr>
							<tr>
								<td colspan="9"></td>								
							</tr>
							<tr>
								<td colspan="9"></td>								
							</tr>
							<tr>
								<td colspan="9"></td>								
							</tr>							
							<tr>
								<td colspan="9" style="text-align: center"><input type="submit" value="Add Group"/></td>
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