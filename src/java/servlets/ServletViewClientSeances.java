/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

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
public class ServletViewClientSeances extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*----- Type de la réponse -----*/
        response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /*----- Ecriture de la page XML -----*/
            out.println("<?xml version=\"1.0\"?>");
            out.println("<seances>");
            /*----- Récupération des paramètres -----*/
            String progID = request.getParameter("progID");
            /*----- Ouverture de la session et de la transaction -----*/
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            // la requete SQL native pour avoir le nombre de séance(occurrence) d'un le programme donnée
            Query nbOccs = session.createSQLQuery("SELECT COUNT(*) "
                    + "FROM OCCURRENCE_S "
                    + "WHERE CODESEANCE IN (SELECT CODESEANCE FROM SEANCE WHERE CODEPROG = " + progID + ")");
            List nbtotal = nbOccs.list();
            if (nbtotal.size() > 0) {
                out.println("<nbTotal>" + nbtotal.get(0) + "</nbTotal>");
            }
            // la requete SQL native qui récupére les occurences/séances du programme donné qui sont passées ou en cours 
            Query qoccs = session.createSQLQuery("SELECT * "
                    + "FROM OCCURRENCE_S "
                    + "WHERE DATEOCCS <= SYSDATE() "
                    + "AND CODESEANCE IN (SELECT CODESEANCE FROM SEANCE WHERE CODEPROG = " + progID + ") "
                    + "ORDER BY DATEOCCS ASC").addEntity(OccurrenceS.class);
            // récupérer les résultats en objet de classe OccurenceS
            List<OccurrenceS> occs = qoccs.list();
            // parcourir la liste des objets récupérée
            for (OccurrenceS occ : occs) {
                out.println("<seance>");
                // remplir le nom de la séance correspondant à l'occurence
                out.println("<nom>" + occ.getSeance().getNomseance() + "</nom>");
                // remplir la date de l'occurence
                out.println("<date>" + occ.getDateoccs() + "</date>");
                out.println("</seance>");
            }
            out.println("</seances>");
            t.commit();
            //session.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
