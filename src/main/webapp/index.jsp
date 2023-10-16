<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<div class="container mt-5">

    <% int totalPages = Integer.parseInt(request.getAttribute("totalPages").toString()); %>
    <form action="<%=redirect_url%>" method="post" id="paginationForm" class="mb-4">
        <div class="form-group row align-items-center">
            <label for="itemsPerPage" class="col-form-label" style="margin-right: 10px">
                <strong>Items per Page:</strong>
            </label>
            <input type="number" class="form-control" name="itemsPerPage" id="itemsPerPage" required min="1" style="width: 100%; max-width: 150px;" value="<%= (session.getAttribute("itemsPerPage") != null && !session.getAttribute("itemsPerPage").equals("")) ? session.getAttribute("itemsPerPage") : "10" %>">
        </div>
        <input type="hidden" name="currentPage" id="currentPage" value="<%= request.getParameter("currentPage") != null ? request.getParameter("currentPage") : "1" %>">
    </form>

    <div class="content">
        <div class="row">
            <%= request.getAttribute("cardsHtml") %>
        </div>
        <div class="pagination-controls">
            <button type="button" class="btn btn-primary" id="prevButton" onclick="changePage(-1)">Previous</button>
            <button type="button" class="btn btn-primary" id="nextButton" onclick="changePage(1)">Next</button>
        </div>
    </div>


</div>

<%@ include file="footer.jsp" %>

<script type="text/javascript">
    window.addEventListener('load', function() {

        let userExists = <%= (user != null) ? "true" : "false" %>;
        let totalPages = <%= totalPages %>;
        let currentPage = parseInt(document.getElementById('currentPage').value);
        console.log("totalPages: " + totalPages);
        console.log("currentPage: " + currentPage);

        document.getElementById('prevButton').disabled = (currentPage <= 1);
        document.getElementById('nextButton').disabled = (currentPage >= totalPages);


        let submitButtons = document.querySelectorAll('input[type="submit"]');
        for (let i = 0; i < submitButtons.length; i++) {
            submitButtons[i].disabled = !userExists;
        }
    });

    function changePage(direction) {
        let currentPage = parseInt(document.getElementById('currentPage').value);
        document.getElementById('currentPage').value = currentPage + direction;
        document.getElementById('paginationForm').submit();
    }
</script>
