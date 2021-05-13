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
}
