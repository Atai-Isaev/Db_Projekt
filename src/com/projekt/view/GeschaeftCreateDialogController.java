package com.projekt.view;

import com.projekt.Main;
import com.projekt.model.Geschäft;
import com.projekt.model.GeschäftDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeschaeftCreateDialogController {
    private Main main;
    private DbOverviewController dbOverviewController;
    private Stage stage;
    private Geschäft tempGeschäft;

    @FXML
    private Label geschäftNrLabel;
    @FXML
    private TextField geschäftNameField;
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
            Geschäft geschäft = new Geschäft();
            if (tempGeschäft != null) geschäft.setGeschäftNr(Integer.parseInt(geschäftNrLabel.getText()));
            geschäft.setGeschäftName(geschäftNameField.getText());
            geschäft.setTelefon(telefonField.getText());
            geschäft.setEmail(emailField.getText());
            geschäft.setStraße(straßeField.getText());
            geschäft.setOrt(ortField.getText());
            geschäft.setPLZ(PLZField.getText());

            GeschäftDAO geschäftDAO = new GeschäftDAO();

            if (tempGeschäft == null) {
                geschäftDAO.insertGeschäft(geschäft, main.getUsername(), main.getPassword());
            } else {
                geschäftDAO.updateGeschäft(geschäft, main.getUsername(), main.getPassword());
            }
            stage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (geschäftNameField.getText() == null || geschäftNameField.getText().length() == 0) {
            errorMessage += "No valid geschäftNameField!\n";
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

    public void setTempGeschäft(Geschäft tempGeschäft) {
        this.tempGeschäft = tempGeschäft;

        geschäftNrLabel.setText(String.valueOf(tempGeschäft.getGeschäftNr()));
        geschäftNameField.setText(tempGeschäft.getGeschäftName());
        telefonField.setText(tempGeschäft.getTelefon());
        emailField.setText(tempGeschäft.getEmail());
        straßeField.setText(tempGeschäft.getStraße());
        ortField.setText(tempGeschäft.getOrt());
        PLZField.setText(tempGeschäft.getPLZ());
    }
}
