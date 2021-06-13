package com.projekt.view;

import com.projekt.Main;
import com.projekt.model.Bestellung_Artikel;
import com.projekt.model.Bestellung_ArtikelDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Bestellung_ArtikelCreateDialogController {
    private Main main;
    private DbOverviewController dbOverviewController;
    private Stage stage;
    private Bestellung_Artikel tempBestellung_Artikel;

    @FXML
    private ChoiceBox<Integer> bestellungNrChoiceBox = new ChoiceBox<>();
    @FXML
    private ChoiceBox<Integer> artikelNrChoiceBox = new ChoiceBox<>();
    @FXML
    private TextField mengeField;
    @FXML
    private TextField listenpreisField;
    @FXML
    private TextField rabattField;

    public void setDbOverviewControllerNew(DbOverviewController dbOverviewController) {
        this.dbOverviewController = dbOverviewController;

        dbOverviewController.getBestellungObservableList().forEach(bestellung ->
                bestellungNrChoiceBox.getItems().add(bestellung.getBestellungNr()));
        dbOverviewController.getArtikelObservableList().forEach(artikel ->
                artikelNrChoiceBox.getItems().add(artikel.getArtikelNr()));
    }

    public void setDbOverviewControllerEdit(DbOverviewController dbOverviewController, Integer g, Integer a) {
        this.dbOverviewController = dbOverviewController;
        bestellungNrChoiceBox.getItems().add(g);
        artikelNrChoiceBox.getItems().add(a);
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
    private void handleOk() throws SQLException {
        if (isInputValid()) {
            Bestellung_Artikel bestellung_artikel = new Bestellung_Artikel();
            bestellung_artikel.setBestellungNr(bestellungNrChoiceBox.getSelectionModel().getSelectedItem());
            bestellung_artikel.setArtikelNr(artikelNrChoiceBox.getSelectionModel().getSelectedItem());
            bestellung_artikel.setMenge(Integer.parseInt(mengeField.getText()));
            bestellung_artikel.setListenpreis(new BigDecimal(listenpreisField.getText()));
            bestellung_artikel.setRabatt(new BigDecimal(rabattField.getText()));

            Bestellung_ArtikelDAO bestellung_artikelDAO = new Bestellung_ArtikelDAO();

            if (tempBestellung_Artikel == null) {
                bestellung_artikelDAO.insertBestellung_Artikel(bestellung_artikel, main.getUsername(), main.getPassword());
            } else {
                bestellung_artikelDAO.updateBestellung_Artikel(bestellung_artikel, main.getUsername(), main.getPassword());
            }
            stage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (bestellungNrChoiceBox.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "No valid bestellungNrChoiceBox!\n";
        }
        if (artikelNrChoiceBox.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "No valid artikelNr!\n";
        }
        if (mengeField.getText() == null || mengeField.getText().length() == 0) {
            errorMessage += "No valid mengeField!\n";
        } else {
            try {
                Integer.parseInt(mengeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid mengeField (must be an integer)!\n";
            }
        }
        if (listenpreisField.getText() == null || listenpreisField.getText().length() == 0) {
            errorMessage += "No valid listenpreisField!\n";
        } else {
            try {
                new BigDecimal(listenpreisField.getText());
            } catch (Exception e) {
                errorMessage += "No valid listenpreisField (must be an bigdecimal)!\n";
            }
        }
        if (rabattField.getText() == null || rabattField.getText().length() == 0 || new BigDecimal(rabattField.getText()).compareTo(new BigDecimal(100)) >= 0) {
            errorMessage += "No valid rabattField!\n";
        } else {
            try {
                new BigDecimal(rabattField.getText());
            } catch (Exception e) {
                errorMessage += "No valid rabattField (must be an bigdecimal)!\n";
            }
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

    public void setTempBestellung_Artikel(Bestellung_Artikel bestellung_artikel) {
        this.tempBestellung_Artikel = bestellung_artikel;
        // TODO: 29.05.2021
        bestellungNrChoiceBox.setValue(bestellung_artikel.getBestellungNr());
        artikelNrChoiceBox.setValue(bestellung_artikel.getArtikelNr());
        mengeField.setText(String.valueOf(bestellung_artikel.getMenge()));
        listenpreisField.setText(String.valueOf(bestellung_artikel.getListenpreis()));
        rabattField.setText(String.valueOf(bestellung_artikel.getRabatt()));
    }
}
