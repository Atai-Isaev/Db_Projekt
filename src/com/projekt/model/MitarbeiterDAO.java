package com.projekt.model;

import com.projekt.connectivity.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            m.setGeschäftsführerNr(rs.getInt("GeschäftsführerNr"));
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
}
