package projet.classes.controles;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class ValidationNumerique {
    private final int maxDigits;

    public ValidationNumerique(int maxDigits) {
        this.maxDigits = maxDigits;
    }

    public void keyTyped(KeyEvent e) {
        JTextField textField = (JTextField) e.getSource();

        // Vérifie si la touche pressée est un chiffre
        char keyChar = e.getKeyChar();
        if (!Character.isDigit(keyChar)) {
            e.consume(); // Bloque les caractères non numériques
            return;
        }

        // Vérifie si la longueur dépasse la limite
        if (textField.getText().length() >= maxDigits) {
            e.consume(); // Bloque l'entrée si la limite est atteinte
        }
    }
}
