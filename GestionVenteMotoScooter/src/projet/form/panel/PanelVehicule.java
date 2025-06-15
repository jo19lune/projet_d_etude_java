package projet.form.panel;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import javax.swing.JOptionPane; // Pour afficher des boîtes de dialogue
import java.sql.SQLException; // Pour gérer les exceptions liées aux bases de données
import javax.swing.table.DefaultTableModel;
import projet.classes.gestion.GestionVehicule; // Pour accéder à la gestion des véhicules
import projet.classes.tables.Vehicule; // Pour manipuler les objets Vehicule
import javax.swing.JTable; // Pour manipuler le tableau maTableV
import javax.swing.table.DefaultTableModel; // Pour gérer le modèle de données de JTable
import java.sql.ResultSet; // Pour manipuler les résultats des requêtes SQL
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import projet.classes.controles.ValidationDecimal;
import projet.classes.controles.ValidationNumerique;



public class PanelVehicule extends javax.swing.JPanel {

    public PanelVehicule() {
        initComponents(); // Initialisation des composants graphiques
        
        ajouterFiltrage();
        ajouterValidation();

        id_v.setVisible(false);
        try {
            // Appeler la méthode pour charger les données dans le tableau
            GestionVehicule gestionVehicule = new GestionVehicule();
            gestionVehicule.chargerTableau(maTableV); // maTableV est votre JTable
        } catch (Exception e) {
            // Gérer les erreurs lors du chargement des données
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement initial des données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelRech = new javax.swing.JPanel();
        zoneRech = new javax.swing.JTextField();
        btnRecAleatoire = new javax.swing.JButton();
        btnRechByMarque = new javax.swing.JButton();
        btnRechByCategorie = new javax.swing.JButton();
        tableVehicule = new javax.swing.JScrollPane();
        maTableV = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        panelControl = new javax.swing.JPanel();
        btnAjoutV = new javax.swing.JButton();
        btnModifV = new javax.swing.JButton();
        btnSupV = new javax.swing.JButton();
        btnVideV = new javax.swing.JButton();
        id_v = new javax.swing.JTextField();
        marq = new javax.swing.JTextField();
        model = new javax.swing.JTextField();
        cyl = new javax.swing.JTextField();
        moteur = new javax.swing.JTextField();
        categorie = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        stock = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        prixVehicule = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 255));
        setMinimumSize(new java.awt.Dimension(760, 600));
        setPreferredSize(new java.awt.Dimension(760, 600));

        panelRech.setOpaque(false);
        panelRech.setPreferredSize(new java.awt.Dimension(300, 35));

