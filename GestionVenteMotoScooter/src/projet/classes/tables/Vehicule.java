package projet.classes.tables;

import java.math.BigDecimal; // Import nécessaire pour le type prix

public class Vehicule {
    private int idVehicule;          // Identifiant unique du véhicule
    private String marque;           // Marque du véhicule (ex : Yamaha, Honda)
    private String model;            // Modèle spécifique du véhicule
    private int cyl;                 // Cylindrée du véhicule (ex : 125, 500)
    private String moteur;           // Type de moteur (ex : Essence, Électrique)
    private String categorie;        // Catégorie (ex : Moto, Scooter, Tout-terrain)
    private int stock;               // Nombre de véhicules disponibles en stock
    private BigDecimal prix;         // Prix du véhicule

    // Constructeur par défaut
    public Vehicule() {
    }

    // Constructeur avec tous les paramètres (incluant prix)
    public Vehicule(int idVehicule, String marque, String model, int cyl, String moteur, String categorie, int stock, BigDecimal prix) {
        this.idVehicule = idVehicule;
        this.marque = marque;
        this.model = model;
        this.cyl = cyl;
        this.moteur = moteur;
        this.categorie = categorie;
        this.stock = stock;
        this.prix = prix;
    }

    public Vehicule(String marque, String model, int cyl, String moteur, String categorie, int stock, BigDecimal prix) {
        this.marque = marque;
        this.model = model;
        this.cyl = cyl;
        this.moteur = moteur;
        this.categorie = categorie;
        this.stock = stock;
        this.prix = prix;
    }

    // Getters et Setters
    public int getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCyl() {
        return cyl;
    }

    public void setCyl(int cyl) {
        this.cyl = cyl;
    }

    public String getMoteur() {
        return moteur;
    }

    public void setMoteur(String moteur) {
        this.moteur = moteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    // Méthode toString pour afficher les informations du véhicule
    @Override
    public String toString() {
        return "Vehicule {" +
                "idVehicule=" + idVehicule +
                ", marque='" + marque + '\'' +
                ", model='" + model + '\'' +
                ", cyl=" + cyl +
                ", moteur='" + moteur + '\'' +
                ", categorie='" + categorie + '\'' +
                ", stock=" + stock +
                ", prix=" + prix +
                '}';
    }
}
