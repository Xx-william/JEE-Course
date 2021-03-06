<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="javax.servlet.http.Cookie"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Project Farm</title>

<link rel="stylesheet"
	href="/ProjectFarm/ETC/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/ProjectFarm/ETC/bootstrap-select/css/bootstrap-select.min.css">
<script src="/ProjectFarm/ETC/JQuery/jquery-1.12.1.min.js"></script>
<script src="/ProjectFarm/ETC/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="/ProjectFarm/ETC/Bootstrap-validator/validator.min.js"></script>
<script
	src="/ProjectFarm/ETC/bootstrap-select/js/bootstrap-select.js"></script>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<%
				if (session.getAttribute("isLogIn") == "true") {
					if(session.getAttribute("type") == "Owner"){
			%>
			<a class="navbar-brand"
				href="<%=request.getContextPath()%>/pages/ownerFrontPage.jsp">Project
				Farm</a>
			<%
					}else if(session.getAttribute("type") == "Evaluator"){
						%> 
						
								<a class="navbar-brand"
								href="<%=request.getContextPath()%>/pages/evaluatorFrontPage.jsp">Project
								Farm</a>
						
						<% 
				
								
					}
					
				} else {
			%>
			<a class="navbar-brand"
				href="<%=request.getContextPath()%>/index.jsp">Project Farm</a>
			<%
				}
			%>

		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">


			<ul class="nav navbar-nav navbar-right">


				<!--  If user loged in -->
				<%
					if (session.getAttribute("isLogIn") == "true") {
						if(session.getAttribute("type") == "Owner"){//if user is a Owner
				%>
				<div class="dropdown">				
					<button class="btn btn-default dropdown-toggle" type="button"
						id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="true"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>
						 <%=session.getAttribute("name")%>
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
						
						<li><a href="<%=request.getContextPath()%>/controller/MyProjects">My Projects</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="<%=request.getContextPath()%>/controller/Disconnect">Log out</a></li>
					</ul>
				</div>
				<%
						}else{ //if user is an Evaluator
				%>
							<div class="dropdown">				
							<button class="btn btn-default dropdown-toggle" type="button"
								id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="true"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>
								 <%=session.getAttribute("name")%>
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
								
								<li><a href="<%=request.getContextPath()%>/controller/ListProjects">List Projects</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="<%=request.getContextPath()%>/controller/Disconnect">Log out</a></li>
							</ul>
						</div>
						<%
						}
						
						} else {
							String errorStr = (String) request.getAttribute("error");
							if(errorStr == null){
								errorStr = "";
							}
				%>
				<!-- IF user did not log in -->
				<%
				
				Cookie[] cookies = request.getCookies();
				String userEmail = "";
				for(Cookie cookie : cookies){
					if(cookie.getName().equals("Email")){
						userEmail = cookie.getValue();
					}
				}
				%>
				<form class="navbar-form form-inline" method="post"
					 data-toggle="validator"
					 action="/ProjectFarm/controller/LogIn"
					role="form" id="loginForm">
					<div class="form-group" >
						<label for="logAccount" class="control-label">E-mail</label> <input
							type="email" class="form-control" id="logAccount"
							placeholder="E-Mail" name="email" required value="<%=userEmail%>">
					</div>
					<div class="form-group">
						<label for="password" class="control-label">Password</label> <input
							type="password" class="form-control" id="password"
							placeholder="Password" name="password" required>
					</div>
					<button type="submit" class="btn btn-default">Log in</button>
					<div><h6 style="color: red"><%=errorStr%></h6></div>			
				</form>
				<%
					}
				%>

			</ul>

		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> 
	</nav>

	<!-- body,html tags are still opend -->