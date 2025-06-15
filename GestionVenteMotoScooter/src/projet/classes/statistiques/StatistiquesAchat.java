package projet.classes.statistiques;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import projet.classes.connectionbase.ConnectionBase;

public class StatistiquesAchat {

    public static Map<String, Double> getVentesMensuelles() {
        Map<String, Double> statistiques = new LinkedHashMap<>();
        String sql = "SELECT DATE_FORMAT(date_vente, '%Y-%m') AS mois, " +
                     "SUM(prix_total) AS total_revenu " +
                     "FROM achat " +
                     "GROUP BY mois " +
                     "ORDER BY YEAR(date_vente), mois";

        try (Connection conn = ConnectionBase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                statistiques.put(rs.getString("mois"), rs.getDouble("total_revenu"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des statistiques des achats.", e); // ✅ Meilleure gestion des erreurs
        }

        return statistiques;
    }
}
