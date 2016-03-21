<%@ page language="java" contentType="text/html; charset=UTF-8"
        	pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<jsp:include page="/utils/header.jsp"></jsp:include>

<div class="row">
  <div class="col-xs-8 col-xs-offset-2">
    <div class="panel panel-info">
      <div class="panel-heading">
        <h3 class="panel-title">New Project Idea</h3>
      </div>
      <div class="panel-body">

        <form id="newProjectForm" method="post" data-toggle="validator">
          <div class="form-group">
            <label class="control-label">Title</label>
            <input type="text" class="form-control" id="title" placeholder="Title" name="title" required>
          </div>

          <div class="form-group">
            <label class="control-label">Description</label>
            <textarea
                class="form-control"
                rows="3"
                id="description"
                placeholder="Desctiption"
                name="description"
                required></textarea>

          </div>

          <hr>

          <div>
            <h1></h1>

          </div>
          <div class="row">
            <div class="col-xs-4">
              <div class="form-group">
                <label class="control-label">Category:</label>
                <select class="selectpicker" id="category">
                  <%
                  ArrayList<String> categorys = (ArrayList<String>) request.getAttribute("category");
                  for (int i = 0; i < categorys.size(); i++) {
                    %>
                    <option data="<%= categorys.get(i)%>"><%= categorys.get(i) %></option>
                  <% } %>

                </select>
                <!--
                  <label class="control-label">Category:</label> <input type="text" class="form-control"
                  id="inputCategory" placeholder="Category" name="category" required>
                -->

              </div>
            </div>
            <div class="col-xs-4">
              <div class="form-group">

                <label class="control-label">Incubation # of days:</label>
                <input
                    type="number"
                    class="form-control"
                    id="incubation"
                    placeholder="Incubation"
                    name="incubation"
                    required>

              </div>
            </div>
            <div class="col-xs-4">
              <div class="form-group">

                <label class="control-label">Budget(EUR):</label>
                <input type="number" class="form-control" id="budget" placeholder="Budget" name="budget" required>
              </div>
            </div>
          </div>

          <hr>

          <button id="formSubmit" class="btn btn-default" type="submit">Save</button>
          <button class="btn btn-default">Discard</button>
          <div id="ajaxResponse"></div>
        </form>
        <script>
          $('#newProjectForm')
          .validator()
          .on(
          'submit',
          function(e) {
            if (e.isDefaultPrevented()) {
              // handle the invalid form...
            } else {
              var form = $('#newProjectForm');
              $
              .ajax({
                type : "POST",
                url : "/ProjectFarm/controller/NewProject",

                //data : form.serialize(),
                data:{
                  title: $("#title").val(),
                  description: $("#description").val(),
                  category: $("#category").val(),
                  incubation: $("#incubation").val(),
                  budget: $("#budget").val()
                  },

                  success : function(data) {
                    if (data.isSuccess == "false") {
                      $(
                      "#ajaxResponse")
                      .append(
                      "<hr> <div class='alert alert-danger' role='alert'>"
                      + data.errorMessage
                      + "</div>");
                    } else if (data.isSuccess == "true") {
                      $(
                      "#ajaxResponse")
                      .append(
                      "<hr><div class='alert alert-success' role='alert'>New project has been created</div>");
                    }
                    },
                    error : function() {
                      $("#ajaxResponse")
                      .append(
                      "<h1>fails</h1>");
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

