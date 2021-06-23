package com.projekt.view;

import com.projekt.Main;
import com.projekt.model.Artikel;
import com.projekt.model.Bestände;
import com.projekt.model.BeständeDAO;
import com.projekt.model.Geschäft;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
    private ComboBox<Geschäft> geschäftComboBox;
    @FXML
    private ComboBox<Artikel> artikelComboBox;
    @FXML
    private TextField mengeField;

    public void setDbOverviewControllerNew(DbOverviewController dbOverviewController) {
        this.dbOverviewController = dbOverviewController;

        geschäftComboBox.setItems(dbOverviewController.getGeschäftObservableList());
        artikelComboBox.setItems(dbOverviewController.getArtikelObservableList());

//        dbOverviewController.getGeschäftObservableList().forEach(geschäft ->
//                geschäftNrChoiceBox.getItems().add(geschäft.getGeschäftNr()));
//        dbOverviewController.getArtikelObservableList().forEach(artikel ->
//                artikelNrChoiceBox.getItems().add(artikel.getArtikelNr()));
    }

    public void setDbOverviewControllerEdit(DbOverviewController dbOverviewController, Integer g, Integer a) {
        this.dbOverviewController = dbOverviewController;
        geschäftComboBox.getItems().add(dbOverviewController.getGeschäftObservableList().stream().
                filter(geschäft -> g == geschäft.getGeschäftNr()).
                findFirst().
                orElse(null));
        artikelComboBox.getItems().add(dbOverviewController.getArtikelObservableList().stream().
                filter(artikel -> a == artikel.getArtikelNr()).
                findFirst().
                orElse(null));
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
            bestände.setGeschäftNr(geschäftComboBox.getSelectionModel().getSelectedItem().getGeschäftNr());
            bestände.setArtikelNr(artikelComboBox.getSelectionModel().getSelectedItem().getArtikelNr());
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

        if (geschäftComboBox.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "No valid geschäftNr!\n";
        }
        if (artikelComboBox.getSelectionModel().getSelectedItem() == null) {
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

    public void setTempBestände(Bestände bestände) {
        this.tempBestände = bestände;

        geschäftComboBox.setValue(dbOverviewController.getGeschäftObservableList().stream().
                filter(geschäft -> bestände.getGeschäftNr()== geschäft.getGeschäftNr()).
                findFirst().
                orElse(null));
        artikelComboBox.setValue(dbOverviewController.getArtikelObservableList().stream().
                filter(artikel -> bestände.getArtikelNr() == artikel.getArtikelNr()).
                findFirst().
                orElse(null));
        mengeField.setText(String.valueOf(bestände.getMenge()));
    }
}
