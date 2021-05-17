package com.projekt.model;

public class KategorieDAO {
    private int KategorieNr;
    private String KategorieName;

    public KategorieDAO() {
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
}
