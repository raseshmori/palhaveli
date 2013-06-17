<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page import="org.palhaveli.model.Group"%>
<%@page import="java.util.List"%>
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
					<li class="current_page_item"><a href="HomeServ">Home</a></li>
					<li><a href="addmember.jsp">Add Member</a></li>
					<li><a href="addgroup.jsp">Add Group</a></li>
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
				<h2 class="title"><a href="#">Groups</a></h2>				
				<div style="clear: both;">&nbsp;</div>
				<div class="entry">
					<%
					String errorCode=request.getParameter("errorMsg");
					if(errorCode != null && errorCode.length() > 0){
						
						if(errorCode.equals("1")){
							out.println("<label style=\"color: red\">");
							out.println("Error connecting to database. Please check if database is up.");
						}else if(errorCode.equals("2")){
							out.println("<label style=\"color: red\">");
							out.println("Error creating sticker print. Please try again.");
						}else if(errorCode.equals("3")){
							out.println("<label style=\"color: green\">");
							out.println("Member details deleted successfully.");
						}else if(errorCode.equals("4")){
							out.println("<label style=\"color: red\">");
							out.println("Error deleting member details. Please try again.");
						}
						out.println("</label>");
					}
					%>
					<form action="GetMembers" method="get">
						<table width="100%" border="0">
							<thead>
								<tr>
									<th>Select</th>
									<th>Group Name</th>
									<th>Delete this group</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="3"><input type="hidden" name="all" value="off"/></td>
								</tr>
								<%
									List<Group> groups=(List<Group>)request.getAttribute("groups");
									for(Group group : groups){
								%>
								<tr>
									<td width="15%"><input type="checkbox" name="<%=group.getGroupId()%>"/></td>
									<td width="25%"><%=group.getGroupName() %></td>
									<td><a href="GroupServ?groupid=<%=group.getGroupId() %>">Delete</a></td>
								</tr>
								<% } %>
								<tr>
									<td width="15%"><input type="checkbox" name="nogroup"/></td>
									<td width="25%">Members in no group</td>
									<td></td>
								</tr>
								<tr>									
									<td colspan="3"></td>
								</tr>
								<tr>									
									<td colspan="3"></td>
								</tr>
								<tr>									
									<td colspan="3"></td>
								</tr>
								<tr>									
									<td colspan="3"></td>
								</tr>
								<tr>
									<td colspan="3" style="text-align: center"><input type="submit" value="Show Members"/></td>
								</tr>
							</tbody>
						</table>
					</form>
					<br/><br/>OR<br/><br/>
					<form action="GetMembers" method="post">						
						<table width="100%" border="0">							
							<tbody>								
								<tr>
									<td width="25%">To see all the members: <input type="hidden" name="all" value="on"/></td>
									<td><input type="submit" value="Show All Members"/></td>
								</tr>
							</tbody>
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