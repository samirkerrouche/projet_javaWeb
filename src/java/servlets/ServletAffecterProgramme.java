/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Mapping.Affecter;
import Mapping.AffecterId;
import Mapping.Client;
import Mapping.ComposerCircuit;
import Mapping.ComposerSeance;
import Mapping.ComposerSeanceId;
import Mapping.ExecuterCircuit;
import Mapping.ExecuterCircuitId;
import Mapping.ExecuterExo;
import Mapping.ExecuterExoId;
import Mapping.Exercice;
import Mapping.OccurrenceS;
import Mapping.Programme;
import Mapping.Seance;
import hibernate.TestHibernate;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author samirkerrouche
 */
public class ServletAffecterProgramme extends HttpServlet {

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

        //Prendre le contenu des seances d un programme standard.
        Programme prog = TestHibernate.getPrgm(nomProg);

        if (prog.getIsstandard()) {
            List<Seance> seances = TestHibernate.getSeances(prog);
            HashMap<Seance, List<ComposerSeance>> mapSeances = new HashMap<>();

            for (Seance seance : seances) {
                List<ComposerSeance> exos = new ArrayList<>();
                try {
                    exos = TestHibernate.getExercices(seance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mapSeances.put(seance, exos);
            }
            //Reprendre les donnees du client
            String[] t = nomCli.trim().split("_");
            nomCli = t[1];
            String prenomCli = t[0];
            Client client = TestHibernate.getClient(nomCli, prenomCli);
            String identiteClient = prenomCli + " " + nomCli;
            Set x = new HashSet();
            //System.out.println("TESTVISION");

            //creer un programme avec toutes les donnees
            Programme progRef = new Programme(nomProg + " de " + identiteClient, Boolean.FALSE, x, x, x);
            TestHibernate.insertProgram(progRef);

            for (Map.Entry<Seance, List<ComposerSeance>> e : mapSeances.entrySet()) {

                //Creer une nouvelle seance
                Seance seanceClient;
                if (e.getKey().getCircuit() == null) {
                    seanceClient = new Seance(null, progRef, e.getKey().getNomseance() + " " + identiteClient, Boolean.FALSE, x, x);
                } else {
                    seanceClient = new Seance(e.getKey().getCircuit(), progRef, e.getKey().getNomseance() + " " + identiteClient, Boolean.FALSE, x, x);
                }
                TestHibernate.insertSeance(seanceClient);
                List<OccurrenceS> occs = new ArrayList<>();
                for (int k = 0; k < 4; k++) {
                    Date dateoccs = new Date(2020, 10, 10);
                    OccurrenceS occ = new OccurrenceS(seanceClient, dateoccs, Boolean.FALSE, x, x, x);
                    TestHibernate.insertOccurrenceS(occ);
                    occs.add(occ);
                }
                occs = TestHibernate.getOccurr(seanceClient);
                if (seanceClient.getCircuit() == null) {
                    //Creer des compositions de seances

                    for (ComposerSeance cs : e.getValue()) {
                        cs.getId().getCodeexo();
                        Exercice exo = TestHibernate.getContent(cs);
//                        System.out.println("NOM DE LEXO : " + exo.getNomexo() + " et son code " + exo.getCodeexo());
                        ComposerSeanceId id = new ComposerSeanceId(seanceClient.getCodeseance(), exo.getCodeexo());
                        ComposerSeance csClient = new ComposerSeance(id, exo, seanceClient, cs.getOrdreexoseance());
                        TestHibernate.insertComposerSeance(csClient);
                        for (OccurrenceS occ : occs) {
//                        System.out.println("CODE OCC : " + occ.getCodeoccs());
                            //Exercice exo = cs.getId();
                            //Copier les execution exercice
                            ExecuterExoId curId = new ExecuterExoId(exo.getCodeexo(), client.getCodecli(), occ.getCodeoccs());
                            ExecuterExo exeExo = new ExecuterExo(curId, client, exo, occ);
                            TestHibernate.insertExecutionExercice(exeExo);
                        }
                    }

                } else {
                    //QUE UN SEUL !
                    ExecuterCircuitId idExe;
                    for (OccurrenceS occ : occs) {
                        idExe = new ExecuterCircuitId(occ.getCodeoccs(), seanceClient.getCircuit().getCodecir(), client.getCodecli());
                        ExecuterCircuit exeCir = new ExecuterCircuit(idExe, seanceClient.getCircuit(), client, occ);
                        TestHibernate.insertExecutionCircuit(exeCir);
                    }
                }

            }
            Date date = new Date();

            AffecterId idAff = new AffecterId(progRef.getCodeprog(), client.getCodecli(), date);
            Affecter affecterP = new Affecter(idAff, client, progRef);
            TestHibernate.insertAffectation(affecterP);
        } else { // interdire l affectation
            try (PrintWriter out = response.getWriter()) {
                /*----- Ecriture de la page XML -----*/
                out.println("<?xml version=\"1.0\"?>");
                out.println("<reponse>");
                out.println("<erreur>OUI</erreur>");
                out.println("</reponse>");
                
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
