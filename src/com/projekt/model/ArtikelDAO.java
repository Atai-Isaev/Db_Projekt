package com.projekt.model;

import com.projekt.connectivity.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtikelDAO {

    private Artikel createArtikel(ResultSet rs) {
        Artikel a = new Artikel();

        try {
            a.setArtikelNr(rs.getInt("ArtikelNr"));
            a.setArtikelName(rs.getString("ArtikelName"));
            a.setHerstellerNr(rs.getInt("HerstellerNr"));
            a.setKategorieNr(rs.getInt("KategorieNr"));
            a.setModelljahr(rs.getInt("Modelljahr"));
            a.setListenpreis(rs.getBigDecimal("Listenpreis"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;
    }

    public List<Artikel> getArtikels() {
        String sql = "Select * from Artikel order by ArtikelNr";
        List<Artikel> list = new ArrayList<>();
        try {

            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection con = databaseConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Artikel a = createArtikel(rs);
                list.add(a);
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void insertArtikel(Artikel a, String username, String password) throws SQLException {
        String sql = "INSERT INTO Artikel VALUES (?,?,?,?,?)";

        connectionToArtikelTable(a, username, password, sql);
    }

    public void updateArtikel(Artikel a, String username, String password) throws SQLException {
        String sql = "UPDATE Artikel SET ArtikelName = ?," +
                "HerstellerNr = ?," +
                "KategorieNr = ?," +
                "Modelljahr = ?," +
                "Listenpreis = ? " +
                "WHERE ArtikelNr = "+a.getArtikelNr()+"";

        connectionToArtikelTable(a, username, password, sql);
    }

    private void connectionToArtikelTable(Artikel a, String username, String password, String sql) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, a.getArtikelName());
        ps.setInt(2, a.getHerstellerNr());
        ps.setInt(3, a.getKategorieNr());
        ps.setInt(4, a.getModelljahr());
        ps.setBigDecimal(5, a.getListenpreis());
        ps.execute();
        ps.close();
    }

    public void deleteArtikel(Artikel a, String username, String password) throws SQLException {
        String sql = "DELETE FROM Artikel WHERE ArtikelNr = "+a.getArtikelNr()+"";
        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.execute();
        ps.close();
    }
}
