<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp"%>
<div class="content">
    <div class="py-5 text-center">
        <h1 class="display-4">Your Shopping Cart</h1>
        <p class="lead">Review your products before proceeding to checkout.</p>
    </div>

    <div class="container">
        <div class="row row-cols-auto">
            <%= request.getAttribute("cartHtml") %>
        </div>

        <% String buyUrl = "buy"; %>

        <form action="<%= buyUrl %>" method="post" class="mt-4">
            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary btn-lg">Proceed to Checkout</button>
            </div>
        </form>
    </div>
</div>
<br>
<%@ include file="footer.jsp"%>