        zoneRech.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnRecAleatoire.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/search_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnRecAleatoire.setBorder(null);
        btnRecAleatoire.setBorderPainted(false);
        btnRecAleatoire.setContentAreaFilled(false);
        btnRecAleatoire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecAleatoireActionPerformed(evt);
            }
        });

        btnRechByMarque.setBackground(new java.awt.Color(102, 102, 102));
        btnRechByMarque.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRechByMarque.setForeground(new java.awt.Color(227, 227, 227));
        btnRechByMarque.setText("Par Marque");
        btnRechByMarque.setBorder(null);
        btnRechByMarque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRechByMarqueActionPerformed(evt);
            }
        });

        btnRechByCategorie.setBackground(new java.awt.Color(102, 102, 102));
        btnRechByCategorie.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRechByCategorie.setForeground(new java.awt.Color(227, 227, 227));
        btnRechByCategorie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/category_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnRechByCategorie.setText("Par Categorie");
        btnRechByCategorie.setBorder(null);
        btnRechByCategorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRechByCategorieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRechLayout = new javax.swing.GroupLayout(panelRech);
        panelRech.setLayout(panelRechLayout);
        panelRechLayout.setHorizontalGroup(
            panelRechLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRechLayout.createSequentialGroup()
                .addContainerGap(338, Short.MAX_VALUE)
                .addComponent(zoneRech, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRecAleatoire)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRechByMarque)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRechByCategorie)
                .addGap(0, 0, 0))
        );
        panelRechLayout.setVerticalGroup(
            panelRechLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRechLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRechLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRechLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelRechLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRechByMarque, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRechByCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRecAleatoire, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(zoneRech))
                .addContainerGap())
        );

        panelRechLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnRecAleatoire, btnRechByCategorie, btnRechByMarque});

        maTableV.setModel(new javax.swing.table.DefaultTableModel(
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
        maTableV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maTableVMouseClicked(evt);
            }
        });
        tableVehicule.setViewportView(maTableV);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        panelControl.setBackground(new java.awt.Color(255, 255, 255));
        panelControl.setOpaque(false);

        btnAjoutV.setBackground(new java.awt.Color(0, 180, 219));
        btnAjoutV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAjoutV.setForeground(new java.awt.Color(227, 227, 227));
        btnAjoutV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/add_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnAjoutV.setText("Ajouter");
        btnAjoutV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjoutVActionPerformed(evt);
            }
        });

        btnModifV.setBackground(new java.awt.Color(0, 180, 219));
        btnModifV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnModifV.setForeground(new java.awt.Color(227, 227, 227));
        btnModifV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/refresh_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnModifV.setText("Modifier");
        btnModifV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifVActionPerformed(evt);
            }
        });

        btnSupV.setBackground(new java.awt.Color(0, 180, 219));
        btnSupV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSupV.setForeground(new java.awt.Color(227, 227, 227));
        btnSupV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/delete_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnSupV.setText("Supprimer");
        btnSupV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupVActionPerformed(evt);
            }
        });

        btnVideV.setBackground(new java.awt.Color(0, 180, 219));
        btnVideV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVideV.setForeground(new java.awt.Color(227, 227, 227));
        btnVideV.setText("Vider le Champ");
        btnVideV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVideVActionPerformed(evt);
            }
        });

        cyl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cylKeyTyped(evt);
            }
        });

        jLabel1.setText("Marque :");

        jLabel2.setText("Modele :");

        jLabel3.setText("Cylindre :");

        jLabel4.setText("Moteur :");

        jLabel5.setText("Catégorie :");

        stock.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                stockFocusLost(evt);
            }
        });
        stock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                stockKeyTyped(evt);
            }
        });

        jLabel6.setText("Stock :");

        jLabel7.setText("Prix :");

        javax.swing.GroupLayout panelControlLayout = new javax.swing.GroupLayout(panelControl);
        panelControl.setLayout(panelControlLayout);
        panelControlLayout.setHorizontalGroup(
            panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelControlLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addGroup(panelControlLayout.createSequentialGroup()
                            .addComponent(btnAjoutV)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnModifV))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelControlLayout.createSequentialGroup()
                            .addComponent(btnSupV)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnVideV)))
                    .addGroup(panelControlLayout.createSequentialGroup()
                        .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prixVehicule)
                            .addComponent(id_v)
                            .addComponent(marq)
                            .addComponent(model)
                            .addComponent(stock)
                            .addComponent(cyl)
                            .addComponent(moteur)
                            .addComponent(categorie))))
                .addContainerGap())
        );

        panelControlLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAjoutV, btnModifV, btnSupV, btnVideV});

        panelControlLayout.setVerticalGroup(
            panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelControlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(id_v, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelControlLayout.createSequentialGroup()
                        .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelControlLayout.createSequentialGroup()
                                .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelControlLayout.createSequentialGroup()
                                        .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(marq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cyl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prixVehicule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAjoutV)
                    .addComponent(btnModifV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSupV)
                    .addComponent(btnVideV))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        panelControlLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAjoutV, btnModifV, btnSupV, btnVideV});

        panelControlLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {categorie, cyl, id_v, marq, model, moteur, prixVehicule, stock});

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 21;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(125, 66, 56, 37);
        jPanel1.add(panelControl, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableVehicule, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
            .addComponent(panelRech, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelRech, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableVehicule, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ajouterFiltrage() {
        prixVehicule.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                // uniquement les chiffres et un seul point décimal
                if (!Character.isDigit(c) && c != '.' || (c == '.' && prixVehicule.getText().contains("."))) {
                    e.consume(); // 🚫 Bloque la saisie du caractère invalide
                }
            }
        });
    }
    
        private void ajouterValidation() {
            prixVehicule.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    validerPrix();
                }
            });
        }

        private void validerPrix() {
            String texte = prixVehicule.getText().trim();

            if (!ValidationDecimal.isValidDecimal(texte, 2)) {
                JOptionPane.showMessageDialog(null, "Format incorrect (Ex: 123.45) ou valeur invalide", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                prixVehicule.setBackground(Color.red);
            }
            else {
                prixVehicule.setBackground(Color.white);
            }
        }
        
        private void validerStock() {
            String texte = stock.getText().trim();

            if (!ValidationDecimal.isValidNumber(texte, true)) {
                JOptionPane.showMessageDialog(null, "Valeur invalide", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                stock.setBackground(Color.red);
            }
            else {
                stock.setBackground(Color.white);
            }
        }
    
    private void btnAjoutVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjoutVActionPerformed
        // Récupération des données des champs
        String marque = marq.getText().trim();
        String modele = model.getText().trim();
        String cylindree = cyl.getText().trim();
        String moteurType = moteur.getText().trim();
        String categorieType = categorie.getText().trim();
        String stockValue = stock.getText().trim();
        String prixValue = prixVehicule.getText().trim(); // Récupération du prix

        // Validation des champs
        if (marque.isEmpty() || modele.isEmpty() || cylindree.isEmpty() || moteurType.isEmpty() || categorieType.isEmpty() || stockValue.isEmpty() || prixValue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Vérification et conversion des champs numériques
            int cylindre = Integer.parseInt(cylindree);
            int stockQuantite = Integer.parseInt(stockValue);
            BigDecimal prix = new BigDecimal(prixValue); // Conversion du prix en BigDecimal

            // Vérification que les valeurs numériques sont positives
            if (cylindre <= 0 || stockQuantite < 0 || prix.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this, "La cylindrée et le prix doivent être positifs, et le stock ne peut pas être négatif.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Création de l'objet Vehicule
            Vehicule vehicule = new Vehicule(0, marque, modele, cylindre, moteurType, categorieType, stockQuantite, prix);

            // Insertion dans la base de données
            GestionVehicule gestionVehicule = new GestionVehicule();
            gestionVehicule.insert(vehicule);

            // Réinitialiser les champs de saisie après insertion
            resetFields();

            // Recharger le tableau pour afficher les nouvelles données
            gestionVehicule.chargerTableau(maTableV); // `maTableV` est le JTable de l'interface

            // Confirmation à l'utilisateur
            JOptionPane.showMessageDialog(this, "Le véhicule a été ajouté avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            // Gestion des erreurs liées aux conversions numériques
            JOptionPane.showMessageDialog(this, "La cylindrée, le stock et le prix doivent être des nombres valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            // Gestion des erreurs liées à la base de données
            JOptionPane.showMessageDialog(this, "Erreur lors de l'insertion dans la base de données : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            // Gestion des erreurs générales
            JOptionPane.showMessageDialog(this, "Une erreur inattendue s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAjoutVActionPerformed

    private void btnModifVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifVActionPerformed
        // Vérification que le champ ID est renseigné
        if (id_v.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un véhicule à modifier !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Récupération des données des champs
            String marque = marq.getText().trim();
            String modele = model.getText().trim();
            String cylindree = cyl.getText().trim();
            String moteurType = moteur.getText().trim();
            String categorieType = categorie.getText().trim();
            String stockValue = stock.getText().trim();
            String prixValue = prixVehicule.getText().trim(); // Récupération du prix

            // Validation des champs
            if (marque.isEmpty() || modele.isEmpty() || cylindree.isEmpty() || moteurType.isEmpty() || categorieType.isEmpty() || stockValue.isEmpty() || prixValue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis pour mettre à jour le véhicule !", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Vérification et conversion des champs numériques
            int cylindre = Integer.parseInt(cylindree);
            int stockQuantite = Integer.parseInt(stockValue);
            BigDecimal prix = new BigDecimal(prixValue); // Conversion du prix en BigDecimal

            // Vérification que les valeurs numériques sont valides
            if (cylindre <= 0 || stockQuantite < 0 || prix.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this, "La cylindrée et le prix doivent être positifs, et le stock ne peut pas être négatif.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Création de l'objet Vehicule
            Vehicule vehicule = new Vehicule(
                Integer.parseInt(id_v.getText().trim()), // Identifiant
                marque,
                modele,
                cylindre,
                moteurType,
                categorieType,
                stockQuantite,
                prix
            );

            // Mise à jour dans la base de données
            GestionVehicule gestionVehicule = new GestionVehicule();
            gestionVehicule.update(vehicule.getIdVehicule(), vehicule);

            // Nettoyage des champs après mise à jour
            resetFields();

            // Recharger le tableau pour afficher les nouvelles données
            gestionVehicule.chargerTableau(maTableV); // `maTableV` est le JTable de l'interface

            // Confirmation à l'utilisateur
            JOptionPane.showMessageDialog(this, "Le véhicule a été mis à jour avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            // Gestion des erreurs de conversion numérique
            JOptionPane.showMessageDialog(this, "La cylindrée, le stock et le prix doivent être des nombres valides !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            // Gestion des erreurs liées à la base de données
            JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour dans la base de données : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            // Gestion des erreurs générales
            JOptionPane.showMessageDialog(this, "Une erreur inattendue s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnModifVActionPerformed

    private void btnSupVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupVActionPerformed
        // Vérification que le champ ID est renseigné
        String idText = id_v.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un véhicule à supprimer !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Récupération de l'identifiant du véhicule
            int idVehicule = Integer.parseInt(idText);

            // Demande de confirmation avant suppression
            int confirmation = JOptionPane.showConfirmDialog(this, 
                "Êtes-vous sûr de vouloir supprimer ce véhicule ?", 
                "Confirmation", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);

            if (confirmation == JOptionPane.YES_OPTION) {
                // Initialisation de la gestion des véhicules
                GestionVehicule gestionVehicule = new GestionVehicule();

                // Supprimer le véhicule dans la base de données
                gestionVehicule.delete(idVehicule);

                // Réinitialiser les champs de saisie après suppression
                resetFields();

                // Recharger le tableau pour afficher les données mises à jour
                gestionVehicule.chargerTableau(maTableV); // `maTableV` est le JTable de l'interface

                // Confirmation de la suppression
                JOptionPane.showMessageDialog(this, "Véhicule supprimé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            // Gérer les erreurs liées à un identifiant non valide (non numérique)
            JOptionPane.showMessageDialog(this, "L'identifiant du véhicule doit être un nombre valide !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            // Gérer les erreurs liées à la base de données
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppression dans la base de données : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            // Gérer toute autre erreur imprévue
            JOptionPane.showMessageDialog(this, "Une erreur inattendue s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Affichage de l'erreur pour débogage
        }
    }

    // Méthode pour réinitialiser les champs du formulaire
    private void resetFields() {
        id_v.setText("");       // Réinitialiser le champ ID
        marq.setText("");       // Réinitialiser le champ Marque
        model.setText("");      // Réinitialiser le champ Modèle
        cyl.setText("");        // Réinitialiser le champ Cylindrée
        moteur.setText("");     // Réinitialiser le champ Moteur
        categorie.setText("");  // Réinitialiser le champ Catégorie
        stock.setText("");      // Réinitialiser le champ Stock
        prixVehicule.setText(""); // Réinitialiser le champ Prix
        
        try {
            // Appeler la méthode pour charger les données dans le tableau
            GestionVehicule gestionVehicule = new GestionVehicule();
            gestionVehicule.chargerTableau(maTableV); // maTableV
        } catch (Exception e) {
            // Gérer les erreurs lors du chargement des données
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement initial des données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnSupVActionPerformed

    private void btnVideVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVideVActionPerformed
        resetFields();
    }//GEN-LAST:event_btnVideVActionPerformed

    private void btnRecAleatoireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecAleatoireActionPerformed
        // Récupérer le mot-clé saisi
        String keyword = zoneRech.getText().trim();

        // Vérifier que le mot-clé n'est pas vide
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un mot-clé pour effectuer la recherche.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Initialiser la gestion des véhicules
            GestionVehicule gestionVehicule = new GestionVehicule();

            // Récupérer les résultats de la recherche aléatoire
            List<Vehicule> vehicules = gestionVehicule.rechercheAleatoire(keyword);

            // Préparer le modèle pour le tableau
            String[] titre = {"ID", "Marque", "Modèle", "Cylindrée", "Moteur", "Catégorie", "Stock"};
            DefaultTableModel model = new DefaultTableModel(titre, 0);

            // Parcourir les résultats et remplir le tableau
            for (Vehicule vehicule : vehicules) {
                Object[] ligne = {
                    vehicule.getIdVehicule(),
                    vehicule.getMarque(),
                    vehicule.getModel(),
                    vehicule.getCyl(),
                    vehicule.getMoteur(),
                    vehicule.getCategorie(),
                    vehicule.getStock()
                };
                model.addRow(ligne);
            }

            // Appliquer le modèle de données au tableau
            maTableV.setModel(model);

            // Message si aucun résultat n'est trouvé
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Aucun véhicule trouvé pour le mot-clé : " + keyword, "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            // Gestion des erreurs liées à la base de données
            JOptionPane.showMessageDialog(this, "Erreur lors de la recherche dans la base : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            // Gestion des erreurs générales
            JOptionPane.showMessageDialog(this, "Une erreur inattendue s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnRecAleatoireActionPerformed

    private void btnRechByMarqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRechByMarqueActionPerformed
        // Récupérer le mot-clé saisi
        String keyword = zoneRech.getText().trim();

        // Vérifier que le mot-clé n'est pas vide
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un mot-clé pour effectuer la recherche.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Initialiser la gestion des véhicules
            GestionVehicule gestionVehicule = new GestionVehicule();

            // Récupérer les résultats de la recherche par marque
            List<Vehicule> vehicules = gestionVehicule.rechercheParMarque(keyword);

            // Préparer le modèle pour le tableau
            String[] titre = {"ID", "Marque", "Modèle", "Cylindrée", "Moteur", "Catégorie", "Stock"};
            DefaultTableModel model = new DefaultTableModel(titre, 0);

            // Parcourir les résultats et remplir le tableau
            for (Vehicule vehicule : vehicules) {
                Object[] ligne = {
                    vehicule.getIdVehicule(),
                    vehicule.getMarque(),
                    vehicule.getModel(),
                    vehicule.getCyl(),
                    vehicule.getMoteur(),
                    vehicule.getCategorie(),
                    vehicule.getStock()
                };
                model.addRow(ligne); // Ajouter chaque ligne au modèle
            }

            // Appliquer le modèle de données au tableau
            maTableV.setModel(model);

            // Message si aucun résultat n'est trouvé
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Aucun véhicule trouvé pour le mot-clé : " + keyword, "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            // Gestion des erreurs liées à la base de données
            JOptionPane.showMessageDialog(this, "Erreur lors de la recherche dans la base : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            // Gestion des erreurs générales
            JOptionPane.showMessageDialog(this, "Une erreur inattendue s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnRechByMarqueActionPerformed

    private void btnRechByCategorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRechByCategorieActionPerformed
        // Récupérer le mot-clé saisi
        String keyword = zoneRech.getText().trim();

        // Vérifier que le mot-clé n'est pas vide
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un mot-clé pour effectuer la recherche.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Initialiser la gestion des véhicules
            GestionVehicule gestionVehicule = new GestionVehicule();

            // Récupérer les résultats de la recherche par catégorie
            List<Vehicule> vehicules = gestionVehicule.rechercheParCategorie(keyword);

            // Préparer le modèle pour le tableau
            String[] titre = {"ID", "Marque", "Modèle", "Cylindrée", "Moteur", "Catégorie", "Stock"};
            DefaultTableModel model = new DefaultTableModel(titre, 0);

            // Parcourir les résultats et remplir le tableau
            for (Vehicule vehicule : vehicules) {
                Object[] ligne = {
                    vehicule.getIdVehicule(),
                    vehicule.getMarque(),
                    vehicule.getModel(),
                    vehicule.getCyl(),
                    vehicule.getMoteur(),
                    vehicule.getCategorie(),
                    vehicule.getStock()
                };
                model.addRow(ligne); // Ajouter chaque ligne au modèle
            }

            // Appliquer le modèle de données au tableau
            maTableV.setModel(model);

            // Message si aucun résultat n'est trouvé
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Aucun véhicule trouvé pour le mot-clé : " + keyword, "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            // Gestion des erreurs liées à la base de données
            JOptionPane.showMessageDialog(this, "Erreur lors de la recherche dans la base : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            // Gestion des erreurs générales
            JOptionPane.showMessageDialog(this, "Une erreur inattendue s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnRechByCategorieActionPerformed

    private void maTableVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maTableVMouseClicked
        int ligne = maTableV.getSelectedRow();
        id_v.setText(String.valueOf(maTableV.getValueAt(ligne, 0)));
        marq.setText(String.valueOf(maTableV.getValueAt(ligne, 1)));
        model.setText(String.valueOf(maTableV.getValueAt(ligne, 2)));
        cyl.setText(String.valueOf(maTableV.getValueAt(ligne, 3)));
        moteur.setText(String.valueOf(maTableV.getValueAt(ligne, 4)));
        categorie.setText(String.valueOf(maTableV.getValueAt(ligne, 5)));
        stock.setText(String.valueOf(maTableV.getValueAt(ligne, 6)));
        prixVehicule.setText(String.valueOf(maTableV.getValueAt(ligne, 7)));
    }//GEN-LAST:event_maTableVMouseClicked

    private void stockFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stockFocusLost
        validerStock();
    }//GEN-LAST:event_stockFocusLost

    private void stockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stockKeyTyped
        ValidationNumerique validation = new ValidationNumerique(3);
        validation.keyTyped(evt);
    }//GEN-LAST:event_stockKeyTyped

    private void cylKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cylKeyTyped
        ValidationNumerique validation = new ValidationNumerique(4);
        validation.keyTyped(evt);
    }//GEN-LAST:event_cylKeyTyped

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjoutV;
    private javax.swing.JButton btnModifV;
    private javax.swing.JButton btnRecAleatoire;
    private javax.swing.JButton btnRechByCategorie;
    private javax.swing.JButton btnRechByMarque;
    private javax.swing.JButton btnSupV;
    private javax.swing.JButton btnVideV;
    private javax.swing.JTextField categorie;
    private javax.swing.JTextField cyl;
    private javax.swing.JTextField id_v;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable maTableV;
    private javax.swing.JTextField marq;
    private javax.swing.JTextField model;
    private javax.swing.JTextField moteur;
    private javax.swing.JPanel panelControl;
    private javax.swing.JPanel panelRech;
    private javax.swing.JTextField prixVehicule;
    private javax.swing.JTextField stock;
    private javax.swing.JScrollPane tableVehicule;
    private javax.swing.JTextField zoneRech;
    // End of variables declaration//GEN-END:variables
}
