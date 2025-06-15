package projet.classes.controles;

import javax.swing.*;
import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

public class ValidationChaine {
    private final int maxLength;

    public ValidationChaine(int maxLength) {
        this.maxLength = maxLength;
    }
    
    public void keyTyped(KeyEvent e) {
        JTextField textField = (JTextField) e.getSource();
        char keyChar = e.getKeyChar();

        // Bloquer les caractères non alphabétiques
        if (!Character.isLetter(keyChar)) {
            e.consume(); // Bloque l'entrée des caractères non valides
            return;
        }

        // Limiter à maxLength caractères
        if (textField.getText().length() >= maxLength) {
            e.consume(); // Bloque l'entrée si la limite est atteinte
        }
    }
}
