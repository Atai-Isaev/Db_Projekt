package com.projekt.view;

import com.projekt.Main;
import com.projekt.model.Bestände;
import com.projekt.model.BeständeDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

public class BestaendeCreateDialogController {
    private Main main;
    private DbOverviewController dbOverviewController;
    private Stage stage;
    private Bestände tempBestände;

    @FXML
    private ChoiceBox<Integer> geschäftNrChoiceBox = new ChoiceBox<>();
    @FXML
    private ChoiceBox<Integer> artikelNrChoiceBox = new ChoiceBox<>();
    @FXML
    private TextField mengeField;

    public void setDbOverviewControllerNew(DbOverviewController dbOverviewController) {
        this.dbOverviewController = dbOverviewController;

        dbOverviewController.getGeschäftObservableList().forEach(geschäft ->
                geschäftNrChoiceBox.getItems().add(geschäft.getGeschäftNr()));
        dbOverviewController.getArtikelObservableList().forEach(artikel ->
                artikelNrChoiceBox.getItems().add(artikel.getArtikelNr()));
    }

    public void setDbOverviewControllerEdit(DbOverviewController dbOverviewController, Integer g, Integer a) {
        this.dbOverviewController = dbOverviewController;
        geschäftNrChoiceBox.getItems().add(g);
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
            Bestände bestände = new Bestände();
            bestände.setGeschäftNr(geschäftNrChoiceBox.getSelectionModel().getSelectedItem());
            bestände.setArtikelNr(artikelNrChoiceBox.getSelectionModel().getSelectedItem());
            bestände.setMenge(Integer.parseInt(mengeField.getText()));

            BeständeDAO beständeDAO = new BeständeDAO();

            if (tempBestände == null) {
                beständeDAO.insertBestände(bestände, main.getUsername(), main.getPassword());
            } else {
                beständeDAO.updateBestände(bestände, main.getUsername(), main.getPassword());
            }
            stage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (geschäftNrChoiceBox.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "No valid geschäftNr!\n";
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

    public void setTempArtikel(Bestände bestände) {
        this.tempBestände = bestände;
        // TODO: 29.05.2021
        geschäftNrChoiceBox.setValue(bestände.getGeschäftNr());
        artikelNrChoiceBox.setValue(bestände.getArtikelNr());
        mengeField.setText(String.valueOf(bestände.getMenge()));
    }
}
