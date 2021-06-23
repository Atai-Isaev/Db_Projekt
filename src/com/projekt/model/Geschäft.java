package com.projekt.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Geschäft")
public class Geschäft {
    private int GeschäftNr;
    private String GeschäftName;
    private String Telefon;
    private String Email;
    private String Straße;
    private String Ort;
    private String PLZ;

    public Geschäft() {
    }

    public int getGeschäftNr() {
        return GeschäftNr;
    }

    @XmlAttribute(name = "GeschäftNr")
    public void setGeschäftNr(int geschäftNr) {
        GeschäftNr = geschäftNr;
    }

    public String getGeschäftName() {
        return GeschäftName;
    }

    @XmlElement(name = "GeschäftName")
    public void setGeschäftName(String geschäftName) {
        GeschäftName = geschäftName;
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
        return "ID = " + GeschäftNr +
                ", " + GeschäftName;
    }
}
