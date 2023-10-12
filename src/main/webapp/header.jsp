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
                <div class="d-flex me-auto">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <% String home_url = "redirect.jsp"; %>
                            <a class="nav-link active" aria-current="page" href="<%=home_url %>">Home</a>
                        </li>
<%--                        <li class="nav-item dropdown">--%>
<%--                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">--%>
<%--                                Dropdown--%>
<%--                            </a>--%>
<%--                            <ul class="dropdown-menu">--%>
<%--                                <li><a class="dropdown-item" href="#">Action</a></li>--%>
<%--                                <li><a class="dropdown-item" href="#">Another action</a></li>--%>
<%--                                <li><hr class="dropdown-divider"></li>--%>
<%--                                <li><a class="dropdown-item" href="#">Something else here</a></li>--%>
<%--                            </ul>--%>
<%--                        </li>--%>
                    </ul>
                </div>
<%--                <form class="d-flex mx-auto" role="search">--%>
<%--                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">--%>
<%--                    <button class="btn btn-outline-success" type="submit">Search</button>--%>
<%--                </form>--%>
                <br>
                <%
                    String user = (String) session.getAttribute("user");
                    String servletURL = "login_user";
                    String cartURL = "cart";
                    String logoutURL = "logout";
                    if (user != null) {
                %>
                <a href="<%= cartURL %>" class="btn btn-outline-success me-3" style="margin-right: 15px;">
                    <i class="fas fa-shopping-cart"></i> Cart
                </a>
                <a href="<%=logoutURL %>" class="btn btn-outline-success">
                    Logout
                </a>
                <%
                } else {
                %>
                <a href="<%=servletURL %>" class="btn btn-outline-success me-2">Login</a>
                <%
                    }
                %>

            </div>
        </div>
    </nav>


</div>
