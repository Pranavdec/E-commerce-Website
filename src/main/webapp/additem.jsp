<%--suppress unchecked --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header_shop.jsp"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Dictionary" %>

<div class="container mt-5 content">
  <div class="row">
    <div class="col-md-6 offset-md-3">
      <form action="itemAdd" method="post" enctype="multipart/form-data">
        <input type="hidden" name="itemId" />
        <div class="form-group">
          <label for="itemName">Item Name:</label>
          <input type="text" id="itemName" name="itemName" class="form-control" />
        </div>
        <div class="form-group">
          <label for="itemPrice">Item Price:</label>
          <input type="text" id="itemPrice" name="itemPrice" class="form-control" />
        </div>
        <div class="form-group">
          <label for="itemQuantity">Item Quantity:</label>
          <input type="text" id="itemQuantity" name="itemQuantity" class="form-control" />
        </div>
        <div class="form-group">
          <label for="itemDescription">Item Description:</label>
          <textarea id="itemDescription" name="itemDescription" class="form-control"></textarea>
        </div>
        <div class="form-group">
          <label for="itemCategory">Item Category:</label>
          <input type="text" id="itemCategory" name="itemCategory" class="form-control" />
        </div>
        <div class="form-group">
          <label for="file">Item Image:</label>
          <input type="file" name="file" id="file">
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
        <br>
        <br>
      </form>
    </div>
  </div>
</div>

<%@ include file="footer.jsp"%>
