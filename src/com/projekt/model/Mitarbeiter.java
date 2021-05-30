package com.projekt.model;

public class Mitarbeiter {
    private int MitarbeiterNr;
    private String Vorname;
    private String Nachname;
    private String Email;
    private String Telefon;
    private int Aktiv;
    private int GeschäftNr;
    private int GeschäftsführerNr;

    public Mitarbeiter() {
    }

    public Mitarbeiter(int mitarbeiterNr, String vorname, String nachname, String email, String telefon, int aktiv, int geschäftNr, int geschäftsführerNr) {
        MitarbeiterNr = mitarbeiterNr;
        Vorname = vorname;
        Nachname = nachname;
        Email = email;
        Telefon = telefon;
        Aktiv = aktiv;
        GeschäftNr = geschäftNr;
        GeschäftsführerNr = geschäftsführerNr;
    }

    public int getMitarbeiterNr() {
        return MitarbeiterNr;
    }

    public void setMitarbeiterNr(int mitarbeiterNr) {
        MitarbeiterNr = mitarbeiterNr;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
    }

    public int getAktiv() {
        return Aktiv;
    }

    public void setAktiv(int aktiv) {
        Aktiv = aktiv;
    }

    public int getGeschäftNr() {
        return GeschäftNr;
    }

    public void setGeschäftNr(int geschäftNr) {
        GeschäftNr = geschäftNr;
    }

    public int getGeschäftsführerNr() {
        return GeschäftsführerNr;
    }

    public void setGeschäftsführerNr(int geschäftsführerNr) {
        GeschäftsführerNr = geschäftsführerNr;
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
                ", GeschäftsführerNr=" + GeschäftsführerNr +
                '}';
    }
}
