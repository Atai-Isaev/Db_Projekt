package com.projekt.model;

import com.projekt.connectivity.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HerstellerDAO {
    private Hersteller createHersteller(ResultSet rs) {
        Hersteller h = new Hersteller();

        try {
            h.setHerstellerNr(rs.getInt("HerstellerNr"));
            h.setHerstellerName(rs.getString("HerstellerName"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return h;
    }

    public List<Hersteller> getHerstellers() {
        String sql = "Select * from Hersteller order by HerstellerNr";
        List<Hersteller> list = new ArrayList<>();
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection con = databaseConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Hersteller h = createHersteller(rs);
                list.add(h);
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void deleteHersteller(Hersteller hersteller, String username, String password) throws SQLException {
        String sql = "DELETE FROM Hersteller WHERE HerstellerNr = "+hersteller.getHerstellerNr()+"";
        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.execute();
        ps.close();
    }

    public void insertHersteller(Hersteller a, String username, String password) throws SQLException {
        String sql = "INSERT INTO Hersteller VALUES (?)";

        connectionToHerstellerTable(a, username, password, sql);
    }

    public void updateHersteller(Hersteller a, String username, String password) throws SQLException {
        String sql = "UPDATE Hersteller SET HerstellerName = ? " +
                "WHERE HerstellerNr = "+a.getHerstellerNr()+"";

        connectionToHerstellerTable(a, username, password, sql);
    }

    private void connectionToHerstellerTable(Hersteller a, String username, String password, String sql) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, a.getHerstellerName());
        ps.execute();
        ps.close();
    }
}
