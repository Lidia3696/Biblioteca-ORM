package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {
    public static void main(String[] args) {
        
        Llibre l1 = new Llibre();
        l1.setTitol("Luna de Pluton");
        l1.setAutor("Dross");
        l1.setEditorial("Planeta");
        l1.setAny(2015);
        l1.setPreu(19.99);
        l1.setISBN("1234ACB");

        System.out.println("hoffla");
        
        //aqui hay funciones del hibernate para llamar
        Configuration config = new Configuration();
        config.addAnnotatedClass(com.example.Llibre.class);
        config.configure("hibernate.cfg.xml");
        
        SessionFactory sf = config.buildSessionFactory();
        
        Session session = sf.openSession();
        
        //guardar objete al hibernate
        session.persist(l1);
        
    }


}