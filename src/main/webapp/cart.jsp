<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp"%>
<div class="content">
    <h1>Cart</h1>
    <br>
    <div class="container">
        <div class="row row-cols-auto">
            <%= request.getAttribute("cartHtml") %>
        </div>
    </div>
</div>
<%@ include file="footer.jsp"%>