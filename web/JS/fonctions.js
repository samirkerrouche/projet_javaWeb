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
    // Requête au serveur avec les paramètres éventuels. (url mapping utilisé)
    // créer l'url avec les paramètres
    var url = "ServletViewClientSeances" + "?progID=" + codeProg;
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
            var seances = doc.getElementsByTagName("seance"); // un tableau
            var chaine = "";
            for (var i = 0; i < seances.length; i++) {
                j = i + 1;
                chaine += "<li>" + j + ". " + seances[i].firstElementChild.innerHTML + "</li>";
            }
            elt.innerHTML = "<ul>" + chaine + "</ul>";
        }
    };
    // Envoie de la requête.
    xhr.send();
}