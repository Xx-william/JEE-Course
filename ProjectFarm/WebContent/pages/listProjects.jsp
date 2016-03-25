<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.lang.reflect.Type"%>
<%@ page import="com.google.gson.reflect.TypeToken"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="model.ListProjectsPage" %>
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
				ArrayList<ListProjectsPage> listProjectsPages = new ArrayList<ListProjectsPage>();
				String json = (String)request.getAttribute("projects");
				Gson gson = new Gson();
				 Type collectionType = new TypeToken<ArrayList<ListProjectsPage>>(){}.getType();
				 ArrayList<ListProjectsPage> projects = gson.fromJson(json, collectionType);
				 
				 for(ListProjectsPage project : projects){
					 %> 
				    	<tr>
				    	<td><%=project.getAcronym() %> </td>
				    	<td><%=project.getCategory() %> </td>
				    	<td><%=project.getIncubation() %> </td>
				    	<td><%=project.getBudget() %> </td>
				    	<td><%=project.getNumEvaluations() %> </td>
				    	<td><a class="btn btn-primary" href="<%=request.getContextPath() %>/controller/Evaluate?projectId=<%=project.getProjectId()%>">Evaluate</a></td>
				    	<%
				 }
			%>
			
			</tbody>
		</table>
	</div>
</div>


<jsp:include page="/utils/footer.jsp"></jsp:include>