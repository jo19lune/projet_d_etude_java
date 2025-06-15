package projet.classes.connectionbase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBase {
    // Méthode pour obtenir une nouvelle connexion
    public static Connection getConnection() throws SQLException {
        try {
            // Charger dynamiquement le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC introuvable : " + e.getMessage());
        }

        // Retourner une nouvelle connexion
        return DriverManager.getConnection(Constantes.URL, Constantes.USER, Constantes.PASSWORD);
    }
}
