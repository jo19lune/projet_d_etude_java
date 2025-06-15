package projet.classes.statistiques;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import projet.classes.connectionbase.ConnectionBase;

public class TopVenteVehicule {
    public static Map<String, Integer> getTopVenteVehicule() {
        Map<String, Integer> classement = new LinkedHashMap<>(); // Conservation de l'ordre DESC
        String sql = "SELECT v.marque, v.model, COUNT(a.id_vehicule) AS total_ventes " +
                     "FROM achat a " +
                     "JOIN vehicule v ON a.id_vehicule = v.id_vehicule " +
                     "GROUP BY v.id_vehicule " +
                     "ORDER BY total_ventes DESC " +
                     "LIMIT 5";

        try (Connection conn = ConnectionBase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String vehicule = rs.getString("marque") + " " + rs.getString("model");
                classement.put(vehicule, rs.getInt("total_ventes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classement;
    }
}
