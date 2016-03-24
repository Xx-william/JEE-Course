<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.EvaluatePage" %>
    <%@ page import="java.lang.reflect.Type"%>
<%@ page import="com.google.gson.reflect.TypeToken"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.ArrayList"%>
<jsp:include page="/utils/header.jsp"></jsp:include>



<div class="row">
	<div class="col-xs-8 col-xs-offset-2">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">Project Evaluation</h3>
			</div>
			<% 
			String json = (String)request.getAttribute("project");
			Gson gson = new Gson();
			 Type collectionType = new TypeToken<EvaluatePage>(){}.getType();
			 EvaluatePage evaluatePage = gson.fromJson(json, collectionType);
			%>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-6">
						<h4>Acronym:</h4>
						<h4><%= evaluatePage.getAcronym()%></h4>
					</div>
					<div class="col-xs-6">
						<h4>created:</h4>
						<h4><%= evaluatePage.getCreated()%></h4>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<h4>Description:</h4>
						<p><%= evaluatePage.getDescription()%></p>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4">
						<h4>Category:</h4>
						<h4><%= evaluatePage.getCategory()%></h4>
					</div>
					<div class="col-xs-4">
						<h4>Incubation # of days:</h4>
						<h4><%= evaluatePage.getIncubation()%></h4>
					</div>
					<div class="col-xs-4">
						<h4>Budget:</h4>
						<h4><%= evaluatePage.getBudget()%></h4>
					</div>
				</div>

				<hr>

				<div class="row">
					<div class="col-xs-4">
						<h4>Documents:</h4>
						
						<% 
						ArrayList<String> documents = new ArrayList<String>(evaluatePage.getDocuments());
						if(!documents.isEmpty()){
						for(String path : documents) {
							%>
							<h4>
							<a><%=path%></a>
							</h4>
							<% 
						}	}					
						%>
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