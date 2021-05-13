package com.projekt.model;

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

    public void setKundeNr(int kundeNr) {
        KundeNr = kundeNr;
    }

    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String vorname) {
        Vorname = vorname;
    }

    public String getNachname() {
        return Nachname;
    }

    public void setNachname(String nachname) {
        Nachname = nachname;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getStraße() {
        return Straße;
    }

    public void setStraße(String straße) {
        Straße = straße;
    }

    public String getOrt() {
        return Ort;
    }

    public void setOrt(String ort) {
        Ort = ort;
    }

    public String getPLZ() {
        return PLZ;
    }

    public void setPLZ(String PLZ) {
        this.PLZ = PLZ;
    }
}
