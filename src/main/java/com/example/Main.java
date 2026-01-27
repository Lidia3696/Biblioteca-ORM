package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

        Llibre l1 = new Llibre();
        l1.setISBN("9788441425149");
        l1.setTitol("La Divina Comedia");
        l1.setAutor("Dante Alighieri");
        l1.setEditorial("Edaf");
        l1.setAny(2010);
        l1.setPreu(17.10);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.persist(l1);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

}
/*
SELECT setval(
  'biblioteca.llibres_id_seq',
  (SELECT MAX(id) FROM biblioteca.llibres)
);
*/