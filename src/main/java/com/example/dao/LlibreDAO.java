package com.example.dao;

import com.example.HibernateUtil;
import com.example.model.Llibre;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LlibreDAO {

    // AFEGIR
    public void afegir(Llibre llibre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(llibre);
        tx.commit();
        session.close();
    }

    // LLISTAR
    public void llistar() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Llibre> llibres = session.createQuery("FROM Llibre", Llibre.class).list();
        session.close();

        if (llibres.isEmpty()) {
            System.out.println("No hi ha llibres registrats.");
        } else {
            llibres.forEach(System.out::println);
        }
    }

    // BUSCAR PER ID
    public Llibre buscar(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Llibre llibre = session.get(Llibre.class, id);
        session.close();
        return llibre;
    }

    // MODIFICAR
    public void modificar(Llibre llibre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(llibre);
        tx.commit();
        session.close();
    }

    // ESBORRAR
    public void esborrar(Llibre llibre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.remove(llibre);
        tx.commit();
        session.close();
    }
}
