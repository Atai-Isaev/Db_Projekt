package com.projekt.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "Bestellung_Artikel")
public class Bestellung_Artikel {
    private int BestellungNr;
    private int ArtikelNr;
    private int Menge;
    private BigDecimal Listenpreis;
    private BigDecimal Rabatt;

    public Bestellung_Artikel() {
    }

    public int getBestellungNr() {
        return BestellungNr;
    }

    @XmlAttribute(name = "BestellungNr")
    public void setBestellungNr(int bestellungNr) {
        BestellungNr = bestellungNr;
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

    public BigDecimal getListenpreis() {
        return Listenpreis;
    }

    @XmlElement(name = "Listenpreis")
    public void setListenpreis(BigDecimal listenpreis) {
        Listenpreis = listenpreis;
    }

    public BigDecimal getRabatt() {
        return Rabatt;
    }

    @XmlElement(name = "Rabatt")
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
