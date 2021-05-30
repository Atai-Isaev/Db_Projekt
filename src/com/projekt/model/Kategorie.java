package com.projekt.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Kategorie")
public class Kategorie {
    private int KategorieNr;
    private String KategorieName;

    public Kategorie() {
    }

    public int getKategorieNr() {
        return KategorieNr;
    }

    @XmlAttribute(name = "KategorieNr")
    public void setKategorieNr(int kategorieNr) {
        KategorieNr = kategorieNr;
    }

    public String getKategorieName() {
        return KategorieName;
    }

    @XmlElement(name = "KategorieName")
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
