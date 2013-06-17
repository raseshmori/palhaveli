<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page import="org.palhaveli.model.Group"%>
<%@page import="java.util.List"%>
<%@page import="org.palhaveli.db.DbManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.palhaveli.db.GroupDbHelper"%>
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

	Connection connection=DbManager.getConnection();
	GroupDbHelper groupDb=new GroupDbHelper(connection);
	List<Group> groups=groupDb.getGroups();
%>
<div id="wrapper">
	<div id="header-wrapper">
		<div id="header" class="container">			
			<div id="menu">
				<ul>
					<li><a href="HomeServ">Home</a></li>
					<li class="current_page_item"><a href="addmember.jsp">Add Member</a></li>
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
				<h2 class="title"><a href="#">Add Member</a></h2>				
				<div style="clear: both;">&nbsp;</div>
				<div class="entry">
					<%
					String errorCode=request.getParameter("errorMsg");
					if(errorCode != null && errorCode.length() > 0){
						
						if(errorCode.equals("1")){
							out.println("<label style=\"color: red\">");
							out.println("Error adding member details. Please try again.");
						}else if(errorCode.equals("2")){
							out.println("<label style=\"color: green\">");
							out.println("Member details entered successfully");
						}else if(errorCode.equals("3")){
							out.println("<label style=\"color: red\">");
							out.println("Member details entered but group membership were not added. Please edit member details and complete it.");
						}else if(errorCode.equals("4")){
							out.println("<label style=\"color: red\">");
							out.println("Please enter all the details in the form. All the fields are mandatory.");
						}
						out.println("</label><br/><br/><br/>");
					}
					%>
					<label style="color: brown">Please write these values for no data available: 0 for numeric fields and NA for other fields.</label><br/>
					<form action="MemberServ" method="post">
						<table width="100%" border="0">
							<tr>
								<td><input type="hidden" name="operation" value="add" /></td>
								<td>First Name</td>
								<td>:</td>
								<td><input type="text" name="firstname" /></td>
								<td></td>
								<td>Middle Name</td>
								<td>:</td>
								<td><input type="text" name="middlename" /></td>
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
								<td></td>
								<td>Last Name</td>
								<td>:</td>
								<td><input type="text" name="lastname" /></td>
								<td></td>
								<td>House No.</td>
								<td>:</td>
								<td><input type="text" name="flatapt" /></td>
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
								<td></td>
								<td>Apartment/Row House/Society</td>
								<td>:</td>
								<td><input type="text" name="apartment" /></td>
								<td></td>
								<td>Landmark</td>
								<td>:</td>
								<td><input type="text" name="landmark" /></td>
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
								<td></td>
								<td>Road</td>
								<td>:</td>
								<td><input type="text" name="road" /></td>
								<td></td>
								<td>Area</td>
								<td>:</td>
								<td><input type="text" name="region" /></td>
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
								<td></td>
								<td>City</td>
								<td>:</td>
								<td><input type="text" name="city" value="Surat" /></td>
								<td></td>
								<td>State</td>
								<td>:</td>
								<td><input type="text" name="state" value="Gujarat" /></td>
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
								<td></td>
								<td>Country</td>
								<td>:</td>
								<td><input type="text" name="country" value="India" /></td>
								<td></td>
								<td>Pin Code</td>
								<td>:</td>
								<td><input type="text" name="pincode" value="" /></td>
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
								<td></td>
								<td>Mobile No.</td>
								<td>:</td>
								<td><input type="text" name="mobile" value="" /></td>
								<td></td>
								<td>Residence No.</td>
								<td>:</td>
								<td><input type="text" name="home" value="" /></td>
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
								<td></td>
								<td>Email Id</td>
								<td>:</td>
								<td colspan="3"><input type="text" name="email" value="" style="width: 100%"/></td>
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
								<td></td>
								<td>Caste</td>
								<td>:</td>
								<td><input type="text" name="caste" value="" /></td>
								<td></td>
								<td>Home Town</td>
								<td>:</td>
								<td><input type="text" name="hometown" value="" /></td>
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
								<td></td>
								<td>Profession</td>
								<td>:</td>
								<td colspan="3"><input type="text" name="profession" value="" style="width: 100%"/></td>
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
								<td></td>
								<td>No. of family members</td>
								<td>:</td>
								<td><input type="text" name="familymembers" value=""/></td>
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
								<td></td>
								<td>Brahmsambandh?</td>
								<td>:</td>
								<td><input type="radio" name="brahmsambandh" value="yes" /> Yes</td>
								<td><input type="radio" name="brahmsambandh" value="no" /> No</td>
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
								<td></td>
								<td>Pusht Thakorji?</td>
								<td>:</td>
								<td><input type="radio" name="thakorji" value="yes" /> Yes</td>
								<td><input type="radio" name="thakorji" value="no" /> No</td>
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
								<td></td>
								<td>Groups</td>
								<td>:</td>
								<td colspan="6">
									<table width="100%" border="0">
										<%
											for(Group group : groups){
												out.println("<tr>");
												out.println("<td style=\"text-align: right\"><input type=\"checkbox\" name=\""+group.getGroupId()+"\" /></td>");
												out.println("<td>"+group.getGroupName()+"</td>");
												out.println("</tr>");
											}
										%>										
									</table>
								</td>								
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
								<td colspan="9" style="text-align: center"><input type="submit" value="Add Member"/></td>
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