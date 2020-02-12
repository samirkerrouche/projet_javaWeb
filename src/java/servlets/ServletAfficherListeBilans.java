/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Mapping.Evaluer;
import Mapping.OccurrenceS;
import hibernate.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author WebAgile
 */
public class ServletAfficherListeBilans extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/xml;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // récupérer le parametre 
            String idClient = request.getParameter("idclient");
            // convertir le idClient en int
            int idCli = Integer.parseInt(idClient);
            // Ouverture de la session et de la transaction
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            out.println("<?xml version=\"1.0\"?>");
            out.println("<listeBilans>");
            // la requete SQL native pour avoir la liste des bilans pour un client donné
            Query q = session.createSQLQuery("SELECT occ.* "
                    + "FROM OCCURRENCE_S occ, SEANCE s, PROGRAMME p, AFFECTER a "
                    + "WHERE s.CODESEANCE = occ.CODESEANCE "
                    + "AND s.CODEPROG = p.CODEPROG "
                    + "AND p.CODEPROG = a.CODEPROG "
                    + "AND s.ISBILAN != 0 "
                    + "AND a.CODECLI = " + idCli).addEntity(OccurrenceS.class);
            // récupérer les résultats en objet de classe OccurrenceS
            List<OccurrenceS> occs = q.list();
            // parcourir la liste des objets récupérée
            for (OccurrenceS occ : occs) {
                out.println("<bilan>");
                out.println("<id>" + occ.getCodeoccs() + "</id>");
                out.println("<nom>" + occ.getSeance().getNomseance() + " (" +  occ.getDateoccs() + ")</nom>");
                out.println("</bilan>");
            }
            out.println("</listeBilans>");
            t.commit();
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
