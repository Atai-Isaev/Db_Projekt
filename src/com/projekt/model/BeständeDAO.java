package com.projekt.model;

public class BeständeDAO {
    private int GeschäftNr;
    private int ArtikelNr;
    private int Menge;

    public BeständeDAO() {
    }

    public int getGeschäftNr() {
        return GeschäftNr;
    }

    public void setGeschäftNr(int geschäftNr) {
        GeschäftNr = geschäftNr;
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
}
