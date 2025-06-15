package projet.classes.gestion;

import projet.classes.tables.Clients;
import projet.classes.connectionbase.ConnectionBase;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionClients {
    private final ConnectionBase connexion; // Gestion de la connexion à la base de données

    // Constructeur pour initialiser la connexion
    public GestionClients() throws Exception {
        connexion = new ConnectionBase();
    }

    // Méthode pour insérer un nouveau client
    public void insert(Clients client) throws SQLException {
        String query = "INSERT INTO client (nom_client, prenom_client, cin_client, adresse, telephone, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getPrenom());
            statement.setString(3, client.getCin());
            statement.setString(4, client.getAdresse());
            statement.setString(5, client.getTelephone());
            statement.setString(6, client.getEmail());
            statement.executeUpdate();
            System.out.println("Client ajouté avec succès !");
        }
    }

    // Méthode pour mettre à jour un client existant
    public void update(int id, Clients client) throws SQLException {
        String query = "UPDATE client SET nom_client = ?, prenom_client = ?, cin_client = ?, adresse = ?, telephone = ?, email = ? WHERE id_client = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getPrenom());
            statement.setString(3, client.getCin());
            statement.setString(4, client.getAdresse());
            statement.setString(5, client.getTelephone());
            statement.setString(6, client.getEmail());
            statement.setInt(7, id);
            statement.executeUpdate();
            System.out.println("Client mis à jour avec succès !");
        }
    }

    // Méthode pour supprimer un client par son ID
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM client WHERE id_client = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Client supprimé avec succès !");
        }
    }

    // Méthode pour récupérer un client par son ID
    public Clients selectById(int id) throws SQLException {
        String query = "SELECT * FROM client WHERE id_client = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Clients(
                        rs.getInt("id_client"),
                        rs.getString("nom_client"),
                        rs.getString("prenom_client"),
                        rs.getString("cin_client"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getString("email")
                    );
                }
            }
        }
        return null; // Retourne null si le client n'est pas trouvé
    }

    // Méthode pour récupérer tous les clients
    public List<Clients> selectAll() throws SQLException {
        List<Clients> clients = new ArrayList<>();
        String query = "SELECT * FROM client";
        try (Connection conn = connexion.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                clients.add(new Clients(
                    rs.getInt("id_client"),             // ID du client
                    rs.getString("nom_client"),         // Nom
                    rs.getString("prenom_client"),      // Prénom
                    rs.getString("cin_client"),         // CIN
                    rs.getString("adresse"),            // Adresse
                    rs.getString("telephone"),          // Téléphone
                    rs.getString("email")               // Email
                ));
            }
        }
        return clients;
    }


    // Méthode pour charger les clients dans un JTable
    public void chargerTableau(JTable tableau) {
        String titre[] = {"ID", "Nom", "Prénom", "Cin", "Adresse", "Téléphone", "Email"};
        Object[][] enreg;

        try {
            List<Clients> clients = selectAll();
            enreg = new Object[clients.size()][titre.length];
            int i = 0;
            for (Clients client : clients) {
                enreg[i][0] = client.getId();
                enreg[i][1] = client.getNom();
                enreg[i][2] = client.getPrenom();
                enreg[i][3] = client.getCin();
                enreg[i][4] = client.getAdresse();
                enreg[i][5] = client.getTelephone();
                enreg[i][6] = client.getEmail();
                i++;
            }
            tableau.setModel(new DefaultTableModel(enreg, titre));
        } catch (SQLException ex) {
            System.err.println("Erreur SQL lors du chargement du tableau : " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println("Erreur inattendue : " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Méthode pour une recherche aléatoire
    public List<Clients> rechercheAleatoire(String keyword) throws SQLException {
        List<Clients> clients = new ArrayList<>();
        String query = "SELECT * FROM client WHERE id_client LIKE ? OR nom_client LIKE ? OR prenom_client LIKE ? OR cin_client LIKE ? OR adresse LIKE ? OR telephone LIKE ? OR email LIKE ?";

        try (Connection conn = ConnectionBase.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            for (int i = 1; i <= 7; i++) {
                statement.setString(i, "%" + keyword + "%");
            }

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    clients.add(new Clients(
                        rs.getInt("id_client"),
                        rs.getString("nom_client"),
                        rs.getString("prenom_client"),
                        rs.getString("cin_client"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getString("email")
                    ));
                }
            }
        }
        return clients;
    }

    public Clients getClientById(int id) throws SQLException {
        String query = "SELECT id_client, cin_client, nom_client FROM client WHERE id_client = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Clients(rs.getInt("id_client"), rs.getString("cin_client"), rs.getString("nom_client"));
                }
            }
        }
        return null; // Retourne null si aucun client n'est trouvé
    }

    public Clients getClientByCin(String cin) throws SQLException {
        String query = "SELECT id_client, cin_client, nom_client FROM client WHERE cin_client = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, cin);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Clients(rs.getInt("id_client"), rs.getString("cin_client"), rs.getString("nom_client"));
                }
            }
        }
        return null; // Retourne null si aucun client n'est trouvé
    }

}
