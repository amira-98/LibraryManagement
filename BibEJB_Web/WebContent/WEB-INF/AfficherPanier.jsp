<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Mon Panier</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
        <div class="row">
            <div class="col-md-10">
                <h4 style="padding: 10px 0 0 0;color: slategray">Mon Panier</h4>
            </div>

            <div class="col-md-2">
                <a href="<c:url value="/logout"/>" class="btn btn-link">Deconnecter</a>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Livre</th>
                            <th>Edition</th>
                            <th>Categorie</th>
                        </tr>
                    </thead>
                    <tbody>
						<c:forEach items="${livres}" var="livre">
							<tr id="l${livre.id_livre}">
								<td>${livre.oeuvre.titre}</td>
								<td>${livre.date_edition}</td>
								<td>${livre.oeuvre.categorie.libelle}</td>
								<td>
								  <button class="btn btn-danger delete" id="${livre.id_livre }">
								  <i class="fa fa-trash-o" aria-hidden="true"></i>
								  </button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
                </table>
            </div>
        </div>
        <div class="row">
                <div class="col-md-2 col-md-offset-10">
				<form method="post" action="<c:url value="/user/panier"/>">
				
					<button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-ok"></i> Valider</button>
				</form>
				
                </div>
            </div>     
    </div>



















<script
  src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" ></script>

<script>
$(document).ready(function(){
    $(".delete").click(function(){
       // alert("add called");
        var idL = this.id;
        console.log(idL);
        $.get('<c:url value="/user/delete-from-panier?id="/>'+idL,function(data){
			
			$('#l'+idL).remove();
			
        });
    });
});
</script>
</body>
</html>