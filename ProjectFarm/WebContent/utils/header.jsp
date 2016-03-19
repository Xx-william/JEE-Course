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
<script src="/ProjectFarm/ETC/JQuery/jquery-1.12.1.min.js"></script>
<script src="/ProjectFarm/ETC/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="/ProjectFarm/ETC/Bootstrap-validator/validator.min.js"></script>
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
			<a class="navbar-brand" href="<%= request.getContextPath()%>/index.jsp">Project Farm</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">


			<ul class="nav navbar-nav navbar-right">
				<form class="navbar-form form-inline" method="post">
					<div class="form-group">
						<label for="logAccount">E-mail</label> <input type="email"
							class="form-control" id="logAccount" placeholder="E-Mail">
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							class="form-control" id="password" placeholder="Password">
					</div>
					<button type="submit" class="btn btn-default">Log in</button>
				</form>

			</ul>

		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<!-- body,html tags are still opend -->