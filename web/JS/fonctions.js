/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Cette méthode "Ajax" AfficherClientSeances.
 *
 * On utilise la propriété 'responseText' de l'objet XMLHttpRequest afin
 * de récupérer sous forme de texte le flux envoyé par le serveur.
 * @param {int} codeProg : le code d'un programme.
 */
function AfficherClientSeances(codeProg) {
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();
    // créer l'url avec les paramètres
    var url = "ServletViewClientSeances" + "?progID=" + codeProg;
    // Requête au serveur avec les paramètres éventuels. (url mapping utilisé)
    xhr.open("GET", url);
    // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
    xhr.onload = function ()
    {
        // Si la requête http s'est bien passée.
        if (xhr.status === 200)
        {
            // Elément html que l'on va mettre à jour.
            var elt = document.getElementById("divSeances");
            // doc de la réponse HTML
            var doc = xhr.responseXML;
            var noms = doc.getElementsByTagName("nom"); // un tableau
            var dates = doc.getElementsByTagName("date"); // un tableau
            var nbTotal = doc.getElementsByTagName("nbTotal"); // un tableau(d'un seul élement)
            var chaine = "";
            // boucle on s'arrete à length - 1 pour ne pas faire le dernier élement (car on veut l'afficher avec un style différent(lui donné un id unique).
            for (var i = 0; i < noms.length - 1; i++) {
                // j = le compteur 1,2,3... pour l'odre des séances
                j = i + 1;
                // faire en sorte d'écrier (exemple : 1. nom de séance : (date de début)) 
                chaine += "<li> - (" + j + " sur " + nbTotal[0].innerHTML + ") " + noms[i].innerHTML + " : (" + dates[i].innerHTML + ") </li>";
            }
            // y : c'est l'ordre du dernier élement
            var y = j + 1;
            // rajouter la ligne du dernier élement
            chaine += "<li id='dernier'> - (" + y + " sur " + nbTotal[0].innerHTML + ") " + noms[j].innerHTML + " : (" + dates[j].innerHTML + ")    </li>";
            elt.innerHTML = "<br><ul>" + chaine + "</ul>";
            // récupérer le dernier élément pour modifier son style
            var eltLi = document.getElementById("dernier");
            eltLi.style.color = "green";
            // une fois le bouton afficher est appuyé on lui rend inactif
            var btnAfficher = document.getElementById("btnViewProgSeances");
            btnAfficher.disabled = true;
        }
    };
    // Envoie de la requête.
    xhr.send();
}

function AfficherListeBilans(codeCli) {
    // Objet XMLHttpRequest
    var xhr = new XMLHttpRequest();
    // construire l'URL
    var url = "ServletAfficherListeBilans?idclient=" + codeCli;
    // Requête au serveur avec les paramètres éventuels. (url mapping utilisé)
    xhr.open("GET", url);
    // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
    xhr.onload = function () {
        if (xhr.status === 200) {
            //Remise à blanc de la zone
            var eltPrec = document.getElementById("resultatBilan");
            eltPrec.innerHTML = "";
            
            // Elément html que l'on va mettre à jour.
            var elt = document.getElementById("lstBilan");
            // doc de la réponse HTML
            var doc = xhr.responseXML;
            // récupération des noms et des id dans l'XML 
            var listeB = doc.getElementsByTagName("bilan") // un tableau
            var noms = doc.getElementsByTagName("nom"); // un tableau
            var ids = doc.getElementsByTagName("id"); // un tableau
            // préparer la chaine 
            var chaine = "";
            //for (var i = 0; i < noms.length; i++) {
            for (var i = 0; i < listeB.length; i++) {
                chaine += "<option value='" + ids[i].firstChild.nodeValue + "'>" + noms[i].firstChild.nodeValue + "</option>";
            }
            // mettre le résultat à l'intérieur du div concerné
            elt.innerHTML = "<option ></option>" + chaine;
        }
    };
    // Envoie de la requête.
    xhr.send();
}

/**
 * Cette méthode "Ajax" AfficherBilan.
 *
 * On utilise la propriété 'responseText' de l'objet XMLHttpRequest afin
 * de récupérer sous forme de texte le flux envoyé par le serveur.
 * @param {int} codeCli : le code d'un client.
 */
