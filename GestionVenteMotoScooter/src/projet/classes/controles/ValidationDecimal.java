package projet.classes.controles;

public class ValidationDecimal {

    /**
     * Vérifie si une chaîne est un nombre décimal valide et supérieur à 0.
     * @param input La valeur à tester.
     * @param maxDecimals Nombre maximal de décimales autorisées.
     * @return true si le nombre est valide et > 0, sinon false.
     */
    public static boolean isValidDecimal(String input, int maxDecimals) {
        if (input == null || input.trim().isEmpty()) {
            return true; // ✅ Ne rien faire si le champ est vide
        }

        try {
            // Vérification du format décimal
            double number = Double.parseDouble(input);

            // 🔥 Vérifie que la valeur est bien supérieure à 0
            if (number <= 0) {
                return false;
            }

            // Vérification du nombre de décimales
            String[] parts = input.split("\\.");
            if (parts.length == 2 && parts[1].length() > maxDecimals) {
                return false; // Trop de décimales
            }

            return true; // Format correct et nombre valide
        } catch (NumberFormatException e) {
            return false; // Retourne false si la conversion échoue
        }
    }
    
    /**
     * Vérifie si une chaîne représente un nombre valide.
     * Ne fait rien si la chaîne est vide.
     * @param input La valeur à tester.
     * @param strictMode true = vérifie si positif, false = accepte tous les nombres.
     * @return true si la valeur est valide ou vide, false sinon.
     */
    public static boolean isValidNumber(String input, boolean strictMode) {
        if (input == null || input.trim().isEmpty()) {
            return true; // ✅ Ne rien faire si le champ est vide
        }

        try {
            double number = Double.parseDouble(input);

            // 🔥 Vérifie si strictMode est activé (uniquement positif)
            if (strictMode && number <= 0) {
                return false;
            }

            return true; // ✅ Format correct
        } catch (NumberFormatException e) {
            return false; // 🚫 Échec si la conversion en nombre échoue
        }
    }
}
