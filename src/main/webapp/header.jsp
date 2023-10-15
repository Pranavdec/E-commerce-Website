<!DOCTYPE html>
<html>
<head>
    <title>Your Grocery Store</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>
<div class="header">
    <h1>Welcome to Our Grocery Store</h1>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <div class="collapse navbar-collapse justify-content-between" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <% String home_url = "redirect.jsp"; %>
                        <a class="nav-link active" aria-current="page" href="<%=home_url %>">Home</a>
                    </li>
                </ul>

                <div class="navbar-nav">
                    <%
                        String user = (String) session.getAttribute("user");
                        String servletURL = "login_user";
                        String cartURL = "cart";
                        String logoutURL = "logout";
                        if (user != null) {
                    %>
                    <a href="<%= cartURL %>" class="nav-item nav-link" style="margin-right: 15px;">
                        <i class="fas fa-shopping-cart"></i> Cart
                    </a>
                    <a href="<%=logoutURL %>" class="nav-item nav-link">
                        Logout
                    </a>
                    <%
                    } else {
                    %>
                    <a href="<%=servletURL %>" class="nav-item nav-link">Login</a>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </nav>
</div>
