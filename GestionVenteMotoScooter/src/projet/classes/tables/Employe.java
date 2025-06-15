package projet.classes.tables;

import java.math.BigDecimal;
import java.util.Date;

public class Employe extends Personnes {
    private String motDePasse;          // Mot de passe de l'employé
    private String posteEmploye;        // Poste occupé (ex : Vendeur, Responsable)
    private BigDecimal salaire;         // Salaire de l'employé
    private Date dateEmbauche;          // Date d'embauche

    // Constructeur par défaut
    public Employe() {
        super(0, "", "", "", "", "", ""); // Appel au constructeur par défaut de la classe parent
        this.motDePasse = "";
        this.posteEmploye = "";
        this.salaire = BigDecimal.ZERO;
        this.dateEmbauche = new Date();
    }

    // Constructeur avec tous les paramètres (incluant id)
    public Employe(int id, String nom, String prenom, String cin, String adresseEmploye, String telephone, String email,
                   String motDePasse, String posteEmploye, BigDecimal salaire, Date dateEmbauche) {
        super(id, nom, prenom, cin, adresseEmploye, telephone, email); // Appel au constructeur de la classe parent
        this.motDePasse = motDePasse;
        this.posteEmploye = posteEmploye;
        this.salaire = salaire;
        this.dateEmbauche = dateEmbauche;
    }
    
    // Constructeur sans mot de passe
    public Employe(int id, String nom, String prenom, String cin, String adresseEmploye, String telephone, String email,
                   String posteEmploye, BigDecimal salaire, Date dateEmbauche) {
        super(id, nom, prenom, cin, adresseEmploye, telephone, email); // Appel au constructeur de la classe parent
        this.posteEmploye = posteEmploye;
        this.salaire = salaire;
        this.dateEmbauche = dateEmbauche;
    }

    // Nouveau constructeur sans id
    public Employe(String nom, String prenom, String cin, String adresseEmploye, String telephone, String email,
                   String motDePasse, String posteEmploye, BigDecimal salaire, Date dateEmbauche) {
        super(nom, prenom, cin, adresseEmploye, telephone, email); // Appelle le constructeur sans id de Personnes
        this.motDePasse = motDePasse;
        this.posteEmploye = posteEmploye;
        this.salaire = salaire;
        this.dateEmbauche = dateEmbauche;
    }
    
    public Employe(int id, String posteEmploye, String nom, String cin) {
        super(id, cin, nom);
        this.posteEmploye = posteEmploye;
    }

    // Getters et Setters pour les attributs spécifiques à Employe
    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getPosteEmploye() {
        return posteEmploye;
    }

    public void setPosteEmploye(String posteEmploye) {
        this.posteEmploye = posteEmploye;
    }

    public BigDecimal getSalaire() {
        return salaire;
    }

    public void setSalaire(BigDecimal salaire) {
        this.salaire = salaire;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    // Méthode toString pour afficher les informations de l'employé
    @Override
    public String toString() {
        return "Employe {" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", cin='" + cin + '\'' +
                ", adresseEmploye='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", posteEmploye='" + posteEmploye + '\'' +
                ", salaire=" + salaire +
                ", dateEmbauche=" + dateEmbauche +
                '}';
    }
}
