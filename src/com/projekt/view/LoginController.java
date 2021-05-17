package com.projekt.view;

import com.projekt.Main;
import com.projekt.connectivity.DatabaseConnection;
import com.projekt.model.DbRole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    @FXML
    private Button cancelButton;

    @FXML
    private Label loginInfoLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField enterPasswordField;

    private Main mainApp;

    public void loginButtonOnAction(ActionEvent event) {

        if (!usernameTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()) {
            validateLogin();
        } else {
            loginInfoLabel.setText("Please enter username and password!");
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void validateLogin() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connectDB = databaseConnection.getConnection();
        String verifyLogin = "SELECT count(1) FROM Account WHERE username = '" + usernameTextField.getText() + "' AND password = '" + enterPasswordField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(verifyLogin);

            while (resultSet.next()) {
                if (resultSet.getInt(1) == 1) {

                    loginInfoLabel.setText("Congratulations!");
                    mainApp.setUsername(usernameTextField.getText());
                    mainApp.setPassword(enterPasswordField.getText());

                    switch (usernameTextField.getText()) {
                        case "acc1":
                            mainApp.setRole(DbRole.READER);
                            break;
                        case "acc2":
                            mainApp.setRole(DbRole.WRITER);
                            break;
                        case "admin":
                            mainApp.setRole(DbRole.ADMIN);
                            break;
                    }

                    mainApp.showDb();
                } else {
                    loginInfoLabel.setText("Invalid login. Please try again!");
                }
            }
            resultSet.close();
            connectDB.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}