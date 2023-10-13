<%--suppress unchecked --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header_shop.jsp"%>

<div class="container mt-5 content">
  <div class="row">
    <div class="col-md-6 offset-md-3">
      <form action="itemAdd" method="post" enctype="multipart/form-data" onsubmit="validateForm(event)">
        <input type="hidden" name="itemId" />
        <div class="form-group">
          <label for="itemName">Item Name:</label>
          <input type="text" id="itemName" name="itemName" class="form-control" required />
        </div>
        <div class="form-group">
          <label for="itemPrice">Item Price:</label>
          <input type="text" id="itemPrice" name="itemPrice" class="form-control" required/>
        </div>
        <div class="form-group">
          <label for="itemQuantity">Item Quantity:</label>
          <input type="number" id="itemQuantity" name="itemQuantity" class="form-control" required/>
        </div>
        <div class="form-group">
          <label for="itemDescription">Item Description:</label>
          <textarea id="itemDescription" name="itemDescription" class="form-control" required></textarea>
        </div>
        <div class="form-group">
          <label for="itemCategory">Item Category:</label>
          <input type="text" id="itemCategory" name="itemCategory" class="form-control" required/>
        </div>
        <div class="form-group">
          <label for="file">Item Image:</label>
          <input type="file" name="file" id="file" required>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
        <br>
        <br>
      </form>
    </div>
  </div>
</div>

<script>
  function validateForm(event){
    let itemName = document.getElementById("itemName").value;
    let itemPrice = document.getElementById("itemPrice").value;
    let itemCategory = document.getElementById("itemCategory").value;

    if(itemName.length > 50){
      alert("Item name must be less than 50 characters");
      event.preventDefault()
      return false;
    }

    if(itemCategory.length > 30){
      alert("Item name must be less than 30 characters");
      event.preventDefault()
      return false;
    }

    if (!itemPrice.match(/^\d+(\.\d+)?$/)) {
      alert("Item price must be a valid number");
      event.preventDefault();
      return false;
    }



  }
</script>

<%@ include file="footer.jsp"%>
