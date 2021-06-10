package com.projekt.model;

import com.projekt.connectivity.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bestellung_ArtikelDAO {
    private Bestellung_Artikel createBestellung_Artikel(ResultSet rs) {
        Bestellung_Artikel ba = new Bestellung_Artikel();

        try {
            ba.setBestellungNr(rs.getInt("BestellungNr"));
            ba.setArtikelNr(rs.getInt("ArtikelNr"));
            ba.setMenge(rs.getInt("Menge"));
            ba.setListenpreis(rs.getBigDecimal("Listenpreis"));
            ba.setRabatt(rs.getBigDecimal("Rabatt"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ba;
    }

    public List<Bestellung_Artikel> getBestellung_Artikels() {
        String sql = "Select * from Bestellung_Artikel order by BestellungNr";
        List<Bestellung_Artikel> list = new ArrayList<>();
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection con = databaseConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Bestellung_Artikel ba = createBestellung_Artikel(rs);
                list.add(ba);
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void deleteBestellungArtikel(Bestellung_Artikel bestellung_artikel, String username, String password) throws SQLException {
        String sql = "DELETE FROM Bestellung_Artikel WHERE ArtikelNr = "+bestellung_artikel.getArtikelNr()+" AND BestellungNr = "+bestellung_artikel.getBestellungNr()+"";
        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.execute();
        ps.close();
    }
}
