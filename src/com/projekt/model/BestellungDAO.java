package com.projekt.model;

import com.projekt.connectivity.DatabaseConnection;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BestellungDAO {

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private Bestellung createBestellung(ResultSet rs) {
        Bestellung b = new Bestellung();

        try {
            b.setBestellungNr(rs.getInt("BestellungNr"));
            b.setKundeNr(rs.getInt("KundeNr"));
            b.setBestellstatus(rs.getInt("Bestellstatus"));
            b.setBestelldatum(rs.getDate("Bestelldatum"));
            b.setBedarfsdatum(rs.getDate("Bedarfsdatum"));
            b.setVersanddatum(rs.getDate("Versanddatum"));
            b.setGeschäftNr(rs.getInt("GeschäftNr"));
            b.setMitarbeiterNr(rs.getInt("MitarbeiterNr"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return b;
    }

    public List<Bestellung> getBestellungs() {
        String sql = "Select * from Bestellung order by BestellungNr";
        List<Bestellung> list = new ArrayList<>();
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection con = databaseConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Bestellung b = createBestellung(rs);
                list.add(b);
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void deleteBestellung(Bestellung bestellung, String username, String password) throws SQLException {
        String sql = "DELETE FROM Bestellung WHERE BestellungNr = "+bestellung.getBestellungNr()+"";
        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.execute();
        ps.close();
    }

    public void insertBestellung(Bestellung b, String username, String password) throws SQLException {
        String sql = "INSERT INTO Bestellung VALUES (?,?,?,?,?,?,?)";

        connectionToBestellungTable(b, username, password, sql);
    }

    public void updateBestellung(Bestellung b, String username, String password) throws SQLException {
        String sql = "UPDATE Bestellung SET KundeNr = ?," +
                " Bestellstatus = ?," +
                " Bestelldatum = ?," +
                " Bedarfsdatum = ?," +
                " Versanddatum = ?," +
                " GeschäftNr = ?," +
                " MitarbeiterNr = ? WHERE BestellungNr = "+b.getBestellungNr();

        connectionToBestellungTable(b, username, password, sql);
    }

    private void connectionToBestellungTable(Bestellung b, String username, String password, String sql) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);



//        ps.setInt(1, b.getBestellungNr());
        ps.setInt(1, b.getKundeNr());
        ps.setInt(2, b.getBestellstatus());
        ps.setString(3, df.format(b.getBestelldatum()));
        ps.setString(4, df.format(b.getBedarfsdatum()));
        ps.setString(5, df.format(b.getVersanddatum()));
        ps.setInt(6, b.getGeschäftNr());
        ps.setInt(7, b.getMitarbeiterNr());
        ps.execute();
        ps.close();
    }
}
