<%-- 
    Document   : ViewListeClientByCoach
    Created on : 8 févr. 2020, 17:14:28
    Author     : WebAgile
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des clients</title>
    </head>

    <body
        <h4>Profile Client</h4>
        <form action="./ServletViewClientProgByCoach" method="get">
            <input name="idclient" type="hidden" value="2" > 
            <!-- TODO ID CLIENT (pas dans les US du premier sprint  -->
            <input type="submit" value="Afficher Client Profile" />
        </form>
        <form action="./ServletAvantNotif" method="get">
            <input name="idclient" type="hidden" value="1" > 
            <!-- TODO ID CLIENT (pas dans les US du premier sprint  -->
            <input type="submit" value="Envoyer Notification par message et par mail" />
        </form>
    </body>
    <%
        // Message
        String msg_erreur = (String) request.getAttribute("msg_erreur");
        if (msg_erreur != null) {
            out.println("<p class='error'>" + msg_erreur + "</p>");
        }

    %>
</html>
