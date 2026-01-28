package com.example;

import com.example.dao.LlibreDAO;
import com.example.model.Llibre;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

        int op;
        Scanner sc = new Scanner(System.in);

        do {

            System.out.println("******************************");
            System.out.println("*   MENU GESTIO BIBLIOTECA   *");
            System.out.println("******************************");
            System.out.println("1. Gestionar llibres");
            System.out.println("2. Gestionar usuaris");
            System.out.println("3. Gestionar prestecs");
            System.out.println("0. Sortir");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    //menu llibres
                    menuLlibres(sc);
                    break;
                case 2:
                    //menu usuaris
                    break;
                case 3:
                    //menu prestecs
                    break;
                case 0:
                    //sortir del programa
                    System.exit(0);
                    break;
                default:
                    System.out.println("Si us plau, escolliu una opcio valida [0-3]");
                    break;
            }

        } while (op != 0);

    }

    // MENU GESTIO LLIBRES
    public static void menuLlibres(Scanner sc) {

        int opcio;
        LlibreDAO llibredao = new LlibreDAO();

        do {

            System.out.println("***************************");
            System.out.println("*   MENU GESTIO LLIBRES   *");
            System.out.println("***************************");
            System.out.println("1. Afegir un llibre");
            System.out.println("2. Llistar tots els llibres");
            System.out.println("3. Modificar llibre");
            System.out.println("4. Esborrar un llibre");
            System.out.println("0. Sortir");

            opcio = sc.nextInt();
            sc.nextLine();

            switch (opcio) {
                case 1:
                    //afegir llibre
                    try {
                        Llibre llibre = new Llibre();

                        System.out.println("Titol:");
                        String t = sc.nextLine();
                        llibre.setTitol(t);

                        System.out.println("Autor:");
                        String aut = sc.nextLine();
                        llibre.setAutor(aut);

                        System.out.println("ISBN:");
                        String isbn = sc.nextLine();
                        llibre.setISBN(isbn);

                        System.out.println("Any de publicacio:");
                        int any = sc.nextInt();
                        llibre.setAny(any);
                        sc.nextLine();

                        System.out.println("Editorial:");
                        String ed = sc.nextLine();
                        llibre.setEditorial(ed);

                        System.out.println("Preu:");
                        double preu = sc.nextDouble();
                        llibre.setPreu(preu);
                        sc.nextLine();

                        llibredao.afegir(llibre);
                        
                        //reiniciar buffer del scanner
                        sc.nextLine();

                    } catch (Exception e) {
                        System.out.println("Error creant el llibre: " + e.getMessage());
                    }

                    System.out.println("Llibre afegit correctament.");

                    break;
                case 2:
                    //Llistar llibres
                    llibredao.llistar();
                    break;
                case 3:
                    //Modificar llibre
                    break;
                case 4:
                    //Esborrar un llibre
                    break;
                case 0:
                    //sortir del menu
                    System.out.println("Sortint del menu...");
                    break;
                default:
                    System.out.println("Si us plau, escolliu una opcio valida [0-3]");
                    break;
            }

        } while (opcio != 0);

    }

}
/*
SELECT setval(
  'biblioteca.llibres_id_seq',
  (SELECT MAX(id) FROM biblioteca.llibres)
);
*/
