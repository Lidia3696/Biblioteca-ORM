package com.example.dao;

import com.example.model.Usuari;
import com.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsuariDAO {

    public void save(Usuari usuari) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(usuari);
        tx.commit();
        session.close();
    }

    public Usuari findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Usuari u = session.get(Usuari.class, id);
        session.close();
        return u;
    }

    public List<Usuari> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Usuari> list = session.createQuery("FROM Usuari", Usuari.class).list();
        session.close();
        return list;
    }

    public void update(Usuari usuari) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(usuari);
        tx.commit();
        session.close();
    }

    public void delete(Usuari usuari) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.remove(usuari);
        tx.commit();
        session.close();
    }
}
