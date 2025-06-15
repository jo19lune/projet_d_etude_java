package projet.classes.gestion;

import projet.classes.tables.Facture;
import projet.classes.connectionbase.ConnectionBase;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GestionFacture {
    private final ConnectionBase connexion; // Gestion de la connexion à la base de données

    // Constructeur pour initialiser la connexion
    public GestionFacture() throws Exception {
        connexion = new ConnectionBase();
    }

    // Méthode pour insérer une nouvelle facture
    public void insert(Facture facture) throws SQLException {
        String query = "INSERT INTO facture (id_client, id_employe, date_facturation, mode_paiement, montant_total) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, facture.getIdClient());
            statement.setInt(2, facture.getIdEmploye());
            statement.setDate(3, Date.valueOf(facture.getDateFacturation())); // Utilise LocalDate
            statement.setString(4, facture.getModePaiement().toString()); // Utilise ModePaiement enum
            statement.setBigDecimal(5, facture.getMontantTotal());
            statement.executeUpdate();
            System.out.println("Facture ajoutée avec succès !");
        }
    }

    public void updateModePaiement(int idFacture, String nouveauModePaiement) throws SQLException {
        String query = "UPDATE facture SET mode_paiement = ? WHERE id_facture = ?";

        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, nouveauModePaiement); // 🔹 Met à jour uniquement le mode de paiement
            statement.setInt(2, idFacture); // 🔹 Cible la facture concernée

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Mode de paiement mis à jour avec succès !");
            } else {
                System.out.println("Aucune facture trouvée avec cet ID.");
            }
        }
    }

    // Méthode pour supprimer une facture par son ID
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM facture WHERE id_facture = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Facture supprimée avec succès !");
        }
    }

    // Méthode pour récupérer une facture par son ID
    public Facture selectById(int id) throws SQLException {
        String query = "SELECT * FROM facture WHERE id_facture = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Facture(
                        rs.getInt("id_facture"),
                        rs.getInt("id_client"),
                        rs.getInt("id_employe"),
                        rs.getDate("date_facturation").toLocalDate(), // Conversion en LocalDate
                        Facture.ModePaiement.valueOf(rs.getString("mode_paiement")), // Conversion en ModePaiement enum
                        rs.getBigDecimal("montant_total")
                    );
                }
            }
        }
        return null; // Retourne null si la facture n'est pas trouvée
    }
    
    // Méthode pour récupérer toutes les factures
    public List<Facture> selectAll() throws SQLException {
        List<Facture> factures = new ArrayList<>();
        String query = "SELECT * FROM facture";
        try (Connection conn = connexion.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                factures.add(new Facture(
                    rs.getInt("id_facture"),
                    rs.getInt("id_client"),
                    rs.getInt("id_employe"),
                    rs.getDate("date_facturation").toLocalDate(), // Conversion en LocalDate
                    Facture.ModePaiement.valueOf(rs.getString("mode_paiement")), // Conversion en ModePaiement enum
                    rs.getBigDecimal("montant_total")
                ));
            }
        }
        return factures;
    }
    
    public List<Facture> selectAllHist() throws SQLException {
        List<Facture> factures = new ArrayList<>();
        String query = "SELECT * FROM facture order by date_facturation";
        try (Connection conn = connexion.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                factures.add(new Facture(
                    rs.getInt("id_facture"),
                    rs.getInt("id_client"),
                    rs.getInt("id_employe"),
                    rs.getDate("date_facturation").toLocalDate(), // Conversion en LocalDate
                    Facture.ModePaiement.valueOf(rs.getString("mode_paiement")), // Conversion en ModePaiement enum
                    rs.getBigDecimal("montant_total")
                ));
            }
        }
        return factures;
    }

    // Méthode pour charger les factures dans un JTable
    public void chargerTableau(JTable tableau) {
        String[] titre = {"ID", "Client ID", "Employé ID", "Date Facturation", "Mode Paiement", "Montant Total"};
        Object[][] enreg;

        try {
            List<Facture> factures = selectAll();
            enreg = new Object[factures.size()][titre.length];
            int i = 0;
            for (Facture facture : factures) {
                enreg[i][0] = facture.getIdFacture();
                enreg[i][1] = facture.getIdClient();
                enreg[i][2] = facture.getIdEmploye();
                enreg[i][3] = facture.getDateFacturation();
                enreg[i][4] = facture.getModePaiement().toString();
                enreg[i][5] = facture.getMontantTotal();
                i++;
            }
            tableau.setModel(new DefaultTableModel(enreg, titre));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    //
    public List<Object[]> getAchatsByFactureId(int idFacture) {
        List<Object[]> achats = new ArrayList<>();
        String query = "SELECT id_vente, achat.id_vehicule,categorie, marque, model, quantite, prix_total FROM achat inner join vehicule on achat.id_vehicule = vehicule.id_vehicule WHERE id_facture = ?";

        try (Connection conn = ConnectionBase.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, idFacture);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    // Chaque ligne est stockée sous forme d'array d'objets
                    achats.add(new Object[] {
                        rs.getInt("id_vente"),
                        rs.getInt("id_vehicule"),
                        rs.getString("categorie"),
                        rs.getString("marque"),
                        rs.getString("model"),
                        rs.getInt("quantite"),
                        rs.getBigDecimal("prix_total")
                    });
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement des achats : " + ex.getMessage());
        }

        return achats;
    }

    public int insertEtRetournerId(Facture facture) throws SQLException {
        String query = "INSERT INTO facture (id_client, id_employe, date_facturation, mode_paiement, montant_total) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, facture.getIdClient());
            statement.setInt(2, facture.getIdEmploye());
            statement.setDate(3, Date.valueOf(facture.getDateFacturation()));
            statement.setString(4, facture.getModePaiement().toString());
            statement.setBigDecimal(5, facture.getMontantTotal());
            statement.executeUpdate();

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Retourne l'id_facture généré
                }
            }
        }
        throw new SQLException("Échec de l'insertion de la facture.");
    }

    public int getLastIdFacture() throws SQLException {
        String query = "SELECT MAX(id_facture) AS last_id FROM facture"; // Récupérer le dernier ID
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("last_id")+1; // Retourne l'ID maximum
            }
        }
        return 0; // Retourne 0 si aucun ID n'est trouvé
    }

    public void chargerHistorique(JTable tableau) {
        String[] titre = {"ID", "Client ID", "Employé ID", "Date Facturation", "Mode Paiement", "Montant Total"};
        Object[][] enreg;

        try {
            List<Facture> factures = selectAllHist();
            enreg = new Object[factures.size()][titre.length];
            int i = 0;
            for (Facture facture : factures) {
                enreg[i][0] = facture.getIdFacture();
                enreg[i][1] = facture.getIdClient();
                enreg[i][2] = facture.getIdEmploye();
                enreg[i][3] = facture.getDateFacturation();
                enreg[i][4] = facture.getModePaiement().toString();
                enreg[i][5] = facture.getMontantTotal();
                i++;
            }
            tableau.setModel(new DefaultTableModel(enreg, titre));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
