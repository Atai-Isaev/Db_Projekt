package com.projekt.model;

public class Hersteller {
    private int HerstellerNr;
    private String HerstellerName;

    public Hersteller() {
    }

    public Hersteller(int herstellerNr, String herstellerName) {
        HerstellerNr = herstellerNr;
        HerstellerName = herstellerName;
    }

    public int getHerstellerNr() {
        return HerstellerNr;
    }

    public void setHerstellerNr(int herstellerNr) {
        HerstellerNr = herstellerNr;
    }

    public String getHerstellerName() {
        return HerstellerName;
    }

    public void setHerstellerName(String herstellerName) {
        HerstellerName = herstellerName;
    }

    @Override
    public String toString() {
        return "Hersteller{" +
                "HerstellerNr=" + HerstellerNr +
                ", HerstellerName='" + HerstellerName + '\'' +
                '}';
    }
}
