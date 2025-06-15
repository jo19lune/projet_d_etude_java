package projet.classes.tables;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Facture {
    private int idFacture;             // Identifiant unique de la facture
    private int idClient;              // Référence au client
    private int idEmploye;             // Référence à l'employé
    private LocalDate dateFacturation; // Date d'émission de la facture
    private ModePaiement modePaiement; // Mode de paiement
    private BigDecimal montantTotal;   // Montant total de la facture

    // Enumération pour les modes de paiement
    public enum ModePaiement {
        ESPECES, CHEQUE, CARTE_CREDIT, BANCAIRE;
    }

    // Constructeur par défaut
    public Facture() {
    }

    // Constructeur avec tous les paramètres
    public Facture(int idFacture, int idClient, int idEmploye, LocalDate dateFacturation, ModePaiement modePaiement, BigDecimal montantTotal) {
        if (montantTotal.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le montant total doit être positif.");
        }
        this.idFacture = idFacture;
        this.idClient = idClient;
        this.idEmploye = idEmploye;
        this.dateFacturation = dateFacturation;
        this.modePaiement = modePaiement;
        this.montantTotal = montantTotal;
    }
    
    // Constructeur avec tous les paramètres
    public Facture(int idClient, int idEmploye, LocalDate dateFacturation, ModePaiement modePaiement, BigDecimal montantTotal) {
        if (montantTotal.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le montant total doit être positif.");
        }
        this.idClient = idClient;
        this.idEmploye = idEmploye;
        this.dateFacturation = dateFacturation;
        this.modePaiement = modePaiement;
        this.montantTotal = montantTotal;
    }

    // Getters et Setters
    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
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

    public LocalDate getDateFacturation() {
        return dateFacturation;
    }

    public void setDateFacturation(LocalDate dateFacturation) {
        this.dateFacturation = dateFacturation;
    }

    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        if (montantTotal.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le montant total doit être positif.");
        }
        this.montantTotal = montantTotal;
    }

    // Méthode toString pour afficher les informations de la facture
    @Override
    public String toString() {
        return "Facture {" +
                "idFacture=" + idFacture +
                ", idClient=" + idClient +
                ", idEmploye=" + idEmploye +
                ", dateFacturation=" + dateFacturation +
                ", modePaiement=" + modePaiement +
                ", montantTotal=" + montantTotal +
                '}';
    }
}
