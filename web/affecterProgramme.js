/* global abreClient */

//Permet d'affecter des listener à tous les composants.
document.addEventListener("DOMContentLoaded", () => {
    displayProfiles();
    displayProgrammes();
    document.getElementById("lstPrgm").addEventListener("change", giveDataPrgm);
    document.getElementById("lstClients").addEventListener("change", giveDataClient);
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
            alert("Nombre de séances " + nbSeance);
            alert("reçus par le js : " + lstSeances.length);
            var chaine = "";
            var zonePrgm = document.getElementById("zonePrgm");
            zonePrgm.innerHTML = chaine;
            for (var i = 0; i < lstSeances.length; i++) { //Les seances
                //On affiche la seance
                chaine += "<div id='nomSeance'>" + lstSeances[i].firstChild.nodeValue + "</div>";
                var contentSeance = lstSeances[i].getElementsByTagName("exercice"); //Prendre les noeuds exercice
                for (var j = 0; j < contentSeance.length; j++) {
                    chaine += "<ul>";
                    var nomE = contentSeance[j].getElementsByTagName("nomExo")[0];
                    var descE = contentSeance[j].getElementsByTagName("description")[0];
                    var imgE = contentSeance[j].getElementsByTagName("image")[0];
                    chaine += "<li><b>" + nomE.firstChild.nodeValue + "</b></li>";
                    chaine += "<li>" + descE.firstChild.nodeValue + "</li>";
                    chaine += "<li>" + imgE.firstChild.nodeValue + "</li>";
                    chaine += "</ul>";
                }
                chaine += "</div>";
            }
            var zonePrgm = document.getElementById("zonePrgm");
            zonePrgm.innerHTML = chaine;

        }
    };
    xhr.send();
}

function getProfile() {
    var nomCli = document.getElementById("lstClient").value;
    var xhr;
    xhr.open("", "GET");
}

function getImage(data) {

// Convert the string to bytes
    var bytes = new Uint8Array(data.length / 2);

    for (var i = 0; i < data.length; i += 2) {
        bytes[i / 2] = parseInt(data.substring(i, i + 2), /* base = */ 16);
    }

// Make a Blob from the bytes
    var blob = new Blob([bytes], {type: 'image/bmp'});

// Use createObjectURL to make a URL for the blob
    var image = new Image();
    image.src = URL.createObjectURL(blob);
    return image;
}