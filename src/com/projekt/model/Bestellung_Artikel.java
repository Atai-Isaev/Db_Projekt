package com.projekt.model;

import java.math.BigDecimal;

public class Bestellung_Artikel {
    private int BestellungNr;
    private int ArtikelNr;
    private int Menge;
    private BigDecimal Listenpreis;
    private BigDecimal Rabatt;

    public Bestellung_Artikel() {
    }

    public Bestellung_Artikel(int bestellungNr, int artikelNr, int menge, BigDecimal listenpreis, BigDecimal rabatt) {
        BestellungNr = bestellungNr;
        ArtikelNr = artikelNr;
        Menge = menge;
        Listenpreis = listenpreis;
        Rabatt = rabatt;
    }

    public int getBestellungNr() {
        return BestellungNr;
    }

    public void setBestellungNr(int bestellungNr) {
        BestellungNr = bestellungNr;
    }

    public int getArtikelNr() {
        return ArtikelNr;
    }

    public void setArtikelNr(int artikelNr) {
        ArtikelNr = artikelNr;
    }

    public int getMenge() {
        return Menge;
    }

    public void setMenge(int menge) {
        Menge = menge;
    }

    public BigDecimal getListenpreis() {
        return Listenpreis;
    }

    public void setListenpreis(BigDecimal listenpreis) {
        Listenpreis = listenpreis;
    }

    public BigDecimal getRabatt() {
        return Rabatt;
    }

    public void setRabatt(BigDecimal rabatt) {
        Rabatt = rabatt;
    }

    @Override
    public String toString() {
        return "Bestellung_Artikel{" +
                "BestellungNr=" + BestellungNr +
                ", ArtikelNr=" + ArtikelNr +
                ", Menge=" + Menge +
                ", Listenpreis=" + Listenpreis +
                ", Rabatt=" + Rabatt +
                '}';
    }
}
