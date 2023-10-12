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


<script type="text/javascript">
    window.addEventListener('load', function() {
        let userExists = <%= (user != null) ? "true" : "false" %>;

        let submitButtons = document.querySelectorAll('input[type="submit"]');
        for(let i = 0; i < submitButtons.length; i++) {
            submitButtons[i].disabled = !userExists;
        }
    });
</script>

