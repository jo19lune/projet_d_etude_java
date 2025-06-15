package projet.form.authentification;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import projet.classes.authentification.ConnectionEmploye;
import projet.classes.gestion.GestionEmploye;
import projet.form.main.Main;

public class Authentification extends JFrame {

    private boolean authenticated = false;

    public Authentification() {
        initComponents();
        setTitle("Connexion Employé");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void verifierAuthentification() {
        String email = personne.getText();
        String password = new String(mot_de_passe.getPassword());

        ConnectionEmploye connection = new ConnectionEmploye();

        if (connection.verifierConnexion(email, password)) {
            emailEmploye = email;
            authenticated = true;

            JOptionPane.showMessageDialog(this, "Connexion réussie !");
            
            ouvrirFenetrePrincipale();
        } else {
            authenticated = false;
            JOptionPane.showMessageDialog(this, "Erreur : Identifiants incorrects", "Échec de connexion", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ouvrirFenetrePrincipale() {
        try {
            dispose();
            Main mainForm = new Main();
            GestionEmploye gestionEmploye = new GestionEmploye();
            String posteEmploye = gestionEmploye.getPosteEmploye(emailEmploye);
            String nomEmploye = gestionEmploye.getNomEmploye(emailEmploye);
            mainForm.setEmployeInfo(nomEmploye, posteEmploye);
            mainForm.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        personne = new javax.swing.JTextField();
        mot_de_passe = new javax.swing.JPasswordField();
        btnConx = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(241, 97));
        setMinimumSize(new java.awt.Dimension(241, 97));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(personne, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 230, -1));
        jPanel1.add(mot_de_passe, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 230, -1));

        btnConx.setText("Se connecter");
        btnConx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConxActionPerformed(evt);
            }
        });
        jPanel1.add(btnConx, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 140, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/mail_64dp_000000_FILL0_wght400_GRAD0_opsz48.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 65;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 80, 31, 97);
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnConxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConxActionPerformed
        if (personne.getText().isEmpty() || Arrays.toString(mot_de_passe.getPassword()).isEmpty()){
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.WARNING_MESSAGE);
            return;
        }
        verifierAuthentification();
    }//GEN-LAST:event_btnConxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConx;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField mot_de_passe;
    private javax.swing.JTextField personne;
    // End of variables declaration//GEN-END:variables

    public boolean isAuthenticated() {
        return authenticated;
    }

    private String emailEmploye;

    public String getEmailEmploye() {
        return emailEmploye;
    }

}