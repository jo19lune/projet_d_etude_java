package projet.classes.statistiques;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import projet.classes.connectionbase.ConnectionBase;

public class VenteParCategorie {
    public static Map<String, Integer> getVentesParCategorie() {
        Map<String, Integer> statistiques = new LinkedHashMap<>(); // Conservation de l'ordre
        String sql = "SELECT v.categorie, COUNT(a.id_vehicule) AS nombre_ventes " +
                     "FROM achat a " +
                     "JOIN vehicule v ON a.id_vehicule = v.id_vehicule " +
                     "GROUP BY v.categorie";

        try (Connection conn = ConnectionBase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                statistiques.put(rs.getString("categorie"), rs.getInt("nombre_ventes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statistiques;
    }
}

