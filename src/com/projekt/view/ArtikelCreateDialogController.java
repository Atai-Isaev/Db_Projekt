package com.projekt.view;

import com.projekt.Main;
import com.projekt.model.Artikel;
import com.projekt.model.ArtikelDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.SQLException;

public class ArtikelCreateDialogController {

    private Main main;
    private DbOverviewController dbOverviewController;
    private Stage stage;
    private Artikel tempArtikel;

    @FXML
    private Label artikelNrLabel;
    @FXML
    private TextField artikelNameField;
    @FXML
    private ChoiceBox<Integer> herstellerNrChoiceBox = new ChoiceBox<>();
    @FXML
    private ChoiceBox<Integer> kategorieNrChoiceBox = new ChoiceBox<>();
    @FXML
    private TextField modelljahrField;
    @FXML
    private TextField listenpreisField;

    public void setDbOverviewController(DbOverviewController dbOverviewController) {
        this.dbOverviewController = dbOverviewController;

        dbOverviewController.getHerstellerObservableList().forEach(hersteller ->
                herstellerNrChoiceBox.getItems().add(hersteller.getHerstellerNr()));
        dbOverviewController.getKategorieObservableList().forEach(kategorie ->
                kategorieNrChoiceBox.getItems().add(kategorie.getKategorieNr()));
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
            Artikel artikel = new Artikel();
            if (tempArtikel != null) artikel.setArtikelNr(Integer.parseInt(artikelNrLabel.getText()));
            artikel.setArtikelName(artikelNameField.getText());
            artikel.setHerstellerNr(herstellerNrChoiceBox.getSelectionModel().getSelectedItem());
            artikel.setKategorieNr(kategorieNrChoiceBox.getSelectionModel().getSelectedItem());
            artikel.setModelljahr(Integer.parseInt(modelljahrField.getText()));
            artikel.setListenpreis(new BigDecimal(listenpreisField.getText()));

            ArtikelDAO artikelDAO = new ArtikelDAO();

            if (tempArtikel == null) {
                artikelDAO.insertArtikel(artikel, main.getUsername(), main.getPassword());
            } else {
                artikelDAO.updateArtikel(artikel, main.getUsername(), main.getPassword());
            }
            stage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (artikelNameField.getText() == null || artikelNameField.getText().length() == 0) {
            errorMessage += "No valid artikelNameField!\n";
        }
        if (herstellerNrChoiceBox.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "No valid herstellerNr!\n";
        }
        if (kategorieNrChoiceBox.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "No valid kategorieNr!\n";
        }
        if (modelljahrField.getText() == null || modelljahrField.getText().length() == 0) {
            errorMessage += "No valid modelljahrField!\n";
        } else {
            try {
                Integer.parseInt(modelljahrField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid modelljahrField (must be an integer)!\n";
            }
        }
        if (listenpreisField.getText() == null || listenpreisField.getText().length() == 0) {
            errorMessage += "No valid modelljahrField!\n";
        } else {
            try {
                new BigDecimal(listenpreisField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid listenpreisField (must be an Big Decimal)!\n";
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

    public void setTempArtikel(Artikel tempArtikel) {
        this.tempArtikel = tempArtikel;
        // TODO: 29.05.2021 Add if case when create new Artikel(Label isue)
        artikelNrLabel.setText(String.valueOf(tempArtikel.getArtikelNr()));
        artikelNameField.setText(tempArtikel.getArtikelName());
        herstellerNrChoiceBox.setValue(tempArtikel.getHerstellerNr());
        kategorieNrChoiceBox.setValue(tempArtikel.getKategorieNr());
        modelljahrField.setText(String.valueOf(tempArtikel.getModelljahr()));
        listenpreisField.setText(tempArtikel.getListenpreis().toPlainString());
    }
}
