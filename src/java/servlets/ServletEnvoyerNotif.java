/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Mapping.Client;
import Mapping.Coach;
import Mapping.Notifier;
import Mapping.NotifierId;
import Mapping.User;
import hibernate.HibernateUtil;
import hibernate.TestHibernate;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author chenw
 */
public class ServletEnvoyerNotif extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        String txtObjet = request.getParameter("txtObjet");
        String txtEnvoyer = request.getParameter("txtEnvoyer");

        String idUserCoach = request.getParameter("idUserCoach");
        String idUserClient = request.getParameter("idUserClient");
        int idCoach = Integer.parseInt(idUserCoach);
        int idClient = Integer.parseInt(idUserClient);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        //System.out.println(date);
        //String dateNow = dateFormat.format(date);

        String messageC = "";
        if (txtObjet.isEmpty() || txtEnvoyer.isEmpty()) {
            messageC = "Un champ est vide";
        } else {
            messageC = "Envoie reussi. " ;
        }

        try {

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();

            NotifierId ni = new NotifierId(idCoach, idClient, date);
            User client = (User) session.get(User.class, idClient);
            User coach = (User) session.get(User.class, idCoach);
            Notifier n = new Notifier(ni, coach, client, txtEnvoyer, Boolean.FALSE);
            //t.commit();

            Client clientMail = (Client) session.get(Client.class, idClient);
            //Coach coachMail = (Coach) session.get(Coach.class, idCoach);

            String mailCl = clientMail.getMailcli();
            //String mailCl = clientMail.getMailcli();
            String mailCo = "JDcoaching.Ouloulou@gmail.com";
            if (txtObjet.isEmpty() || txtEnvoyer.isEmpty()) {

                //setup de Host
                String smtpHost = "smtp.gmail.com";
                String from = mailCo;
                String to = mailCl;
                String username = mailCo;
                String password = "ouloulou";

                //Envoie de mail a client
                Properties props = new Properties();
                props.put("mail.smtp.host", smtpHost);
                props.put("mail.smtp.auth", "true");
                //props.put("mail.host", smtpHostServer);
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smptp.ssl.enable", true);
                //props.put("mail.smtp.ssl.trust", smtpHostServer);
                props.put("mail.debug", "true");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.fallback", "false");

                javax.mail.Session session2 = javax.mail.Session.getDefaultInstance(props);
                session2.setDebug(true);
                try {
                    MimeMessage message = new MimeMessage(session2);
                    message.setFrom(new InternetAddress(from));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    message.setSubject("Message Coach : " + txtObjet);
                    message.setText(txtEnvoyer);
                    try (Transport tr = session2.getTransport("smtp")) {
                        tr.connect(smtpHost, username, password);
                        message.saveChanges();
                        tr.sendMessage(message, message.getAllRecipients());
                        tr.close();
                    }
                } catch (MessagingException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                //insertion de la notification
                TestHibernate.insertNotif(n);
            }

            RequestDispatcher rd = request.getRequestDispatcher("ViewEnvoyerNotif");
            request.setAttribute("msg_infoEnvoie", messageC);
            request.setAttribute("mailClient", clientMail.getMailcli());
            rd.forward(request, response);

        } catch (Exception ex) {

            RequestDispatcher rd = request.getRequestDispatcher("ViewEnvoyerNotif");
            request.setAttribute("msg_erreurEnvoie", ex.getMessage());
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
