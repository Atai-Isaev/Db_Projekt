package com.projekt.model;

import java.math.BigDecimal;

public class Bestellung_ArtikelDAO {
    private int BestellungNr;
    private int ArtikelNr;
    private int Menge;
    private BigDecimal Listenpreis;
    private BigDecimal Rabatt;

    public Bestellung_ArtikelDAO() {
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
}
