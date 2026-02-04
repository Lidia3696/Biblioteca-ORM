package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "Llibre")
@Table(name = "llibres", schema = "biblioteca")

public class Llibre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String isbn;
    private int any_publicacio, stock;
    private double preu;
    private String titol, autor, editorial;

    public Llibre () {} ;

    public Llibre (String titol, String autor, int any_publicacio, double preu, String editorial) {
        this.titol = titol;
        this.autor = autor;
        this.any_publicacio = any_publicacio;
        this.preu = preu;
        this.editorial = editorial;
    };

    @Override
    public String toString() {
        return 
          "| Id: " + id + " | Titol: " + titol + " | Autor: " + autor +  " | ISBN: " + isbn + " | Any: " + any_publicacio + " | Preu: " + preu + " | Editorial: " + editorial + " | Estoc: " + stock;
    }
    
    public int getAny() {
        return any_publicacio;
    }

    public int getId() {
        return id;
    }

    public double getPreu() {
        return preu;
    }

    public String getTitol() {
        return titol;
    }

    public String getAutor() {
        return autor;
    }

    public String getISBN() {
        return isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setAny(int any_publicacio) {
        this.any_publicacio = any_publicacio;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int s) {
        this.stock = s;
    }

   

}
