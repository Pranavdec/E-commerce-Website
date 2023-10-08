<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp"%>
<div class="content">
    <br>
    <div class="container">
        <div class="row row-cols-auto">
            <%= request.getAttribute("cardsHtml") %>
        </div>
    </div>
</div>
<%@ include file="footer.jsp"%>