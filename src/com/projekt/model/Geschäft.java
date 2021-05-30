package com.projekt.model;

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

    public Geschäft(int geschäftNr, String geschäftName, String telefon, String email, String straße, String ort, String PLZ) {
        GeschäftNr = geschäftNr;
        GeschäftName = geschäftName;
        Telefon = telefon;
        Email = email;
        Straße = straße;
        Ort = ort;
        this.PLZ = PLZ;
    }

    public int getGeschäftNr() {
        return GeschäftNr;
    }

    public void setGeschäftNr(int geschäftNr) {
        GeschäftNr = geschäftNr;
    }

    public String getGeschäftName() {
        return GeschäftName;
    }

    public void setGeschäftName(String geschäftName) {
        GeschäftName = geschäftName;
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

    @Override
    public String toString() {
        return "Geschäft{" +
                "GeschäftNr=" + GeschäftNr +
                ", GeschäftName='" + GeschäftName + '\'' +
                ", Telefon='" + Telefon + '\'' +
                ", Email='" + Email + '\'' +
                ", Straße='" + Straße + '\'' +
                ", Ort='" + Ort + '\'' +
                ", PLZ='" + PLZ + '\'' +
                '}';
    }
}
