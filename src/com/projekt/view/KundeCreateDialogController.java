package com.projekt.view;

import com.projekt.Main;
import com.projekt.model.Kunde;
import com.projekt.model.KundeDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KundeCreateDialogController {
    private Main main;
    private DbOverviewController dbOverviewController;
    private Stage stage;
    private Kunde tempKunde;

    @FXML
    private Label kundeNrLabel;
    @FXML
    private TextField vornameField;
    @FXML
    private TextField nachnameField;
    @FXML
    private TextField telefonField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField straßeField;
    @FXML
    private TextField ortField;
    @FXML
    private TextField PLZField;

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
            Kunde kunde = new Kunde();
            if (tempKunde != null) kunde.setKundeNr(Integer.parseInt(kundeNrLabel.getText()));
            kunde.setVorname(vornameField.getText());
            kunde.setNachname(nachnameField.getText());
            kunde.setTelefon(telefonField.getText());
            kunde.setEmail(emailField.getText());
            kunde.setStraße(straßeField.getText());
            kunde.setOrt(ortField.getText());
            kunde.setPLZ(PLZField.getText());

            KundeDAO kundeDAO = new KundeDAO();

            if (tempKunde == null) {
                kundeDAO.insertKunde(kunde, main.getUsername(), main.getPassword());
            } else {
                kundeDAO.updateKunde(kunde, main.getUsername(), main.getPassword());
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
        if (telefonField.getText() == null || telefonField.getText().length() == 0) {
            errorMessage += "No valid telefonField!\n";
        }
        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "No valid emailField!\n";
        } else {
            String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(emailField.getText());
            if (!matcher.matches()) errorMessage += "No valid emailField!\n";
        }
        if (straßeField.getText() == null || straßeField.getText().length() == 0) {
            errorMessage += "No valid straßeField!\n";
        }
        if (PLZField.getText() == null || PLZField.getText().length() == 0 || PLZField.getText().length() != 5) {
            errorMessage += "No valid PLZField!\n";
        } else {
            try {
                Integer.parseInt(PLZField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid PLZField (must be an integer)!\n";
            }
        }
        if (ortField.getText() == null || ortField.getText().length() == 0) {
            errorMessage += "No valid ortField!\n";
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

    public void setTempKunde(Kunde tempKunde) {
        this.tempKunde = tempKunde;

        kundeNrLabel.setText(String.valueOf(tempKunde.getKundeNr()));
        vornameField.setText(tempKunde.getVorname());
        nachnameField.setText(tempKunde.getNachname());
        telefonField.setText(tempKunde.getTelefon());
        emailField.setText(tempKunde.getEmail());
        straßeField.setText(tempKunde.getStraße());
        ortField.setText(tempKunde.getOrt());
        PLZField.setText(tempKunde.getPLZ());
    }
}
