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
    private Hersteller hersteller;
    private Kategorie kategorie;

    public Artikel() {
    }

    public Artikel(int artikelNr, String artikelName, int modelljahr, BigDecimal listenpreis, Hersteller hersteller, Kategorie kategorie) {
        ArtikelNr = artikelNr;
        ArtikelName = artikelName;
        Modelljahr = modelljahr;
        Listenpreis = listenpreis;
        this.hersteller = hersteller;
        this.kategorie = kategorie;
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

    public void setHerstellerNr(int herstellerNr) {
        HerstellerNr = herstellerNr;
    }

    public int getKategorieNr() {
        return KategorieNr;
    }

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

    public Hersteller getHersteller() {
        return hersteller;
    }

    @XmlElement(name = "Hersteller")
    public void setHersteller(Hersteller hersteller) {
        this.hersteller = hersteller;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    @XmlElement(name = "Kategorie")
    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }
}
