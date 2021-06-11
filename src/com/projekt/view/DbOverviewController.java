package com.projekt.view;

import com.projekt.Main;
import com.projekt.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
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
    private ObservableList<Bestellung_Artikel> bestellung_artikelObservableList = FXCollections.observableArrayList(bestellung_artikelDAO.getBestellung_Artikels());
    private ObservableList<Bestände> beständeObservableList = FXCollections.observableArrayList(beständeDAO.getBeständes());
    private ObservableList<Geschäft> geschäftObservableList = FXCollections.observableArrayList(geschäftDAO.getGeschäfts());
    private ObservableList<Hersteller> herstellerObservableList = FXCollections.observableArrayList(herstellerDAO.getHerstellers());
    private ObservableList<Kategorie> kategorieObservableList = FXCollections.observableArrayList(kategorieDAO.getKategories());
    private ObservableList<Kunde> kundeObservableList = FXCollections.observableArrayList(kundeDAO.getKundes());
    private ObservableList<Mitarbeiter> mitarbeiterObservableList = FXCollections.observableArrayList(mitarbeiterDAO.getMitarbeiters());

    private Main main;

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
    private TableView<Bestellung_Artikel> bestellung_ArtikelTableView;
    @FXML
    private TableColumn<Bestellung_Artikel, Integer> bestellung_ArtikelBestellungNrColumn;
    @FXML
    private TableColumn<Bestellung_Artikel, Integer> bestellung_ArtikelArtikelNrColumn;
    @FXML
    private TableColumn<Bestellung_Artikel, Integer> bestellung_ArtikelMengeColumn;
    @FXML
    private TableColumn<Bestellung_Artikel, BigDecimal> bestellung_ArtikelListenpreisColumn;
    @FXML
    private TableColumn<Bestellung_Artikel, BigDecimal> bestellung_ArtikelRabattColumn;


    @FXML
    private TableView<Bestände> beständeTableView;
    @FXML
    private TableColumn<Bestände, Integer> beständeGeschäftNrColumn;
    @FXML
    private TableColumn<Bestände, Integer> beständeArtikelNrColumn;
    @FXML
    private TableColumn<Bestände, Integer> beständeMengeColumn;

    @FXML
    private TableView<Geschäft> geschäftTableView;
    @FXML
    private TableColumn<Geschäft, Integer> geschäftNrColumn;
    @FXML
    private TableColumn<Geschäft, String> geschäftNameColumn;
    @FXML
    private TableColumn<Geschäft, String> geschäftTelefonColumn;
    @FXML
    private TableColumn<Geschäft, String> geschäftEmailColumn;
    @FXML
    private TableColumn<Geschäft, String> geschäftStraßeColumn;
    @FXML
    private TableColumn<Geschäft, String> geschäftOrtColumn;
    @FXML
    private TableColumn<Geschäft, String> geschäftPLZColumn;

    @FXML
    private TableView<Hersteller> herstellerTableView;
    @FXML
    private TableColumn<Hersteller, Integer> herstellerNrColumn;
    @FXML
    private TableColumn<Hersteller, String> herstellerNameColumn;

    @FXML
    private TableView<Kategorie> kategorieTableView;
    @FXML
    private TableColumn<Kategorie, Integer> kategorieNrColumn;
    @FXML
    private TableColumn<Kategorie, String> kategorieNameColumn;

    @FXML
    private TableView<Kunde> kundeTableView;
    @FXML
    private TableColumn<Kunde, Integer> kundeNrColumn;
    @FXML
    private TableColumn<Kunde, String> kundeVornameColumn;
    @FXML
    private TableColumn<Kunde, String> kundeNachnameColumn;
    @FXML
    private TableColumn<Kunde, String> kundeTelefonColumn;
    @FXML
    private TableColumn<Kunde, String> kundeEmailColumn;
    @FXML
    private TableColumn<Kunde, String> kundeStraßeColumn;
    @FXML
    private TableColumn<Kunde, String> kundeOrtColumn;
    @FXML
    private TableColumn<Kunde, String> kundePLZColumn;

    @FXML
    private TableView<Mitarbeiter> mitarbeiterTableView;
    @FXML
    private TableColumn<Mitarbeiter, Integer> mitarbeiterNrColumn;
    @FXML
    private TableColumn<Mitarbeiter, String> mitarbeiterVornameColumn;
    @FXML
    private TableColumn<Mitarbeiter, String> mitarbeiterNachnameColumn;
    @FXML
    private TableColumn<Mitarbeiter, String> mitarbeiterEmailColumn;
    @FXML
    private TableColumn<Mitarbeiter, String> mitarbeiterTelefonColumn;
    @FXML
    private TableColumn<Mitarbeiter, Integer> mitarbeiterAktivColumn;
    @FXML
    private TableColumn<Mitarbeiter, Integer> mitarbeiterGeschäftNrColumn;

    @FXML
    private ButtonBar buttonBar;

    @FXML
    private Button exportButton;

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

    private final String[] bestellung_ArtikelPropertyName = {"BestellungNr",
            "ArtikelNr", "Menge", "Listenpreis", "Rabatt"};

    private final String[] beständePropertyName = {"GeschäftNr",
            "ArtikelNr", "Menge"};

    private final String[] geschäftPropertyName = {"GeschäftNr",
            "GeschäftName", "Telefon", "Email", "Straße",
            "Ort", "PLZ"};

    private final String[] herstellerPropertyName = {"HerstellerNr",
            "HerstellerName"};

    private final String[] kategoriePropertyName = {"KategorieNr",
            "KategorieName"};

    private final String[] kundePropertyName = {"KundeNr",
            "Vorname", "Nachname", "Telefon", "Email", "Straße",
            "Ort", "PLZ"};

    private final String[] mitarbeiterPropertyName = {"MitarbeiterNr",
            "Vorname", "Nachname", "Email", "Telefon",
            "Aktiv", "GeschäftNr"};

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

        bestellung_ArtikelTableView.setItems(this.bestellung_artikelObservableList);
        bestellung_ArtikelBestellungNrColumn.setCellValueFactory(new PropertyValueFactory<>(bestellung_ArtikelPropertyName[0]));
        bestellung_ArtikelArtikelNrColumn.setCellValueFactory(new PropertyValueFactory<>(bestellung_ArtikelPropertyName[1]));
        bestellung_ArtikelMengeColumn.setCellValueFactory(new PropertyValueFactory<>(bestellung_ArtikelPropertyName[2]));
        bestellung_ArtikelListenpreisColumn.setCellValueFactory(new PropertyValueFactory<>(bestellung_ArtikelPropertyName[3]));
        bestellung_ArtikelRabattColumn.setCellValueFactory(new PropertyValueFactory<>(bestellung_ArtikelPropertyName[4]));

        beständeTableView.setItems(this.beständeObservableList);
        beständeGeschäftNrColumn.setCellValueFactory(new PropertyValueFactory<>(beständePropertyName[0]));
        beständeArtikelNrColumn.setCellValueFactory(new PropertyValueFactory<>(beständePropertyName[1]));
        beständeMengeColumn.setCellValueFactory(new PropertyValueFactory<>(beständePropertyName[2]));

        geschäftTableView.setItems(this.geschäftObservableList);
        geschäftNrColumn.setCellValueFactory(new PropertyValueFactory<>(geschäftPropertyName[0]));
        geschäftNameColumn.setCellValueFactory(new PropertyValueFactory<>(geschäftPropertyName[1]));
        geschäftTelefonColumn.setCellValueFactory(new PropertyValueFactory<>(geschäftPropertyName[2]));
        geschäftEmailColumn.setCellValueFactory(new PropertyValueFactory<>(geschäftPropertyName[3]));
        geschäftStraßeColumn.setCellValueFactory(new PropertyValueFactory<>(geschäftPropertyName[4]));
        geschäftOrtColumn.setCellValueFactory(new PropertyValueFactory<>(geschäftPropertyName[5]));
        geschäftPLZColumn.setCellValueFactory(new PropertyValueFactory<>(geschäftPropertyName[6]));

        herstellerTableView.setItems(this.herstellerObservableList);
        herstellerNrColumn.setCellValueFactory(new PropertyValueFactory<>(herstellerPropertyName[0]));
        herstellerNameColumn.setCellValueFactory(new PropertyValueFactory<>(herstellerPropertyName[1]));

        kategorieTableView.setItems(this.kategorieObservableList);
        kategorieNrColumn.setCellValueFactory(new PropertyValueFactory<>(kategoriePropertyName[0]));
        kategorieNameColumn.setCellValueFactory(new PropertyValueFactory<>(kategoriePropertyName[1]));

        kundeTableView.setItems(this.kundeObservableList);
        kundeNrColumn.setCellValueFactory(new PropertyValueFactory<>(kundePropertyName[0]));
        kundeVornameColumn.setCellValueFactory(new PropertyValueFactory<>(kundePropertyName[1]));
        kundeNachnameColumn.setCellValueFactory(new PropertyValueFactory<>(kundePropertyName[2]));
        kundeTelefonColumn.setCellValueFactory(new PropertyValueFactory<>(kundePropertyName[3]));
        kundeEmailColumn.setCellValueFactory(new PropertyValueFactory<>(kundePropertyName[4]));
        kundeStraßeColumn.setCellValueFactory(new PropertyValueFactory<>(kundePropertyName[5]));
        kundeOrtColumn.setCellValueFactory(new PropertyValueFactory<>(kundePropertyName[6]));
        kundePLZColumn.setCellValueFactory(new PropertyValueFactory<>(kundePropertyName[7]));

        mitarbeiterTableView.setItems(this.mitarbeiterObservableList);
        mitarbeiterNrColumn.setCellValueFactory(new PropertyValueFactory<>(mitarbeiterPropertyName[0]));
        mitarbeiterVornameColumn.setCellValueFactory(new PropertyValueFactory<>(mitarbeiterPropertyName[1]));
        mitarbeiterNachnameColumn.setCellValueFactory(new PropertyValueFactory<>(mitarbeiterPropertyName[2]));
        mitarbeiterEmailColumn.setCellValueFactory(new PropertyValueFactory<>(mitarbeiterPropertyName[3]));
        mitarbeiterTelefonColumn.setCellValueFactory(new PropertyValueFactory<>(mitarbeiterPropertyName[4]));
        mitarbeiterAktivColumn.setCellValueFactory(new PropertyValueFactory<>(mitarbeiterPropertyName[5]));
        mitarbeiterGeschäftNrColumn.setCellValueFactory(new PropertyValueFactory<>(mitarbeiterPropertyName[6]));

    }

    // TODO: 30.05.2021 handleAdminAccounts for Admin

    @FXML
    private void handleUpdateAllData() {
        artikelObservableList.setAll(artikelDAO.getArtikels());
        bestellungObservableList.setAll(bestellungDAO.getBestellungs());
        bestellung_artikelObservableList.setAll(bestellung_artikelDAO.getBestellung_Artikels());
        beständeObservableList.setAll(beständeDAO.getBeständes());
        geschäftObservableList.setAll(geschäftDAO.getGeschäfts());
        herstellerObservableList.setAll(herstellerDAO.getHerstellers());
        kategorieObservableList.setAll(kategorieDAO.getKategories());
        kundeObservableList.setAll(kundeDAO.getKundes());
        mitarbeiterObservableList.setAll(mitarbeiterDAO.getMitarbeiters());
    }

    @FXML
    private void handleExportData() {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select a folder");
            File selectedDirectory = directoryChooser.showDialog(exportButton.getScene().getWindow());

            if(selectedDirectory != null){
                JAXBContext context = JAXBContext.newInstance(AllDataExportXml.class);

                Marshaller marshaller = context.createMarshaller();

                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                AllDataExportXml allDataExportXml = new AllDataExportXml();

                allDataExportXml.setArtikelList(artikelObservableList);
                allDataExportXml.setBestellungList(bestellungObservableList);
                allDataExportXml.setBestellung_artikelList(bestellung_artikelObservableList);
                allDataExportXml.setBeständeList(beständeObservableList);
                allDataExportXml.setGeschäftList(geschäftObservableList);
                allDataExportXml.setHerstellerList(herstellerObservableList);
                allDataExportXml.setKategorieList(kategorieObservableList);
                allDataExportXml.setKundeList(kundeObservableList);
                allDataExportXml.setMitarbeiterList(mitarbeiterObservableList);

                marshaller.marshal(allDataExportXml, new File(selectedDirectory.getAbsolutePath() + "/" +"Db_Projekt.xml"));

                marshaller.marshal(allDataExportXml, System.out);
            }
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteButton() throws SQLException {
        if (artikelTab.isSelected()) {
            Artikel selectedArtikel = artikelTableView.getSelectionModel().getSelectedItem();
            if (selectedArtikel != null) {
                ArtikelDAO artikelDAO = new ArtikelDAO();
                artikelDAO.deleteArtikel(selectedArtikel, main.getUsername(), main.getPassword());
                artikelObservableList.setAll(artikelDAO.getArtikels());
                handleUpdateAllData();
            } else {
                alertNoSelection("Artikel");
            }
        }
        else if (bestellungTab.isSelected()) {
            Bestellung bestellung = bestellungTableView.getSelectionModel().getSelectedItem();
            if (bestellung != null) {
                BestellungDAO bestellungDAO = new BestellungDAO();
                bestellungDAO.deleteBestellung(bestellung, main.getUsername(), main.getPassword());
                bestellungObservableList.setAll(bestellungDAO.getBestellungs());
                handleUpdateAllData();
            } else {
                alertNoSelection("Bestellung");
            }
        }
        else if (bestellung_artikelTab.isSelected()) {
            Bestellung_Artikel bestellung_artikel = bestellung_ArtikelTableView.getSelectionModel().getSelectedItem();
            if (bestellung_artikel != null) {
                Bestellung_ArtikelDAO bestellung_artikelDAO = new Bestellung_ArtikelDAO();
                bestellung_artikelDAO.deleteBestellungArtikel(bestellung_artikel, main.getUsername(), main.getPassword());
                bestellung_artikelObservableList.setAll(bestellung_artikelDAO.getBestellung_Artikels());
                handleUpdateAllData();
            } else {
                alertNoSelection("Bestellung_Artikel");
            }
        }
        else if (beständeTab.isSelected()) {
            Bestände bestände = beständeTableView.getSelectionModel().getSelectedItem();
            if (bestände != null) {
                BeständeDAO beständeDAO = new BeständeDAO();
                beständeDAO.deleteBestände(bestände, main.getUsername(), main.getPassword());
                beständeObservableList.setAll(beständeDAO.getBeständes());
                handleUpdateAllData();
            } else {
                alertNoSelection("Bestände");
            }
        }
        else if (geschäftTab.isSelected()) {
            Geschäft geschäft = geschäftTableView.getSelectionModel().getSelectedItem();
            if (geschäft != null) {
                GeschäftDAO geschäftDAO = new GeschäftDAO();
                geschäftDAO.deleteGeschäft(geschäft, main.getUsername(), main.getPassword());
                geschäftObservableList.setAll(geschäftDAO.getGeschäfts());
                handleUpdateAllData();
            } else {
                alertNoSelection("Geschäft");
            }
        }
        else if (herstellerTab.isSelected()) {
            Hersteller hersteller = herstellerTableView.getSelectionModel().getSelectedItem();
            if (hersteller != null) {
                HerstellerDAO herstellerDAO = new HerstellerDAO();
                herstellerDAO.deleteHersteller(hersteller, main.getUsername(), main.getPassword());
                herstellerObservableList.setAll(herstellerDAO.getHerstellers());
                handleUpdateAllData();
            } else {
                alertNoSelection("Hersteller");
            }
        }
        else if (kategorieTab.isSelected()) {
            Kategorie kategorie = kategorieTableView.getSelectionModel().getSelectedItem();
            if (kategorie != null) {
                KategorieDAO kategorieDAO = new KategorieDAO();
                kategorieDAO.deleteKategorie(kategorie, main.getUsername(), main.getPassword());
                kategorieObservableList.setAll(kategorieDAO.getKategories());
                handleUpdateAllData();
            } else {
                alertNoSelection("Kategorie");
            }
        }
        else if (kundeTab.isSelected()) {
            Kunde kunde = kundeTableView.getSelectionModel().getSelectedItem();
            if (kunde != null) {
                KundeDAO kundeDAO = new KundeDAO();
                kundeDAO.deleteKunde(kunde, main.getUsername(), main.getPassword());
                kundeObservableList.setAll(kundeDAO.getKundes());
                handleUpdateAllData();
            } else {
                alertNoSelection("Kunde");
            }
        }
        else if (mitarbeiterTab.isSelected()) {
            Mitarbeiter mitarbeiter = mitarbeiterTableView.getSelectionModel().getSelectedItem();
            if (mitarbeiter != null) {
                MitarbeiterDAO mitarbeiterDAO = new MitarbeiterDAO();
                mitarbeiterDAO.deleteMitarbeiter(mitarbeiter, main.getUsername(), main.getPassword());
                mitarbeiterObservableList.setAll(mitarbeiterDAO.getMitarbeiters());
                handleUpdateAllData();
            } else {
                alertNoSelection("Mitarbeiter");
            }
        }
    }

    @FXML
    private void handleNewButton() throws IOException {
        // TODO: 30.05.2021 Update each Table after new
        if (artikelTab.isSelected()) {
            handleNewArtikel();
            artikelObservableList.setAll(artikelDAO.getArtikels());
        }
        else if (beständeTab.isSelected()) {
            handleNewBestände();
            beständeObservableList.setAll(beständeDAO.getBeständes());
        }
        // TODO: 29.05.2021 Rest if for each tab implement
    }

    private void handleNewArtikel() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/ArtikelCreateDialog.fxml"));
        AnchorPane page = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Artikel erstellen");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(page));

        ArtikelCreateDialogController controller = loader.getController();
        controller.setDbOverviewController(this);
        controller.setMain(this.main);
        controller.setStage(dialogStage);
        dialogStage.showAndWait();
    }

    public void handleNewBestellung() {
        // TODO: 29.05.2021 new Bestellung
    }

    public void handleNewBestellungArtikel() {
        // TODO: 29.05.2021 new BA
    }

    public void handleNewBestände() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/BestaendeCreateDialog.fxml"));
        AnchorPane page = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Bestände erstellen");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(page));

        BestaendeCreateDialogController controller = loader.getController();
        controller.setDbOverviewControllerNew(this);
        controller.setMain(this.main);
        controller.setStage(dialogStage);
        dialogStage.showAndWait();
    }

    public void handleNewGeschäft() {
        // TODO: 29.05.2021 new Geshaeft
    }

    public void handleNewHersteller() {
        // TODO: 29.05.2021 new Hersteller
    }

    public void handleNewKategorie() {
        // TODO: 29.05.2021 new Kategorie
    }

    public void handleNewKunde() {
        // TODO: 29.05.2021 new Kunde
    }

    public void handleNewMitarbeiter() {
        // TODO: 29.05.2021 new Mitarbeiter
    }

    @FXML
    private void handleEditButton() throws IOException {
        // TODO: 30.05.2021 Update each Table after edit
        if (artikelTab.isSelected()) {
            handleEditArtikel();
            artikelObservableList.setAll(artikelDAO.getArtikels());
        }
        else if (beständeTab.isSelected()) {
            handleEditBestände();
            beständeObservableList.setAll(beständeDAO.getBeständes());
        }
        // TODO: 28.05.2021 Rest Handle edit button impl
    }

    private void handleEditArtikel() throws IOException {
        Artikel selectedArtikel = artikelTableView.getSelectionModel().getSelectedItem();

        if (selectedArtikel != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ArtikelCreateDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Artikel editieren");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(page));

            ArtikelCreateDialogController controller = loader.getController();
            controller.setDbOverviewController(this);
            controller.setMain(this.main);
            controller.setTempArtikel(selectedArtikel);

            controller.setStage(dialogStage);
            dialogStage.showAndWait();
        } else {
            alertNoSelection("Artikel");
        }
    }

    private void alertNoSelection(String type) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("No " + type + " Selected");
        alert.setContentText("Please select a " + type + " in the table.");
        alert.showAndWait();
    }

    public void handleEditBestellung() {
        // TODO: 29.05.2021 edit Bestellung
    }

    public void handleEditBestellungArtikel() {
        // TODO: 29.05.2021 edit BA
    }

    public void handleEditBestände() throws IOException {
        Bestände selectedBestände = beständeTableView.getSelectionModel().getSelectedItem();

        if (selectedBestände != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/BestaendeCreateDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Bestände editieren");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(page));

            BestaendeCreateDialogController controller = loader.getController();
            controller.setDbOverviewControllerEdit(this, selectedBestände.getGeschäftNr(), selectedBestände.getArtikelNr());
            controller.setMain(this.main);
            controller.setTempArtikel(selectedBestände);

            controller.setStage(dialogStage);
            dialogStage.showAndWait();
        } else {
            alertNoSelection("Bestände");
        }
    }

    public void handleEditGeschäft() {
        // TODO: 29.05.2021 edit Geshaeft
    }

    public void handleEditHersteller() {
        // TODO: 29.05.2021 edit Hersteller
    }

    public void handleEditKategorie() {
        // TODO: 29.05.2021 edit Kategorie
    }

    public void handleEditKunde() {
        // TODO: 29.05.2021 edit Kunde
    }

    public void handleEditMitarbeiter() {
        // TODO: 29.05.2021 edit Mitarbeiter
    }

    public void setMain(Main main) {
        this.main = main;
        if (this.main.getRole() == DbRole.READER) {
            buttonBar.getButtons().remove(exportButton);
            buttonBar.getButtons().remove(newButton);
            buttonBar.getButtons().remove(editButton);
            buttonBar.getButtons().remove(deleteButton);
        }
    }

    @FXML
    private void handleBackButton() {
        main.start(main.getPrimaryStage());
    }

    public ObservableList<Artikel> getArtikelObservableList() {
        return artikelObservableList;
    }

    public ObservableList<Bestellung> getBestellungObservableList() {
        return bestellungObservableList;
    }

    public ObservableList<Bestellung_Artikel> getBestellung_artikelObservableList() {
        return bestellung_artikelObservableList;
    }

    public ObservableList<Bestände> getBeständeObservableList() {
        return beständeObservableList;
    }

    public ObservableList<Geschäft> getGeschäftObservableList() {
        return geschäftObservableList;
    }

    public ObservableList<Hersteller> getHerstellerObservableList() {
        return herstellerObservableList;
    }

    public ObservableList<Kategorie> getKategorieObservableList() {
        return kategorieObservableList;
    }

    public ObservableList<Kunde> getKundeObservableList() {
        return kundeObservableList;
    }

    public ObservableList<Mitarbeiter> getMitarbeiterObservableList() {
        return mitarbeiterObservableList;
    }
}
