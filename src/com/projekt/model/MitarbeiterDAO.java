package com.projekt.model;

import com.projekt.connectivity.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MitarbeiterDAO {
    private Mitarbeiter createMitarbeiter(ResultSet rs) {
        Mitarbeiter m = new Mitarbeiter();

        try {
            m.setMitarbeiterNr(rs.getInt("MitarbeiterNr"));
            m.setVorname(rs.getString("Vorname"));
            m.setNachname(rs.getString("Nachname"));
            m.setEmail(rs.getString("Email"));
            m.setTelefon(rs.getString("Telefon"));
            m.setAktiv(rs.getInt("Aktiv"));
            m.setGeschäftNr(rs.getInt("GeschäftNr"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return m;
    }

    public List<Mitarbeiter> getMitarbeiters() {
        String sql = "Select * from Mitarbeiter order by MitarbeiterNr";
        List<Mitarbeiter> list = new ArrayList<>();
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection con = databaseConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Mitarbeiter m = createMitarbeiter(rs);
                list.add(m);
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void deleteMitarbeiter(Mitarbeiter mitarbeiter, String username, String password) throws SQLException {
        String sql = "DELETE FROM Mitarbeiter WHERE MitarbeiterNr = "+mitarbeiter.getMitarbeiterNr()+"";
        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.execute();
        ps.close();
    }

    public void insertMitarbeiter(Mitarbeiter a, String username, String password) throws SQLException {
        String sql = "INSERT INTO Mitarbeiter VALUES (?,?,?,?,?,?)";

        connectionToMitarbeiterTable(a, username, password, sql);
    }

    public void updateMitarbeiter(Mitarbeiter a, String username, String password) throws SQLException {
        String sql = "UPDATE Mitarbeiter SET Vorname = ?," +
                "Nachname = ?," +
                "Email = ?," +
                "Telefon = ?," +
                "Aktiv = ?, " +
                "GeschäftNr = ? " +
                "WHERE MitarbeiterNr = "+a.getMitarbeiterNr()+"";

        connectionToMitarbeiterTable(a, username, password, sql);
    }

    private void connectionToMitarbeiterTable(Mitarbeiter a, String username, String password, String sql) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, a.getVorname());
        ps.setString(2, a.getNachname());
        ps.setString(3, a.getEmail());
        ps.setString(4, a.getTelefon());
        ps.setInt(5, a.getAktiv());
        ps.setInt(6, a.getGeschäftNr());
        ps.execute();
        ps.close();
    }
}
