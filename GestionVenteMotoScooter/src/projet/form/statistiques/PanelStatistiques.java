package projet.form.statistiques;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class PanelStatistiques extends javax.swing.JPanel {

    private PanelEvolutionVente stat1;
    private PanelRevenuEmploye stat2;
    private PanelRepartitionVente stat3;
    private PanelClassementEmploye stat4;
    private PanelVenteParPayement stat5;
    private PanelTopVenteVehicule stat6;
    
    public PanelStatistiques() {
        initComponents();

        stat1 = new PanelEvolutionVente();
        stat2 = new PanelRevenuEmploye();
        stat3 = new PanelRepartitionVente();
        stat4 = new PanelClassementEmploye();
        stat5 = new PanelVenteParPayement();
        stat6 = new PanelTopVenteVehicule();

        // Affichage initial
        showPanel(stat1, evolutionVente);
        showPanel(stat2, comparaisonVente);
        showPanel(stat3, repartitionVente);
        showPanel(stat4, classement);
        showPanel(stat5, modePayement);
        showPanel(stat6, topVenteVehicule);

        // Ajout d'un écouteur d'événements pour rafraîchir les onglets
        TableauStat.addChangeListener(e -> {
            int selectedTab = TableauStat.getSelectedIndex();
            switch (selectedTab) {
                case 0 -> showPanel(stat1, evolutionVente); // Onglet "Évolution des ventes"
                case 1 -> showPanel(stat2, comparaisonVente); // Onglet "Comparaison des ventes"
                case 2 -> showPanel(stat3, repartitionVente); // Onglet "Répartition des ventes"
                case 3 -> showPanel(stat4, classement); // Onglet "Classement des employes"
                case 4 -> showPanel(stat5, modePayement); // Onglet "Vente par Mode Payement"
                case 5 -> showPanel(stat6, topVenteVehicule); // Onglet "Top vente vehicule"
            }
        });
    }

    
    // Méthode générique pour afficher un panneau
    private void showPanel(JPanel panel, JPanel mere) {
        mere.removeAll();
        mere.setLayout(new BorderLayout());
        mere.add(panel, BorderLayout.CENTER);
        mere.revalidate();
        mere.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TableauStat = new javax.swing.JTabbedPane();
        evolutionVente = new javax.swing.JPanel();
        comparaisonVente = new javax.swing.JPanel();
        repartitionVente = new javax.swing.JPanel();
        classement = new javax.swing.JPanel();
        modePayement = new javax.swing.JPanel();
        topVenteVehicule = new javax.swing.JPanel();
        actualiser = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(760, 600));

        javax.swing.GroupLayout evolutionVenteLayout = new javax.swing.GroupLayout(evolutionVente);
        evolutionVente.setLayout(evolutionVenteLayout);
        evolutionVenteLayout.setHorizontalGroup(
            evolutionVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );
        evolutionVenteLayout.setVerticalGroup(
            evolutionVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );

        TableauStat.addTab("Evoluation des Ventes", evolutionVente);

        javax.swing.GroupLayout comparaisonVenteLayout = new javax.swing.GroupLayout(comparaisonVente);
        comparaisonVente.setLayout(comparaisonVenteLayout);
        comparaisonVenteLayout.setHorizontalGroup(
            comparaisonVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );
        comparaisonVenteLayout.setVerticalGroup(
            comparaisonVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );

        TableauStat.addTab("Comparaison des ventes par employé", comparaisonVente);

        javax.swing.GroupLayout repartitionVenteLayout = new javax.swing.GroupLayout(repartitionVente);
        repartitionVente.setLayout(repartitionVenteLayout);
        repartitionVenteLayout.setHorizontalGroup(
            repartitionVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );
        repartitionVenteLayout.setVerticalGroup(
            repartitionVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );

        TableauStat.addTab("répartition des ventes par catégorie de véhicule", repartitionVente);

        javax.swing.GroupLayout classementLayout = new javax.swing.GroupLayout(classement);
        classement.setLayout(classementLayout);
        classementLayout.setHorizontalGroup(
            classementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );
        classementLayout.setVerticalGroup(
            classementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );

        TableauStat.addTab("Classement Employe", classement);

        javax.swing.GroupLayout modePayementLayout = new javax.swing.GroupLayout(modePayement);
        modePayement.setLayout(modePayementLayout);
        modePayementLayout.setHorizontalGroup(
            modePayementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );
        modePayementLayout.setVerticalGroup(
            modePayementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );

        TableauStat.addTab("Vente Par Payement", modePayement);

        javax.swing.GroupLayout topVenteVehiculeLayout = new javax.swing.GroupLayout(topVenteVehicule);
        topVenteVehicule.setLayout(topVenteVehiculeLayout);
        topVenteVehiculeLayout.setHorizontalGroup(
            topVenteVehiculeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );
        topVenteVehiculeLayout.setVerticalGroup(
            topVenteVehiculeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );

        TableauStat.addTab("Top Vehicule", topVenteVehicule);

        actualiser.setText("Actualiser");
        actualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualiserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TableauStat)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(actualiser)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(actualiser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TableauStat, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void actualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualiserActionPerformed
        // Supprime et réinitialise tous les onglets
        TableauStat.removeAll();

        // Recharge les panneaux des statistiques
        stat1 = new PanelEvolutionVente();
        stat2 = new PanelRevenuEmploye();
        stat3 = new PanelRepartitionVente();
        stat4 = new PanelClassementEmploye();
        stat5 = new PanelVenteParPayement();
        stat6 = new PanelTopVenteVehicule();

        // Réaffecte les panneaux aux onglets
        showPanel(stat1, evolutionVente);
        showPanel(stat2, comparaisonVente);
        showPanel(stat3, repartitionVente);
        showPanel(stat4, classement);
        showPanel(stat5, modePayement);
        showPanel(stat6, topVenteVehicule);

        // Réajoute les onglets après mise à jour
        TableauStat.addTab("Évolution des Ventes", evolutionVente);
        TableauStat.addTab("Comparaison des ventes par employé", comparaisonVente);
        TableauStat.addTab("Répartition des ventes par catégorie de véhicule", repartitionVente);
        TableauStat.addTab("Classement Employé", classement);
        TableauStat.addTab("Vente Par Payement", modePayement);
        TableauStat.addTab("Top Véhicule", topVenteVehicule);

        // Rafraîchit l'affichage
        revalidate();
        repaint();
    }//GEN-LAST:event_actualiserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TableauStat;
    private javax.swing.JButton actualiser;
    private javax.swing.JPanel classement;
    private javax.swing.JPanel comparaisonVente;
    private javax.swing.JPanel evolutionVente;
    private javax.swing.JPanel modePayement;
    private javax.swing.JPanel repartitionVente;
    private javax.swing.JPanel topVenteVehicule;
    // End of variables declaration//GEN-END:variables
}
