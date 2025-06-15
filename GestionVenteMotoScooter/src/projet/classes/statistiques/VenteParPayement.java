package projet.classes.statistiques;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import projet.classes.connectionbase.ConnectionBase;

public class VenteParPayement {
    public static Map<String, Integer> getVentesParPayement() {
        Map<String, Integer> statistiques = new LinkedHashMap<>(); // Conservation de l'ordre DESC
        String sql = "SELECT mode_paiement, COUNT(*) AS nombre_ventes " +
                     "FROM facture " +
                     "GROUP BY mode_paiement " +
                     "ORDER BY nombre_ventes DESC";

        try (Connection conn = ConnectionBase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                statistiques.put(rs.getString("mode_paiement"), rs.getInt("nombre_ventes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statistiques;
    }
}
