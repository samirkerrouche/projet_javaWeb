<%-- 
    Document   : ViewClientProgByCoach
    Created on : 2020-2-8, 16:30:04
    Author     : webAgile
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../CSS/profilClient.css">
        <title>Profil client view by coach</title>
    </head>
    <body>
        <div class="container emp-profile">
            <form method="GET">
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img" id="photoClient">
                            <img id="photoClientImg" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52y5aInsxSm31CvHOFHWujqUx_wWTS9iM6s7BAm21oEN_RiGoog" alt="Photo du client."/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="profile-head">
                                    <h4 id="inputNomPrenomClient">
                                        Nom prenom de client
                                    </h4>
                                    <h6 id="roleClient">
                                        Role de User
                                    </h6>
                                    <p class="proile-rating">RANKINGS : <span>8/10</span></p>
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">About</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Program</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <input type="submit" class="profile-edit-btn" name="btnAddMore" value="Edit Profile"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-work">
                            <br/>
                            <h4>Informations physique</h4>
                            <p>Sex : <span id="inputSexClient"></span></p>
                            <p>Poids : <span id="inputPoidsClient"></span></p>
                            <p>Taille : <span id="inputTailleClient"></span></p>
                            <p>Age : <span id="inputAgeClient"></span></p>
                            <p>SKILLS</p>
                            <p>Profil : <span id="inputProfilClient"></span></p>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade in active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>User Id</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p id="inputIdClient">12345</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Name</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p id="inputNomPrenomClient">CHEN Wenchang</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Email</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p id="inputMailClient">chenwenchang03@gmail.com</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Phone</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p id="inputPhoneClient">123 456 7890</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Date de naissance</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p id="inputDateNaissanceClient">01-01-1995</p>
                                            </div>
                                        </div>
                            </div>
                            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Nom de program</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p id="inputNomProgramClient">Reprise de xx</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Statut</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p id="inputStatutProramClient">En cours</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Date d'affectation</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p id="inputDateAffClient">01-01-2020</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Date de fin</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p id="inputDateFinClient">01-02-2020</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Autre</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>autre</p>
                                            </div>
                                        </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label>Details</label><br/>
                                        <p>Your detail description</p>
                                         <input type="submit" class="profile-edit-btn" name="btnAddMore" value="Your Detail description"/>
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

