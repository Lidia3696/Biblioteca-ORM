package com.example.dao;

import java.util.List;

import org.hibernate.Session;

import com.example.HibernateUtil;
import com.example.model.Prestec;
import com.example.model.Usuari;

public class ConsultesDAO {

    // CONSULTA 1: Tots els préstecs d’un usuari
    public List<Prestec> prestecsPerUsuari(int usuariId) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Prestec> result = session.createQuery(
                "FROM Prestec p WHERE p.usuari.id = :id", Prestec.class)
                .setParameter("id", usuariId)
                .list();

        session.close();
        return result;
    }

    // CONSULTA 2: Tots els usuaris que han prestat un llibre
    public List<Usuari> usuarisQueHanAlquilatLlibre(int llibreId) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Usuari> result = session.createQuery(
                "SELECT DISTINCT p.usuari FROM Prestec p WHERE p.llibre.id = :id", Usuari.class)
                .setParameter("id", llibreId)
                .list();

        session.close();
        return result;
    }
}
