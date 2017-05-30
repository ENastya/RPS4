/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clientacc.Bankmeth;
import com.mysql.jdbc.Connection;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author Настя
 */
@Stateless
@LocalBean
public class Clientmeth {

    @PersistenceContext(unitName = "lab4-ejbPU")
    private EntityManager em;
    @EJB
    private Bankmeth bm;
    @Resource
    SessionContext sc;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean platezh (int id, int sum){
    Clent cl = em.find(Clent.class, id);
    if (bm.takeMoney(cl.getBankAccount(), sum)){
             cl.setBalance(cl.getBalance() + sum);
            em.merge(cl);return true;
    }
    else return false;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean platezhBankEx (int id, int sum){
    Clent cl = em.find(Clent.class, id);
    if (bm.takeMoneyEx(cl.getBankAccount(), sum)){
             cl.setBalance(cl.getBalance() + sum);
            em.merge(cl);return true;
    }
    else return false;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean platezhOtkat (int id, int sum){
    Clent cl = em.find(Clent.class, id);
    if (bm.takeMoney(cl.getBankAccount(), sum)){
             cl.setBalance(cl.getBalance() + sum);
            em.merge(cl);
            sc.setRollbackOnly();
            return true;
    }
    else return false;
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public boolean platezhOtkatNT (int id, int sum){
        try{
    Clent cl = em.find(Clent.class, id);
    boolean c = false;
    
    if (bm.takeMoneyNT(cl.getBankAccount(), sum)){
             cl.setBalance(cl.getBalance() + sum);
            em.merge(cl);
            sc.setRollbackOnly();
            return true;
    }
    else return false;
    }
    catch(Exception ex ){
    System.out.println(ex);
    return false;
    }
    }
    
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean platezhOtkatRN (int id, int sum){
        try{
    Clent cl = em.find(Clent.class, id);
    boolean c = false;
    
    if (bm.takeMoneyNT(cl.getBankAccount(), sum)){
             cl.setBalance(cl.getBalance() + sum);
            em.merge(cl);
            sc.setRollbackOnly();
            return true;
    }
    else return false;
    }
    catch(Exception ex ){
    System.out.println(ex);
    return false;
    }
    }
    public void persist(Object object) {
        em.persist(object);
    }

    public void persist1(Object object) {
        em.persist(object);
    }
}
