package projet.classes.tables;

public class Personnes {
    protected int id; // Identifiant unique
    protected String nom;
    protected String prenom;
    protected String cin;
    protected String adresse;
    protected String telephone;
    protected String email;

    // Constructeur avec tous les paramètres
    public Personnes(int id, String nom, String prenom, String cin, String adresse, String telephone, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cin= cin;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
    }

    // Nouveau constructeur sans id
    public Personnes(String nom, String prenom, String cin, String adresse, String telephone, String email) {
        this.id = 0; // Initialisation par défaut pour id
        this.nom = nom;
        this.prenom = prenom;
        this.cin= cin;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
    }
    
    // Constructeur avec ID et CIN
    public Personnes(int id, String cin, String nom) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getCin() {
        return cin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
