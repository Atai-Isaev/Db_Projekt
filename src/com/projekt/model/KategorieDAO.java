package com.projekt.model;

import com.projekt.connectivity.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KategorieDAO {
    private Kategorie createKategorie(ResultSet rs) {
        Kategorie k = new Kategorie();

        try {
            k.setKategorieNr(rs.getInt("KategorieNr"));
            k.setKategorieName(rs.getString("KategorieName"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return k;
    }

    public List<Kategorie> getKategories() {
        String sql = "Select * from Kategorie order by KategorieNr";
        List<Kategorie> list = new ArrayList<>();
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection con = databaseConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Kategorie k = createKategorie(rs);
                list.add(k);
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void deleteKategorie(Kategorie kategorie, String username, String password) throws SQLException {
        String sql = "DELETE FROM Kategorie WHERE KategorieNr = "+kategorie.getKategorieNr()+"";
        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.execute();
        ps.close();
    }
}
