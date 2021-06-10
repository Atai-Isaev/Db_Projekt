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
}
