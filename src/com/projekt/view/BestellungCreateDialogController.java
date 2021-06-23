package com.projekt.view;

import com.projekt.Main;
import com.projekt.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BestellungCreateDialogController {

    private Main main;
    private DbOverviewController dbOverviewController;
    private Stage stage;
    private Bestellung tempBestellung;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    private ComboBox<Bestellung> bestellungComboBox;
    @FXML
    private ComboBox<Kunde> kundeComboBox;
    @FXML
    private ComboBox<Geschäft> geschäftComboBox;
    @FXML
    private ComboBox<Mitarbeiter> mitarbeiterComboBox;
    @FXML
    private TextField bestellstatusField;
    @FXML
    private TextField bestelldatumField;
    @FXML
    private TextField bedarfsdatumField;
    @FXML
    private TextField versanddatumField;


    public void setDbOverviewControllerNew(DbOverviewController dbOverviewController) {
        comboBoxFill(dbOverviewController);
    }

    public void setDbOverviewControllerEdit(DbOverviewController dbOverviewController, Integer b, Integer k, Integer g, Integer m) {
//        comboBoxFill(dbOverviewController);
        this.dbOverviewController = dbOverviewController;

        bestellungComboBox.getItems().add(dbOverviewController.getBestellungObservableList().stream().
                filter(bestellung -> b == bestellung.getBestellungNr()).
                findFirst().
                orElse(null));
        kundeComboBox.getItems().add(dbOverviewController.getKundeObservableList().stream().
                filter(kunde -> k == kunde.getKundeNr()).
                findFirst().
                orElse(null));
        geschäftComboBox.getItems().add(dbOverviewController.getGeschäftObservableList().stream().
                filter(geschäft -> g == geschäft.getGeschäftNr()).
                findFirst().
                orElse(null));
        mitarbeiterComboBox.getItems().add(dbOverviewController.getMitarbeiterObservableList().stream().
                filter(mitarbeiter -> m == mitarbeiter.getMitarbeiterNr()).
                findFirst().
                orElse(null));
    }

    private void comboBoxFill(DbOverviewController dbOverviewController) {


        kundeComboBox.setItems(dbOverviewController.getKundeObservableList());
        geschäftComboBox.setItems(dbOverviewController.getGeschäftObservableList());
        mitarbeiterComboBox.setItems(dbOverviewController.getMitarbeiterObservableList());

//        dbOverviewController.getKundeObservableList().forEach(kunde ->
//                kundeNrChoiceBox.getItems().add(kunde.getKundeNr()));
//        dbOverviewController.getGeschäftObservableList().forEach(geschäft ->
//                geschäftNrChoiceBox.getItems().add(geschäft.getGeschäftNr()));
//        dbOverviewController.getMitarbeiterObservableList().forEach(mitarbeiter ->
//                mitarbeiterNrChoiceBox.getItems().add(mitarbeiter.getMitarbeiterNr()));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private void handleCancel() {
        stage.close();
    }

    @FXML
    private void handleOk() throws SQLException, ParseException {
        if (isInputValid()) {
            Bestellung bestellung = new Bestellung();
            bestellung.setKundeNr(kundeComboBox.getSelectionModel().getSelectedItem().getKundeNr());
            bestellung.setBestellstatus(Integer.parseInt(bestellstatusField.getText()));
            bestellung.setBestelldatum(simpleDateFormat.parse(bestelldatumField.getText()));
            bestellung.setBedarfsdatum(simpleDateFormat.parse(bedarfsdatumField.getText()));
            bestellung.setVersanddatum(simpleDateFormat.parse(versanddatumField.getText()));
            bestellung.setGeschäftNr(geschäftComboBox.getSelectionModel().getSelectedItem().getGeschäftNr());
            bestellung.setMitarbeiterNr(mitarbeiterComboBox.getSelectionModel().getSelectedItem().getMitarbeiterNr());

            BestellungDAO bestellungDAO = new BestellungDAO();

            if (tempBestellung == null) {
                bestellungDAO.insertBestellung(bestellung, main.getUsername(), main.getPassword());
            } else {
                bestellung.setBestellungNr(bestellungComboBox.getSelectionModel().getSelectedItem().getBestellungNr());
                bestellungDAO.updateBestellung(bestellung, main.getUsername(), main.getPassword());
            }
            stage.close();
        }
    }

    private boolean isInputValid() throws ParseException {
        String errorMessage = "";

        if (kundeComboBox.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "No valid kundeNr!\n";
        }

        if (bestellstatusField.getText() == null || bestellstatusField.getText().length() == 0) {
            errorMessage += "No valid bestellstatus!\n";
        } else {
            try {
                Integer.parseInt(bestellstatusField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid bestellstatus (must be an integer)!\n";
            }
        }


        if (bestelldatumField.getText() == null || bestelldatumField.getText().length() == 0) {
            errorMessage += "No valid bestelldatum!\n";
        } else {
            try {
                simpleDateFormat.parse(bestelldatumField.getText());
            }
            catch (ParseException e){
                errorMessage += "No valid bestelldatum. Use the format yyyy-MM-dd!\n";
            }
        }
        if (bedarfsdatumField.getText() == null || bedarfsdatumField.getText().length() == 0) {
            errorMessage += "No valid bedarfsdatumField!\n";
        } else {
            try {
                simpleDateFormat.parse(bedarfsdatumField.getText());
            }
            catch (ParseException e){
                errorMessage += "No valid bedarfsdatumField. Use the format yyyy-MM-dd!\n";
            }
        }
        if (versanddatumField.getText() == null || versanddatumField.getText().length() == 0) {
            errorMessage += "No valid versanddatumField!\n";
        } else {
            try {
                simpleDateFormat.parse(versanddatumField.getText());
            }
            catch (ParseException e){
                errorMessage += "No valid versanddatumField. Use the format yyyy-MM-dd!\n";
            }
        }
        if (geschäftComboBox.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "No valid geschäftNr!\n";
        }
        if (mitarbeiterComboBox.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "No valid mitarbeiterNr!\n";
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.initModality(Modality.NONE);
            alert.showAndWait();
            return false;
        }
    }

    public void setTempBestellung(Bestellung b) {
        this.tempBestellung = b;

        bestellungComboBox.setValue(dbOverviewController.getBestellungObservableList().stream().
                filter(bestellung -> b.getBestellungNr() == bestellung.getBestellungNr()).
                findFirst().
                orElse(null));
        kundeComboBox.setValue(dbOverviewController.getKundeObservableList().stream().
                filter(kunde -> b.getKundeNr() == kunde.getKundeNr()).
                findFirst().
                orElse(null));
        bestellstatusField.setText(String.valueOf(b.getBestellstatus()));
        bestelldatumField.setText(String.valueOf(b.getBestelldatum()));
        bedarfsdatumField.setText(String.valueOf(b.getBedarfsdatum()));
        versanddatumField.setText(String.valueOf(b.getVersanddatum()));
        geschäftComboBox.setValue(dbOverviewController.getGeschäftObservableList().stream().
                filter(geschäft -> b.getGeschäftNr() == geschäft.getGeschäftNr()).
                findFirst().
                orElse(null));
        mitarbeiterComboBox.setValue(dbOverviewController.getMitarbeiterObservableList().stream().
                filter(mitarbeiter -> b.getMitarbeiterNr() == mitarbeiter.getMitarbeiterNr()).
                findFirst().
                orElse(null));
    }
}
