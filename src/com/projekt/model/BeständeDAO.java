package com.projekt.model;

import com.projekt.connectivity.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BeständeDAO {
    private Bestände createBestände(ResultSet rs) {
        Bestände b = new Bestände();

        try {
            b.setGeschäftNr(rs.getInt("GeschäftNr"));
            b.setArtikelNr(rs.getInt("ArtikelNr"));
            b.setMenge(rs.getInt("Menge"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return b;
    }

    public List<Bestände> getBeständes() {
        String sql = "Select * from Bestände";
        List<Bestände> list = new ArrayList<>();
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection con = databaseConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Bestände b = createBestände(rs);
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
