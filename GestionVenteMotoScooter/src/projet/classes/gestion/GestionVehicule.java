package projet.classes.gestion;

import projet.classes.tables.Vehicule;
import projet.classes.connectionbase.ConnectionBase;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class GestionVehicule {
    private final ConnectionBase connexion;

    // Constructeur pour initialiser la connexion
    public GestionVehicule() throws Exception {
        connexion = new ConnectionBase();
    }

    // Méthode pour insérer un nouveau véhicule
    public void insert(Vehicule vehicule) throws SQLException {
        String query = "INSERT INTO vehicule (marque, model, cyl, moteur, categorie, stock, prix) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, vehicule.getMarque());
            statement.setString(2, vehicule.getModel());
            statement.setInt(3, vehicule.getCyl());
            statement.setString(4, vehicule.getMoteur());
            statement.setString(5, vehicule.getCategorie());
            statement.setInt(6, vehicule.getStock());
            statement.setBigDecimal(7, vehicule.getPrix());
            statement.executeUpdate();
            System.out.println("Véhicule ajouté avec succès !");
        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'insertion du véhicule : " + ex.getMessage());
            throw ex;
        }
    }

    // Méthode pour mettre à jour un véhicule existant
    public void update(int id, Vehicule vehicule) throws SQLException {
        String query = "UPDATE vehicule SET marque = ?, model = ?, cyl = ?, moteur = ?, categorie = ?, stock = ?, prix = ? WHERE id_vehicule = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, vehicule.getMarque());
            statement.setString(2, vehicule.getModel());
            statement.setInt(3, vehicule.getCyl());
            statement.setString(4, vehicule.getMoteur());
            statement.setString(5, vehicule.getCategorie());
            statement.setInt(6, vehicule.getStock());
            statement.setBigDecimal(7, vehicule.getPrix());
            statement.setInt(8, id);
            statement.executeUpdate();
            System.out.println("Véhicule mis à jour avec succès !");
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la mise à jour du véhicule : " + ex.getMessage());
            throw ex;
        }
    }
    
    // Méthode pour supprimer un véhicule par son ID
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM vehicule WHERE id_vehicule = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id); // Définit l'identifiant du véhicule à supprimer
            int rowsAffected = statement.executeUpdate(); // Exécute la requête
            if (rowsAffected > 0) {
                System.out.println("Véhicule avec l'ID " + id + " supprimé avec succès !");
            } else {
                System.out.println("Aucun véhicule trouvé avec l'ID " + id);
            }
        }
    }


    // Méthode pour récupérer tous les véhicules
    public List<Vehicule> selectAll() throws SQLException {
        List<Vehicule> vehicules = new ArrayList<>();
        String query = "SELECT * FROM vehicule";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                vehicules.add(new Vehicule(
                    rs.getInt("id_vehicule"),
                    rs.getString("marque"),
                    rs.getString("model"),
                    rs.getInt("cyl"),
                    rs.getString("moteur"),
                    rs.getString("categorie"),
                    rs.getInt("stock"),
                    rs.getBigDecimal("prix") // Récupérer le prix
                ));
            }
        }
        return vehicules;
    }

    // Méthode pour charger les données dans un JTable
    public void chargerTableau(JTable tableau) {
        String[] titre = {"ID", "Marque", "Modèle", "Cylindrée", "Moteur", "Catégorie", "Stock", "Prix"};
        Object[][] enreg;

        try {
            List<Vehicule> vehicules = selectAll();
            enreg = new Object[vehicules.size()][titre.length];
            int i = 0;
            for (Vehicule vehicule : vehicules) {
                enreg[i][0] = vehicule.getIdVehicule();
                enreg[i][1] = vehicule.getMarque();
                enreg[i][2] = vehicule.getModel();
                enreg[i][3] = vehicule.getCyl();
                enreg[i][4] = vehicule.getMoteur();
                enreg[i][5] = vehicule.getCategorie();
                enreg[i][6] = vehicule.getStock();
                enreg[i][7] = vehicule.getPrix();
                i++;
            }
            tableau.setModel(new DefaultTableModel(enreg, titre));
        } catch (SQLException ex) {
            System.err.println("Erreur lors du chargement des données dans le tableau : " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<Vehicule> rechercheAleatoire(String keyword) throws SQLException {
        List<Vehicule> vehicules = new ArrayList<>();
        String query = "SELECT * FROM vehicule WHERE marque LIKE ? OR model LIKE ? OR categorie LIKE ? OR moteur LIKE ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            for (int i = 1; i <= 4; i++) {
                statement.setString(i, "%" + keyword + "%");
            }
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    vehicules.add(new Vehicule(
                        rs.getInt("id_vehicule"),
                        rs.getString("marque"),
                        rs.getString("model"),
                        rs.getInt("cyl"),
                        rs.getString("moteur"),
                        rs.getString("categorie"),
                        rs.getInt("stock"),
                        rs.getBigDecimal("prix") // Inclusion du champ prix
                    ));
                }
            }
        }
        return vehicules;
    }
    
    public List<Vehicule> rechercheParCategorie(String categorie) throws SQLException {
        List<Vehicule> vehicules = new ArrayList<>();
        String query = "SELECT * FROM vehicule WHERE categorie LIKE ?";

        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            // Ajouter le mot-clé avec un wildcard pour une recherche flexible
            statement.setString(1, "%" + categorie + "%");

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    vehicules.add(new Vehicule(
                        rs.getInt("id_vehicule"),       // ID du véhicule
                        rs.getString("marque"),        // Marque du véhicule
                        rs.getString("model"),         // Modèle
                        rs.getInt("cyl"),              // Cylindrée
                        rs.getString("moteur"),        // Type de moteur
                        rs.getString("categorie"),     // Catégorie
                        rs.getInt("stock"),            // Stock disponible
                        rs.getBigDecimal("prix")       // Prix du véhicule
                    ));
                }
            }
        }
        return vehicules;
    }


    
    public List<Vehicule> rechercheParMarque(String marque) throws SQLException {
        List<Vehicule> vehicules = new ArrayList<>();
        String query = "SELECT * FROM vehicule WHERE marque LIKE ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, "%" + marque + "%");
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    vehicules.add(new Vehicule(
                        rs.getInt("id_vehicule"),
                        rs.getString("marque"),
                        rs.getString("model"),
                        rs.getInt("cyl"),
                        rs.getString("moteur"),
                        rs.getString("categorie"),
                        rs.getInt("stock"),
                        rs.getBigDecimal("prix") // Inclusion du champ prix
                    ));
                }
            }
        }
        return vehicules;
    }

    
    public List<String> getCategories() throws SQLException {
        List<String> categories = new ArrayList<>();
        String query = "SELECT DISTINCT categorie FROM vehicule";

        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                categories.add(rs.getString("categorie"));
            }
        }
        return categories;
    }
    
    public List<String> getMarquesByCategorie(String categorie) throws SQLException {
        List<String> marques = new ArrayList<>();
        String query = "SELECT DISTINCT marque FROM vehicule WHERE categorie = ?";

        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, categorie);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    marques.add(rs.getString("marque"));
                }
            }
        }
        return marques;
    }

    public List<String> getModelesByMarque(String categorie, String marque) throws SQLException {
        List<String> modeles = new ArrayList<>();
        String query = "SELECT DISTINCT model FROM vehicule WHERE categorie = ? and marque = ?";

        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, categorie);
            statement.setString(2, marque);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    modeles.add(rs.getString("model"));
                }
            }
        }
        return modeles;
    }

    public Vehicule getVehiculeByModele(String modele) throws SQLException {
        if (modele == null || modele.trim().isEmpty()) {
            throw new IllegalArgumentException("Le modèle ne peut pas être vide ou nul.");
        }

        Vehicule vehicule = null;
        String query = "SELECT * FROM vehicule WHERE model = ?";

        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, modele);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    vehicule = new Vehicule(
                        rs.getInt("id_vehicule"),
                        rs.getString("marque"),
                        rs.getString("model"),
                        rs.getInt("cyl"),
                        rs.getString("moteur"),
                        rs.getString("categorie"),
                        rs.getInt("stock"),
                        rs.getBigDecimal("prix") // Inclusion du prix récupéré depuis la base
                    );
                } else {
                    System.err.println("Aucun véhicule trouvé pour le modèle : " + modele);
                }
            }
        }
        return vehicule;
    }

    public int getStock(int idVehiculeValue) throws SQLException {
        String query = "SELECT stock FROM vehicule WHERE id_vehicule = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, idVehiculeValue);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("stock");
                }
            }
        }
        return 0; // 🔹 Retourne 0 si le véhicule n'est pas trouvé
    }

    public void updateStock(int idVehiculeValue, int quantiteModifiee) throws SQLException {
        String query = "UPDATE vehicule SET stock = stock + ? WHERE id_vehicule = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, quantiteModifiee);
            statement.setInt(2, idVehiculeValue);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Stock du véhicule mis à jour avec succès !");
            } else {
                System.out.println("Échec de la mise à jour : véhicule introuvable.");
            }
        }
    }

}
