<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page import="java.util.List"%>
<%@page import="org.palhaveli.model.Group"%>
<%@page import="org.palhaveli.model.Member"%>
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
		return;
	}
	Member member=(Member)request.getAttribute("member");
	if(member==null){
		response.sendRedirect("home.jsp");
		return;
	}
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
				<h2 class="title"><a href="#">View/Edit Member Details</a></h2>				
				<div style="clear: both;">&nbsp;</div>
				<div class="entry">
					<%
					String errorCode=request.getParameter("errorMsg");
					if(errorCode != null && errorCode.length() > 0){
						
						if(errorCode.equals("1")){
							out.println("<label style=\"color: red\">");
							out.println("Error updating member details. Please try again.");
						}else if(errorCode.equals("2")){
							out.println("<label style=\"color: green\">");
							out.println("Member details updated successfully");
						}else if(errorCode.equals("3")){
							out.println("<label style=\"color: red\">");
							out.println("Member details updated but group membership were not edited. Please try again.");
						}else if(errorCode.equals("3")){
							out.println("<label style=\"color: red\">");
							out.println("Member details updated but group membership were not edited. Please try again.");
						}
						
						out.println("</label>");
					}
					%>
					<form action="MemberServ" method="post">
						<table width="100%" border="0">
							<tr>
								<td><input type="hidden" name="operation" value="edit" /></td>
								<td>Member Id</td>
								<td>:</td>
								<td><input type="text" name="memberid" value="<%=member.getMemberId() %>" disabled="disabled"/></td>
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
								<td>First Name</td>
								<td>:</td>
								<td><input type="text" name="firstname" value="<%=member.getFirstName()%>"/></td>
								<td></td>
								<td>Middle Name</td>
								<td>:</td>
								<td><input type="text" name="middlename" value="<%=member.getMiddleName()%>"/></td>
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
								<td><input type="text" name="lastname" value="<%=member.getLastName()%>"/></td>
								<td></td>
								<td>House No.</td>
								<td>:</td>
								<td><input type="text" name="flatapt" value="<%=member.getFlatApt()%>"/></td>
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
								<td><input type="text" name="apartment" value="<%=member.getApartment()%>"/></td>
								<td></td>
								<td>Landmark</td>
								<td>:</td>
								<td><input type="text" name="landmark" value="<%=member.getLandmark()%>"/></td>
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
								<td><input type="text" name="road" value="<%=member.getRoad()%>"/></td>
								<td></td>
								<td>Area</td>
								<td>:</td>
								<td><input type="text" name="region" value="<%=member.getRegion()%>"/></td>
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
								<td><input type="text" name="city" value="<%=member.getCity()%>" /></td>
								<td></td>
								<td>State</td>
								<td>:</td>
								<td><input type="text" name="state" value="<%=member.getState()%>" /></td>
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
								<td><input type="text" name="country" value="<%=member.getCountry()%>" /></td>
								<td></td>
								<td>Pin Code</td>
								<td>:</td>
								<td><input type="text" name="pincode" value="<%=member.getPincode()%>" /></td>
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
								<td><input type="text" name="mobile" value="<%=member.getMobile()%>" /></td>
								<td></td>
								<td>Residence No.</td>
								<td>:</td>
								<td><input type="text" name="home" value="<%=member.getHome()%>" /></td>
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
								<td colspan="3"><input type="text" name="email" value="<%=member.getEmail()%>" style="width: 100%"/></td>
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
								<td><input type="text" name="caste" value="<%=member.getCaste()%>" /></td>
								<td></td>
								<td>Home Town</td>
								<td>:</td>
								<td><input type="text" name="hometown" value="<%=member.getHomeTown()%>" /></td>
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
								<td colspan="3"><input type="text" name="profession" value="<%=member.getProfession()%>" style="width: 100%"/></td>
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
								<td><input type="text" name="familymembers" value="<%=member.getFamilyMembers()%>"/></td>
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
								<td><input type="radio" name="brahmsambandh" value="yes" <% if(member.isBramsambandh()) out.println("checked=checked"); %> /> Yes</td>
								<td><input type="radio" name="brahmsambandh" value="no" <% if(!member.isBramsambandh()) out.println("checked=checked"); %> /> No</td>
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
								<td><input type="radio" name="thakorji" value="yes" <% if(member.isThakorji()) out.println("checked=checked"); %>/> Yes</td>
								<td><input type="radio" name="thakorji" value="no" <% if(!member.isThakorji()) out.println("checked=checked"); %>/> No</td>
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
											List<Group> groups=(List<Group>) request.getAttribute("groups");

											for(Group group : groups){
												boolean isMember=member.getGroupIds()!=null && member.getGroupIds().contains(group.getGroupId());
												out.println("<tr>");
												out.println("<td style=\"text-align: right\"><input type=\"checkbox\"  name=\""+group.getGroupId()+"\"  ");
												if(isMember)
													out.println("checked=checked");
												out.println("/></td>");
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
								<td colspan="4" style="text-align: right"><input type="submit" name="editmember" value="Update Member Details"/></td>
								<td colspan="5" style="text-align: left"><a href="MemberServ?operation=delete&memberid=<%=member.getMemberId()%>"> Delete Member</td>
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