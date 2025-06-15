package projet.classes.gestion;

import projet.classes.tables.Achat;
import projet.classes.connectionbase.ConnectionBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GestionAchat {
    private final ConnectionBase connexion;

    // Constructeur pour initialiser la connexion
    public GestionAchat() throws Exception {
        connexion = new ConnectionBase();
    }

    // Méthode pour insérer un achat et mettre à jour le stock
    public void insert(Achat achat) throws SQLException {
        String queryInsert = "INSERT INTO achat (date_vente, id_client, id_employe, id_vehicule, id_facture, quantite, prix_total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String queryUpdateStock = "UPDATE vehicule SET stock = stock - ? WHERE id_vehicule = ?";

        try (Connection conn = connexion.getConnection();
             PreparedStatement statementInsert = conn.prepareStatement(queryInsert);
             PreparedStatement statementUpdateStock = conn.prepareStatement(queryUpdateStock)) {

            // Insérer les données de l'achat
            statementInsert.setDate(1, new java.sql.Date(achat.getDateVente().getTime()));
            statementInsert.setInt(2, achat.getIdClient());
            statementInsert.setInt(3, achat.getIdEmploye());
            statementInsert.setInt(4, achat.getIdVehicule());
            statementInsert.setInt(5, achat.getIdFacture());
            statementInsert.setInt(6, achat.getQuantite());
            statementInsert.setBigDecimal(7, achat.getPrixTotal());
            statementInsert.executeUpdate();

            // Mettre à jour le stock
            statementUpdateStock.setInt(1, achat.getQuantite());
            statementUpdateStock.setInt(2, achat.getIdVehicule());
            statementUpdateStock.executeUpdate();

            System.out.println("Achat inséré et stock mis à jour avec succès !");
        }
    }

    // Méthode pour récupérer un achat par son ID
    public Achat selectById(int id) throws SQLException {
        String query = "SELECT * FROM achat WHERE id_vente = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Achat(
                        rs.getInt("id_vente"),
                        rs.getDate("date_vente"),
                        rs.getInt("id_client"),
                        rs.getInt("id_employe"),
                        rs.getInt("id_vehicule"),
                        rs.getInt("id_facture"),
                        rs.getInt("quantite"),
                        rs.getBigDecimal("prix_total")
                    );
                }
            }
        }
        return null; // Retourne null si l'achat n'est pas trouvé
    }

    // Méthode pour récupérer tous les achats
    public List<Achat> selectAll() throws SQLException {
        List<Achat> achats = new ArrayList<>();
        String query = "SELECT * FROM achat";

        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                achats.add(new Achat(
                    rs.getInt("id_vente"),
                    rs.getDate("date_vente"),
                    rs.getInt("id_client"),
                    rs.getInt("id_employe"),
                    rs.getInt("id_vehicule"),
                    rs.getInt("id_facture"),
                    rs.getInt("quantite"),
                    rs.getBigDecimal("prix_total")
                ));
            }
        }
        return achats;
    }
    
    public List<Achat> selectAllHist() throws SQLException {
        List<Achat> achats = new ArrayList<>();
        String query = "SELECT id_vente, date_vente, id_client, id_employe, id_vehicule, id_facture, sum(quantite) as quantite, sum(prix_total) as prix_total FROM achat group by id_facture order by date_vente";

        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                achats.add(new Achat(
                    rs.getInt("id_vente"),
                    rs.getDate("date_vente"),
                    rs.getInt("id_client"),
                    rs.getInt("id_employe"),
                    rs.getInt("id_vehicule"),
                    rs.getInt("id_facture"),
                    rs.getInt("quantite"),
                    rs.getBigDecimal("prix_total")
                ));
            }
        }
        return achats;
    }

    // Méthode pour supprimer un achat et ajuster le stock
    public void delete(int id, int idVehicule, int quantite) throws SQLException {
        String queryDelete = "DELETE FROM achat WHERE id_vente = ?";
        String queryUpdateStock = "UPDATE vehicule SET stock = stock + ? WHERE id_vehicule = ?";

        try (Connection conn = connexion.getConnection();
             PreparedStatement statementDelete = conn.prepareStatement(queryDelete);
             PreparedStatement statementUpdateStock = conn.prepareStatement(queryUpdateStock)) {

            // Supprimer l'achat
            statementDelete.setInt(1, id);
            statementDelete.executeUpdate();

            // Ajuster le stock du véhicule
            statementUpdateStock.setInt(1, quantite);
            statementUpdateStock.setInt(2, idVehicule);
            statementUpdateStock.executeUpdate();

            System.out.println("Achat supprimé et stock ajusté !");
        }
    }

    // Méthode pour récupérer le stock d'un véhicule
    public int getStock(int idVehicule) throws SQLException {
        String query = "SELECT stock FROM vehicule WHERE id_vehicule = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, idVehicule);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("stock");
                }
            }
        }
        return 0; // Retourne 0 si aucun stock n'est trouvé
    }
    
    public void updateAchat(Achat achat) throws SQLException {
        String queryUpdate = "UPDATE achat SET date_vente = ?, id_client = ?, id_employe = ?, id_vehicule = ?, id_facture = ?, quantite = ?, prix_total = ? WHERE id_vente = ?";

        try (Connection conn = connexion.getConnection();
             PreparedStatement statementUpdate = conn.prepareStatement(queryUpdate)) {

            // Mettre à jour les valeurs
            statementUpdate.setDate(1, new java.sql.Date(achat.getDateVente().getTime()));
            statementUpdate.setInt(2, achat.getIdClient());
            statementUpdate.setInt(3, achat.getIdEmploye());
            statementUpdate.setInt(4, achat.getIdVehicule());
            statementUpdate.setInt(5, achat.getIdFacture());
            statementUpdate.setInt(6, achat.getQuantite());
            statementUpdate.setBigDecimal(7, achat.getPrixTotal());
            statementUpdate.setInt(8, achat.getIdVente()); // 🔹 Identifiant de l'achat à modifier

            // Exécuter la requête de mise à jour
            int rowsAffected = statementUpdate.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Achat mis à jour avec succès !");
            } else {
                System.out.println("Échec de la mise à jour : aucun achat correspondant trouvé.");
            }
        }
    }

    public void chargerHistorique(JTable tableau) {
        String[] titre = {"ID Vente", "Date vente", "ID Client", "ID Employe", "ID Vehicule", "ID Facture", "Quantité", "Prix Total"};
        Object[][] enreg;
        try {
            List<Achat> achats = selectAllHist();
            enreg = new Object[achats.size()][titre.length];
            
            int i = 0;

            for (Achat achat : achats) {
                enreg[i][0] = achat.getIdVente();
                enreg[i][1] = achat.getDateVente();
                enreg[i][2] = achat.getIdClient();
                enreg[i][3] = achat.getIdEmploye();
                enreg[i][4] = achat.getIdVehicule();
                enreg[i][5] = achat.getIdFacture();
                enreg[i][6] = achat.getQuantite();
                enreg[i][7] = achat.getPrixTotal();
                i++;
            }

            tableau.setModel(new javax.swing.table.DefaultTableModel(enreg, titre));

        } catch (SQLException ex) {
            System.err.println("Erreur lors du chargement des achats : " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
