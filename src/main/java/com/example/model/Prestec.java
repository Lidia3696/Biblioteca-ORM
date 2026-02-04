package com.example.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prestecs", schema = "biblioteca")
public class Prestec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id autoincrement
    private int id;

    @ManyToOne //relacions amb el usuari (un usuari te molts prestecs)
    @JoinColumn(name = "usuari_id")
    private Usuari usuari;

    @ManyToOne //relacions amb el llibre (un prestec te molts llibres)
    @JoinColumn(name = "llibre_id")
    private Llibre llibre;

    private LocalDate dataInici;
    private LocalDate dataFi;

    public Prestec() {}

    public Prestec(Usuari usuari, Llibre llibre, LocalDate dataInici, LocalDate dataFi) {
        this.usuari = usuari;
        this.llibre = llibre;
        this.dataInici = dataInici;
        this.dataFi = dataFi;
    }

    public int getId() {
        return id;
    }

    public Usuari getUsuari() {
        return usuari;
    }

    public Llibre getLlibre() {
        return llibre;
    }

    public LocalDate getDataInici() {
        return dataInici;
    }

    public LocalDate getDataFi() {
        return dataFi;
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }

    public void setLlibre(Llibre llibre) {
        this.llibre = llibre;
    }

    public void setDataInici(LocalDate dataInici) {
        this.dataInici = dataInici;
    }

    public void setDataFi(LocalDate dataFi) {
        this.dataFi = dataFi;
    }

    @Override
    public String toString() {
        return "| Id: " + id +
               " | Usuari: " + usuari.getNom() +
               " | Llibre: " + llibre.getTitol() +
               " | Inici: " + dataInici +
               " | Fi: " + dataFi + " |";
    }


}
