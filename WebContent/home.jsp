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

		$("#vrstaButton").click( function(event) {
			  event.preventDefault();
			  
			  $.post("${pageContext.request.contextPath}/RestoranServlet",
					  {
				       sort: true,
				       param: "vrsta"
					  },
					  function(data, status) {
						  if(data == "false") {
					        	 $("#tableDiv").html("");
					         } else {
					        	 $("#tableDiv").html("");
					        	 $("#tableDiv").append('<tr><th>Naziv</th><th>Vrsta</th><th>Adresa</th><th></th></tr>');
					        	 $.each(data, function(i, k) {  
					        		    $("#tableDiv").append('<tr> <td>' + k.naziv + '</td>' +
					        		                               '<td>' + k.vrsta +  '</td>' +
					        		                               '<td>' + k.adresa + '</td>' +
					        		                               '<td><a href="RestoranServlet?detalji=true&id=' + k.id + '">Detalji</a></td></tr>');
					        	 });
					        }
				})
		  });
		
		$("#nazivButton").click( function(event) {
			  event.preventDefault();
			  
			  $.post("${pageContext.request.contextPath}/RestoranServlet",
					  {
				       sort: true,
				       param: "naziv"
					  },
					  function(data, status) {
						  if(data == "false") {
					        	 $("#tableDiv").html("");
					         } else {
					        	 $("#tableDiv").html("");
					        	 $("#tableDiv").append('<tr><th>Naziv</th><th>Vrsta</th><th>Adresa</th><th></th></tr>');
					        	 $.each(data, function(i, k) {  
					        		    $("#tableDiv").append('<tr> <td>' + k.naziv + '</td>' +
					        		                               '<td>' + k.vrsta +  '</td>' +
					        		                               '<td>' + k.adresa + '</td>' +
					        		                               '<td><a href="RestoranServlet?detalji=true&id=' + k.id + '">Detalji</a></td></tr>');
					        	 });
					        }
				})
		  });
		
		$("#adresaButton").click( function(event) {
			  event.preventDefault();
			  
			  $.post("${pageContext.request.contextPath}/RestoranServlet",
					  {
				       sort: true,
				       param: "adresa"
					  },
					  function(data, status) {
						  if(data == "false") {
					        	 $("#tableDiv").html("");
					         } else {
					        	 $("#tableDiv").html("");
					        	 $("#tableDiv").append('<tr><th>Naziv</th><th>Vrsta</th><th>Adresa</th><th></th></tr>');
					        	 $.each(data, function(i, k) {  
					        		    $("#tableDiv").append('<tr> <td>' + k.naziv + '</td>' +
					        		                               '<td>' + k.vrsta +  '</td>' +
					        		                               '<td>' + k.adresa + '</td>' +
					        		                               '<td><a href="RestoranServlet?detalji=true&id=' + k.id + '">Detalji</a></td></tr>');
					        	 });
					        }
				})
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
					<li><a href="#">Restorani</a></li>
					<li><a href="./UserAccServlet">Prijatelji</a></li>
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
					<li><a href="./RestoranMenadzerServlet?izbor=restoran">Restoran</a></li>
					<li><a href="./RestoranMenadzerServlet?izbor=jelovnik">Jelovnik</a></li>
					<li><a href="./RestoranMenadzerServlet?izbor=konfiguracija">Konfiguracija</a></li>
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
					<li><a href="./RestoranServlet">Restorani</a></li>
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


		<br />
		<h1>Dobrodosli</h1>
		<p class="lead">Zakazite termin u omiljenom restoranu i povezite
			se sa svojim prijateljima</p>

		<div>
			<h3>Restorani</h3>
			<table class="table table-striped table-hover table-bordered"
				id="tableDiv">
				<tbody>
					<tr>
						<th>Naziv</th>
						<th>Vrsta</th>
						<th>Adresa</th>
						<th></th>
					</tr>
					<c:forEach var="r" items="${restoraniHome}">
						<tr>
							<td>${r.naziv}</td>
							<td>${r.vrsta}</td>
							<td>${r.adresa}</td>
							<td><a href="RestoranServlet?detalji=true&id=${r.id}">Detalji</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<button type="button" id="vrstaButton">Sortiranje: Vrsta</button>
		<button type="button" id="adresaButton">Sortiranje: Adresa</button>
		<button type="button" id="nazivButton">Sortiranje: Naziv</button>
	</div>
	<!-- /.container -->

</body>
</html>