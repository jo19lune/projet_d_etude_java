package projet.classes.controles;

import javax.swing.text.*;
import javax.swing.JOptionPane;

public class ValidationMix extends DocumentFilter {
    private final int limite;

    public ValidationMix(int limite) {
        this.limite = limite;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) return; // Vérifie que `str` n'est pas null

        str = str.trim(); // Supprime les espaces inutiles

        if ((fb.getDocument().getLength() + str.length()) <= limite) {
            super.insertString(fb, offset, str, attr);
        } else {
            afficherMessageErreur();
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) return; // Vérifie que `str` n'est pas null

        str = str.trim(); // Supprime les espaces inutiles

        if ((fb.getDocument().getLength() - length + str.length()) <= limite) {
            super.replace(fb, offset, length, str, attr);
        } else {
            afficherMessageErreur();
        }
    }

    // 🔹 Affiche un message d'erreur si la limite est atteinte
    private void afficherMessageErreur() {
        JOptionPane.showMessageDialog(null, "Vous avez atteint la limite de " + limite + " caractères.", "Avertissement", JOptionPane.WARNING_MESSAGE);
    }
}
