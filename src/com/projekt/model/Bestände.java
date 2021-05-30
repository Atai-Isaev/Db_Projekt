package com.projekt.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Bestände")
public class Bestände {
    private int GeschäftNr;
    private int ArtikelNr;
    private int Menge;

    public Bestände() {
    }

    public int getGeschäftNr() {
        return GeschäftNr;
    }

    @XmlAttribute(name = "GeschäftNr")
    public void setGeschäftNr(int geschäftNr) {
        GeschäftNr = geschäftNr;
    }

    public int getArtikelNr() {
        return ArtikelNr;
    }

    @XmlAttribute(name = "ArtikelNr")
    public void setArtikelNr(int artikelNr) {
        ArtikelNr = artikelNr;
    }

    public int getMenge() {
        return Menge;
    }

    @XmlElement(name = "Menge")
    public void setMenge(int menge) {
        Menge = menge;
    }

    @Override
    public String toString() {
        return "Bestände{" +
                "GeschäftNr=" + GeschäftNr +
                ", ArtikelNr=" + ArtikelNr +
                ", Menge=" + Menge +
                '}';
    }
}
