/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientacc;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Настя
 */
@Stateless
@LocalBean

public class Bankmeth {

    @PersistenceContext(unitName = "lab4bd2PU")
    private EntityManager em;
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
     public boolean takeMoneyNT(int acc, int sum){
         Client cl = em.find(Client.class, acc);
         if (cl.getBalance() > sum) {
             cl.setBalance(cl.getBalance() - sum);
         em.merge(cl);
         return true;
         }
         return false;
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
     public boolean takeMoney(int acc, int sum){
         Client cl = em.find(Client.class, acc);
         if (cl.getBalance() > sum) {
             cl.setBalance(cl.getBalance() - sum);
         em.merge(cl);
         return true;
         }
         return false;
    }
     
     @TransactionAttribute(TransactionAttributeType.MANDATORY)
       public boolean takeMoneyEx(int acc, int sum){
         Client cl = em.find(Client.class, acc);
         if (cl.getBalance() > sum) {
             cl.setBalance(cl.getBalance() - sum);
         em.merge(cl);
         throw new EJBException();
         }
         return false;
    }
     
     
     
   // public EntityManager em = Persistence.createEntityManagerFactory("lab4bd2PU").createEntityManager();/

    public void persist(Object object) {
        em.persist(object);
    }

    public void persist1(Object object) {
        em.persist(object);
    }
  
    
}
