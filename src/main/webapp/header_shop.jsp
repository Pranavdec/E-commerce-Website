<!DOCTYPE html>
<html>
<head>
    <title>Your Grocery Store</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="header">
    <h1>Welcome to Our Grocery Store</h1>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-between" id="navbarSupportedContent">
                <div class="d-flex me-auto">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <% String home_url = "home_shopkeeper"; %>
                            <a class="nav-link active" aria-current="page" href="<%=home_url %>">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Update</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link">Add</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link">Reports</a>
                        </li>
                    </ul>
                </div>
                <form class="d-flex mx-auto" role="search">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
                <br>
                <%
                    String user = (String) session.getAttribute("user");
                    String servletURL = "login_user";
                    String cartURL = "cart";
                    if (user != null) {
                %>
                <a href="<%= cartURL %>" class="navbar-cart">

                    <i class="fas fa-shopping-cart"></i> Cart
                </a>
                <%
                } else {
                %>
                <a href="<%=servletURL %>" class="btn btn-outline-success">Login</a>
                <%
                    }
                %>

            </div>
        </div>
    </nav>


</div>
