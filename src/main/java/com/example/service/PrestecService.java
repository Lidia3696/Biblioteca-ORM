package com.example.service;

import com.example.model.Prestec;
import com.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.model.Llibre;
import com.example.model.Prestec;
import com.example.model.Usuari;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.time.LocalDate;

@Stateless
public class PrestecService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void crearPrestec(Long idUsuari, Long idLlibre) {

        Llibre llibre = em.find(Llibre.class, idLlibre);
        Usuari usuari = em.find(Usuari.class, idUsuari);

        if (llibre.getStock() <= 0) {
            throw new IllegalStateException("No hi ha stock disponible");
        }

        // baixar stock
        llibre.setStock(llibre.getStock() - 1);

        // crear el prestec
        Prestec p = new Prestec();
        p.setUsuari(usuari);
        p.setLlibre(llibre);
        p.setDataInici(LocalDate.now());

        em.persist(p);
    }

    @Transactional
    public void tancarPrestec(Long idPrestec) {

        Prestec p = em.find(Prestec.class, idPrestec);

        if (p.getDataFi() != null) {
            throw new IllegalStateException("El préstec ja està tancat");
        }

        // tancar prestec
        p.setDataFi(LocalDate.now());

        // pujar stock
        Llibre llibre = p.getLlibre();
        llibre.setStock(llibre.getStock() + 1);
    }

}
