<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<jsp:include page="/utils/header.jsp"></jsp:include>

	<div class="jumbotron">
		<div class="row">
			<center>
				<h3>Project ideas are seeds to change the world.</h3>
			</center>
		</div>
		
		<div class="row">	
			<div class="col-xs-3 col-xs-offset-4">
			<form action="" method="get">
			<button class="btn btn-default">Learn More</button>
			</form>
			
			</div>
			<div class="col-xs-3">
			<form action="<%=request.getContextPath()%>/signUp.jsp" method="get">
			<button class="btn btn-default">Sign up</button>
			</form>
			
			</div>			
		</div>
	</div>

  <jsp:include page="/utils/footer.jsp"></jsp:include>

