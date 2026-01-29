package com.example.dao;

import com.example.model.Prestec;
import com.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PrestecDAO {

    public void save(Prestec prestec) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(prestec);
        tx.commit();
        session.close();
    }

    public Prestec findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Prestec p = session.get(Prestec.class, id);
        session.close();
        return p;
    }

    public List<Prestec> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Prestec> list = session.createQuery("FROM Prestec", Prestec.class).list();
        session.close();
        return list;
    }

    public void update(Prestec prestec) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(prestec);
        tx.commit();
        session.close();
    }

    public void delete(Prestec prestec) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.remove(prestec);
        tx.commit();
        session.close();
    }
}
