package com.projekt.model;

import com.projekt.connectivity.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KundeDAO {
    private Kunde createKunde(ResultSet rs) {
        Kunde k = new Kunde();

        try {
            k.setKundeNr(rs.getInt("KundeNr"));
            k.setVorname(rs.getString("Vorname"));
            k.setNachname(rs.getString("Nachname"));
            k.setTelefon(rs.getString("Telefon"));
            k.setEmail(rs.getString("Email"));
            k.setStraße(rs.getString("Straße"));
            k.setOrt(rs.getString("Ort"));
            k.setPLZ(rs.getString("PLZ"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return k;
    }

    public List<Kunde> getKundes() {
        String sql = "Select * from Kunde order by KundeNr";
        List<Kunde> list = new ArrayList<>();
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection con = databaseConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Kunde k = createKunde(rs);
                list.add(k);
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void deleteKunde(Kunde kunde, String username, String password) throws SQLException {
        String sql = "DELETE FROM Kunde WHERE KundeNr = "+kunde.getKundeNr()+"";
        DatabaseConnection databaseConnection = new DatabaseConnection(username, password);
        Connection con = databaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.execute();
        ps.close();
    }
}
