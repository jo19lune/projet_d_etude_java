package projet.form.statistiques;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import projet.classes.statistiques.VenteParPayement;

public class PanelVenteParPayement extends javax.swing.JPanel {

    public PanelVenteParPayement() {
        initComponents();
        setLayout(new BorderLayout());

        // Récupérer les statistiques SQL
        Map<String, Integer> ventes = VenteParPayement.getVentesParPayement();

        // Création du dataset pour Pie Chart
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : ventes.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        // Générer le graphique en secteurs
        JFreeChart chart = ChartFactory.createPieChart(
                "Répartition des ventes par mode de paiement",
                dataset,
                true, // Afficher la légende
                true, // Activer les infobulles
                false // Désactiver les liens interactifs
        );

        // Ajouter le graphique au JPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
