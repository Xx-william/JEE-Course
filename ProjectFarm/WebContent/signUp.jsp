<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/utils/header.jsp"></jsp:include>


<div class="row">
	<div class="col-xs-6 col-xs-offset-3">
		<div class="panel panel-primary">
			<div class="panel-body"></div>
			<div class="panel-footer">
				<form id="signUpForm" method="post" data-toggle="validator"
					role="form">
					<div class="form-group">
						<label for="inputEmail" class="control-label">E-Mail</label> <input
							type="email" class="form-control " id=
							inputEmail" placeholder="E-mail" name="email" required>
					</div>
					<div class="form-group">
						<label for="inputUserName" class="control-label">Name</label> <input
							type="text" class="form-control" id=
							inputUserName" placeholder="Name" name="userName" required>
					</div>
					<div class="form-group">
						<label for="inputPassword" class="control-label">Password</label>
						<div class="form-group ">
							<input type="password" class="form-control" id="inputPassword"
								placeholder="Password" name="password" required>
							<div class="help-block">Minimum of 6 characters</div>
						</div>
						<div class="form-group ">
							<label for="inputPassword" class="control-label">Password
								confirm</label> <input type="password" class="form-control"
								id="inputPasswordConfirm" data-match="#inputPassword"
								data-match-error="Whoops, these don't match"
								placeholder="Confirm" name="passwordVerify" required>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label for="radio" class="control-label">User Type</label>
						<div class="radio">
							<label class="radio-inline" class="control-label"><input
								type="radio" name="optradio" value="Owner" required>Owner</label>
							<label class="radio-inline" class="control-label"><input
								type="radio" name="optradio" value="Evaluator" required>Evaluator</label>
						</div>


					</div>
					<center>
						<button id="formSubmit" class="btn btn-default" type="submit">Sign Up</button>
				</form>
				</center>
				<div id="ajaxResponse"></div>
				<script>
				$('#signUpForm').validator().on('submit', function (e) {
					$("#ajaxResponse").empty();
					  if (e.isDefaultPrevented()) {
					    // handle the invalid form...
					  } else {
						  var form = $('#signUpForm');
							$.ajax({type : "POST",
								url : "/ProjectFarm/controller/SignUp",
								data : form.serialize(),
								success : function(data) {
								if (data.isSuccess == "false") {
									$("#ajaxResponse").append("<div class='alert alert-danger' role='alert'>"
										+ data.errorMessage + "</div>");
								} else if (data.isSuccess == "true") {
									
									$("#ajaxResponse").append("<div class='alert alert-success' role='alert'>Welcome to Project Farm</div>");
									}
								},
								error : function() {
									
										$("#ajaxResponse").append("<h1>fails</h1>");
										}
							});
							return false;
					  }
					})					
				</script>
			</div>
		</div>
	</div>
</div>

<jsp:include page="/utils/footer.jsp"></jsp:include>