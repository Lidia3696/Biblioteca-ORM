package com.example;

public class Llibre {

    private int id, any;
    private double preu;
    private String titol, autor, ISBN, editorial;


    public int getId() {
        return id;
    }

    public int getAny() {
        return any;
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
        return ISBN;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAny(int any) {
        this.any = any;
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

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Llibre{" + "id=" + id + ", any=" + any + ", preu=" + preu + ", titol=" + titol + ", autor=" + autor + ", ISBN=" + ISBN + ", editorial=" + editorial + '}';
    }

    

}
