/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Cette méthode "Ajax" AfficherClientInfos.
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
                chaine += "<li> - (" + j + " sur " + nbTotal[0].innerHTML  + ") " + noms[i].innerHTML + " : (" + dates[i].innerHTML + ") </li>";
            }
            // y : c'est l'ordre du dernier élement
            var y = j + 1;
            // rajouter la ligne du dernier élement
            chaine += "<li id='dernier'> - (" + y + " sur " + nbTotal[0].innerHTML  + ") " + noms[j].innerHTML + " : (" + dates[j].innerHTML + ")    </li>";
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