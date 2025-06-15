package projet.classes.tables;

import java.math.BigDecimal;
import java.util.Date;

public class Achat {
    private int idVente;              // Identifiant unique de la vente
    private Date dateVente;           // Date de la vente
    private int idClient;             // Référence au client
    private int idEmploye;            // Référence à l'employé
    private int idVehicule;           // Référence au véhicule vendu
    private int idFacture;            // Référence à la facture
    private int quantite;             // Quantité vendue
    private BigDecimal prixTotal;     // Total de la vente (PRIX * QUANTITE)

    // Constructeur par défaut
    public Achat() {
    }

    // Constructeur avec tous les paramètres
    public Achat(int idVente, Date dateVente, int idClient, int idEmploye, int idVehicule,
                 int idFacture, int quantite, BigDecimal prixTotal) {
        this.idVente = idVente;
        this.dateVente = dateVente;
        this.idClient = idClient;
        this.idEmploye = idEmploye;
        this.idVehicule = idVehicule;
        this.idFacture = idFacture;
        this.quantite = quantite;
        this.prixTotal = prixTotal;
    }

    // Nouveau constructeur sans idVente
    public Achat(Date dateVente, int idClient, int idEmploye, int idVehicule,
                 int idFacture, int quantite, BigDecimal prixTotal) {
        this.dateVente = dateVente;
        this.idClient = idClient;
        this.idEmploye = idEmploye;
        this.idVehicule = idVehicule;
        this.idFacture = idFacture;
        this.quantite = quantite;
        this.prixTotal = prixTotal;
    }

    // Getters et Setters
    public int getIdVente() {
        return idVente;
    }

    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }

    public Date getDateVente() {
        return dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public int getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    // Méthode toString pour afficher les informations de l'achat
    @Override
    public String toString() {
        return "Achat {" +
                "idVente=" + idVente +
                ", dateVente=" + dateVente +
                ", idClient=" + idClient +
                ", idEmploye=" + idEmploye +
                ", idVehicule=" + idVehicule +
                ", idFacture=" + idFacture +
                ", quantite=" + quantite +
                ", prixTotal=" + prixTotal +
                '}';
    }
}
