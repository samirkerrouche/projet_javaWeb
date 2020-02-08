/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import hibernate.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Mapping.Client;
import java.util.Arrays;
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

        /*----- Type de la réponse -----*/
        response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Récupérer l'id du client à vouloir afficher
            String idClient = request.getParameter("idclient");
            
            /*----- Ouverture de la session et de la transaction -----*/ Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();

            Client client = (Client) session.load(Client.class, idClient);

            out.println("<?xml version=\"1.0\"?>");

            out.println("<client>");
            out.println("<nom>" + client.getNomcli() + "</nom>");
            out.println("<prenom>" + client.getPrenomcli() + "</prenom>");
            out.println("<age>" + client.getAgeClient() + "</age>");
            out.println("<profil>" + client.getProfil() + "</profil>");
            out.println("<sexe>" + client.getSexecli() + "</sexe>");
            out.println("<poids>" + client.getPoidscli() + "</poids>");
            out.println("<taille>" + client.getTaillecli() + "</taille>");
            out.println("<email>" + client.getMailcli() + "</email>");
            out.println("<tel>" + client.getTelcli() + "</tel>");
            out.println("<dateNaissance>" + client.getDatenaisscli() + "</dateNaissance>");
            out.println("<photo>" + Arrays.toString(client.getPhotocli()) + "</photo>");

            out.println("</client>");
            
            t.commit();
            session.close();
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
