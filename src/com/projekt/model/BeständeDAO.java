package com.projekt.model;

import com.projekt.connectivity.DatabaseConnection;

import java.sql.*;
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
        String sql = "Select * from Bestände order by GeschäftNr";
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

    public void deleteBestände(Bestände bestände, String username, String password) throws SQLException {
        String sql = "DELETE FROM Bestände WHERE ArtikelNr = "+bestände.getArtikelNr()+" AND GeschäftNr = "+bestände.getGeschäftNr()+"";
        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.execute();
        ps.close();
    }
}
