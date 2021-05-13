package com.projekt.connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private String username = "";
    private String password = "";
    private Connection connection;

    public DatabaseConnection() {
        this.username = "acc1";
        this.password = "acc1";
    }

    public DatabaseConnection(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() {
        try {
            String url = "jdbc:sqlserver://127.0.0.1;databaseName=Db_Projekt";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

        return connection;
    }
}
