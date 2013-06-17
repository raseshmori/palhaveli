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
	if(session.getAttribute("user") != null){
		response.sendRedirect("home.jsp");
	}
%>
<div id="wrapper">
	<div id="header-wrapper">
		<div id="header" class="container">			
			<div id="menu">
				<ul>
					<li class="current_page_item"><a href="login.jsp">Login</a></li>					
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
				<h2 class="title"><a href="#">Login </a></h2>				
				<div style="clear: both;">&nbsp;</div>
				<div class="entry">
					<%
					String errorCode=request.getParameter("errorMsg");
					if(errorCode != null && errorCode.length() > 0){
						out.println("<label style=\"color: red\">");
						if(errorCode.equals("1")){
							out.println("Invalid Username/Password.");
						}else if(errorCode.equals("2")){
							out.println("Error connecting to database. Please check if database is up.");
						}
						out.println("</label>");
					}
					%>
					<form action="LoginServ" method="post">
						<table style="text-align: center" border="0" width="100%">
							<tr>
								<td style="text-align: right">User Name</td>
								<td>:</td>
								<td style="text-align: left"><input type="text" name="username" value=""/></td>
							</tr>
							<tr>
								<td style="text-align: right">Password</td>
								<td>:</td>
								<td style="text-align: left"><input type="password" name="password" value=""/></td>
							</tr>
							<tr></tr>
							<tr>
								<td colspan="2" style="text-align: right"></td>
								<td style="text-align: left"><input class="current_page_item" type="submit" value="Login"/></td>
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