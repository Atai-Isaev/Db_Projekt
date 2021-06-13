package com.projekt.view;

import com.projekt.Main;
import com.projekt.model.Hersteller;
import com.projekt.model.HerstellerDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HerstellerCreateDialogController {
    private Main main;
    private DbOverviewController dbOverviewController;
    private Stage stage;
    private Hersteller tempHersteller;

    @FXML
    private Label herstellerNrLabel;
    @FXML
    private TextField herstellerNameField;

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
            Hersteller hersteller = new Hersteller();
            if (tempHersteller != null) hersteller.setHerstellerNr(Integer.parseInt(herstellerNrLabel.getText()));
            hersteller.setHerstellerName(herstellerNameField.getText());

            HerstellerDAO herstellerDAO = new HerstellerDAO();

            if (tempHersteller == null) {
                herstellerDAO.insertHersteller(hersteller, main.getUsername(), main.getPassword());
            } else {
                herstellerDAO.updateHersteller(hersteller, main.getUsername(), main.getPassword());
            }
            stage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (herstellerNameField.getText() == null || herstellerNameField.getText().length() == 0) {
            errorMessage += "No valid herstellerNameField!\n";
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

    public void setTempHersteller(Hersteller tempHersteller) {
        this.tempHersteller = tempHersteller;

        herstellerNrLabel.setText(String.valueOf(tempHersteller.getHerstellerNr()));
        herstellerNameField.setText(tempHersteller.getHerstellerName());
    }
}
