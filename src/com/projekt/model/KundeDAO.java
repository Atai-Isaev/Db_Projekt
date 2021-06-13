package com.projekt.model;

import com.projekt.connectivity.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KundeDAO {
    private Kunde createKunde(ResultSet rs) {
        Kunde k = new Kunde();

        try {
            k.setKundeNr(rs.getInt("KundeNr"));
            k.setVorname(rs.getString("Vorname"));
            k.setNachname(rs.getString("Nachname"));
            k.setTelefon(rs.getString("Telefon"));
            k.setEmail(rs.getString("Email"));
            k.setStraße(rs.getString("Straße"));
            k.setOrt(rs.getString("Ort"));
            k.setPLZ(rs.getString("PLZ"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return k;
    }

    public List<Kunde> getKundes() {
        String sql = "Select * from Kunde order by KundeNr";
        List<Kunde> list = new ArrayList<>();
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection con = databaseConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Kunde k = createKunde(rs);
                list.add(k);
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void deleteKunde(Kunde kunde, String username, String password) throws SQLException {
        String sql = "DELETE FROM Kunde WHERE KundeNr = "+kunde.getKundeNr()+"";
        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.execute();
        ps.close();
    }

    public void insertKunde(Kunde a, String username, String password) throws SQLException {
        String sql = "INSERT INTO Kunde VALUES (?,?,?,?,?,?,?)";

        connectionToKundeTable(a, username, password, sql);
    }

    public void updateKunde(Kunde a, String username, String password) throws SQLException {
        String sql = "UPDATE Kunde SET Vorname = ?," +
                "Nachname = ?," +
                "Telefon = ?," +
                "Email = ?," +
                "Straße = ?," +
                "Ort = ?, " +
                "PLZ = ? " +
                "WHERE KundeNr = "+a.getKundeNr()+"";

        connectionToKundeTable(a, username, password, sql);
    }

    private void connectionToKundeTable(Kunde a, String username, String password, String sql) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, a.getVorname());
        ps.setString(2, a.getNachname());
        ps.setString(3, a.getTelefon());
        ps.setString(4, a.getEmail());
        ps.setString(5, a.getStraße());
        ps.setString(6, a.getOrt());
        ps.setInt(7, Integer.parseInt(a.getPLZ()));
        ps.execute();
        ps.close();
    }
}
