package servlets;

import Mapping.Evaluer;
import Mapping.ExecuterExo;
import hibernate.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "ServletAfficherBilan", urlPatterns = {"/ServletAfficherBilan"})
public class ServletAfficherBilan extends HttpServlet {

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
            // récupérer les deux parametres 
            String idClient = request.getParameter("idclient");
            String idOccS = request.getParameter("idOccS");
            // convertir le idClient et idOccs en int
            int idCli = Integer.parseInt(idClient);
            int idOcc = Integer.parseInt(idOccS);

            // Ouverture de la session et de la transaction
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            out.println("<?xml version=\"1.0\"?>");
            out.println("<bilans>");
            // la requete SQL native pour avoir les données de la table évaluer pour un client et une occSéance
            Query evaluer = session.createSQLQuery("SELECT * "
                    + "FROM EVALUER "
                    + "WHERE CODECLI = " + idCli + " "
                    + "AND CODEOCCS = " + idOcc).addEntity(Evaluer.class);
            // récupérer les résultats en objet de classe Evaluer
            List<Evaluer> eval = evaluer.list();
            //Pour convertir le format date aaaaMMjj hhmmss
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            // parcourir la liste des objets récupérée
            for (Evaluer e : eval) {
                // créaction de l'XML résultat
                out.println("<date>" + dateFormat.format(e.getDateeval()) + "</date>");
                out.println("<bras>" + e.getBras() + " cm</bras>");
                out.println("<cuisse>" + e.getCuisses() + " cm</cuisse>");
                out.println("<hanche>" + e.getHanches() + " cm</hanche>");
                out.println("<poitrine>" + e.getPoitrine() + " cm</poitrine>");
                out.println("<taille>" + e.getTaille() + " cm</taille>");
                out.println("<apresflex>" + e.getApresflexion() + "</apresflex>");
                out.println("<cinqallong>" + e.getCinqminallong() + "</cinqallong>");
                out.println("<uneallong>" + e.getUneminallong() + "</uneallong>");
                out.println("<indiceDickson>" + e.IndiceDickson() + "</indiceDickson>");
                out.println("<formuleK>" + e.FormuleKarvonen() + "</formuleK>");
            }
            // la requete SQL native pour avoir les données(tuples 1 ou +) de la table EXECUTER_EXO pour un client et une occSéance
            Query execute = session.createSQLQuery("SELECT * "
                    + "FROM EXECUTER_EXO "
                    + "WHERE CODECLI = " + idCli + " "
                    + "AND CODEOCCS = " + idOcc).addEntity(ExecuterExo.class);
            // récupérer les résultats en objet de classe ExecuterExo
            List<ExecuterExo> lexe = execute.list();
            // parcourir la liste des objets récupérée
            out.println("<exercices>");
            for (ExecuterExo ex : lexe) {
                out.println("<exo>");
                out.println("<exoNom>" + ex.getExercice().getNomexo() + "</exoNom>");
                if (ex.getDistance() != null) {
                    out.println("<performance>" + ex.getDistance() + " km</performance>");
                } else if (ex.getDuree() != null) {
                    out.println("<performance>" + ex.getDuree() + " secondes</performance>");
                } else if (ex.getRepetitionexo() != null) {
                    out.println("<performance>" + ex.getRepetitionexo() + " répétition(s)</performance>");
                } else {
                    out.println("<performance>Pas disponible</performance>");
                }
                out.println("</exo>");
            }
            out.println("</exercices>");
            out.println("</bilans>");
            // commit la transaction
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
