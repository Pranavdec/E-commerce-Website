<!DOCTYPE html>
<html data-bs-theme="light" lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
  <title>Untitled</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/Login-Form-Basic-icons.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

</head>

<body>
<script>
  function formvalidation(event) {
    console.log("form validation");
    let email = document.forms["register"]["email"].value;
    let password = document.forms["register"]["password"].value;

    if(password.length >30){
      alert("Password must be less than 30 characters");
      event.preventDefault();
      return false;
    }
    if(email.length >50){
      alert("Email must be less than 30 characters");
      event.preventDefault();
      return false;
    }
  }
</script>
<section class="position-relative py-4 py-xl-5">
  <div class="container">
    <div class="row mb-5">
      <div class="col-md-8 col-xl-6 text-center mx-auto">
        <h2>Log in</h2>
      </div>
    </div>
    <div class="d-flex justify-content-center align-items-center" style="height: 5rem;">
      <div class="custom-control custom-switch" style="transform: scale(1.5);">
        <input type="checkbox" class="custom-control-input" id="customSwitches" onclick="handleToggle()">
        <label id= "switch" class="custom-control-label" for="customSwitches" style="font-size: 1.25rem;">User</label>
      </div>
    </div>
    <div id="user" class="row d-flex justify-content-center">
      <div class="col-md-6 col-xl-4">
        <div class="card mb-5">
          <div class="card-body d-flex flex-column align-items-center">
            <div class="bs-icon-xl bs-icon-circle bs-icon-primary bs-icon my-4"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-person">
              <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"></path>
            </svg></div>
            <form class="text-center" method="post" action="${pageContext.request.contextPath}/login_user" onsubmit="formvalidation(event)">
              <div class="mb-3"><label>
                <input class="form-control" type="email" name="email" placeholder="Email" required>
              </label></div>
              <div class="mb-3"><label>
                <input class="form-control" type="password" name="password" placeholder="Password" required>
              </label></div>
              <div class="mb-3"><button class="btn btn-primary d-block w-100" type="submit">Login</button></div>
              <a class="text-muted" href="${pageContext.request.contextPath}/register_user">Register</a>
            </form>
            <div>
              <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
              %>
              <p style="color: red"><%= error %></p>
              <%
                }
              %>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div id="shopkeeper" class="row d-flex justify-content-center" style="display: none !important;"  >
      <div class="col-md-6 col-xl-4">
        <div class="card mb-5">
          <div class="card-body d-flex flex-column align-items-center">
            <div class="bs-icon-xl bs-icon-circle bs-icon-primary bs-icon my-4"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-person">
              <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"></path>
            </svg></div>
            <form class="text-center" method="post" action="${pageContext.request.contextPath}/login_shopkeeper" onsubmit="formvalidation(event)">
              <div class="mb-3"><label>
                <input class="form-control" type="email" name="email" placeholder="Email" required>
              </label></div>
              <div class="mb-3"><label>
                <input class="form-control" type="password" name="password" placeholder="Password" required>
              </label></div>
              <div class="mb-3"><button class="btn btn-primary d-block w-100" type="submit">Login</button></div>
              <a class="text-muted" href="${pageContext.request.contextPath}/register_shopkeeper">Register</a>
            </form>
            <div>
              <%
                error = (String) request.getAttribute("error");
                if (error != null) {
              %>
              <p style="color: red"><%= error %></p>
              <%
                }
              %>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<script>
  function handleToggle() {
    let toggleSwitch = document.getElementById('customSwitches');
    if (toggleSwitch.checked) {
      document.getElementById('user').style.cssText = "display: none !important;";
      document.getElementById('shopkeeper').style.cssText = "display: flex !important;";
      document.getElementById('switch').innerHTML = "Shopkeeper";
    } else {
      document.getElementById('user').style.cssText = "display: flex !important;";
      document.getElementById('shopkeeper').style.cssText = "display: none !important;";
      document.getElementById('switch').innerHTML = "User";
    }
  }

</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>

</html>