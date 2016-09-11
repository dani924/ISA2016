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

  </head>

  <body>

    <div class="container">
    <div class="col-xs-4">
      <form class="form-signin" action="./LogInServlet" method="post">
        <h2 class="form-signin-heading">Unesite podatke za prijavu</h2>
        <label for="inputEmail" class="sr-only">Email adresa</label>
        <input type="email" name="korisnickoIme" id="inputEmail" class="form-control" placeholder="Email adresa" required autofocus>
        <label for="inputPassword" class="sr-only">Lozinka</label>
        <input type="password" name="lozinka" id="inputPassword" class="form-control" placeholder="Lozinka" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Prijava</button>
      </form>
    </div>
    <br/>
    <a href="Registration.jsp">Registracija</button>
    </div> <!-- /container -->

  </body>
</html>
