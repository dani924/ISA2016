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

<title>Korisnicki nalog</title>

<!-- Bootstrap core CSS -->
<link href="bootstrap.min.css" rel="stylesheet">

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

		$("#izmenaDiv").hide();

		$("[name='izmenaButton']").click(function(event) {
			event.preventDefault();
			$("#izmenaDiv").show();
		});
	
		$("#potvrdaIzmene").click( function(event) {
			  event.preventDefault();
			 
			  var ime = $("#ime").val();
			  var prezime = $("#prezime").val();
			  var password = $("#password").val();
			  
			  
			  $.post("${pageContext.request.contextPath}/UserAccServlet",
					  {
				       ime: ime,
			           prezime: prezime,
		               password: password
					  },
					  function(data, status) {
						 if(data == "true") {
							 alert("Izmenjeni podaci");
							// $("#prikazIzboraAkcija").hide();
						 } else {
							 alert("Doslo je do problema prilikom obrade podataka");
							// $("#prikazIzboraAkcija").hide();
						 }
					  });
		  });
		
		$("#pretragaButton").click( function(event) {
			  event.preventDefault();
			  
			  var ime = $("#pretragaIme").val();
			  var prezime = $("#pretragaPrezime").val();
			  
			  if(ime == "") {
				  alert("Morate popuniti ime.");
				  return;
			  }
			  
			  $.post("${pageContext.request.contextPath}/FriendServlet",
					  {
				  		pretraga: true,
			       		ime: ime,
			       		prezime: prezime
					  },
					  function(data, status) {
						  if(data == "false") {
					        	 $("#tableDiv").html("");
					         } else {
					        	 $("#tableDiv").html("");
					        	 $("#tableDiv").append('<tr><th>Ime</th><th>Prezime</th><th>Email</th><th></th></tr>');
					        	 $.each(data, function(i, k) {  
					        		    $("#tableDiv").append('<tr> <td>' + k.imeKorisnika + '</td>' +
					        		                               '<td>' + k.prezimeKorisnika +  '</td>' +
					        		                               '<td>' + k.korisnickoImeKorisnika + '</td>' +
					        		                               '<td><button type="button" name="dodavanjeButton">Dodaj</button></td></tr>');
					        	 });
					         }
					  })
		  });
		
		$("[name='uklanjanjeButton']").click( function(event) {
			 menjaniRed = $(this).parent().siblings().first().parent();
			 var email = $(this).parent().siblings().eq(2).html();
			 
			 $.post("${pageContext.request.contextPath}/FriendServlet",
					  {
				       ukloni: true,
				       email: email
					  },
					  function(data, status) {
						 if(data == "true") {
							 alert("Izmenjeni podaci");
						 } else {
							 alert("Doslo je do problema prilikom obrade podataka");
						 }
					  });
		 });
		
		$("[name='dodavanjeButton']").click( function(event) {
			 menjaniRed = $(this).parent().siblings().first().parent();
			 var email = $(this).parent().siblings().eq(2).html();
			 
			 $.post("${pageContext.request.contextPath}/FriendServlet",
					  {
				       add: true,
				       email: email
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
					<li><a href="#restaraunt">Restorani</a></li>
					<li><a href="./UserAccServlet">Nalog</a></li>
					<li><a href="./LogOutServlet">Odjava</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</c:if>
	</div>
	</nav>


	<div class="container">
		<br /> <br />
		<h3>Podaci o korisniku</h3>
		<br />
		<div class="row">
			<div class="col-xs-4">
				<table class="table table-bordred table-striped">
					<tr>
						<td><label class="text-right">Email adresa:</label></td>
						<td><label class="text-left">${korisnik.korisnickoImeKorisnika }</label>
						</td>
					</tr>
					<tr>
						<td><label class="text-right">Ime:</label></td>
						<td><label class="text-justify">${korisnik.imeKorisnika }</label>
						</td>
					</tr>
					<tr>
						<td><label class="text-right">Prezime:</label></td>
						<td><label class="text-justify">${korisnik.prezimeKorisnika }</label>
						</td>
					</tr>
				</table>
				<button type="button" name="izmenaButton">Izmeni</button>
			</div>
			<div class="col-xs-4" id="izmenaDiv">
				<form>
					<table class="table table-bordred table-striped">
						<tr>
							<td><label class="text-right">Ime:</label></td>
							<td><input type="text" id="ime"></td>
						</tr>
						<tr>
							<td><label class="text-right">Prezime:</label></td>
							<td><input type="text" id="prezime"></td>
						</tr>
						<tr>
							<td><label class="text-right">Lozinka:</label></td>
							<td><input type="password" id="password"></td>
						</tr>
					</table>
					<button type="submit" id="potvrdaIzmene">Potvrda
						izmene</button>
				</form>
			</div>
		</div>
		<div>
		    <h3>Prijatelji</h3>
		    <table class="table table-striped table-hover table-bordered">
              <tbody>
                <tr>
                    <th>Ime</th>
                    <th>Prezime</th>
                    <th>Email</th>
                    <th></th>
                </tr>
                <c:forEach var="p" items="${prijatelji}">
		        <tr>
		          <td>${p.imeKorisnika}</td>
                  <td>${p.prezimeKorisnika}</td>
                  <td>${p.korisnickoImeKorisnika}</td>
                  <td><button type="button" name="uklanjanjeButton">Ukloni</button></td>
		        </tr>
		        </c:forEach>
		       </tbody>
		    </table>
		</div>
		
		<div>
		    <h3>Pretraga</h3>
		    
		    <label>Ime</label>
		    <input type="text" id="pretragaIme">
		    <label>Prezime</label>
		    <input type="text" id="pretragaPrezime">
		    <button type="button" id="pretragaButton">Pretraga</button>
		    
		    <table class="table table-striped table-hover table-bordered" id="tableDiv">
              <tbody>
                <tr>
                    <th>Ime</th>
                    <th>Prezime</th>
                    <th>Email</th>
                    <th></th>
                </tr>
                
		        <tr>
		          <td></td>
                  <td></td>
                  <td>aa@fdsd.com</td>
                  <td><button type="button" name="dodavanjeButton">Dodaj</button></td>
		        </tr>
		        
		       </tbody>
		    </table>
		</div>
		
		
	</div>	
</body>
</html>