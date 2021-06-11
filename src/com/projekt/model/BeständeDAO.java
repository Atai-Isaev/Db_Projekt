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

    public void insertBestände(Bestände b, String username, String password) throws SQLException {
        String sql = "INSERT INTO Bestände VALUES (?,?,?)";

        connectionToBeständeTable(b, username, password, sql);
    }

    public void updateBestände(Bestände b, String username, String password) throws SQLException {
        String sql = "UPDATE Bestände SET Menge = ? WHERE GeschäftNr = "+b.getGeschäftNr()+" AND ArtikelNr = "+b.getArtikelNr();

        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, b.getMenge());
        ps.execute();
        ps.close();
    }

    private void connectionToBeständeTable(Bestände b, String username, String password, String sql) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, b.getGeschäftNr());
        ps.setInt(2, b.getArtikelNr());
        ps.setInt(3, b.getMenge());
        ps.execute();
        ps.close();
    }
}
