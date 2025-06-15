package projet.classes.statistiques;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import projet.classes.connectionbase.ConnectionBase;

public class ClassementEmploye {
    public static Map<String, Integer> getClassementEmployes() {
        Map<String, Integer> classement = new LinkedHashMap<>(); // Conservation de l'ordre DESC
        String sql = "SELECT e.nom_employe, COUNT(a.id_vente) AS nombre_ventes " +
                     "FROM achat a " +
                     "JOIN employe e ON a.id_employe = e.id_employe " +
                     "GROUP BY e.id_employe " +
                     "ORDER BY nombre_ventes DESC";

        try (Connection conn = ConnectionBase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                classement.put(rs.getString("nom_employe"), rs.getInt("nombre_ventes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classement;
    }
}
