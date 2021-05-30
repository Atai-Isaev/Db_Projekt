package com.projekt.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Hersteller")
public class Hersteller {
    private int HerstellerNr;
    private String HerstellerName;

    public Hersteller() {
    }

    public int getHerstellerNr() {
        return HerstellerNr;
    }

    @XmlAttribute(name = "HerstellerNr")
    public void setHerstellerNr(int herstellerNr) {
        HerstellerNr = herstellerNr;
    }

    public String getHerstellerName() {
        return HerstellerName;
    }

    @XmlElement(name = "HerstellerName")
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
