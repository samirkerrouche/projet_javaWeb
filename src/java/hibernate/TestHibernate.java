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
import Mapping.Profil;
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

/**
 *
 * @author samirkerrouche
 */
public class TestHibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        Programme pTest = new Programme("TestdeGetCode", Boolean.FALSE, null, null, null, null);
//        //insertProgram(pTest);
//        System.out.println(getProgrammes());
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        String hql = "from Programme";
        Query q = session.createQuery(hql);
        for(Programme prgm : (List<Programme>) q.list()){
            for(Object str : prgm.getProfils()){
                Profil string = (Profil) str;
                System.out.println(string.getNomprof());
            }
        }
    }

    /**
     * *
     * Le nom du client doit etre de la forme : prenom_nom
     *
     * @param nomCli
     * @return Les programmes sans triage.
     */
    public static List<Programme> getProgrammes() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        String hql;
        ArrayList<Programme> programmes = new ArrayList<>();
        hql = "from Programme";
        Query q = session.createQuery(hql);
        programmes = (ArrayList<Programme>) q.list();
        return programmes;
    }

    /**
     * *
     *
     * @param client
     * @return les programmes tries selon un Client
     */
    public static List<Programme> sortPrograms(Client client) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        String hql;
        List<Programme> programmes = new ArrayList<>();
        System.out.println(client.getNomcli());
        Query q;
        if (client.getProfil().getCodeprof() != null) {
            System.out.println(client.getProfil().getCodeprof());
            hql = "from Programme as p where p.profils.codeprof =" + client.getProfil().getCodeprof();
            q = session.createQuery(hql);
            programmes = (List<Programme>) q.list();
            System.out.print("Ensemble des programmes répondant au besoin : ");
            System.out.println(programmes.size());
        }
        hql = "from Programme";
        q = session.createQuery(hql);
        List<Programme> prgmSansProfil = (List<Programme>) q.list();
        for (Programme prgm : prgmSansProfil) {
            if (!programmes.contains(prgm)) {
                programmes.add(prgm);
            }
        }
        System.out.println(programmes.size());
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

    /**
     * *
     *
     * @return
     */
    public static List<Client> getClients() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        List<Client> renvoi = new ArrayList<>();
        String hql1 = "from Client as c where c.codecli not in"
                + " (select a.client.codecli "
                + "from Affecter as a) "
                + "order by c.dateinscriptioncli asc";
        Query q = session.createQuery(hql1);
        List<Client> clients = (List<Client>) q.list();

        String hql2 = " from Client as c where c.codecli in "
                + "( select a.client.codecli from Affecter as a WHERE a.datefinaff "
                + "is not null and a.id.dateaff in (select max(ar.id.dateaff)"
                + "FROM Affecter as ar WHERE ar.programme.codeprog in "
                + "(SELECT af.programme.codeprog FROM Affecter as af GROUP BY af.client.codecli)) "
                + "ORDER by a.id.dateaff ASC)";
        Query l = session.createQuery(hql2);
        List<Client> clientes = (List<Client>) l.list();
        for (Client cl : clients) {
            renvoi.add(cl);
        }
        for (Client cl : clientes) {
            if (!renvoi.contains(cl)) {
                renvoi.add(cl);
            }
        }

        return renvoi;
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
        return client;
    }

    /**
     * *
     * insere des donnees dans la table programme.
     *
     * @param programme
     */
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
     * Cree des occurrences pour pouvoir les manipuler et les sauver.
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

    /**
     * *
     * Donne les occurrences selon une seance
     *
     * @param seance
     * @return
     */
    public static List<OccurrenceS> getOccurr(Seance seance) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        String hql = "from OccurrenceS where codeseance =" + seance.getCodeseance();
        Query q = session.createQuery(hql);
        List<OccurrenceS> occs = (List<OccurrenceS>) q.list();
        return occs;
    }

    /**
     * *
     * Donne l executer exo selon une occurrence, un client et un exo. En cree
     * une nouvelle.
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

    /**
     * *
     * Insere une seule seance
     *
     * @param seance
     */
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

    /**
     * *
     * Insere une liste de seances
     *
     * @param seances
     */
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

    /**
     * *
     * Insere une seule occurrence de seance
     *
     * @param oc
     */
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

    /**
     * *
     * Insere une liste d occurrence de seance.
     *
     * @param ocs
     */
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

    /**
     * *
     *
     * @param nomExo
     * @return l exercice selon un nom.
     */
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

    /**
     * *
     * Insere une composition de seance
     *
     * @param compS
     */
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

    /**
     * *
     *
     * @param circuit
     * @return tous les exercices qui composent le circuit mentionne.
     */
    public static List<ComposerCircuit> getComposerCircuit(Circuit circuit) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        String hql = "from ComposerCircuit where circuit.codecir=" + circuit.getCodecir();
        Query q = session.createQuery(hql);
        List<ComposerCircuit> cc = (List<ComposerCircuit>) q.list();
        return cc;
    }

    /**
     * *
     * On ne peut pas instancier les objets exercice et Seance d un composer
     * seance.
     *
     * @param cs
     * @return Le contenu d un exercice en utilisant l id(le seul attribut
     * instancie).
     */
    public static Exercice getContent(ComposerSeance cs) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        String hql = "from Exercice as e where e.codeexo ="
                + "(select cs.id.codeexo from ComposerSeance as cs "
                + "where cs.id.codeexo=" + cs.getId().getCodeexo() + " and cs.id.codeseance=" + cs.getId().getCodeseance() + ")";
        Query q = session.createQuery(hql);
        Exercice exo = (Exercice) q.list().get(0);
//        t.commit();
        return exo;
    }

    /**
     * *
     * Finalise l affectation en entrant une affectation entre un client et un
     * programme complet. A faire a la fin de la creation d un programme.
     *
     * @param affecter
     */
    public static void insertAffectation(Affecter affecter) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t;
        try {
            t = session.beginTransaction();
        } catch (Exception e) {
            t = session.getTransaction();
        }
        session.save(affecter);
        t.commit();
    }
}
