package com.example.service;

import com.example.HibernateUtil;
import com.example.model.Llibre;
import com.example.model.Prestec;
import com.example.model.Usuari;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDate;

public class PrestecService {
    
    public class Main {
        public static void main(String[] args) {
            PrestecService service = new PrestecService();
        }
    }

    public void crearPrestec(Usuari u, Llibre l) {

        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();


            if (u == null || l == null) {
                throw new RuntimeException("Usuari o llibre no existeix");
            }

            if (l.getStock() <= 0) {
                throw new RuntimeException("No hi ha stock");
            }

            l.setStock(l.getStock() - 1);

            Prestec p = new Prestec();
            p.setUsuari(u);
            p.setLlibre(l);
            p.setDataInici(LocalDate.now());

            session.persist(p);

            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void tancarPrestec(int prestecId) {

        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            
            Prestec prestec = session.createQuery(
                "FROM Prestec p LEFT JOIN FETCH p.usuari LEFT JOIN FETCH p.llibre WHERE p.id = :id", 
                Prestec.class)
                .setParameter("id", prestecId)
                .uniqueResult();

            if (prestec == null) {
                throw new RuntimeException("Prestec no trobat");
            }

            if (prestec.getDataFi() != null) {
                throw new RuntimeException("Prestec ja tancat");
            }

            prestec.setDataFi(LocalDate.now());

            Llibre llibre = prestec.getLlibre();
            llibre.setStock(llibre.getStock() + 1);
            
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
