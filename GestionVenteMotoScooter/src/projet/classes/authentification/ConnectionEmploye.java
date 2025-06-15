package projet.classes.authentification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import projet.classes.connectionbase.ConnectionBase;

public class ConnectionEmploye {

    private boolean authenticated = false;
    private int idEmploye;
    private String emailEmploye;

    public boolean verifierConnexion(String email, String password) {
        String requete = "SELECT id_employe, email_employe, mot_de_passe FROM employe WHERE email_employe = ?";
        try (Connection conn = ConnectionBase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(requete)) {

            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("mot_de_passe");

                    System.out.println("Email trouvé : " + email);
                    System.out.println("Mot de passe en base : " + storedPassword);
                    System.out.println("Mot de passe entré : " + password);

                    if (password.equals(storedPassword)) {
                        authenticated = true;
                        System.out.println("Authentification réussie !");
                    } else {
                        JOptionPane.showMessageDialog(null, "Mot de passe incorrect !.", "Erreur", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Aucun compte n'a été trouvé avec cet email !.", "Erreur", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur d'authentification : " + e.getMessage(), "Erreur SQL", JOptionPane.WARNING_MESSAGE);
        }
        return authenticated;
    }


    public boolean isAuthenticated() {
        return authenticated; //Retourne l'état de connexion
    }

    public int getIdEmploye() {
        return idEmploye; //Retourne l'ID de l'employé après connexion
    }

    public String getEmailEmploye() {
        return emailEmploye; //Retourne l'email de l'employé connecté
    }
}