function AfficherBilan(codeCli) {
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();
    // créer l'url avec les paramètres
    var codeOcc = document.getElementById("lstBilan").value;
    var url = "ServletAfficherBilan" + "?idclient=" + codeCli + "&idOccS=" + codeOcc;
    // Requête au serveur avec les paramètres éventuels. (url mapping utilisé)
    xhr.open("GET", url);
    // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
    xhr.onload = function ()
    {
        // Si la requête http s'est bien passée.
        if (xhr.status === 200)
        {
            // Elément html que l'on va mettre à jour.
            var elt = document.getElementById("resultatBilan");
            // doc de la réponse HTML
            var doc = xhr.responseXML;
            if (doc !== null) {
                // récupération des tableaux
                var bilans = doc.getElementsByTagName("bilans"); // un tableau
                var dates = doc.getElementsByTagName("date"); // un tableau
                var bras = doc.getElementsByTagName("bras"); // un tableau
                var cuisses = doc.getElementsByTagName("cuisse"); // un tableau
                var hanches = doc.getElementsByTagName("hanche"); // un tableau
                var poitrines = doc.getElementsByTagName("poitrine"); // un tableau
                var tailles = doc.getElementsByTagName("taille"); // un tableau
                var apresflex = doc.getElementsByTagName("apresflex"); // un tableau
                var cinqallong = doc.getElementsByTagName("cinqallong"); // un tableau
                var uneallong = doc.getElementsByTagName("uneallong"); // un tableau
                var indiceDickson = doc.getElementsByTagName("indiceDickson"); // un tableau
                var formK = doc.getElementsByTagName("formuleK"); // un tableau
                var chaine = "";
                var chaine1 = "";
                var chaine2 = "";
                for (var i = 0; i < bilans.length; i++) {
                    chaine += "<h3>Date bilan : " + dates[i].firstChild.nodeValue + "</h3>";
                    chaine += "<p>Indice de Dickson : " + indiceDickson[i].firstChild.nodeValue + "</p>";
                    chaine += "<p>Formule de karvonen : " + formK[i].firstChild.nodeValue + "</p>";
                    //Construction de la partie "Condition"
                    chaine1 += "<h3> Condition : </h3>";
                    chaine1 += "<b>Fréquence cardiaque :</b>";
                    chaine1 += "<ul>";
                    chaine1 += "<li>Après flexion : " + apresflex[i].firstChild.nodeValue + "</li>";
                    chaine1 += "<li>Après 5 min allongé : " + cinqallong[i].firstChild.nodeValue + "</li>";
                    chaine1 += "<li>Allongé 1 min après exercice : " + uneallong[i].firstChild.nodeValue + "</li>";                
                    chaine1 += "</ul>";
                    //Construction de la partie "Mensuration"
                    chaine2 += "<h3>Mensuration :</h3>";
                    chaine2 += "<ul>";
                    chaine2 += "<li>Bras : " + bras[i].firstChild.nodeValue + "</li>";
                    chaine2 += "<li>Cuisse : " + cuisses[i].firstChild.nodeValue + "</li>";
                    chaine2 += "<li>Hanche : " + hanches[i].firstChild.nodeValue + "</li>";
                    chaine2 += "<li>Poitrine : " + poitrines[i].firstChild.nodeValue + "</li>";
                    chaine2 += "<li>Taille : " + tailles[i].firstChild.nodeValue + "</li>";
                    chaine2 += "</ul>";
                }
                //Construction de la partie "Performance"
                var chaine3 = "";
                chaine3 += "<h3> Performance : </h3>";
                var exercices = doc.getElementsByTagName("exo");
                var nomExo = doc.getElementsByTagName("exoNom");
                var perfExo = doc.getElementsByTagName("performance");
                for (var i = 0; i < exercices.length; i++) {
                    chaine3 += "<ul>";
                    chaine3 += "<li>Nom exercice : " + nomExo[i].firstChild.nodeValue + "</li>";
                    chaine3 += "<li>Performance : " + perfExo[i].firstChild.nodeValue + "</li>";
                    chaine3 += "</ul>";
                }
                elt.innerHTML = chaine + chaine1 + chaine3 + chaine2;
            } else {
                elt.innerHTML = "";
            }
        }
    };
    // Envoie de la requête.
    xhr.send();
}
