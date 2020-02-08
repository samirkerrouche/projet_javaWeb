<%-- 
    Document   : ViewListeClientByCoach
    Created on : 8 févr. 2020, 17:14:28
    Author     : UT1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des clients</title>
    </head>
    <body>
        <form action="../controller.ServletViewClientProgByCoach.java" method="get">
            <input name="idclient" type="hidden" value="2" > 
            <!-- TODO ID CLIENT (pas dans les US du premier sprint  -->
            <input type="submit" value="Afficher" id="bt_afficherUnClientByCoach"/>
        </form>
    </body>
</html>
