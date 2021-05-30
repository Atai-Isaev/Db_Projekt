package com.projekt.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "Bestellung")
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

    public int getBestellungNr() {
        return BestellungNr;
    }

    @XmlAttribute(name = "BestellungNr")
    public void setBestellungNr(int bestellungNr) {
        BestellungNr = bestellungNr;
    }

    public int getKundeNr() {
        return KundeNr;
    }

    @XmlElement(name = "KundeNr")
    public void setKundeNr(int kundeNr) {
        KundeNr = kundeNr;
    }

    public int getBestellstatus() {
        return Bestellstatus;
    }

    @XmlElement(name = "Bestellstatus")
    public void setBestellstatus(int bestellstatus) {
        Bestellstatus = bestellstatus;
    }

    public Date getBestelldatum() {
        return Bestelldatum;
    }

    @XmlElement(name = "Bestelldatum")
    public void setBestelldatum(Date bestelldatum) {
        Bestelldatum = bestelldatum;
    }

    public Date getBedarfsdatum() {
        return Bedarfsdatum;
    }

    @XmlElement(name = "Bedarfsdatum")
    public void setBedarfsdatum(Date bedarfsdatum) {
        Bedarfsdatum = bedarfsdatum;
    }

    public Date getVersanddatum() {
        return Versanddatum;
    }

    @XmlElement(name = "Versanddatum")
    public void setVersanddatum(Date versanddatum) {
        Versanddatum = versanddatum;
    }

    public int getGeschäftNr() {
        return GeschäftNr;
    }

    @XmlElement(name = "GeschäftNr")
    public void setGeschäftNr(int geschäftNr) {
        GeschäftNr = geschäftNr;
    }

    public int getMitarbeiterNr() {
        return MitarbeiterNr;
    }

    @XmlElement(name = "MitarbeiterNr")
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
