package projet.classes.tables;

import java.util.Objects;

public class Clients extends Personnes {

    // Constructeur avec tous les paramètres (y compris l'id)
    public Clients(int id, String nom, String prenom, String cin, String adresse, String telephone, String email) {
        super(id, nom, prenom, cin, adresse, telephone, email);
        validateEmail(email); // Validation optionnelle
    }

    // Constructeur sans l'id (id géré automatiquement ou non nécessaire)
    public Clients(String nom, String prenom, String cin, String adresse, String telephone, String email) {
        super(nom, prenom, cin, adresse, telephone, email); // Appelle le constructeur sans id de Personnes
        validateEmail(email); // Validation optionnelle
    }

    // Méthode pour valider l'email
    private void validateEmail(String email) {
        if (email != null && !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Email invalide !");
        }
    }
    
    // Constructeur pour Client
    public Clients(int id, String cin, String nom) {
        super(id, cin, nom); // Appelle le constructeur de la classe parent
    }

    // Méthode toString pour afficher les détails du client
    @Override
    public String toString() {
        return "Clients {" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", cin='" + cin + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    // Méthode equals pour comparer deux instances
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clients clients = (Clients) o;
        return id == clients.id &&
                nom.equals(clients.nom) &&
                prenom.equals(clients.prenom) &&
                cin.equals(clients.cin) &&
                adresse.equals(clients.adresse) &&
                telephone.equals(clients.telephone) &&
                email.equals(clients.email);
    }

    // Méthode hashCode pour générer un hash unique
    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, cin, adresse, telephone, email);
    }
}
