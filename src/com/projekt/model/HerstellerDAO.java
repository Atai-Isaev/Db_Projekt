package com.projekt.model;

public class HerstellerDAO {
    private int HerstellerNr;
    private String HerstellerName;

    public HerstellerDAO() {
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
}
