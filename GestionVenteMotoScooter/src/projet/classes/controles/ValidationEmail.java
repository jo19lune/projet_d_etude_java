package projet.classes.controles;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationEmail {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Valide une adresse e-mail.
     *
     * @param email L'adresse e-mail à valider.
     * @param parent La fenêtre parente pour afficher le message en cas d'erreur (facultatif).
     * @param errorMessage Message d'erreur personnalisé (facultatif).
     * @return true si l'e-mail est valide ou vide (optionnel), sinon false.
     */
    public static boolean validateEmail(String email, JFrame parent, String errorMessage) {
        // Vérifie si l'e-mail est nul ou vide
        if (email == null || email.trim().isEmpty()) {
            return true; // Considéré comme valide si aucune saisie
        }

        // Supprime les espaces autour de l'e-mail
        email = email.trim();

        try {
            Matcher matcher = PATTERN.matcher(email);
            if (!matcher.matches()) {
                // Affiche un message d'erreur si l'e-mail est invalide
                if (parent != null) {
                    JOptionPane.showMessageDialog(
                        parent,
                        errorMessage != null ? errorMessage : "L'adresse e-mail est invalide. Veuillez vérifier le format.",
                        "Erreur de validation",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
                return false;
            }
        } catch (Exception e) {
            if (parent != null) {
                JOptionPane.showMessageDialog(
                    parent,
                    "Une erreur s'est produite lors de la validation de l'adresse e-mail : " + e.getMessage(),
                    "Erreur inattendue",
                    JOptionPane.ERROR_MESSAGE
                );
            }
            return false;
        }

        return true; // Retourne true si l'e-mail est valide
    }
}
