package projet.form.statistiques;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import projet.classes.statistiques.TopVenteVehicule;

public class PanelTopVenteVehicule extends javax.swing.JPanel {

    public PanelTopVenteVehicule() {
        initComponents();
        setLayout(new BorderLayout());

        // Récupérer les statistiques SQL
        Map<String, Integer> classement = TopVenteVehicule.getTopVenteVehicule();

        // Création du dataset pour le Bar Chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : classement.entrySet()) {
            dataset.addValue(entry.getValue(), "Ventes", entry.getKey());
        }

        // Générer le graphique en barres
        JFreeChart chart = ChartFactory.createBarChart(
                "Top 5 véhicules les plus vendus",
                "Véhicules",
                "Nombre de ventes",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL, // ?Barres vertivales
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
