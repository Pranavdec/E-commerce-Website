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
  function formvalidation(event){
    console.log("form validation");
    let email = document.forms["user"]["email"].value;
    let contact = document.forms["user"]["contact"].value;
    let address = document.forms["user"]["address"].value;
    let password = document.forms["user"]["password"].value;
    let password_confirm = document.forms["user"]["password_confirm"].value;
    if(email === ""){
      alert("Email must be filled out");
      event.preventDefault();
      return false;
    }
    if(contact === ""){
      alert("Contact must be filled out");
      event.preventDefault();
      return false;
    }
    if(address === ""){
      alert("Address must be filled out");
      event.preventDefault();
      return false;
    }
    if(password === ""){
      alert("Password must be filled out");
      event.preventDefault();
      return false;
    }
    if(password_confirm === ""){
      alert("Password must be filled out");
      event.preventDefault();
      return false;
    }
    if(password !== password_confirm){
      alert("Password and Confirm Password must be same");
      event.preventDefault();
      return false;
    }

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
    if(contact.length >15){
      alert("Contact must be less than 10 characters");
      event.preventDefault();
      return false;
    }

    //validate email
    let emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if(!emailRegex.test(email)){
      alert("Email is not valid");
      return false;
    }

    return true;
  }

  function formvalidation1(event){
    console.log("form validation");
    let email = document.forms["shopkeeper"]["email"].value;
    let contact = document.forms["shopkeeper"]["contact"].value;
    let address = document.forms["shopkeeper"]["address"].value;
    let company_name = document.forms["shopkeeper"]["company_name"].value;
    let password = document.forms["shopkeeper"]["password"].value;
    let password_confirm = document.forms["shopkeeper"]["password_confirm"].value;
    if(email === ""){
      alert("Email must be filled out");
      event.preventDefault();
      return false;
    }
    if(contact === ""){
      alert("Contact must be filled out");
      event.preventDefault();
      return false;
    }
    if(address === ""){
      alert("Address must be filled out");
      event.preventDefault();
      return false;
    }
    if(company_name === ""){
      alert("Company Name must be filled out");
      event.preventDefault();
      return false;
    }
    if(password === ""){
      alert("Password must be filled out");
      event.preventDefault();
      return false;
    }
    if(password_confirm === ""){
      alert("Password must be filled out");
      event.preventDefault();
      return false;
    }
    if(password !== password_confirm){
      alert("Password and Confirm Password must be same");
      event.preventDefault();
      return false;
    }

    if(password.length >30){
      alert("Password must be less than 30 characters");
      event.preventDefault();
      return false;
    }
    if(email.length >50){
      alert("Email must be less than 50 characters");
      event.preventDefault();
      return false;
    }
    if(contact.length >15){
      alert("Contact must be less than 15 characters");
      event.preventDefault();
      return false;
    }
    if(company_name.length >50){
      alert("Company Name must be less than 50 characters");
      event.preventDefault();
      return false;
    }

    //validate email
    let emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if(!emailRegex.test(email)){
      alert("Email is not valid");
      return false;
    }

    return true;
  }
</script>
<section class="position-relative py-4 py-xl-5">
  <div class="container">
    <div class="row mb-5">
      <div class="col-md-8 col-xl-6 text-center mx-auto">
        <h2>Register</h2>
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
            <form class="text-center" method="post" onsubmit="formvalidation(event)" name="register" action="${pageContext.request.contextPath}/register_user">
              <div class="mb-3"><label>
                <input class="form-control" type="email" name="email" placeholder="Email" required>
              </label></div>
              <div class="mb-3"><label>
                <input class="form-control"  name="contact" placeholder="Phone Number" required>
              </label></div>
              <div class="mb-3"><label>
                <input class="form-control"  name="address" placeholder="Address" required>
              </label></div>
              <div class="mb-3"><label>
                <input class="form-control" type="password" name="password" placeholder="Password" required>
              </label></div>
              <div class="mb-3"><label>
                <input class="form-control" type="password" name="password_confirm" placeholder="Confirm Password" required>
              </label></div>

              <div class="mb-3"><button class="btn btn-primary d-block w-100" type="submit">Register</button></div>
            </form>
          </div>
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
    <div id="shopkeeper" class="row d-flex justify-content-center" style="display: none !important;">
      <div class="col-md-6 col-xl-4">
        <div class="card mb-5">
          <div class="card-body d-flex flex-column align-items-center">
            <div class="bs-icon-xl bs-icon-circle bs-icon-primary bs-icon my-4"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-person">
              <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"></path>
            </svg></div>
            <form class="text-center" method="post" onsubmit="formvalidation1(event)" name="register" action="${pageContext.request.contextPath}/register_shopkeeper">
              <div class="mb-3"><label>
                <input class="form-control" type="email" name="email" placeholder="Email" required>
              </label></div>
              <div class="mb-3"><label>
                <input class="form-control"  name="contact" placeholder="Phone Number" required>
              </label></div>
              <div class="mb-3"><label>
                <input class="form-control"  name="address" placeholder="Address" required>
              </label></div>
              <div class="mb-3"><label>
                <input class="form-control"  name="company_name" placeholder="Company Name" required>
              </label></div>
              <div class="mb-3"><label>
                <input class="form-control" type="password" name="password" placeholder="Password" required>
              </label></div>
              <div class="mb-3"><label>
                <input class="form-control" type="password" name="password_confirm" placeholder="Confirm Password" required>
              </label></div>

              <div class="mb-3"><button class="btn btn-primary d-block w-100" type="submit">Register</button></div>
            </form>
          </div>
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