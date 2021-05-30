package com.projekt.model;

import java.util.Date;

public class Bestellung {
    private int BestellungNr;
    private int KundeNr;
    private int Bestellstatus;
    private Date Bestelldatum;
    private Date Bedarfsdatum;
    private Date Versanddatum;
    private int GeschäftNr;
    private int MitarbeiterNr;

    public Bestellung() {
    }

    public Bestellung(int bestellungNr, int kundeNr, int bestellstatus, Date bestelldatum, Date bedarfsdatum, Date versanddatum, int geschäftNr, int mitarbeiterNr) {
        BestellungNr = bestellungNr;
        KundeNr = kundeNr;
        Bestellstatus = bestellstatus;
        Bestelldatum = bestelldatum;
        Bedarfsdatum = bedarfsdatum;
        Versanddatum = versanddatum;
        GeschäftNr = geschäftNr;
        MitarbeiterNr = mitarbeiterNr;
    }

    public int getBestellungNr() {
        return BestellungNr;
    }

    public void setBestellungNr(int bestellungNr) {
        BestellungNr = bestellungNr;
    }

    public int getKundeNr() {
        return KundeNr;
    }

    public void setKundeNr(int kundeNr) {
        KundeNr = kundeNr;
    }

    public int getBestellstatus() {
        return Bestellstatus;
    }

    public void setBestellstatus(int bestellstatus) {
        Bestellstatus = bestellstatus;
    }

    public Date getBestelldatum() {
        return Bestelldatum;
    }

    public void setBestelldatum(Date bestelldatum) {
        Bestelldatum = bestelldatum;
    }

    public Date getBedarfsdatum() {
        return Bedarfsdatum;
    }

    public void setBedarfsdatum(Date bedarfsdatum) {
        Bedarfsdatum = bedarfsdatum;
    }

    public Date getVersanddatum() {
        return Versanddatum;
    }

    public void setVersanddatum(Date versanddatum) {
        Versanddatum = versanddatum;
    }

    public int getGeschäftNr() {
        return GeschäftNr;
    }

    public void setGeschäftNr(int geschäftNr) {
        GeschäftNr = geschäftNr;
    }

    public int getMitarbeiterNr() {
        return MitarbeiterNr;
    }

    public void setMitarbeiterNr(int mitarbeiterNr) {
        MitarbeiterNr = mitarbeiterNr;
    }

    @Override
    public String toString() {
        return "Bestellung{" +
                "BestellungNr=" + BestellungNr +
                ", KundeNr=" + KundeNr +
                ", Bestellstatus=" + Bestellstatus +
                ", Bestelldatum=" + Bestelldatum +
                ", Bedarfsdatum=" + Bedarfsdatum +
                ", Versanddatum=" + Versanddatum +
                ", GeschäftNr=" + GeschäftNr +
                ", MitarbeiterNr=" + MitarbeiterNr +
                '}';
    }
}
