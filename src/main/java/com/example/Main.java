package com.example;

import java.time.LocalDate;
import java.util.Scanner;

import com.example.dao.LlibreDAO;
import com.example.dao.PrestecDAO;
import com.example.dao.UsuariDAO;
import com.example.model.Llibre;
import com.example.model.Prestec;
import com.example.model.Usuari;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int op;

        do {
            System.out.println("******************************");
            System.out.println("*   MENU GESTIO BIBLIOTECA   *");
            System.out.println("******************************");
            System.out.println("1. Gestionar llibres");
            System.out.println("2. Gestionar usuaris");
            System.out.println("3. Gestionar prestecs");
            System.out.println("4. Consultes");
            System.out.println("0. Sortir");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> menuLlibres(sc);
                case 2 -> menuUsuaris(sc);
                case 3 -> menuPrestecs(sc);
                case 4 -> Consultes.menuConsultes(sc);
                case 0 -> System.out.println("Sortint del programa...");
                default -> System.out.println("Opció no vàlida.");
            }

        } while (op != 0);
    }

    // ---------------------------------------------------------
    // MENU LLIBRES
    // ---------------------------------------------------------
    public static void menuLlibres(Scanner sc) {

        LlibreDAO llibredao = new LlibreDAO();
        int op;

        do {
            System.out.println("***************************");
            System.out.println("*   MENU GESTIO LLIBRES   *");
            System.out.println("***************************");
            System.out.println("1. Afegir un llibre");
            System.out.println("2. Llistar tots els llibres");
            System.out.println("3. Modificar llibre");
            System.out.println("4. Esborrar un llibre");
            System.out.println("0. Sortir");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> {
                    try {
                        Llibre llibre = new Llibre();

                        System.out.println("Títol:");
                        llibre.setTitol(sc.nextLine());

                        System.out.println("Autor:");
                        llibre.setAutor(sc.nextLine());

                        System.out.println("ISBN:");
                        llibre.setISBN(sc.nextLine());

                        System.out.println("Any de publicació:");
                        llibre.setAny(sc.nextInt());
                        sc.nextLine();

                        System.out.println("Editorial:");
                        llibre.setEditorial(sc.nextLine());

                        System.out.println("Preu:");
                        llibre.setPreu(sc.nextDouble());
                        sc.nextLine();

                        llibredao.afegir(llibre);
                        System.out.println("Llibre afegit correctament.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 2 -> llibredao.llistar();

                case 3 -> {
                    System.out.println("ID del llibre a modificar:");
                    int id = sc.nextInt();
                    sc.nextLine();

                    Llibre llibre = llibredao.buscar(id);
                    if (llibre == null) {
                        System.out.println("No existeix aquest llibre.");
                        break;
                    }

                    System.out.println("Nou títol (" + llibre.getTitol() + "):");
                    llibre.setTitol(sc.nextLine());

                    System.out.println("Nou autor (" + llibre.getAutor() + "):");
                    llibre.setAutor(sc.nextLine());

                    llibredao.modificar(llibre);
                    System.out.println("Llibre modificat correctament.");
                }

                case 4 -> {
                    System.out.println("ID del llibre a eliminar:");
                    int id = sc.nextInt();
                    sc.nextLine();

                    Llibre llibre = llibredao.buscar(id);
                    if (llibre != null) {
                        llibredao.esborrar(llibre);
                        System.out.println("Llibre eliminat.");
                    } else {
                        System.out.println("No existeix aquest llibre.");
                    }
                }

                case 0 -> System.out.println("Sortint del menú llibres...");
                default -> System.out.println("Opció no vàlida.");
            }

        } while (op != 0);
    }

    // ---------------------------------------------------------
    // MENU USUARIS
    // ---------------------------------------------------------
    public static void menuUsuaris(Scanner sc) {

        UsuariDAO usuariDAO = new UsuariDAO();
        int op;

        do {
            System.out.println("***************************");
            System.out.println("*   MENU GESTIO USUARIS   *");
            System.out.println("***************************");
            System.out.println("1. Afegir usuari");
            System.out.println("2. Llistar usuaris");
            System.out.println("3. Esborrar usuari");
            System.out.println("0. Sortir");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> {
                    Usuari u = new Usuari();

                    System.out.println("Nom:");
                    u.setNom(sc.nextLine());

                    System.out.println("Email:");
                    u.setEmail(sc.nextLine());

                    usuariDAO.save(u);
                    System.out.println("Usuari afegit.");
                }

                case 2 -> usuariDAO.findAll().forEach(System.out::println);

                case 3 -> {
                    System.out.println("ID usuari a eliminar:");
                    int id = sc.nextInt();
                    sc.nextLine();

                    Usuari u = usuariDAO.findById(id);
                    if (u != null) {
                        usuariDAO.delete(u);
                        System.out.println("Usuari eliminat.");
                    } else {
                        System.out.println("No existeix aquest usuari.");
                    }
                }

                case 0 -> System.out.println("Sortint del menú usuaris...");
                default -> System.out.println("Opció no vàlida.");
            }

        } while (op != 0);
    }

    // ---------------------------------------------------------
    // MENU PRESTECS
    // ---------------------------------------------------------
    public static void menuPrestecs(Scanner sc) {

        PrestecDAO prestecDAO = new PrestecDAO();
        UsuariDAO usuariDAO = new UsuariDAO();
        LlibreDAO llibreDAO = new LlibreDAO();

        int op;

        do {
            System.out.println("***************************");
            System.out.println("*   MENU GESTIO PRESTECS  *");
            System.out.println("***************************");
            System.out.println("1. Crear préstec");
            System.out.println("2. Llistar préstecs");
            System.out.println("0. Sortir");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> {
                    System.out.println("ID usuari:");
                    int idU = sc.nextInt();
                    sc.nextLine();

                    Usuari u = usuariDAO.findById(idU);
                    if (u == null) {
                        System.out.println("Usuari no trobat.");
                        break;
                    }

                    System.out.println("ID llibre:");
                    int idL = sc.nextInt();
                    sc.nextLine();

                    Llibre l = llibreDAO.buscar(idL);
                    if (l == null) {
                        System.out.println("Llibre no trobat.");
                        break;
                    }

                    Prestec p = new Prestec(u, l, LocalDate.now(), LocalDate.now().plusDays(15));
                    prestecDAO.save(p);

                    System.out.println("Préstec creat correctament.");
                }

                case 2 -> prestecDAO.findAll().forEach(System.out::println);

                case 0 -> System.out.println("Sortint del menú préstecs...");
                default -> System.out.println("Opció no vàlida.");
            }

        } while (op != 0);
    }
}

