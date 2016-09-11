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
		    <h3>Restoran</h3>
		    <table class="table table-striped table-hover table-bordered">
              <tbody>
                <tr>
                    <th>Naziv</th>
                    <th>Vrsta</th>
                    <th>Adresa</th>
                    <th></th>
                </tr>
                <tr>
		          <td>${restoranDetalji.naziv}</td>
                  <td>${restoranDetalji.vrsta}</td>
                  <td>${restoranDetalji.adresa}</td>
                  <td><a href="RestoranServlet?rezervisi=true&id=${restoranDetalji.id}">Rezervisi</a></td>
		        </tr>
		        
		       </tbody>
		    </table>
		</div>


		 	<div id="floating-panel">
				<input id="address" type="textbox" value="Sydney, NSW"> <input
					id="submit" type="button" value="Geocode">
			</div>
			<div id="map" style="width: 100%; height: 500px"></div>
			<script>
				function initMap() {
					var map = new google.maps.Map(document
							.getElementById('map'), {
						zoom : 8,
						center : {
							lat : -34.397,
							lng : 150.644
						}
					});
					var geocoder = new google.maps.Geocoder();

					document.getElementById('submit').addEventListener('click',
							function() {
								geocodeAddress(geocoder, map);
							});
				}

				function geocodeAddress(geocoder, resultsMap) {
					var address = document.getElementById('address').value;
					geocoder
							.geocode(
									{
										'address' : address
									},
									function(results, status) {
										if (status === 'OK') {
											resultsMap
													.setCenter(results[0].geometry.location);
											var marker = new google.maps.Marker(
													{
														map : resultsMap,
														position : results[0].geometry.location
													});
										} else {
											alert('Geocode was not successful for the following reason: '
													+ status);
										}
									});
				}
			</script>
			<script async defer
				src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDtms0flyAJ38-PksQeLG-MpObSrN0znNA&callback=initMap">
				
			</script>

            
		

	</div>
	<!-- /.container -->

</body>
</html>