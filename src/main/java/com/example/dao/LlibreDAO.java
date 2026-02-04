package com.example.dao;

import com.example.HibernateUtil;
import com.example.model.Llibre;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LlibreDAO {

    // AFEGIR
    public void afegir(Llibre llibre) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(llibre);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    // LLISTAR
    public void llistar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Llibre> llibres = session.createQuery("FROM Llibre", Llibre.class).list();
            if (llibres.isEmpty()) {
                System.out.println("No hi ha llibres registrats.");
            } else {
                llibres.forEach(System.out::println);
            }
        }
    }

    // BUSCAR PER ID
    public Llibre buscar(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Llibre.class, id);
        }
    }

    // MODIFICAR
    public void modificar(Llibre llibre) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(llibre);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    // ESBORRAR
    public void esborrar(Llibre llibre) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(llibre);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
