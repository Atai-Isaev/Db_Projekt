package com.projekt.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "Artikel")
public class Artikel {
    private int ArtikelNr;
    private String ArtikelName;
    private int HerstellerNr;
    private int KategorieNr;
    private int Modelljahr;
    private BigDecimal Listenpreis;

    public Artikel() {
    }

    public int getArtikelNr() {
        return ArtikelNr;
    }

    @XmlAttribute(name = "ArtikelNr")
    public void setArtikelNr(int artikelNr) {
        ArtikelNr = artikelNr;
    }

    public String getArtikelName() {
        return ArtikelName;
    }

    @XmlElement(name = "ArtikelName")
    public void setArtikelName(String artikelName) {
        ArtikelName = artikelName;
    }

    public int getHerstellerNr() {
        return HerstellerNr;
    }

    @XmlElement(name = "HerstellerNr")
    public void setHerstellerNr(int herstellerNr) {
        HerstellerNr = herstellerNr;
    }

    public int getKategorieNr() {
        return KategorieNr;
    }

    @XmlElement(name = "KategorieNr")
    public void setKategorieNr(int kategorieNr) {
        KategorieNr = kategorieNr;
    }

    public int getModelljahr() {
        return Modelljahr;
    }

    @XmlElement(name = "Modelljahr")
    public void setModelljahr(int modelljahr) {
        Modelljahr = modelljahr;
    }

    public BigDecimal getListenpreis() {
        return Listenpreis;
    }

    @XmlElement(name = "Listenpreis")
    public void setListenpreis(BigDecimal listenpreis) {
        Listenpreis = listenpreis;
    }
}
