package com.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Main {
   public static void main(String[] args) {


       Configuration cfg = new Configuration();


       // Configuración de Supabase
       cfg.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
       cfg.setProperty("hibernate.connection.url",
               "jdbc:postgresql://db.kardzcxpkyicnrcobuzx.supabase.co:5432/postgres?user=postgres&password=12345m");
       cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
       cfg.setProperty("hibernate.hbm2ddl.auto", "update");
       cfg.setProperty("hibernate.show_sql", "true");


       // Registrar entidades
       cfg.addAnnotatedClass(com.example.Llibre.class);
       cfg.addAnnotatedClass(com.example.models.usuario.class);


       ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
               .applySettings(cfg.getProperties()).build();


       SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);


       Session session = factory.openSession();


       System.out.println("✔ Hibernate 7 conectado correctamente a Supabase!");
   }
}