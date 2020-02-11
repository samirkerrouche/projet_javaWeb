/* global abreClient */

//Permet d'affecter des listener à tous les composants.
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("lstPrgm").addEventListener("change", giveDataPrgm);
    document.getElementById("lstClients").addEventListener("change", giveDataClient);
    document.getElementById("affectercliquer").addEventListener("click", affecterPrgm);
    displayProfiles();
    displayProgrammes();

});


/***
 * Sort les programmes de la base de données.
 * @returns {undefined}
 */
function displayProfiles() {
    var xhr;
    xhr = new XMLHttpRequest();
    xhr.open("GET", "ServletVoirClient");
    xhr.onload = function () {
        if (xhr.status === 200) {
            var arbreCli = xhr.responseXML;
            var clients = arbreCli.getElementsByTagName("client");
            var chaine = "";
            for (var i = 0; i < clients.length; i++) {
                chaine += "<option>" + clients[i].firstChild.nodeValue + "</option>";
            }
            var lstClient = document.getElementById("lstClients");
            lstClient.innerHTML = chaine;
        }
    };
    xhr.send();

}

function displayProgrammes() {
    var xhr;
    xhr = new XMLHttpRequest();
    xhr.open("GET", "ServletVoirPrgm");
    xhr.onload = function () {
        if (xhr.status === 200) {
            var arbre = xhr.responseXML;
            var lstProfiles = arbre.getElementsByTagName("programme");
            var chaine = "";
            for (var i = 0; i < lstProfiles.length; i++) {
                chaine += "<option>" + lstProfiles[i].firstChild.nodeValue + "</option>";
            }
            var lstClient = document.getElementById("lstPrgm");
            lstClient.innerHTML = chaine;
        }
    };
    xhr.send();
}

/***
 * Donne les donnees specifiques du client choisi
 * @returns {undefined}
 */
function giveDataClient() {
    var xhr;
    xhr = new XMLHttpRequest();
    var nomCli = document.getElementById("lstClients").value;
    xhr.open("GET", "ServletVoirClient?nomCli=" + nomCli);
    xhr.onload = function () {
        if (xhr.status === 200) {
            var arbreClient = xhr.responseXML;
//            arbreClient = arbreClient.getElementsByTagName("client");
            var chaine = "";
//            chaine += "<div id='photo'>";
//            chaine += arbreClient.getElementsByTagName("photo")[0].firstChild.nodeValue;
//            chaine += "</div>";
            var image = arbreClient.getElementsByTagName("photo")[0].firstChild.nodeValue;
            chaine += "<div id ='photo'><img id='pdp' src='" + image + "' alt='Photo de profil de " + arbreClient.getElementsByTagName("nom")[0].firstChild.nodeValue + "'/></div>";
            chaine += "<div id='reste'>";
            chaine += "<ul>";
            chaine += "<li>" + arbreClient.getElementsByTagName("nom")[0].firstChild.nodeValue + "</li>";
            chaine += "<li>" + arbreClient.getElementsByTagName("naissance")[0].firstChild.nodeValue + "</li>";
            chaine += "<li>" + arbreClient.getElementsByTagName("taille")[0].firstChild.nodeValue + "</li>";
            chaine += "<li>" + arbreClient.getElementsByTagName("poids")[0].firstChild.nodeValue + "</li>";
            chaine += "<li>" + arbreClient.getElementsByTagName("profil")[0].firstChild.nodeValue + "</li>";
            chaine += "</ul>";
            chaine += "</div>";

            var zoneCli = document.getElementById("zoneClient");
            zoneCli.innerHTML = chaine;
        }
    };
    xhr.send();
}

/***
 * Donne les donnees du programme ( seance,exo,circuits,nb repetitions).
 * @returns {undefined}
 */
function giveDataPrgm() {
    var nomPrgm = document.getElementById("lstPrgm").value;
    var xhr;
    xhr = new XMLHttpRequest();
    xhr.open("GET", "ServletVoirPrgm?nomPrgm=" + nomPrgm);
    xhr.onload = function () {
        if (xhr.status === 200) {
            var arbrePrgm = xhr.responseXML;
            var lstSeances = arbrePrgm.getElementsByTagName("seance");
            var nbSeance = arbrePrgm.getElementsByTagName("nbSeances")[0].firstChild.nodeValue;
            var chaine = "";
            var zonePrgm = document.getElementById("zonePrgm");
            zonePrgm.innerHTML = chaine;
            for (var i = 0; i < lstSeances.length; i++) { //Les seances
                //On affiche la seance
                chaine += "<div id='nomSeance'>" + lstSeances[i].firstChild.nodeValue + "</div>";
                var contentSeance = lstSeances[i].getElementsByTagName("exercice"); //Prendre les noeuds exercice
                for (var j = 0; j < contentSeance.length; j++) {
                    var imgE = contentSeance[j].getElementsByTagName("image")[0];
                    chaine +="<div id='exo'>";
                    chaine +="<div><img id='pdp' src='"+imgE.firstChild.nodeValue+"'/></div>";
                    chaine +="<div>"; //Le contenu en image
                    chaine += "<ul>";
                    var nomE = contentSeance[j].getElementsByTagName("nomExo")[0];
                    var descE = contentSeance[j].getElementsByTagName("description")[0];
                    
                    chaine += "<li><b>" + nomE.firstChild.nodeValue + "</b></li>";
                    chaine += "<li>" + descE.firstChild.nodeValue + "</li>";
                    chaine += "</ul>";
                    chaine +="</div>"; //Le contenu ecrit
                    
                    chaine +="</div>"; //Le contenu de l exercice
                }
                chaine += "</div>";
            }
            var zonePrgm = document.getElementById("zonePrgm");
            zonePrgm.innerHTML = chaine;

        }
    };
    xhr.send();
}

function affecterPrgm() {
    var nomclient = document.getElementById("lstClients").value;
    var nomPrgm = document.getElementById("lstPrgm").value;
    alert(nomclient + " et le programme " + nomPrgm);
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "ServletAffecterProgramme?nomCli=" + nomclient + "&nomProg=" + nomPrgm);
    xhr.onload = function () {
        if (xhr.status === 200) {
            var resultat = document.getElementById("resultat");
            var result = xhr.responseXML;
            var erreur = result.getElementsByTagName("erreur")[0];
            resultat.innerHTML = "";
            if (erreur === null) {
                resultat.innerHTML = "Le programme a été affecté";
            } else {
                resultat.innerHTML = "Vous ne pouvez pas affecter de programme personnalisé";
            }
        }
    };
    xhr.send();
}
