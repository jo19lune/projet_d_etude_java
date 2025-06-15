package projet.form.panel;

import java.awt.*;
import java.awt.print.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import projet.classes.connectionbase.ConnectionBase;
import projet.classes.gestion.GestionFacture;

public class PanelFacture extends javax.swing.JPanel implements Printable {
    
    public PanelFacture() {
        initComponents();
        idCli.setVisible(false);
        
        // Chargement des factures dès le démarrage
        chargerIdFactureDansComboBox();
    }
    
    private void chargerDetailsFacture(int idFactureValue) {
        String query = """
            SELECT 
                f.date_facturation, f.mode_paiement, f.montant_total,
                c.id_client, c.nom_client, c.prenom_client, c.cin_client, c.email, c.telephone,
                e.nom_employe, e.email_employe, e.telephone_employe
            FROM facture f
            JOIN client c ON f.id_client = c.id_client
            JOIN employe e ON f.id_employe = e.id_employe
            WHERE f.id_facture = ?
        """;

        try (Connection conn = ConnectionBase.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idFactureValue);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dateFacture.setText(rs.getString("date_facturation"));
                    modePayement.setSelectedItem(rs.getString("mode_paiement"));
                    montantTotal.setText(String.valueOf(rs.getDouble("montant_total")));

                    idCli.setText(String.valueOf(rs.getInt("id_client")));
                    nomCli.setText(rs.getString("nom_client"));
                    prenomCli.setText(rs.getString("prenom_client"));
                    cinCli.setText(rs.getString("cin_client"));
                    mailCli.setText(rs.getString("email"));
                    phoneCli.setText(rs.getString("telephone"));

                    nomEmploye.setText(rs.getString("nom_employe"));
                    emailEmploye.setText(rs.getString("email_employe"));
                    telephoneEmploye.setText(rs.getString("telephone_employe"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PanelFacture.class.getName()).log(Level.SEVERE, "Erreur lors du chargement des détails de la facture", ex);
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des détails de la facture : " + ex.getMessage());
        }
    }

