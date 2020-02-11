/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import hibernate.HibernateUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Mapping.Client;
import Mapping.Programme;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.RequestDispatcher;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author WebAgile
 */
@WebServlet(name = "Servlet1", urlPatterns = {"/Servlet1"})
public class ServletViewClientProgByCoach extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Récupérer l'id du client à vouloir afficher
            String idClient = request.getParameter("idclient");
            // convertir le idClient en int
            int id = Integer.parseInt(idClient);
            /*----- Ouverture de la session et de la transaction -----*/
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            Client client = (Client) session.get(Client.class, id);
            // todo : regrouper dans une seule requete et trouver un moyen de comment accèder aux différents champs
            List resultatCodeProg = session.createSQLQuery("SELECT aff.CODEPROG "
                    + "FROM AFFECTER aff "
                    + "WHERE aff.CODECLI = " + client.getCodecli() + " "
                    + "AND aff.DATEAFF <= SYSDATE() "
                    + "AND aff.DATEFINAFF IS NULL "
                    + "AND aff.STATUTAFF = 'en cours'").list();
            List resultatDateAff = session.createSQLQuery("SELECT aff.DATEAFF "
                    + "FROM AFFECTER aff "
                    + "WHERE aff.CODECLI = " + client.getCodecli() + " "
                    + "AND aff.DATEAFF <= SYSDATE() "
                    + "AND aff.DATEFINAFF IS NULL "
                    + "AND aff.STATUTAFF = 'en cours'").list();
            List resultatStatutAff = session.createSQLQuery("SELECT aff.STATUTAFF "
                    + "FROM AFFECTER aff "
                    + "WHERE aff.CODECLI = " + client.getCodecli() + " "
                    + "AND aff.DATEAFF <= SYSDATE() "
                    + "AND aff.DATEFINAFF IS NULL "
                    + "AND aff.STATUTAFF = 'en cours'").list();
            // récupération des résultats
            int programmeId = (int) resultatCodeProg.get(0);
            String statutAff = (String) resultatStatutAff.get(0);
            Timestamp dateAff = (Timestamp) resultatDateAff.get(0);
            // charger l'objet programme
            Programme programme = (Programme) session.get(Programme.class, programmeId);
            // retourner à la page du ViewListeClientByCoach
            RequestDispatcher rd = request.getRequestDispatcher("ViewClientProgByCoach");
            // set l'objet client pour le passer à la page suivante
            request.setAttribute("client", client);
            // set l'objet programme pour le passer la page suivante
            request.setAttribute("programme", programme);
            // set les autres informations pour la page suivante
            request.setAttribute("dateAff", dateAff.toString());
            request.setAttribute("statutAff", statutAff);
            // aller vers la page de ViewClientProgByCoach
            rd.forward(request, response);
            // ferméture de la session et commit la transaction
           t.commit();
           // session.close();
        } catch (Exception ex) {
            // retour sur la page d'avant avec un message d'erreur
            RequestDispatcher rd = request.getRequestDispatcher("ViewListeClientByCoach");
            // rajouter un attribut message
            request.setAttribute("msg_erreur", ex.getMessage());
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
