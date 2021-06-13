package com.projekt.view;

import com.projekt.Main;
import com.projekt.model.Kategorie;
import com.projekt.model.KategorieDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

public class KategorieCreateDialogController {
    private Main main;
    private DbOverviewController dbOverviewController;
    private Stage stage;
    private Kategorie tempKategorie;

    @FXML
    private Label kategorieNrLabel;
    @FXML
    private TextField kategorieNameField;

    public void setDbOverviewController(DbOverviewController dbOverviewController) {
        this.dbOverviewController = dbOverviewController;
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
            Kategorie kategorie = new Kategorie();
            if (tempKategorie != null) kategorie.setKategorieNr(Integer.parseInt(kategorieNrLabel.getText()));
            kategorie.setKategorieName(kategorieNameField.getText());

            KategorieDAO kategorieDAO = new KategorieDAO();

            if (tempKategorie == null) {
                kategorieDAO.insertKategorie(kategorie, main.getUsername(), main.getPassword());
            } else {
                kategorieDAO.updateKategorie(kategorie, main.getUsername(), main.getPassword());
            }
            stage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (kategorieNameField.getText() == null || kategorieNameField.getText().length() == 0) {
            errorMessage += "No valid kategorieNameField!\n";
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

    public void setTempKategorie(Kategorie tempKategorie) {
        this.tempKategorie = tempKategorie;

        kategorieNrLabel.setText(String.valueOf(tempKategorie.getKategorieNr()));
        kategorieNameField.setText(tempKategorie.getKategorieName());
    }
}
