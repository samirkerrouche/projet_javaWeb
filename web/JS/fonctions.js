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
 */
function AfficherClientInfos()
{
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();

    // Requête au serveur avec les paramètres éventuels. (url mapping utilisé)
    xhr.open("GET", "ServletViewProgByCoach");

    // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
    xhr.onload = function ()
    {
        // Si la requête http s'est bien passée.
        if (xhr.status === 200)
        {
            // Elément html que l'on va mettre à jour.
            var elt = document.getElementById("tt_zone");
            elt.innerHTML = xhr.responseText;
        }
    };

    // Envoie de la requête.
    xhr.send();
}
/**
 * Lancement après le chargement du DOM.
 * il faut toujors à la fin
 */
document.addEventListener("DOMContentLoaded", () => {
    
    document.getElementById("bt_afficherUnClientByCoach").addEventListener("click", AfficherClientInfos);
    
}
);