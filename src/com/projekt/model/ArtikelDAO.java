package com.projekt.model;

import com.projekt.connectivity.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
