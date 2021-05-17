package com.projekt.model;

import com.projekt.connectivity.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BestellungDAO {
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
}
