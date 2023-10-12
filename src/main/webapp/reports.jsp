<%--suppress unchecked --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header_shop.jsp"%>

<div class="container mt-5 content">
  <div class="row">
    <div class="col-md-6 offset-md-3">
      <form action="report" method="post" id="itemSelectionForm" class="mb-4">
        <div class="form-group">
          <label for="startDate">Start Date</label>
          <input type="date" class="form-control" id="startDate" name="startDate" required>
          <label for="endDate">End Date</label>
          <input type="date" class="form-control" id="endDate" name="endDate" required>
          <input type="submit" class="bs-icon">
        </div>
      </form>

      <div>
        ${content}
      </div>
    </div>
  </div>
</div>

<%@ include file="footer.jsp"%>
