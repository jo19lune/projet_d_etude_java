package projet.classes.connectionbase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionBase2 {
    private static Connection connection = null; // Connexion unique (singleton)

    public static Connection getConnection() {
        if (connection == null) { // Vérifie si la connexion n'a pas déjà été établie
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Charger le driver JDBC
                connection = DriverManager.getConnection(Constantes.URL, Constantes.USER, Constantes.PASSWORD); // Utilisation de MesConstantes
                System.out.println("Connexion réussie à la base de données !");
            } catch (ClassNotFoundException e) {
                System.err.println("Driver JDBC introuvable : " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Erreur lors de la connexion : " + e.getMessage());
            }
        }
        return connection;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement stat = null; // Déclarer le Statement
        ResultSet res = null; // Déclarer le ResultSet

        try {
            Connection conn = getConnection(); // Obtenir la connexion
            stat = conn.createStatement(); // Initialiser le Statement
            res = stat.executeQuery(query); // Exécuter la requête
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            throw e; // Relancer l'exception
        }
        return res;
    }

    public int executeUpdate(String query) throws SQLException {
        Statement stat = null; // Déclarer le Statement
        int res = 0; // Initialiser le résultat

        try {
            Connection conn = getConnection(); // Obtenir la connexion
            stat = conn.createStatement(); // Initialiser le Statement
            res = stat.executeUpdate(query); // Exécuter la requête
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'exécution de la requête UPDATE/INSERT/DELETE : " + e.getMessage());
            throw e; // Relancer l'exception
        } finally {
            if (stat != null) {
                try {
                    stat.close(); // Fermer le Statement
                } catch (SQLException e) {
                    System.err.println("Erreur lors de la fermeture du Statement : " + e.getMessage());
                }
            }
        }
        return res;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close(); // Fermer la connexion
                connection = null; // Réinitialiser la variable connexion
                System.out.println("Connexion fermée avec succès.");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}
