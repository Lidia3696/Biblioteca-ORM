package com.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    // FUNCIONS DE HIBERNATE EN UNA MATEIXA CLASSE 

    static {
        try {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");

            sessionFactory = cfg.buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Error al crear SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    //obtenir la sessio
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //tancar la sessio
    public static void shutdown() {
         sessionFactory.close();
    }
}
