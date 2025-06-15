package projet.form.panel;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import projet.classes.gestion.GestionAchat;
import projet.classes.gestion.GestionEmploye;
import projet.classes.gestion.GestionFacture;

public class PanelHistorique extends javax.swing.JPanel {

    public PanelHistorique() {
        initComponents();
        
        chargerTableEmploye();
        chargerTableFacture();
        chargerTableVente();
    }
    
    private void chargerTableEmploye(){
        try {
            // Initialiser la classe GestionEmploye
            GestionEmploye gestionEmploye = new GestionEmploye();

            // Appeler la méthode chargerHistorique
            gestionEmploye.chargerHistorique(tableHistEmbauche);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'actualisation du tableau des historiques des employés : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Une erreur inattendue s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void chargerTableFacture(){
        try {
            // Initialiser 
            GestionFacture gestionFact = new GestionFacture();

            // Appeler la méthode chargerHistorique pour actualiser maTableEmp
            gestionFact.chargerHistorique(tableHistFact);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'actualisation du tableau des historiques des Factures : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Une erreur inattendue s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void chargerTableVente(){
        try {
            // Initialiser
            GestionAchat gestionAchat = new GestionAchat();

            // Appeler la méthode chargerHistorique pour actualiser maTableEmp
            gestionAchat.chargerHistorique(tableHistVente);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'actualisation du tableau des historiques des Achats : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Une erreur inattendue s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        actualiser = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHistFact = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableHistVente = new javax.swing.JTable();
        pageHistEmp = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableHistEmbauche = new javax.swing.JTable();

        setBackground(new java.awt.Color(0, 0, 255));
        setMinimumSize(new java.awt.Dimension(760, 600));

        actualiser.setBackground(new java.awt.Color(0, 180, 219));
        actualiser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        actualiser.setForeground(new java.awt.Color(227, 227, 227));
        actualiser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/autorenew_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        actualiser.setText("Actualiser");
        actualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualiserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(actualiser)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(actualiser)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tableHistFact.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableHistFact);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 748, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane2.addTab("Mes Factures", jPanel2);

        tableHistVente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableHistVente);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 748, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane2.addTab("Mes Ventes", jPanel3);

        tableHistEmbauche.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tableHistEmbauche);

        javax.swing.GroupLayout pageHistEmpLayout = new javax.swing.GroupLayout(pageHistEmp);
        pageHistEmp.setLayout(pageHistEmpLayout);
        pageHistEmpLayout.setHorizontalGroup(
            pageHistEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 748, Short.MAX_VALUE)
            .addGroup(pageHistEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pageHistEmpLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pageHistEmpLayout.setVerticalGroup(
            pageHistEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
            .addGroup(pageHistEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pageHistEmpLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane2.addTab("Mes Employes", pageHistEmp);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void actualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualiserActionPerformed
        chargerTableEmploye();
        chargerTableFacture();
        chargerTableVente();
    }//GEN-LAST:event_actualiserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualiser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPanel pageHistEmp;
    private javax.swing.JTable tableHistEmbauche;
    private javax.swing.JTable tableHistFact;
    private javax.swing.JTable tableHistVente;
    // End of variables declaration//GEN-END:variables
}
