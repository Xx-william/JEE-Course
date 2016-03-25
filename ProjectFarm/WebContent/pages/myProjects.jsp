<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Project" %>
<%@ page import="java.lang.reflect.Type" %>
<%@ page import="model.MyProjectPage" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%@ page import="com.google.gson.Gson" %>
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
    <th>Risk Level</th>
    <th>Attractiveness</th>
    <th>Number of evaluators</th>
  </tr>
  </thead>
  <tbody>
  
   <%
   	String json = (String)request.getAttribute("projects");
    Gson gson = new Gson();
    Type collectionType = new TypeToken<ArrayList<MyProjectPage>>(){}.getType();
    ArrayList<MyProjectPage> projects = gson.fromJson(json, collectionType);
      
    for(MyProjectPage project : projects){
    	%> 
    	<tr>
		<td><a href="<%=request.getContextPath() %>/controller/Evaluate?projectId=<%=project.getProjectId()%>"><%=project.getAcronym()%></a></td>
    	
    	<td><%=project.getCategory() %> </td>
    	<td><%=project.getIncubation() %> </td>
    	<td><%=project.getBudget() %> </td>
    	<%
    		double risk = project.getRisk();
    		String riskColor = "";
    		if(risk < 2){
    			riskColor = "#5cb85c"; // green 
    		}else if(risk>=2 && risk < 4){
    			riskColor = "#f0ad4e"; // yellow
    		}else if(risk >=4 && risk <=5){
    			riskColor = "#d9534f"; // red
    		}
    	%>
    	<td style="background-color: <%=riskColor%>"><%=project.getRisk() %> </td>
    	
    	<%
    		double attractive = project.getAttractiveness();
    		String attractiveColor = "";
    		if(attractive < 2){
    			attractiveColor = "#d9534f"; // red 
    		}else if(attractive>=2 && attractive < 4){
    			attractiveColor = "#f0ad4e"; // yellow
    		}else if(attractive >=4 && attractive <=5){
    			attractiveColor = "#5cb85c"; // green 
    		}
    	%>
    	<td style="background-color: <%=attractiveColor%>"><%=project.getAttractiveness() %> </td>
    	<td><%=project.getNumEvaluators() %> </td>
    	</tr>
    	<% } %>
</tbody>
</table>
</div>
</div>
<jsp:include page="/utils/footer.jsp"></jsp:include>