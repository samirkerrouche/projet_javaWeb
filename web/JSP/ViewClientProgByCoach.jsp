<%-- 
    Document   : ViewClientProgByCoach
    Created on : 2020-2-8, 16:30:04
    Author     : WebAgile
--%>

<%@page import="Mapping.Programme"%>
<%@page import="java.util.Date"%>
<%@page import="Mapping.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        // récupérer l'objet client envoyer depuis la servlet
        Client client = (Client) request.getAttribute("client");
        String nom = client.getNomcli();
        String prenom = client.getPrenomcli();
        String tel = client.getTelcli();
        Date dateNaiss = client.getDatenaisscli();
        String email = client.getMailcli();
        char sexe = client.getSexecli();
        String poids = client.getPoidscli();
        String taille = client.getTaillecli();
        int age = client.getAgeClient();
        String profil = client.getProfil().getNomprof();
        byte[] photo = client.getPhotocli();
        // récupérer l'objet programme envoyer depuis la servlet
        Programme programme = (Programme) request.getAttribute("programme");
        int codeProg = programme.getCodeprog();
        String nomProg = programme.getNomprog();
        // récupérer les autres infos de la table AFFECTER depuis la servlet
        String dateAff = (String) request.getAttribute("dateAff");
        String statutAff = (String) request.getAttribute("statutAff");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="./JS/fonctions.js"></script>
        <link rel="stylesheet" type="text/css" href="./CSS/profilClient.css">        
        <title>Profil du client <%= nom%> <%= prenom%></title>
    </head>
    <body>
        <div class="container emp-profile">
            <form method="GET">
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img" id="photoClient">
                            <img id="photoClientImg" src="<%= photo%>" alt="Photo du client."/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="profile-head">
                            <h4 id="inputNomPrenomClient">
                                <%= nom%> <%= prenom%>
                            </h4>
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Infos</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Programme</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-work">
                            <br/>
                            <h4>Informations physiques</h4>
                            <p>Sexe : <%=  sexe%></p>
                            <p>Poids : <%=  poids%> kg</p>
                            <p>Taille : <%= taille%></p>
                            <p>Age : <%= age%> ans</p>
                            <p>Profil :<%= profil%></p>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade in active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Nom du client</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p><%=  nom%> <%=  prenom%></p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Email</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p><%=  email%></p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Téléphone</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p><%=  tel%></p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Date de naissance</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p><%= dateNaiss%></p>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Nom de program</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p id="inputNomProgramClient"><%= nomProg%></p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Statut</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p><%= statutAff%></p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Date d'affectation</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p><%= dateAff%></p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label>Séances</label><br/>
                                        <p>Voir les séances du programme</p>
                                        <input type="button" id="btnViewProgSeances" value="Afficher les séances" onclick="AfficherClientSeances(<%= codeProg%>);"/>
                                    </div>
                                    <div id="divSeances">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>           
        </div>
    </body>
</html>

