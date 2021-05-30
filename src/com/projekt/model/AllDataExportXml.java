package com.projekt.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Db_Projekt")
public class AllDataExportXml {
    private List<Artikel> artikelList;
    private List<Bestellung> bestellungList;
    private List<Bestellung_Artikel> bestellung_artikelList;
    private List<Bestände> beständeList;
    private List<Geschäft> geschäftList;
    private List<Hersteller> herstellerList;
    private List<Kategorie> kategorieList;
    private List<Kunde> kundeList;
    private List<Mitarbeiter> mitarbeiterList;


    public List<Artikel> getArtikelList() {
        return artikelList;
    }

    @XmlElementWrapper(name = "Artikels")
    @XmlElement(name = "Artikel")
    public void setArtikelList(List<Artikel> artikelList) {
        this.artikelList = artikelList;
    }

    public List<Bestellung> getBestellungList() {
        return bestellungList;
    }

    @XmlElementWrapper(name = "Bestellungs")
    @XmlElement(name = "Bestellung")
    public void setBestellungList(List<Bestellung> bestellungList) {
        this.bestellungList = bestellungList;
    }

    public List<Bestellung_Artikel> getBestellung_artikelList() {
        return bestellung_artikelList;
    }

    @XmlElementWrapper(name = "Bestellung_Artikels")
    @XmlElement(name = "Bestellung_Artikel")
    public void setBestellung_artikelList(List<Bestellung_Artikel> bestellung_artikelList) {
        this.bestellung_artikelList = bestellung_artikelList;
    }

    public List<Bestände> getBeständeList() {
        return beständeList;
    }

    @XmlElementWrapper(name = "Beständes")
    @XmlElement(name = "Bestände")
    public void setBeständeList(List<Bestände> beständeList) {
        this.beständeList = beständeList;
    }

    public List<Geschäft> getGeschäftList() {
        return geschäftList;
    }

    @XmlElementWrapper(name = "Geschäfts")
    @XmlElement(name = "Geschäft")
    public void setGeschäftList(List<Geschäft> geschäftList) {
        this.geschäftList = geschäftList;
    }

    public List<Hersteller> getHerstellerList() {
        return herstellerList;
    }

    @XmlElementWrapper(name = "Herstellers")
    @XmlElement(name = "Hersteller")
    public void setHerstellerList(List<Hersteller> herstellerList) {
        this.herstellerList = herstellerList;
    }

    public List<Kategorie> getKategorieList() {
        return kategorieList;
    }

    @XmlElementWrapper(name = "Kategories")
    @XmlElement(name = "Kategorie")
    public void setKategorieList(List<Kategorie> kategorieList) {
        this.kategorieList = kategorieList;
    }

    public List<Kunde> getKundeList() {
        return kundeList;
    }

    @XmlElementWrapper(name = "Kundes")
    @XmlElement(name = "Kunde")
    public void setKundeList(List<Kunde> kundeList) {
        this.kundeList = kundeList;
    }

    public List<Mitarbeiter> getMitarbeiterList() {
        return mitarbeiterList;
    }

    @XmlElementWrapper(name = "Mitarbeiters")
    @XmlElement(name = "Mitarbeiter")
    public void setMitarbeiterList(List<Mitarbeiter> mitarbeiterList) {
        this.mitarbeiterList = mitarbeiterList;
    }
}
