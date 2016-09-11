<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--  <meta http-equiv="X-UA-Compatible" content="IE=edge">    -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Pocetna strana</title>

<!-- Bootstrap core CSS -->
<link href="bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
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
			           uloga: 2
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

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">RestoranReservationApp</a>
		</div>
		<c:if test="${korisnik.ulogaKorisnika == 1}">
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Pocetna</a></li>
					<li><a href="#restaraunt">Restorani</a></li>
					<li><a href="#about">Prijatelji</a></li>
					<li><a href="#contact">Rezervacije</a></li>
					<li><a href="#ocene">Ocene</a></li>
					<li><a href="./UserAccServlet">Nalog</a></li>
					<li><a href="./LogOutServlet">Odjava</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</c:if>
		<c:if test="${korisnik.ulogaKorisnika == 2}">
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Pocetna</a></li>
					<li><a href="#restaraunt">Restoran</a></li>
					<li><a href="./UserAccServlet">Nalog</a></li>
					<li><a href="./LogOutServlet">Odjava</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</c:if>
		<c:if test="${korisnik.ulogaKorisnika == 3}">
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Pocetna</a></li>
					<li><a href="./ResturauntServlet">Restorani</a></li>
					<li><a href="AddMenadzer.jsp">Menadzeri</a></li>
					<li><a href="./UserAccServlet">Nalog</a></li>
					<li><a href="./LogOutServlet">Odjava</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</c:if>
	</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<br /> <br /> <br /> <br />
			<div class="col-xs-4">
				<form class="form-signin" action="./LogInServlet" method="post">
					<h2 class="form-signin-heading">Unesite podatke za
						menadzera</h2>
					<label for="inputEmail" class="sr-only">Email adresa</label> <input
						type="email" name="korisnickoIme" id="inputEmail"
						class="form-control" placeholder="Email adresa" required autofocus>
					<label for="inputIme" class="sr-only">Ime</label> <input
						type="text" name="ime" id="inputIme" class="form-control"
						placeholder="Ime" required> <label for="inputPrezime"
						class="sr-only">Prezime</label> <input type="text" name="prezime"
						id="inputPrezime" class="form-control" placeholder="Prezime"
						required> <label for="inputPassword" class="sr-only">Lozinka</label>
					<input type="password" name="lozinka" id="inputPassword"
						class="form-control" placeholder="Lozinka" required> <label
						for="inputPassword2" class="sr-only">Lozinka</label> <input
						type="password" name="lozinka" id="inputPassword2"
						class="form-control" placeholder="Ponovljena lozinka" required>
					<button class="btn btn-lg btn-primary btn-block" type="submit"
						id="regButton">Prijava</button>
				</form>
			</div>
		</div>

	</div>
	<!-- /.container -->

</body>
</html>