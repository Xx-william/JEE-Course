<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script src="/ProjectFarm/ETC/bootstrap-select/js/bootstrap-select.min.js"></script>
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
			<%if (session.getAttribute("isLogIn") == "true") {%>
			<a class="navbar-brand" href="<%= request.getContextPath()%>/ownerFrontPage.jsp">Project Farm</a>
			<%} else { %>
			<a class="navbar-brand" href="<%= request.getContextPath()%>/index.jsp">Project Farm</a>
			<%} %>
			
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">


			<ul class="nav navbar-nav navbar-right">
			
			<% if(session.getAttribute("isLogIn") == "true") { %>
				<button type="button" class="btn btn-default" aria-label="Left Align">
				<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
				<%= session.getAttribute("name") %>
				</button>
				
			<%} else {%>
				<form class="navbar-form form-inline" method="post" action="/ProjectFarm/controller/LogIn" data-toggle="validator"
					role="from" id="loginForm">
					<div class="form-group">
						<label for="logAccount" class="control-label">E-mail</label> <input type="email"
							class="form-control" id="logAccount" placeholder="E-Mail" name="email" required>
					</div>
					<div class="form-group">
						<label for="password" class="control-label">Password</label> <input type="password"
							class="form-control" id="password" placeholder="Password" name="password" required>
					</div>
					<button type="submit" class="btn btn-default">Log in</button>
				</form>
			<%}%>

			</ul>

		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<!-- body,html tags are still opend -->