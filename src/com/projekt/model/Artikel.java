package com.projekt.model;

import java.math.BigDecimal;

public class Artikel {
    private int ArtikelNr;
    private String ArtikelName;
    private int HerstellerNr;
    private int KategorieNr;
    private int Modelljahr;
    private BigDecimal Listenpreis;

    public Artikel() {
    }

    public int getArtikelNr() {
        return ArtikelNr;
    }

    public void setArtikelNr(int artikelNr) {
        ArtikelNr = artikelNr;
    }

    public String getArtikelName() {
        return ArtikelName;
    }

    public void setArtikelName(String artikelName) {
        ArtikelName = artikelName;
    }

    public int getHerstellerNr() {
        return HerstellerNr;
    }

    public void setHerstellerNr(int herstellerNr) {
        HerstellerNr = herstellerNr;
    }

    public int getKategorieNr() {
        return KategorieNr;
    }

    public void setKategorieNr(int kategorieNr) {
        KategorieNr = kategorieNr;
    }

    public int getModelljahr() {
        return Modelljahr;
    }

    public void setModelljahr(int modelljahr) {
        Modelljahr = modelljahr;
    }

    public BigDecimal getListenpreis() {
        return Listenpreis;
    }

    public void setListenpreis(BigDecimal listenpreis) {
        Listenpreis = listenpreis;
    }
}
