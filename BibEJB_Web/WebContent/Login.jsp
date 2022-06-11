<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Authentification</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
        <div class="row">
            <div class="col-md-4">
                <div class="wrapper">
                    <form class="form-signin" method="post" action="<c:url value="/login"/>">
                        <h2 class="form-signin-heading">Bienvenue !</h2>
                        <input type="text" class="form-control" name="login" value="${param.login }" placeholder="Nom d'utilisateur" required="required" autofocus="autofocus" />
                        <input type="password" class="form-control" name="pass" placeholder="Mot de passe" required="required" />
						<c:if test="${requestScope.error }">
						<p class="text-danger">Login ou mot de passe invalide !</p>
						</c:if>
						
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Se connecter</button>
                    </form>
                    
                </div>
            </div>
            <div class="col-md-8">
                <div class="wrapper">
                   
                        <img src="https://www.magd.cam.ac.uk/sites/default/files/styles/1302x600/public/2017-09/magdalene_college_library.jpg?itok=1v6BgUME"
                            class="img-thumbnail">
                    
                </div>
            </div>
        </div>

    </div>





<!-- 

<h1>Connectez Vous</h1>


<form method="post" action="">
 <div class="form-group">
   <label>Login</label>
   <input type="text" name="login" placeHolder = "Login" required class="form-control"/>
 </div>
 
 <div class="form-group">
   <label>Mot de passe</label>
   <input type="password" name="pass" placeHolder = "Mot de passe" required class="form-control"/>
 </div>
 
 <div class="form-group">
   <button class="btn btn-outline-dark">Connecter</button>
 </div>
</form>
 -->

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
