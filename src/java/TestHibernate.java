
import Mapping.Client;
import java.util.ArrayList;
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
    /*----- Ouverture de la session et de la transaction -----*/
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction t = session.beginTransaction();

    // Votre code sera ici !
    String hql = "from Client";
    Query q = session.createQuery(hql);
    ArrayList<Client> clients = (ArrayList<Client>) q.list();
        System.out.println(clients);
    t.commit();
    }
    
}
