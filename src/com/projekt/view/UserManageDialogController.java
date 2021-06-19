package com.projekt.view;

import com.projekt.Main;
import com.projekt.connectivity.DatabaseConnection;
import com.projekt.model.Artikel;
import com.projekt.model.ArtikelDAO;
import com.projekt.model.DbRole;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserManageDialogController {

    // TODO: 13.06.2021 Fix needed
    private Main main;
    private DbOverviewController dbOverviewController;
    private Stage stage;
    private Artikel tempArtikel;
    private boolean isAcc1Writer;
    private boolean isAcc2Writer;

    @FXML
    private RadioButton acc1ReaderRB;
    @FXML
    private RadioButton acc1WriterRB;

    @FXML
    private ToggleGroup acc1;

    @FXML
    private RadioButton acc2ReaderRB;
    @FXML
    private RadioButton acc2WriterRB;

    @FXML
    private ToggleGroup acc2;

    public void setDbOverviewController(DbOverviewController dbOverviewController) {
        this.dbOverviewController = dbOverviewController;

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connectDB = databaseConnection.getConnection();

        String verifyLogin1 = "SELECT DP1.name AS DatabaseRoleName,   \n" +
                "   DP2.name AS DatabaseUserName   \n" +
                " FROM sys.database_role_members AS DRM  \n" +
                " RIGHT OUTER JOIN sys.database_principals AS DP1  \n" +
                "   ON DRM.role_principal_id = DP1.principal_id  \n" +
                " LEFT OUTER JOIN sys.database_principals AS DP2  \n" +
                "   ON DRM.member_principal_id = DP2.principal_id  \n" +
                "WHERE DP1.type = 'R' AND DP1.name = 'db_datawriter' AND DP2.name = 'acc1' \n" +
                "ORDER BY DP1.name;";

        String verifyLogin2 = "SELECT DP1.name AS DatabaseRoleName,   \n" +
                "   DP2.name AS DatabaseUserName   \n" +
                " FROM sys.database_role_members AS DRM  \n" +
                " RIGHT OUTER JOIN sys.database_principals AS DP1  \n" +
                "   ON DRM.role_principal_id = DP1.principal_id  \n" +
                " LEFT OUTER JOIN sys.database_principals AS DP2  \n" +
                "   ON DRM.member_principal_id = DP2.principal_id  \n" +
                "WHERE DP1.type = 'R' AND DP1.name = 'db_datawriter' AND DP2.name = 'acc2' \n" +
                "ORDER BY DP1.name;";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(verifyLogin1);

            int count1 = 0;

            while (resultSet.next()) {
                ++count1;
            }

            isAcc1Writer = count1 == 1;

            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        try {
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(verifyLogin2);

            int count1 = 0;

            while (resultSet.next()) {
                ++count1;
            }

            isAcc2Writer = count1 == 1;

            resultSet.close();
            connectDB.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        if (isAcc1Writer){
            acc1WriterRB.setSelected(true);
        }
        else {
            acc1ReaderRB.setSelected(true);
        }
        if (isAcc2Writer){
            acc2WriterRB.setSelected(true);
        }
        else {
            acc2ReaderRB.setSelected(true);
        }
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
//            Artikel artikel = new Artikel();
//            if (tempArtikel != null) artikel.setArtikelNr(Integer.parseInt(artikelNrLabel.getText()));
//            artikel.setArtikelName(artikelNameField.getText());
//            artikel.setHerstellerNr(herstellerNrChoiceBox.getSelectionModel().getSelectedItem());
//            artikel.setKategorieNr(kategorieNrChoiceBox.getSelectionModel().getSelectedItem());
//            artikel.setModelljahr(Integer.parseInt(modelljahrField.getText()));
//            artikel.setListenpreis(new BigDecimal(listenpreisField.getText()));
//
//            ArtikelDAO artikelDAO = new ArtikelDAO();
//
//            if (tempArtikel == null) {
//                artikelDAO.insertArtikel(artikel, main.getUsername(), main.getPassword());
//            } else {
//                artikelDAO.updateArtikel(artikel, main.getUsername(), main.getPassword());
//            }
//            stage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";
        return false;

//        if (artikelNameField.getText() == null || artikelNameField.getText().length() == 0) {
//            errorMessage += "No valid artikelNameField!\n";
//        }
//        if (herstellerNrChoiceBox.getSelectionModel().getSelectedItem() == null) {
//            errorMessage += "No valid herstellerNr!\n";
//        }
//        if (kategorieNrChoiceBox.getSelectionModel().getSelectedItem() == null) {
//            errorMessage += "No valid kategorieNr!\n";
//        }
//        if (modelljahrField.getText() == null || modelljahrField.getText().length() == 0) {
//            errorMessage += "No valid modelljahrField!\n";
//        } else {
//            try {
//                Integer.parseInt(modelljahrField.getText());
//            } catch (NumberFormatException e) {
//                errorMessage += "No valid modelljahrField (must be an integer)!\n";
//            }
//        }
//        if (listenpreisField.getText() == null || listenpreisField.getText().length() == 0) {
//            errorMessage += "No valid modelljahrField!\n";
//        } else {
//            try {
//                new BigDecimal(listenpreisField.getText());
//            } catch (NumberFormatException e) {
//                errorMessage += "No valid listenpreisField (must be an Big Decimal)!\n";
//            }
//        }
//        if (errorMessage.length() == 0) {
//            return true;
//        } else {
//            // Показываем сообщение об ошибке.
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.initOwner(stage);
//            alert.setTitle("Invalid Fields");
//            alert.setHeaderText("Please correct invalid fields");
//            alert.setContentText(errorMessage);
//
//            alert.initModality(Modality.NONE);
//            alert.showAndWait();
//            return false;
//        }
    }

    public void setTempArtikel(Artikel tempArtikel) {
//        this.tempArtikel = tempArtikel;
//        artikelNrLabel.setText(String.valueOf(tempArtikel.getArtikelNr()));
//        artikelNameField.setText(tempArtikel.getArtikelName());
//        herstellerNrChoiceBox.setValue(tempArtikel.getHerstellerNr());
//        kategorieNrChoiceBox.setValue(tempArtikel.getKategorieNr());
//        modelljahrField.setText(String.valueOf(tempArtikel.getModelljahr()));
//        listenpreisField.setText(tempArtikel.getListenpreis().toPlainString());
    }
}
