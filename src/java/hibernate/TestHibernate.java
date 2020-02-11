package hibernate;

import Mapping.Affecter;
import Mapping.Circuit;
import hibernate.HibernateUtil;
import Mapping.Client;
import Mapping.ComposerCircuit;
import Mapping.ComposerCircuitId;
import Mapping.ComposerSeance;
import Mapping.ExecuterCircuit;
import Mapping.ExecuterExo;
import Mapping.ExecuterExoId;
import Mapping.Exercice;
import Mapping.OccurrenceS;
import Mapping.Programme;
import Mapping.Seance;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

//        // System.out.println(getClients());
//        //Romain_Giroux et le programme Fitness
        Programme pTest = new Programme("TestdeGetCode", Boolean.FALSE, null, null, null);
        insertProgram(pTest);
        System.out.println(pTest.getCodeprog());

//        Seance seanceTest = new Seance(null, null, "TESTSEANCE", Boolean.FALSE, null, null);
// //       insertSeance(seanceTest);
//
//        Date dateoccs = new Date(2020, 10, 10);
//        OccurrenceS oc = new OccurrenceS(seanceTest, dateoccs, Boolean.FALSE, null, null, null);
// //       insertOccurrenceS(oc);
//
//        Client client = getClient("Giroux", "Romain");
//        Exercice exercice = getExercice("Squat");
//        ExecuterExoId id = new ExecuterExoId(5,5,6);//(5, 5, 5);
//        client.setCodecli(5);
//        exercice.setCodeexo(5);
//        oc.setCodeoccs(6);
//        ExecuterExo exeTest = new ExecuterExo(id, client, exercice, oc);
//        insertExecutionExercice(exeTest);
        //       System.out.println(getExercice("Squat"));
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
        //t.commit();
        //session.close();
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
        //System.out.println(q.list().size());
        // t.commit();
        //session.close();
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
        //  t.commit();
        //  session.close();
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
        // t.commit();
        // session.close();
        return composers;
    }

    /**
     * *
     *
     * @param seance
     * @return le circuit de la seance si elle en possede une
     */
    public static List<ComposerCircuit> getExoFromCircuit(Circuit circuit) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        // Votre code sera ici !
        String hql = "from ComposerCircuit as cc  where  codecir=" + circuit.getCodecir();
        Query q = session.createQuery(hql);
        List<ComposerCircuit> comCir = (List<ComposerCircuit>) q.list();
        // t.commit();
        // session.close();
        return comCir;
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
        // t.commit();
        //session.close();
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
        //session.close();
        //t.commit();
        return client;
    }

    //UTILS
    public static void commit() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.getTransaction();
        t.commit();
    }

    /**
     * *
     * Prend un programme et rempli la BD pour toutes les entites.
     *
     * @param programme
     * @param affecter
     * @param client
     * @param seances
     */
    public static void transfertProgramme(Programme programme, Affecter affecter,
            Client client, HashMap<Seance, List<ComposerSeance>> seances) {

        insertProgram(programme);
        for (Map.Entry<Seance, List<ComposerSeance>> e : seances.entrySet()) {
            Seance seance = e.getKey();
            insertSeance(seance);
            ArrayList<OccurrenceS> refOs = new ArrayList<>();
            for (OccurrenceS os : getOccurences(e.getKey())) {
                refOs.add(os);
                insertOccurrenceS(os);
            }
//            if (seance.getCircuit() == null) {
//                for (int i = 0; i < e.getValue().size(); i++) {
//                    ComposerSeance compS = e.getValue().get(i);
//                    ExecuterExo ex = getExecuterExo(refOs.get(i), client, compS.getExercice());
//                    session.save(ex);
//                }
//            } else {
//                List<ComposerCircuit> compCir = TestHibernate.getExoFromCircuit(seance.getCircuit());
//                Set x = new HashSet();
//                Circuit circuitClient = new Circuit(seance.getCircuit().getNomcir() + " de " + client.getNomcli(), x, x, x);
//                session.save(circuitClient);
//                for (ComposerCircuit compoCir : compCir) {
//                    ComposerCircuitId id = new ComposerCircuitId(compoCir.getExercice().getCodeexo(), circuitClient.getCodecir());
//                    ComposerCircuit ccClient = new ComposerCircuit(id, circuitClient, compoCir.getExercice(), Integer.SIZE, 0);
//                }
//
//         }

        }
        //session.close();
    }

    public static void insertProgram(Programme programme) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        session.save(programme);
        t.commit();
        //session.close();
    }

    /**
     * *
     *
     * @param seance
     * @return une liste d'occurence
     */
    public static List<OccurrenceS> getOccurences(Seance seance) {
        Date date = new Date(2020, 10, 23);
        Set x = new HashSet();
        OccurrenceS occ = new OccurrenceS(seance, date, Boolean.FALSE, x, x, x);
        OccurrenceS occ1 = new OccurrenceS(seance, date, Boolean.FALSE, x, x, x);
        OccurrenceS occ2 = new OccurrenceS(seance, date, Boolean.FALSE, x, x, x);
        OccurrenceS occ3 = new OccurrenceS(seance, date, Boolean.FALSE, x, x, x);
        List<OccurrenceS> lst = new ArrayList<>();
        lst.add(occ);
        lst.add(occ1);
        lst.add(occ2);
        lst.add(occ3);

        return lst;

    }
    
    public static List<OccurrenceS> getOccurr(Seance seance){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        String hql = "from OccurrenceS where codeseance ="+seance.getCodeseance();
        Query q = session.createQuery(hql);
        List<OccurrenceS> occs = (List<OccurrenceS>)q.list();
        return occs;
    }

    /**
     * *
     *
     * @param occ
     * @param client
     * @param exo
     * @return les exercices que le client pourra remplir selon l occurrence.
     */
    public static ExecuterExo getExecuterExo(OccurrenceS occ, Client client, Exercice exo) {
        ExecuterExoId id = new ExecuterExoId(exo.getCodeexo(), client.getCodecli(), occ.getCodeoccs());
        ExecuterExo execute = new ExecuterExo(id, client, exo, occ);
        return execute;
    }

    private void test(int codeOccs) {
        String hql = "from ExecuterExo as e where e.ExecuterExoId.codeoccs =" + codeOccs;
    }

    public static void insertSeance(Seance seance) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        session.save(seance);
        t.commit();

    }

    public static void insertSeances(List<Seance> seances) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        for (Seance seance : seances) {
            session.save(seance);

        }
        t.commit();

    }

    public static void insertOccurrenceS(OccurrenceS oc) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        session.save(oc);
        t.commit();

    }

    public static void insertOccurrencesS(List<OccurrenceS> ocs) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        for (OccurrenceS oc : ocs) {
            session.save(oc);

        }
        t.commit();

    }

    public static Exercice getExercice(String nomExo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        String hql = "from Exercice where nomexo='" + nomExo + "'";
        Query q = session.createQuery(hql);
        Exercice exercice = (Exercice) q.uniqueResult();
        return exercice;
    }

    /**
     * *
     * Pour que ça marche il faut recuperer les id des clients dans les objets
     * et dans l id de executer exo.
     *
     * @param exeExo
     */
    public static void insertExecutionExercice(ExecuterExo exeExo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        session.save(exeExo);
        t.commit();
    }

    /**
     * *
     * Marche de la meme maniere que pour insertexecuterExercice.
     *
     * @param exeCir
     */
    public static void insertExecutionCircuit(ExecuterCircuit exeCir) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        session.save(exeCir);
        t.commit();
    }

    public static void insertComposerSeance(ComposerSeance compS) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        session.save(compS);
        t.commit();
    }

    /***
     * 
     * @param circuit
     * @return tous les exercices qui composent le circuit mentionne.
     */
    public static List<ComposerCircuit> getComposerCircuit(Circuit circuit){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();           
        }
        String hql = "from ComposerCircuit where circuit.codecir="+circuit.getCodecir() ;
        Query q = session.createQuery(hql);
        List<ComposerCircuit> cc = (List<ComposerCircuit>) q.list();
        return cc;
    } 
    
    public static Exercice getContent(ComposerSeance cs){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();           
        }
        return cs.getExercice();
    }
}
