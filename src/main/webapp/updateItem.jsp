<%--suppress unchecked --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header_shop.jsp"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Dictionary" %>

<div class="container mt-5 content">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <form action="itemList" method="get" id="itemSelectionForm" class="mb-4">
                <div class="form-group">
                    <label for="selectedItem">Choose an Item:</label>
                    <select name="selectedItem" id="selectedItem" class="form-control" onchange="document.getElementById('itemSelectionForm').submit()">
                        <%
                            List<String> itemNames = (List<String>) request.getAttribute("itemNames");
                            for (String itemName : itemNames) {
                        %>
                        <option value="<%= itemName %>"><%= itemName %></option>
                        <%
                            }
                        %>
                    </select>
                </div>
            </form>

            <form action="itemList" method="post" enctype="multipart/form-data">
                <%
                    Dictionary<String, String> itemDetails = (Dictionary<String, String>) request.getAttribute("itemDetails");
                    if (itemDetails != null) {
                %>
                <input type="hidden" name="itemId" value="<%= itemDetails.get("item_id") %>" />
                <div class="form-group">
                    <label for="itemName">Item Name:</label>
                    <input type="text" id="itemName" name="itemName" class="form-control" value="<%= itemDetails.get("item_name") %>" />
                </div>
                <div class="form-group">
                    <label for="itemPrice">Item Price:</label>
                    <input type="text" id="itemPrice" name="itemPrice" class="form-control" value="<%= itemDetails.get("price") %>" />
                </div>
                <div class="form-group">
                    <label for="itemQuantity">Item Quantity:</label>
                    <input type="text" id="itemQuantity" name="itemQuantity" class="form-control" value="<%= itemDetails.get("quantity") %>" />
                </div>
                <div class="form-group">
                    <label for="itemDescription">Item Description:</label>
                    <textarea id="itemDescription" name="itemDescription" class="form-control"><%= itemDetails.get("description") %></textarea>
                </div>
                <div class="form-group">
                    <label for="itemCategory">Item Category:</label>
                    <input type="text" id="itemCategory" name="itemCategory" class="form-control" value="<%= itemDetails.get("category") %>" />
                </div>
                <input type="hidden" name="itemPath" value="<%= itemDetails.get("image_path") %>" />
                <div class="form-group">
                    <label for="file">Item Image:</label>
                    <input type="file" name="file" id="file">
                </div>
                <button type="submit" class="btn btn-primary">Update</button>
                <br>
                <br>
                <%
                    }
                %>
            </form>
        </div>
    </div>
</div>

<%@ include file="footer.jsp"%>
