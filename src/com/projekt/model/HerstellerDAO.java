package com.projekt.model;

import com.projekt.connectivity.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
