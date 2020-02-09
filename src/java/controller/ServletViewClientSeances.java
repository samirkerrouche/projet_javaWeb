/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Mapping.Seance;
import hibernate.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
            // la requete SQL Native
            Query q = session.createSQLQuery("SELECT * FROM SEANCE WHERE CODEPROG = " + progID).addEntity(Seance.class);
            // Query q = session.createSQLQuery("SELECT * FROM SEANCE WHERE CODEPROG = " + progID + " ORDER BY ORDERSEANCE ASC).addEntity(Seance.class);
            // récupérer les résultats en objet de classe Seance
            List<Seance> seances = q.list();
            // l'écriture de chaque séance dans notre XML
            for (Seance s : seances) {
                out.println("<seance>");
                //out.println("<nom>" + seances.get(0).getNomseance() + "</nom>");
                out.println("<nom>" + s.getNomseance() + "</nom>");
//                out.println("<bilan>" + s.getIsbilan() + "</bilan>");
                out.println("</seance>");
            }

            out.println("</seances>");
            t.commit();
            session.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
