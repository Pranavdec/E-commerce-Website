<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<div class="container mt-5">

    <% String redirect_url = "home"; %>
    <form action="<%=redirect_url%>" method="post" id="paginationForm" class="mb-4">
        <div class="form-group row align-items-center">
            <label for="itemsPerPage" class="col-form-label" style="margin-right: 10px">
                <strong>Items per Page:</strong>
            </label>
            <input type="number" class="form-control" name="itemsPerPage" id="itemsPerPage" required min="1" style="width: 100%; max-width: 150px;">
        </div>
    </form>

    <div class="row">
        <%= request.getAttribute("cardsHtml") %>
    </div>
</div>


<%@ include file="footer.jsp" %>

<script type="text/javascript">
    window.addEventListener('load', function() {
        let userExists = <%= (user != null) ? "true" : "false" %>;

        let submitButtons = document.querySelectorAll('input[type="submit"]');
        for (let i = 0; i < submitButtons.length; i++) {
            submitButtons[i].disabled = !userExists;
        }
    });
</script>
