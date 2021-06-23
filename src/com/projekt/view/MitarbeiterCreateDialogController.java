package com.projekt.view;

import com.projekt.Main;
import com.projekt.model.Mitarbeiter;
import com.projekt.model.MitarbeiterDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MitarbeiterCreateDialogController {
    private Main main;
    private DbOverviewController dbOverviewController;
    private Stage stage;
    private Mitarbeiter tempMitarbeiter;

    @FXML
    private Label mitarbeiterNrLabel;
    @FXML
    private TextField vornameField;
    @FXML
    private TextField nachnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telefonField;
    @FXML
    private TextField aktivField;
    @FXML
    private ChoiceBox<Integer> geschäftNrChoiceBox = new ChoiceBox<>();

    public void setDbOverviewController(DbOverviewController dbOverviewController) {
        this.dbOverviewController = dbOverviewController;

        geschäftNrChoiceBox.getItems().add(0);
        dbOverviewController.getMitarbeiterObservableList().forEach(mitarbeiter ->
        {
            if (tempMitarbeiter != null && tempMitarbeiter.getMitarbeiterNr()==mitarbeiter.getMitarbeiterNr()) {

            }
            else geschäftNrChoiceBox.getItems().add(mitarbeiter.getMitarbeiterNr());

        });

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
            Mitarbeiter mitarbeiter = new Mitarbeiter();
            if (tempMitarbeiter != null) mitarbeiter.setMitarbeiterNr(Integer.parseInt(mitarbeiterNrLabel.getText()));
            mitarbeiter.setVorname(vornameField.getText());
            mitarbeiter.setNachname(nachnameField.getText());
            mitarbeiter.setEmail(emailField.getText());
            mitarbeiter.setTelefon(telefonField.getText());
            mitarbeiter.setAktiv(Integer.parseInt(aktivField.getText()));
            mitarbeiter.setGeschäftNr(geschäftNrChoiceBox.getSelectionModel().getSelectedItem());

            MitarbeiterDAO mitarbeiterDAO = new MitarbeiterDAO();

            if (tempMitarbeiter == null) {
                mitarbeiterDAO.insertMitarbeiter(mitarbeiter, main.getUsername(), main.getPassword());
            } else {
                mitarbeiterDAO.updateMitarbeiter(mitarbeiter, main.getUsername(), main.getPassword());
            }
            stage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (vornameField.getText() == null || vornameField.getText().length() == 0) {
            errorMessage += "No valid vornameField!\n";
        }
        if (nachnameField.getText() == null || nachnameField.getText().length() == 0) {
            errorMessage += "No valid nachnameField!\n";
        }
        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "No valid emailField!\n";
        } else {
            String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(emailField.getText());
            if (!matcher.matches()) errorMessage += "No valid emailField!\n";
        }
        if (telefonField.getText() == null || telefonField.getText().length() == 0) {
            errorMessage += "No valid telefonField!\n";
        }
        if (aktivField.getText() == null || aktivField.getText().length() == 0) {
            errorMessage += "No valid aktivField!\n";
        } else {
            try {
                Integer.parseInt(aktivField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid aktivField (must be an integer)!\n";
            }
        }
        if (geschäftNrChoiceBox.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "No valid kategorieNr!\n";
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

    public void setTempMitarbeiter(Mitarbeiter tempMitarbeiter) {
        this.tempMitarbeiter = tempMitarbeiter;

        mitarbeiterNrLabel.setText(String.valueOf(tempMitarbeiter.getMitarbeiterNr()));
        vornameField.setText(tempMitarbeiter.getVorname());
        nachnameField.setText(tempMitarbeiter.getNachname());
        emailField.setText(tempMitarbeiter.getEmail());
        telefonField.setText(tempMitarbeiter.getTelefon());
        aktivField.setText(String.valueOf(tempMitarbeiter.getAktiv()));
        geschäftNrChoiceBox.setValue(tempMitarbeiter.getGeschäftNr());
    }
}
