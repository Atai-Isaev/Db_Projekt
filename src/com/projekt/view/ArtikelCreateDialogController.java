package com.projekt.view;

import com.projekt.Main;
import com.projekt.model.Artikel;
import com.projekt.model.ArtikelDAO;
import com.projekt.model.Hersteller;
import com.projekt.model.Kategorie;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private ComboBox<Hersteller> herstellerComboBox;
    @FXML
    private ComboBox<Kategorie> kategorieComboBox;
    @FXML
    private TextField modelljahrField;
    @FXML
    private TextField listenpreisField;

    public void setDbOverviewController(DbOverviewController dbOverviewController) {
        this.dbOverviewController = dbOverviewController;

        herstellerComboBox.setItems(dbOverviewController.getHerstellerObservableList());
        kategorieComboBox.setItems(dbOverviewController.getKategorieObservableList());

//        dbOverviewController.getHerstellerObservableList().forEach(hersteller ->
//                herstellerNrChoiceBox.getItems().add(hersteller.getHerstellerNr()));
//        dbOverviewController.getKategorieObservableList().forEach(kategorie ->
//                kategorieNrChoiceBox.getItems().add(kategorie.getKategorieNr()));
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
            artikel.setHerstellerNr(herstellerComboBox.getSelectionModel().getSelectedItem().getHerstellerNr());
            artikel.setKategorieNr(kategorieComboBox.getSelectionModel().getSelectedItem().getKategorieNr());
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
        if (herstellerComboBox.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "No valid herstellerNr!\n";
        }
        if (kategorieComboBox.getSelectionModel().getSelectedItem() == null) {
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

        artikelNrLabel.setText(String.valueOf(tempArtikel.getArtikelNr()));
        artikelNameField.setText(tempArtikel.getArtikelName());
        herstellerComboBox.setValue(dbOverviewController.getHerstellerObservableList().stream().
                filter(hersteller -> tempArtikel.getHerstellerNr() == hersteller.getHerstellerNr()).
                findFirst().
                orElse(null));
        kategorieComboBox.setValue(dbOverviewController.getKategorieObservableList().stream().
                filter(kategorie -> tempArtikel.getKategorieNr() == kategorie.getKategorieNr()).
                findFirst().
                orElse(null));
        modelljahrField.setText(String.valueOf(tempArtikel.getModelljahr()));
        listenpreisField.setText(tempArtikel.getListenpreis().toPlainString());
    }
}
