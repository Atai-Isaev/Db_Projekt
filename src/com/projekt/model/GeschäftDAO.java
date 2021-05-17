package com.projekt.model;

import com.projekt.connectivity.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GeschäftDAO {
    private Geschäft createGeschäft(ResultSet rs) {
        Geschäft g = new Geschäft();

        try {
            g.setGeschäftNr(rs.getInt("GeschäftNr"));
            g.setGeschäftName(rs.getString("GeschäftName"));
            g.setTelefon(rs.getString("Telefon"));
            g.setEmail(rs.getString("Email"));
            g.setStraße(rs.getString("Straße"));
            g.setOrt(rs.getString("Ort"));
            g.setPLZ(rs.getString("PLZ"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return g;
    }

    public List<Geschäft> getGeschäfts() {
        String sql = "Select * from Geschäft order by GeschäftNr";
        List<Geschäft> list = new ArrayList<>();
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection con = databaseConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Geschäft g = createGeschäft(rs);
                list.add(g);
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
