package com.projekt.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Kunde")
public class Kunde {
    private int KundeNr;
    private String Vorname;
    private String Nachname;
    private String Telefon;
    private String Email;
    private String Straße;
    private String Ort;
    private String PLZ;

    public Kunde() {
    }

    public int getKundeNr() {
        return KundeNr;
    }

    @XmlAttribute(name = "KundeNr")
    public void setKundeNr(int kundeNr) {
        KundeNr = kundeNr;
    }

    public String getVorname() {
        return Vorname;
    }

    @XmlElement(name = "Vorname")
    public void setVorname(String vorname) {
        Vorname = vorname;
    }

    public String getNachname() {
        return Nachname;
    }

    @XmlElement(name = "Nachname")
    public void setNachname(String nachname) {
        Nachname = nachname;
    }

    public String getTelefon() {
        return Telefon;
    }

    @XmlElement(name = "Telefon")
    public void setTelefon(String telefon) {
        Telefon = telefon;
    }

    public String getEmail() {
        return Email;
    }

    @XmlElement(name = "Email")
    public void setEmail(String email) {
        Email = email;
    }

    public String getStraße() {
        return Straße;
    }

    @XmlElement(name = "Straße")
    public void setStraße(String straße) {
        Straße = straße;
    }

    public String getOrt() {
        return Ort;
    }

    @XmlElement(name = "Ort")
    public void setOrt(String ort) {
        Ort = ort;
    }

    public String getPLZ() {
        return PLZ;
    }

    @XmlElement(name = "PLZ")
    public void setPLZ(String PLZ) {
        this.PLZ = PLZ;
    }

    @Override
    public String toString() {
        return "ID = " + KundeNr + ", Vorname = " + Vorname + ", Nachname = " + Nachname;
    }
}
