package com.projekt.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Mitarbeiter")
public class Mitarbeiter {
    private int MitarbeiterNr;
    private String Vorname;
    private String Nachname;
    private String Email;
    private String Telefon;
    private int Aktiv;
    private int GeschäftNr;

    public Mitarbeiter() {
    }

    public int getMitarbeiterNr() {
        return MitarbeiterNr;
    }

    @XmlAttribute(name = "MitarbeiterNr")
    public void setMitarbeiterNr(int mitarbeiterNr) {
        MitarbeiterNr = mitarbeiterNr;
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

    public String getEmail() {
        return Email;
    }

    @XmlElement(name = "Email")
    public void setEmail(String email) {
        Email = email;
    }

    public String getTelefon() {
        return Telefon;
    }

    @XmlElement(name = "Telefon")
    public void setTelefon(String telefon) {
        Telefon = telefon;
    }

    public int getAktiv() {
        return Aktiv;
    }

    @XmlElement(name = "Aktiv")
    public void setAktiv(int aktiv) {
        Aktiv = aktiv;
    }

    public int getGeschäftNr() {
        return GeschäftNr;
    }

    @XmlElement(name = "GeschäftNr")
    public void setGeschäftNr(int geschäftNr) {
        GeschäftNr = geschäftNr;
    }

    @Override
    public String toString() {
        return "Mitarbeiter{" +
                "MitarbeiterNr=" + MitarbeiterNr +
                ", Vorname='" + Vorname + '\'' +
                ", Nachname='" + Nachname + '\'' +
                ", Email='" + Email + '\'' +
                ", Telefon='" + Telefon + '\'' +
                ", Aktiv=" + Aktiv +
                ", GeschäftNr=" + GeschäftNr +
                '}';
    }
}
