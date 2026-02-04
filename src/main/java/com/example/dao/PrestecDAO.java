package com.example.dao;

import com.example.model.Prestec;
import com.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PrestecDAO {

    public void save(Prestec prestec) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(prestec);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Prestec findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Prestec p LEFT JOIN FETCH p.usuari LEFT JOIN FETCH p.llibre WHERE p.id = :id", Prestec.class)
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    public List<Prestec> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Prestec p LEFT JOIN FETCH p.usuari LEFT JOIN FETCH p.llibre", Prestec.class).list();
        }
    }

    public void update(Prestec prestec) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(prestec);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void delete(Prestec prestec) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(prestec);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
