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

<title>Restorani</title>

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

		$("#dodajButton").click( function(event) {
			  event.preventDefault();
			  var ime = $("#inputNazivRest").val();
			  var vrsta = $("#inputVrstaRest").val();
			  var adresa = $("#inputAdresaRest").val();
			  
			  if(ime == "") {
				  alert("Morate popuniti ime.");
				  return;
			  }
			  
			  $.post("${pageContext.request.contextPath}/RestoranServlet",
					  {
				       dodaj: true,
				       naziv: ime,
				       vrsta: vrsta,
				       adresa: adresa
					  },
					  function(data, status) {
						 if(data == "true") {
							 alert("Restoran je dodat");
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
					<li><a href="Restorani.jsp">Restorani</a></li>
					<li><a href="#">Menadzeri</a></li>
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
			<br /> <br /> <br />

            <div>
		    <h3>Restorani</h3>
		    <table class="table table-striped table-hover table-bordered">
              <tbody>
                <tr>
                    <th>Naziv</th>
                    <th>Vrsta</th>
                    <th>Adresa</th>
                    <th></th>
                </tr>
                <c:forEach var="r" items="${restorani}">
		        <tr>
		          <td>${r.naziv}</td>
                  <td>${r.vrsta}</td>
                  <td>${r.adresa}</td>
                  <td><a href="RestoranServlet?postavi=true&id=${r.id}">Postavi menadzera</a></td>
		        </tr>
		        </c:forEach>
		       </tbody>
		    </table>
		</div>

			<div class="col-xs-4">
				<form class="form-signin" action="./LogInServlet" method="post">
					<h2 class="form-signin-heading">Unesite naziv restorana</h2>
					<label for="inputEmail" class="sr-only">Naziv restorana</label> 
					<input type="text" name="nazivRestorana" id="inputNazivRest"
						class="form-control" placeholder="Naziv restorana" required>
					
					<label for="inputEmail" class="sr-only">Vrsta restorana</label> 
					<input type="text" name="vrstaRestorana" id="inputVrstaRest"
						class="form-control" placeholder="Vrsta restorana" required>
						
					<label for="inputEmail" class="sr-only">Adresa restorana</label> 
					<input type="text" name="adresaRestorana" id="inputAdresaRest"
						class="form-control" placeholder="Adresa restorana" required>
						
					<button class="btn btn-lg btn-primary btn-block" type="submit"
						id="dodajButton">Prijava</button>
					</button>
				</form>
			</div>
		</div>

	</div>
	<!-- /.container -->

</body>
</html>