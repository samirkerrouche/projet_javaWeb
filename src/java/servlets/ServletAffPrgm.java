/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Mapping.Client;
import Mapping.ComposerSeance;
import Mapping.Programme;
import Mapping.Seance;
import hibernate.TestHibernate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author samirkerrouche
 */
public class ServletAffPrgm extends HttpServlet {

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
        String nomCli = request.getParameter("nomCli");
        String nomProg = request.getParameter("nomProg");

        Programme prog = TestHibernate.getPrgm(nomProg);

        List<Seance> seances = TestHibernate.getSeances(prog);

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

        String[] t = nomCli.trim().split("_");
        nomCli = t[1];
        String prenomCli = t[0];
        Client client = TestHibernate.getClient(nomCli, prenomCli);
        String identiteClient = prenomCli+" "+ nomCli;
        Set x = new HashSet();
        Programme progRef = new Programme(nomProg + " de "+identiteClient, Boolean.FALSE,null, x, x,x);
        TestHibernate.insertProgram(progRef);
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
