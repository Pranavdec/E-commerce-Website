<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header_shop.jsp"%>

<script>
  function toggleReportForm() {
    let reportType = document.getElementById("reportType").value;
    let dateInputs = document.getElementById("dateInputs");
    let userSelectInput = document.getElementById("userSelectInput");

    if(reportType === "1") {
      dateInputs.style.display = "block";
      userSelectInput.style.display = "none";
    } else {
      dateInputs.style.display = "none";
      userSelectInput.style.display = "block";
    }
  }
</script>

<div class="container mt-5 content">
  <div class="row">
    <div class="col-md-6 offset-md-3">
      <form action="report" method="post" id="itemSelectionForm" class="mb-4" onsubmit="return validateForm()">
      <div class="form-group">
          <label for="reportType">Report Type</label>
          <select class="form-control" id="reportType" name="reportType" required onchange="toggleReportForm()">
            <option value="1" selected>Sales Report</option>
            <option value="2">User Report</option>
          </select>
        </div>

        <div id="dateInputs" class="form-group">
          <label for="startDate">Start Date</label>
          <input type="date" class="form-control" id="startDate" name="startDate">
          <label for="endDate">End Date</label>
          <input type="date" class="form-control" id="endDate" name="endDate">
        </div>


        <div id="userSelectInput" class="form-group" style="display:none;">
          <label for="userSelect">Select User:</label>
          <select class="form-control" id="userSelect" name="userSelect">
            <%
              List<String> users = (List<String>) request.getAttribute("users");
              for (String user1 : users) {
            %>
            <option value="<%= user1 %>"><%= user1 %></option>
            <%
              }
            %>
          </select>
        </div>

        <input type="submit" class="bs-icon">
      </form>

      <div>
        ${content}
      </div>
    </div>
  </div>
</div>

<script>
  toggleReportForm();
  function validateForm() {
    let reportType = document.getElementById("reportType").value;
    let startDate = document.getElementById("startDate");
    let endDate = document.getElementById("endDate");
    let userSelect = document.getElementById("userSelect");

    if(reportType === "1") {
      if(startDate.value === "" || endDate.value === "") {
        alert("Please select both a start date and an end date.");
        return false;
      }
    } else {
      if(userSelect.value === "") {
        alert("Please select a user.");
        return false;
      }
    }

    return true;
  }
</script>

<%@ include file="footer.jsp"%>
