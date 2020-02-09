/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Mapping.ComposerSeance;
import Mapping.Exercice;
import hibernate.TestHibernate;
import Mapping.Programme;
import Mapping.Seance;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author samirkerrouche
 */
public class ServletVoirPrgm extends HttpServlet {

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
        response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /*----- Ecriture de la page XML -----*/

            String nomProg = request.getParameter("nomPrgm");

            if (nomProg == null) {
                //On demande tous les programmes.
                List<Programme> programmes;
                out.println("<?xml version=\"1.0\"?>");
                try {
                    programmes = TestHibernate.getProgrammes();

                    out.println("<programmes>");
                    programmes.forEach((prgm) -> {
                        out.println("<programme>" + prgm.getNomprog() + "</programme>");
                    });
                    out.println("</programmes>");
                } catch (Exception e) {
                    out.println("<programmes>");
                    out.println("<programme>erreur -" + e.getMessage() + "</programme>");
                    out.println("</programmes>");
                }
            } else {
                //On demande un prgm complet
                Programme prgm = TestHibernate.getPrgm(nomProg);
                List<Seance> seances = (List<Seance>) TestHibernate.getSeances(prgm);
                HashMap<Seance, List<ComposerSeance>> mapSeances = new HashMap<>();
                for (Seance seance : seances) {
                    List<ComposerSeance> exos = null;
                    try {
                        exos = TestHibernate.getExercices(seance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mapSeances.put(seance, exos);
                }

                //On fait l'arbre
                out.println("<?xml version=\"1.0\"?>");
                out.println("<programme>");
                out.println("<nbSeances>" + mapSeances.size() + "</nbSeances>");
                for (Map.Entry<Seance, List<ComposerSeance>> e : mapSeances.entrySet()) {
                    out.println("<seance>" + e.getKey().getNomseance());
                    if (e.getValue() != null) {
                        for (ComposerSeance cs : e.getValue()) {
                            out.println("<exercice>");
                            Exercice exo = cs.getExercice();
                            out.println("<nomExo>" + exo.getNomexo() + "</nomExo>");
                            out.println("<description>" + exo.getDescriptionexo() + "</description>");
                            out.println("<image>" + exo.getImageexo() + "</image>");
                            out.println("</exercice>");
                        }
                    }else{
                        out.println("<exercice>");
                            out.println("<nomExo>Aucun exercice</nomExo>");
                            out.println("<description></description>");
                            out.println("<image></image>");
                            out.println("</exercice>");
                    }
                    out.println("</seance>");
                }
                out.println("</programme>");
            }
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