    private void chargerIdFactureDansComboBox() {
        String query = "SELECT id_facture FROM facture";

        try (Connection conn = ConnectionBase.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            idFacture.removeAllItems();
            while (rs.next()) {
                idFacture.addItem(String.valueOf(rs.getInt("id_facture")));
            }

            if (idFacture.getItemCount() == 0) {
                Logger.getLogger(PanelFacture.class.getName()).log(Level.WARNING, "Aucune facture disponible.");
                JOptionPane.showMessageDialog(this, "Aucune facture trouvée en base de données.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PanelFacture.class.getName()).log(Level.SEVERE, "Erreur lors du chargement des ID factures", ex);
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des ID factures : " + ex.getMessage());
        }
    }
    
    private void chargerTableAchats(int idFacture) {
        try {
            GestionFacture gestionFacture = new GestionFacture();
            var achats = gestionFacture.getAchatsByFactureId(idFacture);

            String[] colonnes = {"ID", "ID Vehicule", "Catégorie", "Marque", "Modèle", "Quantité", "Prix Total"};
            Object[][] data = new Object[achats.size()][colonnes.length];
            for (int i = 0; i < achats.size(); i++) {
                data[i] = achats.get(i);
            }

            tableAchat.setModel(new javax.swing.table.DefaultTableModel(data, colonnes));

            if (achats.isEmpty()) {
                Logger.getLogger(PanelFacture.class.getName()).log(Level.WARNING, "Aucun achat trouvé pour cette facture.");
                JOptionPane.showMessageDialog(this, "Aucun achat trouvé pour cette facture.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }

            // ✅ Appelle la mise à jour du montant total
            updateMontantTotal();

        } catch (Exception ex) {
            Logger.getLogger(PanelFacture.class.getName()).log(Level.SEVERE, "Erreur lors du chargement du tableau des achats", ex);
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement du tableau : " + ex.getMessage());
        }
    }
    
    private void imprimerPanel() {
        btnImprimer.setVisible(false);
        btnActualiser.setVisible(false);

        // 🔹 (PrinterJob)
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

        // 🔹 orientation amin'ny LANDSCAPE (marindrano) mba ahitana tsara ny pejy
        PageFormat format = job.defaultPage();
        format.setOrientation(PageFormat.LANDSCAPE);
        job.setPrintable(this, format);

        // 🔹 boîte de dialogue
        if (job.printDialog()) {
            try {
                job.print(); // 🔹 Manontana ilay pejy rehetra
                JOptionPane.showMessageDialog(this, "Impression réussie !", "Imprimer", JOptionPane.INFORMATION_MESSAGE);
            } catch (PrinterException e) {
                Logger.getLogger(PanelFacture.class.getName()).log(Level.SEVERE, "Erreur lors de l'impression", e);
                JOptionPane.showMessageDialog(this, "Erreur lors de l'impression : " + e.getMessage(), "Imprimer", JOptionPane.ERROR_MESSAGE);
            }
        }

        btnImprimer.setVisible(true);
        btnActualiser.setVisible(true);
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        // 🔹 Raha misy pejy fanampiny, tsy misy ho print-na
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        // 🔹 Mametraka scale mba hifanaraka amin'ny habe printable
        double scaleX = pageFormat.getImageableWidth() / this.getWidth();
        double scaleY = pageFormat.getImageableHeight() / this.getHeight();
        g2d.scale(Math.min(scaleX, scaleY), Math.min(scaleX, scaleY));

        // 🔹 Manontana ny sary rehetra ao amin'ny `PanelFacture`
        this.paint(g2d);

        // 🔹 parcour tous les donner du tableau
        int rowCount = tableAchat.getRowCount();
        int colCount = tableAchat.getColumnCount();
        int startY = this.getHeight() + 20; 

        g2d.setFont(new Font("Serif", Font.PLAIN, 10)); // 🔹 Mametraka habe kely mba ho hita tsara ny lahatsoratra

        for (int i = 0; i < rowCount; i++) {
            int startX = (int) pageFormat.getImageableX();
            for (int j = 0; j < colCount; j++) {
                String cellValue = String.valueOf(tableAchat.getValueAt(i, j));
                g2d.drawString(cellValue, startX, startY);
                startX += 100; // 🔹 Mametraka elanelana eo amin'ny tsanganana (colonnes)
            }
            startY += 15; // 🔹 Mametraka elanelana eo amin'ny andalana (lignes)
        }

        return PAGE_EXISTS;
    }
    
    private void updateMontantTotal() {
        double somme = 0.0;
        for (int i = 0; i < tableAchat.getRowCount(); i++) {
            Object value = tableAchat.getValueAt(i, 6); // Prix total de chaque ligne
            if (value != null && !value.toString().trim().isEmpty()) { // Vérification si la cellule contient une valeur valide
                try {
                    String valeurStr = value.toString().replace(",", ".").replaceAll("[^0-9.]", ""); // Nettoyage et remplacement des virgules par des points
                    somme += Double.parseDouble(valeurStr);
                } catch (NumberFormatException e) {
                    Logger.getLogger(PanelFacture.class.getName()).log(Level.WARNING, "Erreur de format dans montant total", e);
                }
            }
        }
        montantTotal.setText(String.format("%.2f", somme)); // ✅ Formatage en 2 décimales
    }
    
    private void initTableListener() {
        tableAchat.getModel().addTableModelListener(e -> updateMontantTotal());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnImprimer = new javax.swing.JButton();
        btnActualiser = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        nomCli = new javax.swing.JTextField();
        prenomCli = new javax.swing.JTextField();
        cinCli = new javax.swing.JTextField();
        phoneCli = new javax.swing.JTextField();
        mailCli = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        idCli = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAchat = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        nomEmploye = new javax.swing.JTextField();
        telephoneEmploye = new javax.swing.JTextField();
        emailEmploye = new javax.swing.JTextField();
        montantTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        idFacture = new javax.swing.JComboBox<>();
        dateFacture = new javax.swing.JTextField();
        modePayement = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        titre = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(760, 600));
        setPreferredSize(new java.awt.Dimension(760, 600));

        jPanel1.setOpaque(false);

        btnImprimer.setBackground(new java.awt.Color(0, 180, 219));
        btnImprimer.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnImprimer.setForeground(new java.awt.Color(227, 227, 227));
        btnImprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/print_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnImprimer.setText("Imprimer");
        btnImprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimerActionPerformed(evt);
            }
        });

        btnActualiser.setBackground(new java.awt.Color(0, 180, 219));
        btnActualiser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualiser.setForeground(new java.awt.Color(227, 227, 227));
        btnActualiser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/autorenew_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnActualiser.setText("Actualiser");
        btnActualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualiserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(325, Short.MAX_VALUE)
                .addComponent(btnActualiser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImprimer)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImprimer)
                    .addComponent(btnActualiser))
                .addGap(11, 11, 11))
        );

        jPanel2.setOpaque(false);

        nomCli.setEditable(false);
        nomCli.setBorder(null);
        nomCli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nomCli.setEnabled(false);
        nomCli.setFocusable(false);
        nomCli.setRequestFocusEnabled(false);

        prenomCli.setEditable(false);
        prenomCli.setBorder(null);
        prenomCli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        prenomCli.setEnabled(false);
        prenomCli.setFocusable(false);
        prenomCli.setRequestFocusEnabled(false);

        cinCli.setEditable(false);
        cinCli.setBorder(null);
        cinCli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        cinCli.setEnabled(false);
        cinCli.setFocusable(false);
        cinCli.setRequestFocusEnabled(false);

        phoneCli.setEditable(false);
        phoneCli.setBorder(null);
        phoneCli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        phoneCli.setEnabled(false);
        phoneCli.setFocusable(false);
        phoneCli.setRequestFocusEnabled(false);

        mailCli.setEditable(false);
        mailCli.setBorder(null);
        mailCli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        mailCli.setEnabled(false);
        mailCli.setFocusable(false);
        mailCli.setRequestFocusEnabled(false);

        jLabel6.setText("EMail :");

        jLabel5.setText("Téléphone :");

        jLabel3.setText("Cin :");

        jLabel1.setText("Droit à :");

        idCli.setEditable(false);
        idCli.setBorder(null);
        idCli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        idCli.setEnabled(false);
        idCli.setFocusable(false);
        idCli.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mailCli, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                            .addComponent(phoneCli)
                            .addComponent(cinCli)
                            .addComponent(prenomCli)
                            .addComponent(nomCli)))
                    .addComponent(idCli))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(nomCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(prenomCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cinCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mailCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setOpaque(false);

        tableAchat.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableAchat);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );

        jPanel4.setOpaque(false);

        nomEmploye.setEditable(false);
        nomEmploye.setBorder(null);
        nomEmploye.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nomEmploye.setEnabled(false);
        nomEmploye.setFocusable(false);
        nomEmploye.setRequestFocusEnabled(false);

        telephoneEmploye.setEditable(false);
        telephoneEmploye.setBorder(null);
        telephoneEmploye.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        telephoneEmploye.setEnabled(false);
        telephoneEmploye.setFocusable(false);
        telephoneEmploye.setRequestFocusEnabled(false);

        emailEmploye.setEditable(false);
        emailEmploye.setBorder(null);
        emailEmploye.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        emailEmploye.setEnabled(false);
        emailEmploye.setFocusable(false);
        emailEmploye.setRequestFocusEnabled(false);

        montantTotal.setEditable(false);
        montantTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        montantTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        montantTotal.setBorder(null);
        montantTotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        montantTotal.setEnabled(false);
        montantTotal.setFocusable(false);
        montantTotal.setRequestFocusEnabled(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Arirary");

        jLabel9.setText("Par :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(montantTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel8))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(nomEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(telephoneEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 181, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(montantTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telephoneEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setOpaque(false);

        idFacture.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));
        idFacture.setOpaque(true);
        idFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idFactureActionPerformed(evt);
            }
        });

        dateFacture.setOpaque(true);

        modePayement.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ESPECES", "CHEQUE", "CARTE_CREDIT", "BANCAIRE" }));
        modePayement.setOpaque(true);

        jLabel2.setText("Facture n°");

        jLabel4.setText("Date :");

        jLabel7.setText("Mode Payement :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(idFacture, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateFacture)
                    .addComponent(modePayement, 0, 163, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idFacture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateFacture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modePayement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        titre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        titre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/apartment_32dp_000000_FILL0_wght400_GRAD0_opsz40.png"))); // NOI18N
        titre.setText("Mon Entreprise");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titre, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualiserActionPerformed
        try {
            chargerIdFactureDansComboBox();

            if (idFacture.getItemCount() > 0) {
                idFacture.setSelectedIndex(0);
                idFactureActionPerformed(null);
            }

            JOptionPane.showMessageDialog(this, "Mise à jour réussie !", "Actualisation", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(PanelFacture.class.getName()).log(Level.SEVERE, "Erreur lors de l'actualisation des ID factures", ex);
            JOptionPane.showMessageDialog(this, "Échec de l'actualisation : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualiserActionPerformed

    private void idFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idFactureActionPerformed
        try {
            if (idFacture.getItemCount() == 0) return; 

            String selectedItem = (String) idFacture.getSelectedItem();

            if (selectedItem != null && !selectedItem.trim().isEmpty()) {
                int id = Integer.parseInt(selectedItem);
                chargerDetailsFacture(id);
                chargerTableAchats(id); // Chargement du tableau
            } else {
                Logger.getLogger(PanelFacture.class.getName()).log(Level.WARNING, "Aucun ID facture sélectionné.");
            }
        } catch (NumberFormatException e) {
            Logger.getLogger(PanelFacture.class.getName()).log(Level.SEVERE, "Format d'ID invalide", e);
        } catch (Exception ex) {
            Logger.getLogger(PanelFacture.class.getName()).log(Level.SEVERE, "Erreur lors du chargement des détails de la facture", ex);
        }
    }//GEN-LAST:event_idFactureActionPerformed

    private void btnImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimerActionPerformed
        if (idFacture.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une facture avant d'imprimer.", "Avertissement", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idFactureValue;
        try {
            idFactureValue = Integer.parseInt(idFacture.getSelectedItem().toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID de facture invalide. Veuillez vérifier votre sélection.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return; // Arrêter l'exécution si l'ID est incorrect
        }

        // 🔹 Liste des modes de paiement valides
        String[] modesValides = {"ESPECES", "CHEQUE", "CARTE_CREDIT", "BANCAIRE"};

        // 🔹 Récupérer le mode de paiement actuel sélectionné
        String modePaiementActuel = modePayement.getSelectedItem().toString();

        // 🔹 Vérifier que le mode de paiement est valide
        boolean modeValide = false;
        for (String mode : modesValides) {
            if (modePaiementActuel.equals(mode)) {
                modeValide = true;
                break;
            }
        }

        if (!modeValide) {
            JOptionPane.showMessageDialog(this, "Mode de paiement inconnu ou invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return; // Arrêter l'exécution si le mode de paiement est incorrect
        }

        // 🔹 Mettre à jour la facture dans la base de données après modification du mode de paiement
        try {
            GestionFacture gestionFacture = new GestionFacture();
            gestionFacture.updateModePaiement(idFactureValue, modePaiementActuel); // Mise à jour en base

            // 🔹 Confirmation de mise à jour
            JOptionPane.showMessageDialog(this, "Mode de paiement mis à jour avec succès : " + modePaiementActuel, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            Logger.getLogger(PanelFacture.class.getName()).log(Level.SEVERE, "Erreur SQL lors de la mise à jour du mode de paiement", e);
            JOptionPane.showMessageDialog(this, "Erreur SQL lors de la mise à jour du mode de paiement : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return; // Arrêter l'exécution si la mise à jour échoue
        } catch (Exception e) {
            Logger.getLogger(PanelFacture.class.getName()).log(Level.SEVERE, "Erreur inattendue", e);
            JOptionPane.showMessageDialog(this, "Une erreur est survenue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 🔹 Lancer l'impression après modification du mode de paiement
        imprimerPanel();
    }//GEN-LAST:event_btnImprimerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualiser;
    private javax.swing.JButton btnImprimer;
    private javax.swing.JTextField cinCli;
    private javax.swing.JTextField dateFacture;
    private javax.swing.JTextField emailEmploye;
    private javax.swing.JTextField idCli;
    private javax.swing.JComboBox<String> idFacture;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mailCli;
    private javax.swing.JComboBox<String> modePayement;
    private javax.swing.JTextField montantTotal;
    private javax.swing.JTextField nomCli;
    private javax.swing.JTextField nomEmploye;
    private javax.swing.JTextField phoneCli;
    private javax.swing.JTextField prenomCli;
    private javax.swing.JTable tableAchat;
    private javax.swing.JTextField telephoneEmploye;
    private javax.swing.JLabel titre;
    // End of variables declaration//GEN-END:variables
}
