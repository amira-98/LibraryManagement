<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Liste des oeuvres</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>">


    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
        <div class="row">
            <div class="col-md-10">
                <h4 style="padding: 10px 0 0 0;color: slategray">Liste des Oeuvres</h4>
            </div>

            <div class="col-md-2">
                <a href="<c:url value="/logout"/>" class="btn btn-link">Deconnecter</a>
            </div>
        </div>
        <hr>
        <br>
        <form method="get" action="<c:url value="/user/rechercher"/>">
        <div class="row">
            
            <div class="col-md-4">
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="glyphicon glyphicon-book"></i>
                    </span>
                    <input id="livre" type="text" class="form-control" name="titre" placeholder="Nom de livre ...">
                </div>
            </div>
            <div class="col-md-3">
                <select class="form-control" id="sel1" name="auteur">
                    <option value="">-- Tous les auteurs --</option>
                    <c:forEach items="${auteurs}" var="item">
					<option value="${item.id_auteur }">${item.nom } ${item.prenom}</option>
					</c:forEach>
                </select>
            </div>
            <div class="col-md-3">
                    <select class="form-control" id="sel1" name="categ">
                        <option value="">-- Toutes les catégories --</option>
                        <c:forEach items="${categs}" var="item">
							<option value="${item.id_categorie }">${item.libelle }</option>
						</c:forEach>
                    </select>
                </div>
                <div class="col-md-2">
                	<button class="btn btn-outline-dark" type="submit"><i class="fa fa-search" aria-hidden="true"></i>
                	 Rechercher</button>
                </div>
        </div>
        <div class="row">
          
        </div>
        </form>
        
        
        <br>
        <hr>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Livre</th>
                            <th>Auteur</th>
                            <th>Categorie</th>
                            
                            <th>Nb DVD</th>
                           
                            <th>Nb livres</th>
                        </tr>
                    </thead>
                    <tbody>

						<c:forEach items="${requestScope.ovrs }" var="item">
							<tr>
								<td>${item.titre }</td>
								<td>${item.auteur.nom } ${item.auteur.prenom }</td>
								<td>${item.categorie.libelle }</td>
								<td>${item.nb_dvd }</td>
								
								<td>${item.l_livre.size() }</td>

								<td><c:if
										test="${requestScope.borrow && item.getNbLivreDispo()>0 }">
										<c:forEach items="${item.l_livre}" var="livre">
											<p>
												<strong>Edition : </strong> ${livre.date_edition }
												<c:if test="${livre.qte>0 }">
												<button class="bnt btn-outline-primary add" id="${livre.id_livre }"><i class="fa fa-cart-plus" aria-hidden="true"></i></button>
												</c:if>
												<c:if test="${livre.qte<=0 }">
    	    										<p> Aucune copie disponible</p>
    	  									    </c:if>
											</p>
										</c:forEach>

									</c:if></td>
							</tr>
						</c:forEach>

						
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2 col-md-offset-10">
                <a href="<c:url value="/user/panier"/>" class="btn btn-info"><i class="glyphicon glyphicon-shopping-cart"></i> Mon Panier</a>
            </div>
        </div> 
    </div>


















<script
  src="https://code.jquery.com/jquery-3.3.1.js"
  ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" ></script>
<script>
//code java script ne se charge dans le navigateur que si le document html s'st chargé totalement
$(document).ready(function(){
	//chaque element a la classe add je vais faire la fonction click 
    $(".add").click(function(){
       // alert("add called");je recupere le button html qui a subit l'action
        var idL = this.id;
      
        console.log(idL);
        //lien ajouter au panier - id html
        //je vais envoyer une requete ajax de type get qui prend deux parametres le 1er c est l url le 2eme est
        // une fonction qui prend en parametre data qui contient les données que le serveur a envoyé
        
        $.get('<c:url value="/user/add-to-panel?id="/>'+idL,function(data){
            //si le serveur m'a envoyé une requete ok 
			if(data=='OK'){
				alert("Livre ajouté au panié");
			}
        });
    });
});

</script>
</body>
</html>