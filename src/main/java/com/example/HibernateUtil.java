package com.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    // La SessionFactory se crea UNA sola vez y nunca se vuelve a crear
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");

            // FIX para Supabase: evitar prepared statements persistentes
            cfg.setProperty("hibernate.jdbc.prepare_sql", "false");
            cfg.setProperty("hibernate.connection.provider_disables_autocommit", "true");
            cfg.setProperty("hibernate.jdbc.use_get_generated_keys", "false");

            sessionFactory = cfg.buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Error al crear SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // NO cierres la SessionFactory en Supabase
    public static void shutdown() {
        // vacío a propósito
    }
}
