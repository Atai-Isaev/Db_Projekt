package com.projekt.model;

public class Kategorie {
    private int KategorieNr;
    private String KategorieName;

    public Kategorie() {
    }

    public Kategorie(int kategorieNr, String kategorieName) {
        KategorieNr = kategorieNr;
        KategorieName = kategorieName;
    }

    public int getKategorieNr() {
        return KategorieNr;
    }

    public void setKategorieNr(int kategorieNr) {
        KategorieNr = kategorieNr;
    }

    public String getKategorieName() {
        return KategorieName;
    }

    public void setKategorieName(String kategorieName) {
        KategorieName = kategorieName;
    }

    @Override
    public String toString() {
        return "Kategorie{" +
                "KategorieNr=" + KategorieNr +
                ", KategorieName='" + KategorieName + '\'' +
                '}';
    }
}
