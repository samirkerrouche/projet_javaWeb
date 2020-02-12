<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="./CSS/inputNotif.css">  
    </head>
    <%
        Integer idClient = (Integer) request.getAttribute("idClient");
        String mailClient = (String) request.getAttribute("mailClient");
    %>
    <body>

        <h2>Envoyer notification</h2>
        <p>Click on the button at the bottom of this page to open the notification place.</p>

        <button class="open-button" onclick="openForm()">Envoyer notification</button>


        <div class="form-popup" id="myForm">
            <form action="./ServletEnvoyerNotif" method="get" class="form-container">
                <h1>Message</h1>
                <input name="idUserCoach" type="hidden" value="9" />
                <input name="idUserClient" type="hidden" value="<%=idClient%>" />
                <!-- TODO ID CLIENT (pas dans les US du premier sprint  -->
                <label ><b>Objet</b></label>
                <textarea id="txtrObjet" name="txtObjet" rows="1" cols="25"></textarea><br/>
                <label ><b>Email</b></label>
                <textarea id="txtrMail" name="txtMail" rows="1" cols="25" disabled><%=mailClient%></textarea><br/>
                <label ><b>Message</b></label>
                <textarea id="txtrMessage" name="txtEnvoyer" rows="4" cols="50"></textarea><br/>  
                <input type="submit" id="btn_envoyer" value="Envoyer" class="btn"/>
                <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
                <div id="retourMsg">
            </form>
                <%
                    // Message
                    String msg_erreurEnvoie = (String) request.getAttribute("msg_erreurEnvoie");
                    if (msg_erreurEnvoie != null) {
                        out.println("<p class='errorEnvoie'>" + msg_erreurEnvoie + "</p>");
                    }

                    String msg_infoEnvoie = (String) request.getAttribute("msg_infoEnvoie");
                    if (msg_infoEnvoie != null) {
                        out.println("<p class='infoEnvoie'>" + msg_infoEnvoie + "</p>");
                    }

                %>
                </div>
        </div>

        <script>
            function openForm() {
                document.getElementById("myForm").style.display = "block";
            }

            function closeForm() {
                document.getElementById("myForm").style.display = "none";
            }
        </script>

    </body>

</html>
