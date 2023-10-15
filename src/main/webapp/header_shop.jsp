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
            <div class="collapse navbar-collapse justify-content-between" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <% String home_url = "home_shopkeeper"; %>
                        <a class="nav-link active" aria-current="page" href="<%=home_url %>">Home</a>
                    </li>
                    <li class="nav-item">
                        <% String update_url = "itemList"; %>
                        <a class="nav-link" href="<%= update_url %>">Update</a>
                    </li>
                    <li class="nav-item">
                        <% String add_url = "itemAdd"; %>
                        <a class="nav-link" href="<%= add_url%>">Add</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href = "report">Reports</a>
                    </li>
                </ul>

                <div class="navbar-nav">
                    <%
                        String user = (String) session.getAttribute("Shopkeeper_email");
                        String servletURL = "login_user";
                        String logoutURL = "logoutShopkeeper";
                        if (user != null) {
                    %>
                    <a href="<%=logoutURL %>" class="nav-item nav-link">Logout</a>
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
</body>
</html>
