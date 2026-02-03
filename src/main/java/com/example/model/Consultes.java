package com.example.model;

import java.util.List;
import java.util.Scanner;

import com.example.dao.ConsultesDAO;
import com.example.model.Prestec;
import com.example.model.Usuari;

public class Consultes {

    public static void menuConsultes(Scanner sc) {

        ConsultesDAO dao = new ConsultesDAO();
        int op;

        do {
            System.out.println("***************************");
            System.out.println("*   MENU CONSULTES        *");
            System.out.println("***************************");
            System.out.println("1. Préstecs d’un usuari");
            System.out.println("2. Usuaris que han prestat un llibre");
            System.out.println("0. Sortir");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1 -> {
                    System.out.println("ID de l’usuari:");
                    int id = sc.nextInt();
                    sc.nextLine();

                    List<Prestec> prestecs = dao.prestecsPerUsuari(id);

                    if (prestecs.isEmpty()) {
                        System.out.println("Aquest usuari no té préstecs.");
                    } else {
                        prestecs.forEach(System.out::println);
                    }
                }

                case 2 -> {
                    System.out.println("ID del llibre:");
                    int id = sc.nextInt();
                    sc.nextLine();

                    List<Usuari> usuaris = dao.usuarisQueHanAlquilatLlibre(id);

                    if (usuaris.isEmpty()) {
                        System.out.println("Aquest llibre no ha estat prestat.");
                    } else {
                        usuaris.forEach(System.out::println);
                    }
                }

                case 0 -> System.out.println("Sortint del menú consultes...");
                default -> System.out.println("Opció no vàlida.");
            }

        } while (op != 0);
    }
}
