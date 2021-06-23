package com.projekt;

import com.projekt.model.DbRole;
import com.projekt.view.DbOverviewController;
import com.projekt.view.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private String username = "";
    private String password = "";
    private DbRole role;

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Lucky Bike Projekt");

        showLogin();
    }

    public void showDb() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/DbOverview.fxml"));
            Parent dbOverview = loader.load();

            Scene scene = new Scene(dbOverview);
            primaryStage.setScene(scene);
            primaryStage.show();
//            primaryStage.setMaximized(true);

            DbOverviewController controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Login.fxml"));
            Parent login = loader.load();

            Scene scene = new Scene(login);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
//            primaryStage.setMaximized(false);

            LoginController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(DbRole role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public DbRole getRole() {
        return role;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
