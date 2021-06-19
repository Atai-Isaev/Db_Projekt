package com.projekt.view;

import com.projekt.Main;
import com.projekt.model.Bestellung;
import com.projekt.model.BestellungDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BestellungCreateDialogController {

    private Main main;
    private DbOverviewController dbOverviewController;
    private Stage stage;
    private Bestellung tempBestellung;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    private ChoiceBox<Integer> bestellungNrChoiceBox = new ChoiceBox<>();
    @FXML
    private ChoiceBox<Integer> kundeNrChoiceBox = new ChoiceBox<>();
    @FXML
    private TextField bestellstatusField;
    @FXML
    private TextField bestelldatumField;
    @FXML
    private TextField bedarfsdatumField;
    @FXML
    private TextField versanddatumField;
    @FXML
    private ChoiceBox<Integer> geschäftNrChoiceBox = new ChoiceBox<>();
    @FXML
    private ChoiceBox<Integer> mitarbeiterNrChoiceBox = new ChoiceBox<>();

    public void setDbOverviewControllerNew(DbOverviewController dbOverviewController) {
        choiceBoxFill(dbOverviewController);
    }

    public void setDbOverviewControllerEdit(DbOverviewController dbOverviewController, Integer b, Integer k, Integer g, Integer m) {
        choiceBoxFill(dbOverviewController);

        bestellungNrChoiceBox.getItems().add(b);
        kundeNrChoiceBox.getItems().add(k);
        geschäftNrChoiceBox.getItems().add(g);
        mitarbeiterNrChoiceBox.getItems().add(m);
    }

    private void choiceBoxFill(DbOverviewController dbOverviewController) {
        this.dbOverviewController = dbOverviewController;

        dbOverviewController.getKundeObservableList().forEach(kunde ->
                kundeNrChoiceBox.getItems().add(kunde.getKundeNr()));
        dbOverviewController.getGeschäftObservableList().forEach(geschäft ->
                geschäftNrChoiceBox.getItems().add(geschäft.getGeschäftNr()));
        dbOverviewController.getMitarbeiterObservableList().forEach(mitarbeiter ->
                mitarbeiterNrChoiceBox.getItems().add(mitarbeiter.getMitarbeiterNr()));
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
            bestellung.setKundeNr(kundeNrChoiceBox.getSelectionModel().getSelectedItem());
            bestellung.setBestellstatus(Integer.parseInt(bestellstatusField.getText()));
            bestellung.setBestelldatum(simpleDateFormat.parse(bestelldatumField.getText()));
            bestellung.setBedarfsdatum(simpleDateFormat.parse(bedarfsdatumField.getText()));
            bestellung.setVersanddatum(simpleDateFormat.parse(versanddatumField.getText()));
            bestellung.setGeschäftNr(geschäftNrChoiceBox.getSelectionModel().getSelectedItem());
            bestellung.setMitarbeiterNr(mitarbeiterNrChoiceBox.getSelectionModel().getSelectedItem());

            BestellungDAO bestellungDAO = new BestellungDAO();

            if (tempBestellung == null) {
                bestellungDAO.insertBestellung(bestellung, main.getUsername(), main.getPassword());
            } else {
                bestellung.setBestellungNr(bestellungNrChoiceBox.getSelectionModel().getSelectedItem());
                bestellungDAO.updateBestellung(bestellung, main.getUsername(), main.getPassword());
            }
            stage.close();
        }
    }

    private boolean isInputValid() throws ParseException {
        String errorMessage = "";

        if (kundeNrChoiceBox.getSelectionModel().getSelectedItem() == null) {
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
                errorMessage += "No valid bestelldatum. Use the format dd.MM.yyyy!\n";
            }
        }
        if (bedarfsdatumField.getText() == null || bedarfsdatumField.getText().length() == 0) {
            errorMessage += "No valid bedarfsdatumField!\n";
        } else {
            try {
                simpleDateFormat.parse(bedarfsdatumField.getText());
            }
            catch (ParseException e){
                errorMessage += "No valid bedarfsdatumField. Use the format dd.MM.yyyy!\n";
            }
        }
        if (versanddatumField.getText() == null || versanddatumField.getText().length() == 0) {
            errorMessage += "No valid versanddatumField!\n";
        } else {
            try {
                simpleDateFormat.parse(versanddatumField.getText());
            }
            catch (ParseException e){
                errorMessage += "No valid versanddatumField. Use the format dd.MM.yyyy!\n";
            }
        }
        if (geschäftNrChoiceBox.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "No valid geschäftNr!\n";
        }
        if (mitarbeiterNrChoiceBox.getSelectionModel().getSelectedItem() == null) {
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

    public void setTempBestellung(Bestellung bestellung) {
        this.tempBestellung = bestellung;

        bestellungNrChoiceBox.setValue(bestellung.getBestellungNr());
        kundeNrChoiceBox.setValue(bestellung.getKundeNr());
        bestellstatusField.setText(String.valueOf(bestellung.getBestellstatus()));
        bestelldatumField.setText(String.valueOf(bestellung.getBestelldatum()));
        bedarfsdatumField.setText(String.valueOf(bestellung.getBedarfsdatum()));
        versanddatumField.setText(String.valueOf(bestellung.getVersanddatum()));
        geschäftNrChoiceBox.setValue(bestellung.getGeschäftNr());
        mitarbeiterNrChoiceBox.setValue(bestellung.getMitarbeiterNr());
    }
}
