package projet.classes.gestion;

import projet.classes.tables.Employe;
import projet.classes.connectionbase.ConnectionBase;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionEmploye {
    private final ConnectionBase connexion; // Gestion de la connexion à la base de données

    // Constructeur pour initialiser la connexion
    public GestionEmploye() throws Exception {
        connexion = new ConnectionBase();
    }

    // Méthode pour insérer un nouvel employé
    public void insert(Employe employe) throws SQLException {
        String query = "INSERT INTO employe (nom_employe, prenom_employe, cin_employe, adresse_employe, telephone_employe, email_employe, mot_de_passe, poste_employe, salaire, date_embauche) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, employe.getNom());
            statement.setString(2, employe.getPrenom());
            statement.setString(3, employe.getCin());
            statement.setString(4, employe.getAdresse()); // Remplace adresse par adresse_employe
            statement.setString(5, employe.getTelephone());
            statement.setString(6, employe.getEmail());
            statement.setString(7, employe.getMotDePasse());
            statement.setString(8, employe.getPosteEmploye());
            statement.setBigDecimal(9, employe.getSalaire());
            statement.setDate(10, new java.sql.Date(employe.getDateEmbauche().getTime()));
            statement.executeUpdate();
            System.out.println("Employé ajouté avec succès !");
        }
    }

    // Méthode pour mettre à jour un employé existant
    public void update(int id, Employe employe) throws SQLException {
        String query = "UPDATE employe SET nom_employe = ?, prenom_employe = ?, cin_employe = ?, adresse_employe = ?, telephone_employe = ?, email_employe = ?, mot_de_passe = ?, poste_employe = ?, salaire = ?, date_embauche = ? " +
                "WHERE id_employe = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, employe.getNom());
            statement.setString(2, employe.getPrenom());
            statement.setString(3, employe.getCin());
            statement.setString(4, employe.getAdresse()); // Remplace adresse par adresse_employe
            statement.setString(5, employe.getTelephone());
            statement.setString(6, employe.getEmail());
            statement.setString(7, employe.getMotDePasse());
            statement.setString(8, employe.getPosteEmploye());
            statement.setBigDecimal(9, employe.getSalaire());
            statement.setDate(10, new java.sql.Date(employe.getDateEmbauche().getTime()));
            statement.setInt(11, id);
            statement.executeUpdate();
            System.out.println("Employé mis à jour avec succès !");
        }
    }

    // Méthode pour supprimer un employé par son ID
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM employe WHERE id_employe = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Employé supprimé avec succès !");
        }
    }

    // Méthode pour récupérer un employé par son ID
    public Employe selectById(int id) throws SQLException {
        String query = "SELECT * FROM employe WHERE id_employe = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Employe(
                            rs.getInt("id_employe"),
                            rs.getString("nom_employe"),
                            rs.getString("prenom_employe"),
                            rs.getString("cin_employe"),
                            rs.getString("adresse_employe"), 
                            rs.getString("telephone_employe"),
                            rs.getString("email_employe"),
                            rs.getString("mot_de_passe"),
                            rs.getString("poste_employe"),
                            rs.getBigDecimal("salaire"),
                            rs.getDate("date_embauche")
                    );
                }
            }
        }
        return null; // Retourne null si l'employé n'est pas trouvé
    }

    // Méthode pour récupérer tous les employés
    public List<Employe> selectAll() throws SQLException {
        List<Employe> employes = new ArrayList<>();
        String query = "SELECT id_employe, nom_employe, prenom_employe, cin_employe, adresse_employe, telephone_employe, email_employe, poste_employe, salaire, date_embauche FROM employe";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                employes.add(new Employe(
                        rs.getInt("id_employe"),
                        rs.getString("nom_employe"),
                        rs.getString("prenom_employe"),
                        rs.getString("cin_employe"),
                        rs.getString("adresse_employe"),
                        rs.getString("telephone_employe"),
                        rs.getString("email_employe"),
                        rs.getString("poste_employe"),
                        rs.getBigDecimal("salaire"),
                        rs.getDate("date_embauche")
                ));
            }
        }
        return employes;
    }

    // Méthode pour charger les employés dans un JTable
    public void chargerTableau(JTable tableau) {
        String[] titre = {"ID", "Nom", "Prénom", "Cin", "Adresse", "Téléphone", "Email", "Poste", "Salaire", "Date Embauche"};
        Object[][] enreg;

        try {
            // Récupérer les employés via la méthode `selectAll`
            List<Employe> employes = selectAll();
            enreg = new Object[employes.size()][titre.length];
            int i = 0;

            for (Employe employe : employes) {
                enreg[i][0] = employe.getId();
                enreg[i][1] = employe.getNom();
                enreg[i][2] = employe.getPrenom();
                enreg[i][3] = employe.getCin();
                enreg[i][4] = employe.getAdresse();
                enreg[i][5] = employe.getTelephone();
                enreg[i][6] = employe.getEmail();
                enreg[i][7] = employe.getPosteEmploye();
                enreg[i][8] = employe.getSalaire();
                enreg[i][9] = employe.getDateEmbauche();
                i++;
            }

            // Appliquer le modèle au tableau
            tableau.setModel(new DefaultTableModel(enreg, titre));
        } catch (SQLException ex) {
            System.err.println("Erreur lors du chargement des employés : " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public List<Employe> selectAllHist() throws SQLException {
        List<Employe> employes = new ArrayList<>();
        String query = "SELECT id_employe, nom_employe, prenom_employe, cin_employe, adresse_employe, telephone_employe, email_employe, poste_employe, salaire, date_embauche FROM employe order by date_embauche";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                employes.add(new Employe(
                        rs.getInt("id_employe"),
                        rs.getString("nom_employe"),
                        rs.getString("prenom_employe"),
                        rs.getString("cin_employe"),
                        rs.getString("adresse_employe"),
                        rs.getString("telephone_employe"),
                        rs.getString("email_employe"),
                        rs.getString("poste_employe"),
                        rs.getBigDecimal("salaire"),
                        rs.getDate("date_embauche")
                ));
            }
        }
        return employes;
    }

    public void chargerHistorique(JTable tableau) {
        String[] titre = {"ID", "Nom", "Prénom", "Cin", "Adresse", "Téléphone", "Email", "Poste", "Salaire", "Date Embauche"};
        Object[][] enreg;

        try {
            // Récupérer les employés via la méthode `selectAll`
            List<Employe> employes = selectAllHist();
            enreg = new Object[employes.size()][titre.length];
            int i = 0;

            for (Employe employe : employes) {
                enreg[i][0] = employe.getId();
                enreg[i][1] = employe.getNom();
                enreg[i][2] = employe.getPrenom();
                enreg[i][3] = employe.getCin();
                enreg[i][4] = employe.getAdresse();
                enreg[i][5] = employe.getTelephone();
                enreg[i][6] = employe.getEmail();
                enreg[i][7] = employe.getPosteEmploye();
                enreg[i][8] = employe.getSalaire();
                enreg[i][9] = employe.getDateEmbauche();
                i++;
            }

            // Appliquer le modèle au tableau
            tableau.setModel(new DefaultTableModel(enreg, titre));
        } catch (SQLException ex) {
            System.err.println("Erreur lors du chargement des employés : " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    //recherche aleatoire
    public List<Employe> rechercheAleatoire(String keyword) throws SQLException {
        List<Employe> employes = new ArrayList<>();
        String query = "SELECT id_employe, nom_employe, prenom_employe, cin_employe, adresse_employe, telephone_employe, email_employe, poste_employe, salaire, date_embauche " +
                       "FROM employe WHERE id_employe LIKE ? OR nom_employe LIKE ? OR prenom_employe LIKE ? OR cin_employe LIKE ? " +
                       "OR adresse_employe LIKE ? OR telephone_employe LIKE ? OR email_employe LIKE ? OR poste_employe LIKE ? OR salaire LIKE ? OR date_embauche LIKE ?";

        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            // Appliquer le mot-clé sur toutes les colonnes spécifiées
            for (int i = 1; i <= 10; i++) {
                statement.setString(i, "%" + keyword + "%");
            }

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    employes.add(new Employe(
                        rs.getInt("id_employe"),
                        rs.getString("nom_employe"),
                        rs.getString("prenom_employe"),
                        rs.getString("cin_employe"),
                        rs.getString("adresse_employe"),
                        rs.getString("telephone_employe"),
                        rs.getString("email_employe"),
                        rs.getString("poste_employe"),
                        rs.getBigDecimal("salaire"),
                        rs.getDate("date_embauche")
                    ));
                }
            }
        }
        return employes; // Retourne la liste des employés
    }

    public Employe getEmployeById(int id) throws SQLException {
        String query = "SELECT id_employe, cin_employe, poste_employe, nom_employe FROM employe WHERE id_employe = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id); // Assigner l'ID

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    // Ajuster l'ordre des arguments pour correspondre au constructeur
                    return new Employe(
                        rs.getInt("id_employe"),
                        rs.getString("poste_employe"), // Poste employé
                        rs.getString("nom_employe"),   // Nom employé
                        rs.getString("cin_employe")    // CIN employé
                    );
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erreur SQL dans getEmployeById : " + ex.getMessage());
            throw ex;
        }
        return null; // Si aucun employé n'est trouvé
    }


    public Employe getEmployeByPoste(String poste) throws SQLException {
        String query = "SELECT id_employe, cin_employe, poste_employe, nom_employe FROM employe WHERE poste_employe = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, poste); // Assigner le poste

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    // Ajuster l'ordre des arguments pour correspondre au constructeur
                    return new Employe(
                        rs.getInt("id_employe"),
                        rs.getString("poste_employe"), // Poste employé
                        rs.getString("nom_employe"),   // Nom employé
                        rs.getString("cin_employe")    // CIN employé
                    );
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erreur SQL dans getEmployeByPoste : " + ex.getMessage());
            throw ex;
        }
        return null; // Si aucun employé n'est trouvé
    }

    public String getPosteEmploye(String email) {
        String poste = null; // Initialisation du poste

        String sql = "SELECT poste_employe FROM employe WHERE email_employe = ?";

        try (Connection conn = connexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                poste = rs.getString("poste_employe"); // Récupération du poste
            }

            rs.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du poste : " + e.getMessage());
        }

        return poste; // Retourne le poste (ou `null` si aucun employé trouvé)
    }

    public String getNomEmploye(String emailEmploye) {
        String nomEmploye = null; // 🔹 Initialisation du nom

        String sql = "SELECT nom_employe FROM employe WHERE email_employe = ?";

        try (Connection conn = connexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, emailEmploye);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                nomEmploye = rs.getString("nom_employe"); // 🔹 Récupération du nom
            }

            rs.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du nom : " + e.getMessage());
        }

        return nomEmploye; // 🔹 Retourne le nom ou `null` si aucun employé trouvé
    }


}
