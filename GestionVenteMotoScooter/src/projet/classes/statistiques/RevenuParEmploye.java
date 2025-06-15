package projet.classes.statistiques;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import projet.classes.connectionbase.ConnectionBase;

public class RevenuParEmploye {
    public static Map<String, Double> getRevenuParEmploye() {
        Map<String, Double> statistiques = new LinkedHashMap<>(); // Conservation de l'ordre DESC
        String sql = "SELECT e.nom_employe, SUM(a.prix_total) AS total_revenu " +
                     "FROM achat a " +
                     "JOIN employe e ON a.id_employe = e.id_employe " +
                     "GROUP BY e.id_employe " +
                     "ORDER BY total_revenu DESC";

        try (Connection conn = ConnectionBase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                statistiques.put(rs.getString("nom_employe"), rs.getDouble("total_revenu"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statistiques;
    }
}
