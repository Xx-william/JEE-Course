<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.Project" %>
     <%@ page import="model.Document" %>
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
			 Type collectionType = new TypeToken<Project>(){}.getType();
			 Project project = gson.fromJson(json, collectionType);
			%>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-6">
						<h4>Acronym:</h4>
						<h4><%= project.getAcronym()%></h4>
					</div>
					<div class="col-xs-6">
						<h4>created:</h4>
						<h4><%= project.getCreated()%></h4>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<h4>Description:</h4>
						<p><%= project.getDescription()%></p>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4">
						<h4>Category:</h4>
						<h4><%= project.getCategory().getDescription()%></h4>
					</div>
					<div class="col-xs-4">
						<h4>Incubation # of days:</h4>
						<h4><%= project.getFundingDuration()%></h4>
					</div>
					<div class="col-xs-4">
						<h4>Budget:</h4>
						<h4><%= project.getBudget()%></h4>
					</div>
				</div>

				<hr>

				<div class="row">
					<div class="col-xs-8">
						<h4 id="documents">Documents:</h4>						
						<% 
						ArrayList<Document> documents = new ArrayList<Document>(project.getDocuments());
						int projectId = project.getProjectId();
						if(!documents.isEmpty()){
						for(Document document : documents) {
							
							%>
							<h4>
							<a href="<%=request.getContextPath()%>/controller/GetDocument?documentId=<%=document.getDocumentId()%>"><%=document.getDocumentName()%></a>
							</h4>
							<% 
						}	}					
						%>
					</div>
					<!-- Owner : BEGIN -->
					<%
					
					if(request.getSession().getAttribute("type") == "Owner"){
						%>
							<div class="col-xs-4">
								<form id="upload" >
									<input type="file" name="file" id = "file">
									<button type="submit" class="btn btn-default" ">Submit</button>
								</form>
								<div id="uploadResponse"></div>
							</div>
						<%
					}
						%>
						<!--  Owner : END -->
						<script>
					$('#upload').validator().on('submit', function (e) {
						var formData = new FormData();
						var file = document.getElementById("file").files[0];
						var fileName =file.name;
						 formData.append('projectId',<%= project.getProjectId() %>);
						  formData.append('file',file);
						 
						if (e.isDefaultPrevented()) {
						    // handle the invalid form...
						  } else {
								$.ajax({
									type : "POST",
									url : "/ProjectFarm/controller/UploadFile",
									 cache: false,
									    data: formData,									    	 
									    processData: false,
									    contentType: false,							
									success : function(data) {
									if (data.isSuccess == "false") {
										$("#uploadResponse").empty();
										$("#uploadResponse").append("<div class='alert alert-danger' role='alert'>"
											+ data.errorMessage + "</div>");
									} else if (data.isSuccess == "true") {
										$("#uploadResponse").empty();
										$("#uploadResponse").append("<div class='alert alert-success' role='alert'>Your document has been uploaded,if your want to download this file, please refresh this page</div>");
										
										$("#documents").append("<h4>"+fileName+" </h4>");
										
										}
									
									},
									error : function() {
										$("#uploadResponse").empty();
											$("#uploadResponse").append("<h1>fails</h1>");
											}
								});
								return false;
						  }
						})	
					</script>
						
											
				</div>

				<hr>
				<!--  Evaluator : BEGIN -->
				<%if(request.getSession().getAttribute("type") == "Evaluator") {%>
				<div class="row">
					<div class="col-xs-12">
					
						<h4>Your evaluation:</h4>
						<form id="optionForm">
							<div class="form-group col-xs-6">
								<label class="control-label">Attractiveness:</label> <select
									class="selectpicker" id="attractiveness">
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
							
						</form>
						
						<script>
						
						$('#optionForm').validator().on('submit', function (e) {
							  if (e.isDefaultPrevented()) {
							    // handle the invalid form...
							  } else {
								  var form = $('#optionForm');
									$.ajax({type : "POST",
										url : "/ProjectFarm/controller/NewEvaluation",
										data : {
											attractiveness: $("#attractiveness").val(),
												risk: $("#risk").val(),
												projectId: <%= project.getProjectId()%>
										},									
										success : function(data) {
										if (data.isSuccess == "false") {
											$("#ajaxResponse").empty();
											$("#ajaxResponse").append("<div class='alert alert-danger' role='alert'>"
												+ data.errorMessage + "</div>");
										} else if (data.isSuccess == "true") {
											$("#ajaxResponse").empty();
											$("#ajaxResponse").append("<div class='alert alert-success' role='alert'>Your evaluation has been saved</div>");
											}
										},
										error : function() {
											$("#ajaxResponse").empty();
												$("#ajaxResponse").append("<h1>fails</h1>");
												}
									});
									return false;
							  }
							})		
						
						</script>
					</div>
				</div>
				<!--  Evaluator : END -->
				<%} else{ %>
				<!-- Owner : BEGIN -->
				<div class="row">
				<div class="col-xs-12">
				<h4>Statistics</h4>
				</div>
				
					<div class="col-xs-4">
						<h4>Risk Level:</h4>
						<h4><%= project.getRisk()%></h4>
					</div>		
					<div class="col-xs-4">
						<h4>Attractiveness:</h4>
						<h4><%= project.getAttractiveness()%></h4>
					</div>	
					<div class="col-xs-4">
						<h4>#of evaluators</h4>
						<h4><%= project.getEvaluations().size() %></h4>
					</div>			
				</div>				
				<%} %>
				<!-- Owner : END -->
				<div id="ajaxResponse"></div>
			</div>

		</div>

	</div>
</div>


<jsp:include page="/utils/footer.jsp"></jsp:include>