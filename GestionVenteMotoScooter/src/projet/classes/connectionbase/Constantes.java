package projet.classes.connectionbase;

public interface Constantes {
    public final String HOST = "localhost";
    public final String PORT = ":3306/";
    public final String BASE = "gestion_vente_moto_scooter";
    public final String TIMEZONE = "?useLegacyDateTimeCode=false&serverTimezone=Africa/Nairobi";
    public final String URL = "jdbc:mysql://" + HOST + PORT + BASE + TIMEZONE;
    public final String USER = "root";
    public final String PASSWORD = "";
}