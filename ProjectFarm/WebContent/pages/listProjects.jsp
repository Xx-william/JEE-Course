<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.lang.reflect.Type"%>
<%@ page import="com.google.gson.reflect.TypeToken"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="model.Project" %>
<jsp:include page="/utils/header.jsp"></jsp:include>

<div class="row">
	<div class="col-xs-8 col-xs-offset-2">
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Acronym</th>
					<th>Category</th>
					<th>Number of incubation days</th>
					<th>Budget</th>
					<th>Number of evaluations</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			<%
				String json = (String)request.getAttribute("projects");
				Gson gson = new Gson();
				 Type collectionType = new TypeToken<ArrayList<Project>>(){}.getType();
				 ArrayList<Project> projects = gson.fromJson(json, collectionType);
				 
				 for(Project project : projects){
					 %> 
				    	<tr>
				    	<td><%=project.getAcronym() %> </td>
				    	<td><%=project.getCategory().getDescription() %> </td>
				    	<td><%=project.getFundingDuration() %> </td>
				    	<td><%=project.getBudget() %> </td>
				    	<td><%=project.getEvaluations().size() %> </td>
				    	<td><a class="btn btn-primary" href="<%=request.getContextPath() %>/controller/Evaluate?projectId=<%=project.getProjectId()%>">Evaluate</a></td>
				    	<%
				 }
			%>
			
			</tbody>
		</table>
	</div>
</div>


<jsp:include page="/utils/footer.jsp"></jsp:include>