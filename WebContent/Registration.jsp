<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Prijava na servis RestoranReservation</title>

    <!-- Bootstrap -->
    <link href="bootstrap.min.css" rel="stylesheet">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap.min.js"></script>

    <script>
	$(document).ready(function() {

		$("#regButton").click( function(event) {
			  event.preventDefault();
			  var ime = $("#inputIme").val();
			  var prezime = $("#inputPrezime").val();
			  var password = $("#inputPassword").val();
			  var password2 = $("#inputPassword2").val();
			  var email = $("#inputEmail").val();
			  
			  if(password !== password2) {
				  alert("Lozinke se ne poklapaju");
				  return;
			  }
			  
			  if(ime == "") {
				  alert("Morate popuniti ime.");
				  return;
			  }
			  
			  $.post("${pageContext.request.contextPath}/RegisterServlet",
					  {
				       register: true,
				       ime: ime,
				       prezime: prezime,
			           password: password,
			           email: email,
			           uloga: 1
					  },
					  function(data, status) {
						 if(data == "true") {
							 alert("Izmenjeni podaci");
						 } else {
							 alert("Doslo je do problema prilikom obrade podataka");
						 }
					  });
		  });
	});
</script>
    
  </head>

  <body>

    <div class="container">
    <div class="col-xs-4">
      <form class="form-signin" action="./LogInServlet" method="post">
        <h2 class="form-signin-heading">Unesite podatke za registraciju</h2>
        <label for="inputEmail" class="sr-only">Email adresa</label>
        <input type="email" name="korisnickoIme" id="inputEmail" class="form-control" placeholder="Email adresa" required autofocus>
        <label for="inputIme" class="sr-only">Ime</label>
        <input type="text" name="ime" id="inputIme" class="form-control" placeholder="Ime" required>
        <label for="inputPrezime" class="sr-only">Prezime</label>
        <input type="text" name="prezime" id="inputPrezime" class="form-control" placeholder="Prezime" required>
        <label for="inputPassword" class="sr-only">Lozinka</label>
        <input type="password" name="lozinka" id="inputPassword" class="form-control" placeholder="Lozinka" required>
        <label for="inputPassword2" class="sr-only">Lozinka</label>
        <input type="password" name="lozinka" id="inputPassword2" class="form-control" placeholder="Ponovljena lozinka" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit" id="regButton">Prijava</button>
      </form>
    </div>
    <a href="login.jsp">Prijava</a>
    </div> <!-- /container -->

  </body>
</html>
