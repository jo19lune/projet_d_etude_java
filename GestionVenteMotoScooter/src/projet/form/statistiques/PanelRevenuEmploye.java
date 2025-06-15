package projet.form.statistiques;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import projet.classes.statistiques.RevenuParEmploye;

public class PanelRevenuEmploye extends javax.swing.JPanel {

    public PanelRevenuEmploye() {
        setLayout(new BorderLayout());

        // Récupérer les données SQL
        Map<String, Double> revenus = RevenuParEmploye.getRevenuParEmploye();

        // Création du dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : revenus.entrySet()) {
            dataset.addValue(entry.getValue(), "Revenu", entry.getKey());
        }

        // Générer le graphique en barres
        JFreeChart chart = ChartFactory.createBarChart(
                "Revenu généré par employé",
                "Employé",
                "Montant total (Ariary)",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        CategoryPlot plot = chart.getCategoryPlot();

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
