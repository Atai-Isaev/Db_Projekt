package com.projekt.model;

import com.projekt.connectivity.DatabaseConnection;

import java.sql.*;
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

    public void deleteGeschäft(Geschäft geschäft, String username, String password) throws SQLException {
        String sql = "DELETE FROM Geschäft WHERE GeschäftNr = "+geschäft.getGeschäftNr()+"";
        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.execute();
        ps.close();
    }

    public void insertGeschäft(Geschäft a, String username, String password) throws SQLException {
        String sql = "INSERT INTO Geschäft VALUES (?,?,?,?,?,?)";

        connectionToGeschäftTable(a, username, password, sql);
    }

    public void updateGeschäft(Geschäft a, String username, String password) throws SQLException {
        String sql = "UPDATE Geschäft SET GeschäftName = ?," +
                "Telefon = ?," +
                "Email = ?," +
                "Straße = ?," +
                "Ort = ?, " +
                "PLZ = ? " +
                "WHERE GeschäftNr = "+a.getGeschäftNr()+"";

        connectionToGeschäftTable(a, username, password, sql);
    }

    private void connectionToGeschäftTable(Geschäft a, String username, String password, String sql) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, a.getGeschäftName());
        ps.setString(2, a.getTelefon());
        ps.setString(3, a.getEmail());
        ps.setString(4, a.getStraße());
        ps.setString(5, a.getOrt());
        ps.setInt(6, Integer.parseInt(a.getPLZ()));
        ps.execute();
        ps.close();
    }
}
