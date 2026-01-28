package com.example.dao;

import com.example.HibernateUtil;
import com.example.model.Llibre;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class LlibreDAO {
    
    //afegir un llibre a la taula
    public void afegir(Llibre llibre) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.persist(llibre);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        
    }
    
    //llistar tots els llibres
    public List<Llibre> llistar() {
        
        List<Llibre> llista = null;
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hql = "FROM llibres";

        try {
            llista = session.createQuery(hql).list();
            
            for (Llibre l : llista) {
                l.toString();
            }
            
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return llista;
    }
    
}
