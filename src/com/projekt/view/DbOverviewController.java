package com.projekt.view;

import com.projekt.Main;
import com.projekt.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.util.Date;

public class DbOverviewController {
    private ArtikelDAO artikelDAO = new ArtikelDAO();
    private BestellungDAO bestellungDAO = new BestellungDAO();
    private Bestellung_ArtikelDAO bestellung_artikelDAO = new Bestellung_ArtikelDAO();
    private BeständeDAO beständeDAO = new BeständeDAO();
    private GeschäftDAO geschäftDAO = new GeschäftDAO();
    private HerstellerDAO herstellerDAO = new HerstellerDAO();
    private KategorieDAO kategorieDAO = new KategorieDAO();
    private KundeDAO kundeDAO = new KundeDAO();
    private MitarbeiterDAO mitarbeiterDAO = new MitarbeiterDAO();

    private ObservableList<Artikel> artikelObservableList = FXCollections.observableArrayList(artikelDAO.getArtikels());
    private ObservableList<Bestellung> bestellungObservableList = FXCollections.observableArrayList(bestellungDAO.getBestellungs());
//    private ObservableList<Bestellung_Artikel> bestellung_artikelObservableList = FXCollections.observableArrayList(bestellung_artikelDAO.getBestellungs());
//    private ObservableList<Bestände> beständeObservableList = FXCollections.observableArrayList(beständeDAO.getBestellungs());
//    private ObservableList<Geschäft> geschäftObservableList = FXCollections.observableArrayList(geschäftDAO.getBestellungs());
//    private ObservableList<Hersteller> herstellerObservableList = FXCollections.observableArrayList(herstellerDAO.getBestellungs());
//    private ObservableList<Kategorie> kategorieObservableList = FXCollections.observableArrayList(kategorieDAO.getBestellungs());
//    private ObservableList<Kunde> kundeObservableList = FXCollections.observableArrayList(kundeDAO.getBestellungs());
//    private ObservableList<Mitarbeiter> mitarbeiterObservableList = FXCollections.observableArrayList(mitarbeiterDAO.getBestellungs());

    private Main mainApp;

    @FXML
    private Tab artikelTab;
    @FXML
    private Tab bestellungTab;
    @FXML
    private Tab bestellung_artikelTab;
    @FXML
    private Tab beständeTab;
    @FXML
    private Tab geschäftTab;
    @FXML
    private Tab herstellerTab;
    @FXML
    private Tab kategorieTab;
    @FXML
    private Tab kundeTab;
    @FXML
    private Tab mitarbeiterTab;

    @FXML
    private TableView<Artikel> artikelTableView;
    @FXML
    private TableColumn<Artikel, Integer> artikelNrColumn;
    @FXML
    private TableColumn<Artikel, String> artikelNameColumn;
    @FXML
    private TableColumn<Artikel, Integer> artikelHerstellerNrColumn;
    @FXML
    private TableColumn<Artikel, Integer> artikelKategorieNrColumn;
    @FXML
    private TableColumn<Artikel, Integer> artikelModelljahrColumn;
    @FXML
    private TableColumn<Artikel, BigDecimal> artikelListenpreisColumn;

    @FXML
    private TableView<Bestellung> bestellungTableView;
    @FXML
    private TableColumn<Bestellung, Integer> bestellungNrColumn;
    @FXML
    private TableColumn<Bestellung, Integer> bestellungKundeNrColumn;
    @FXML
    private TableColumn<Bestellung, Integer> bestellstatusColumn;
    @FXML
    private TableColumn<Bestellung, Date> bestelldatumColumn;
    @FXML
    private TableColumn<Bestellung, Date> bedarfsdatumColumn;
    @FXML
    private TableColumn<Bestellung, Date> versanddatumColumn;
    @FXML
    private TableColumn<Bestellung, Integer> bestellungGeschäftNrColumn;
    @FXML
    private TableColumn<Bestellung, Integer> bestellungMitarbeiterNrColumn;

    @FXML
    private ButtonBar buttonBar;

    @FXML
    private Button newButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    private final String[] artikelPropertyName = {"ArtikelNr",
            "ArtikelName", "HerstellerNr", "KategorieNr", "Modelljahr", "Listenpreis"};

    private final String[] bestellungPropertyName = {"BestellungNr",
            "KundeNr", "Bestellstatus", "Bestelldatum", "Bedarfsdatum",
            "Versanddatum", "GeschäftNr", "MitarbeiterNr"};


    public DbOverviewController() {
    }

    @FXML
    private void initialize() {
        artikelTableView.setItems(this.artikelObservableList);
        artikelNrColumn.setCellValueFactory(new PropertyValueFactory<>(artikelPropertyName[0]));
        artikelNameColumn.setCellValueFactory(new PropertyValueFactory<>(artikelPropertyName[1]));
        artikelHerstellerNrColumn.setCellValueFactory(new PropertyValueFactory<>(artikelPropertyName[2]));
        artikelKategorieNrColumn.setCellValueFactory(new PropertyValueFactory<>(artikelPropertyName[3]));
        artikelModelljahrColumn.setCellValueFactory(new PropertyValueFactory<>(artikelPropertyName[4]));
        artikelListenpreisColumn.setCellValueFactory(new PropertyValueFactory<>(artikelPropertyName[5]));

        bestellungTableView.setItems(this.bestellungObservableList);
        bestellungNrColumn.setCellValueFactory(new PropertyValueFactory<>(bestellungPropertyName[0]));
        bestellungKundeNrColumn.setCellValueFactory(new PropertyValueFactory<>(bestellungPropertyName[1]));
        bestellstatusColumn.setCellValueFactory(new PropertyValueFactory<>(bestellungPropertyName[2]));
        bestelldatumColumn.setCellValueFactory(new PropertyValueFactory<>(bestellungPropertyName[3]));
        bedarfsdatumColumn.setCellValueFactory(new PropertyValueFactory<>(bestellungPropertyName[4]));
        versanddatumColumn.setCellValueFactory(new PropertyValueFactory<>(bestellungPropertyName[5]));
        bestellungGeschäftNrColumn.setCellValueFactory(new PropertyValueFactory<>(bestellungPropertyName[6]));
        bestellungMitarbeiterNrColumn.setCellValueFactory(new PropertyValueFactory<>(bestellungPropertyName[7]));
    }

    public void setMain(Main main) {
        this.mainApp = main;
        if (mainApp.getRole() == DbRole.READER) {
            buttonBar.getButtons().remove(newButton);
            buttonBar.getButtons().remove(editButton);
            buttonBar.getButtons().remove(deleteButton);
        }
    }

    @FXML
    private void handleUpdateAllData() {
        artikelObservableList = FXCollections.observableArrayList(artikelDAO.getArtikels());
        artikelTableView.setItems(artikelObservableList);

        bestellungObservableList = FXCollections.observableArrayList(bestellungDAO.getBestellungs());
        bestellungTableView.setItems(bestellungObservableList);
    }
}