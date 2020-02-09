package hibernate;

import hibernate.HibernateUtil;
import Mapping.Client;
import Mapping.ComposerSeance;
import Mapping.Programme;
import Mapping.Seance;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author samirkerrouche
 */
public class TestHibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//    /*----- Ouverture de la session et de la transaction -----*/
//    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//    Transaction t = session.beginTransaction();
//
//    // Votre code sera ici !
//    String hql = "from Client";
//    Query q = session.createQuery(hql);
//    ArrayList<Client> clients = (ArrayList<Client>) q.list();
//        System.out.println(clients);
//    t.commit();
//Seance seance;
//        seance = getSeances(getPrgm("FitnessSamir")).get(0);
//        System.out.println(getExercices(seance));
        // Client client = getClient("Giroux", "Romain");
        System.out.println(getClients());
        System.out.println(getProgrammes());
    }

    public static List<Programme> getProgrammes() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }

        // Votre code sera ici !
        String hql = "from Programme";
        Query q = session.createQuery(hql);
        ArrayList<Programme> programmes = (ArrayList<Programme>) q.list();
        return programmes;
    }

    /**
     * *
     *
     * @param nomP
     * @return un programme
     */
    public static Programme getPrgm(String nomP) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }

        // Votre code sera ici !
        String hql = "from Programme where nomProg ='" + nomP + "'";
        Query q = session.createQuery(hql);
        Programme prgm = (Programme) q.list().get(0);
        return prgm;
    }

    /**
     * *
     *
     * @param prgm
     * @return L ensemble des seances d un prgm
     */
    public static List<Seance> getSeances(Programme prgm) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        // Votre code sera ici !
        String hql = "from Seance where CodeProg =" + prgm.getCodeprog();
        Query q = session.createQuery(hql);
        List<Seance> seances = (List<Seance>) q.list();
        return seances;
    }

    /**
     * *
     *
     * @param seance
     * @return l ensemble des exercices qui composent une seance
     */
    public static List<ComposerSeance> getExercices(Seance seance) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        // Votre code sera ici !
        String hql = "from ComposerSeance where codeseance =" + seance.getCodeseance();
        Query q = session.createQuery(hql);
        List<ComposerSeance> composers = (List<ComposerSeance>) q.list();
        return composers;
    }

    //PARTIE DES CLIENTS
    public static List<Client> getClients() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }        //Tous les clients qui n'ont pas eu d'affectation de programme.
        String hql = "from Client as c where c.codecli NOT IN "
                + "(select a.client.codecli "
                + "from Affecter as a)"
                + "and c.statutcli= 'En attente'"
                + "ORDER BY c.dateinscriptioncli ASC";
        Query q = session.createQuery(hql);
        List<Client> clients = (List<Client>) q.list();
        //t.commit();

        return clients;
    }

    /**
     * *
     * Un client et son profil a partir de son nom et de son prenom en string.
     *
     * @param nomCli
     * @param prenomCli
     * @return un client
     */
    public static Client getClient(String nomCli, String prenomCli) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }        //Tous les clients qui n'ont pas eu d'affectation de programme.
        String hql = "from Client where nomcli ='" + nomCli + "' and prenomcli = '" + prenomCli + "'";
        Query q = session.createQuery(hql);
        Client client = (Client) q.uniqueResult();
        //t.commit();
        return client;
    }

    //UTILS
    public static void commit() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.getTransaction();
        t.commit();
    }
}
