<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/utils/header.jsp"></jsp:include>

<div class="row">
	<div class="col-xs-8 col-xs-offset-2">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">Project Evaluation</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-6">
						<h4>Acronym:&nbsp&nbsp&nbsp</h4>
						<h4>testAcronym</h4>
					</div>
					<div class="col-xs-6">
						<h4>created:&nbsp&nbsp&nbsp</h4>
						<h4>03/08/2014 15:22</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<h4>Description:&nbsp&nbsp&nbsp</h4>
						<p>testDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescription
							testDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescription
							testDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescription
							testDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescription
							testDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescriptiontestDescription</p>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4">
						<h4>Category:&nbsp&nbsp&nbsp</h4>
						<h4>testCategory</h4>
					</div>
					<div class="col-xs-4">
						<h4>Incubation # of days:&nbsp&nbsp&nbsp</h4>
						<h4>2000</h4>
					</div>
					<div class="col-xs-4">
						<h4>Budget:&nbsp&nbsp&nbsp</h4>
						<h4>2000 000 00</h4>
					</div>
				</div>

				<hr>

				<div class="row">
					<div class="col-xs-4">
						<h4>Documents:</h4>
						<h4>
							<a>testCategory.pdf</a>
						</h4>
						<h4>
							<a>testCategory.doc</a>
						</h4>
					</div>
				</div>

				<hr>

				<div class="row">
					<div class="col-xs-12">
						<h4>Your evaluation:</h4>
						<form>
							<div class="form-group col-xs-6">
								<label class="control-label">Attractivness:</label> <select
									class="selectpicker" id="attractivness">
									<option data="1">1</option>
									<option data="2">2</option>
									<option data="3">3</option>
									<option data="4">4</option>
									<option data="5">5</option>
								</select>
							</div>

							<div class="form-group col-xs-6">
								<label class="control-label">Risk Level:</label> <select
									class="selectpicker" id="risk">
									<option data="1">1</option>
									<option data="2">2</option>
									<option data="3">3</option>
									<option data="4">4</option>
									<option data="5">5</option>
								</select>
							</div>
							<hr>
							<button id="formSubmit" class="btn btn-default" type="submit">Save</button>
							<button class="btn btn-default">Discard</button>
						</form>
					</div>
				</div>
			</div>

		</div>

	</div>
</div>


<jsp:include page="/utils/footer.jsp"></jsp:include>