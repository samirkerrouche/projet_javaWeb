/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Mapping.Client;
import hibernate.TestHibernate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author samirkerrouche
 */
public class ServletVoirClient extends HttpServlet {

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
            out.println("<?xml version=\"1.0\"?>");
            String nomCli = request.getParameter("nomCli");
            if (nomCli == null) { // Display all the clients

                List<Client> clients = TestHibernate.getClients();
                out.println("<clients>");
                try {
                    for (Client client : clients) {
                        out.println("<client>" + client.getPrenomcli() + "_" + client.getNomcli() + "</client>");
                    }
                } catch (Exception e) {
                    out.println("<client>erreur -" + e.getMessage());
                }
                out.println("</clients>");

            } else { //display datas from specific client
                String[] t = nomCli.trim().split("_");
                nomCli = t[1];
                String prenomCli = t[0];
                Client client = TestHibernate.getClient(nomCli, prenomCli);
               
                out.println("<client>");
                try {
                    out.println("<nom>" + nomCli + " "+prenomCli+"</nom>");
                    out.println("<naissance>" + client.getDatenaisscli() + "</naissance>");
                    out.println("<taille>" + client.getTaillecli() + "</taille>");
                    out.println("<poids>" + client.getPoidscli() + "</poids>");
                    String profil;
                    if(client.getProfil() == null){
                        profil = "Sans profil";
                    }else{
                        profil = client.getProfil().getNomprof();
                    }
                    out.println("<profil>" + profil + "</profil>");
                    out.println("<photo>" + Arrays.toString(client.getPhotocli()) + "</photo>");
                } catch (Exception e) {
                    out.println("<erreur>erreur- " + e.getMessage() + "</erreur>");
                }
                out.println("</client>");

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
