package projet.form.panel;

import java.awt.Color;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import projet.classes.gestion.GestionEmploye;
import projet.classes.tables.Employe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import projet.classes.controles.ValidationChaine;
import projet.classes.controles.ValidationEmail;
import projet.classes.controles.ValidationMix;
import projet.classes.controles.ValidationNumerique;

public class PanelEmploye extends javax.swing.JPanel {

    public PanelEmploye() {
        initComponents();
        chargerTableau();
        
        idEmp.setVisible(false);
        ((AbstractDocument) adrsEmp.getDocument()).setDocumentFilter(new ValidationMix(100)); // 🔹 Limite à 100 caractères max
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        zoneRechEmp = new javax.swing.JTextField();
        btnRechEmp = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        idEmp = new javax.swing.JTextField();
        nomEmp = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnAjoutEmp = new javax.swing.JButton();
        btnModEmp = new javax.swing.JButton();
        btnDelEmp = new javax.swing.JButton();
        btnActEmp = new javax.swing.JButton();
        prenomEmp = new javax.swing.JTextField();
        cinEmp = new javax.swing.JTextField();
        phoneEmp = new javax.swing.JTextField();
        mailEmp = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        adrsEmp = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        posteEmp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        salaireEmp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        dateEmbEmp = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        motDePasse = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        maTableEmp = new javax.swing.JTable();

        setBackground(new java.awt.Color(0, 0, 255));
        setMinimumSize(new java.awt.Dimension(760, 600));

        jPanel2.setOpaque(false);

        zoneRechEmp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        zoneRechEmp.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        btnRechEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/search_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnRechEmp.setBorder(null);
        btnRechEmp.setBorderPainted(false);
        btnRechEmp.setContentAreaFilled(false);
        btnRechEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRechEmpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(zoneRechEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRechEmp)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRechEmp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(zoneRechEmp)
        );

        jPanel4.setLayout(new java.awt.GridBagLayout());

        nomEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomEmpKeyTyped(evt);
            }
        });

        jLabel1.setText("Nom:");

        btnAjoutEmp.setBackground(new java.awt.Color(0, 180, 219));
        btnAjoutEmp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAjoutEmp.setForeground(new java.awt.Color(227, 227, 227));
        btnAjoutEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/person_add_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnAjoutEmp.setText("Ajouter");
        btnAjoutEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjoutEmpActionPerformed(evt);
            }
        });

        btnModEmp.setBackground(new java.awt.Color(0, 180, 219));
        btnModEmp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnModEmp.setForeground(new java.awt.Color(227, 227, 227));
        btnModEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/refresh_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnModEmp.setText("Modifier");
        btnModEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModEmpActionPerformed(evt);
            }
        });

        btnDelEmp.setBackground(new java.awt.Color(0, 180, 219));
        btnDelEmp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelEmp.setForeground(new java.awt.Color(227, 227, 227));
        btnDelEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/person_remove_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnDelEmp.setText("Supprimer");
        btnDelEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelEmpActionPerformed(evt);
            }
        });

        btnActEmp.setBackground(new java.awt.Color(0, 180, 219));
        btnActEmp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActEmp.setForeground(new java.awt.Color(227, 227, 227));
        btnActEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/autorenew_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnActEmp.setText("Vider Champ");
        btnActEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActEmpActionPerformed(evt);
            }
        });

        prenomEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prenomEmpKeyTyped(evt);
            }
        });

        cinEmp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cinEmpFocusLost(evt);
            }
        });
        cinEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cinEmpKeyTyped(evt);
            }
        });

        phoneEmp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                phoneEmpFocusLost(evt);
            }
        });
        phoneEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneEmpKeyTyped(evt);
            }
        });

        mailEmp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                mailEmpFocusLost(evt);
            }
        });

        adrsEmp.setColumns(20);
        adrsEmp.setRows(5);
        jScrollPane3.setViewportView(adrsEmp);

        jLabel2.setText("Prénom :");

        jLabel3.setText("Cin :");

        jLabel4.setText("Adresse :");

        jLabel5.setText("Téléphone :");

        jLabel6.setText("EMail :");

        jLabel7.setText("Poste :");

        jLabel8.setText("Salaire :");

        dateEmbEmp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateEmbEmpFocusLost(evt);
            }
        });
        dateEmbEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateEmbEmpKeyReleased(evt);
            }
        });

        jLabel9.setText("Date Embauche :");

        motDePasse.setText("jPasswordField1");

        jLabel10.setText("Mot de passe :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(posteEmp, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(cinEmp, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prenomEmp, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomEmp, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idEmp, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mailEmp, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneEmp, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(motDePasse, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(salaireEmp, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateEmbEmp))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAjoutEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActEmp))
                .addGap(0, 70, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnActEmp, btnModEmp});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prenomEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cinEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mailEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(motDePasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(posteEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salaireEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateEmbEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAjoutEmp)
                    .addComponent(btnModEmp))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelEmp)
                    .addComponent(btnActEmp))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnActEmp, btnAjoutEmp, btnDelEmp, btnModEmp});

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.ipady = 27;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 26, 0, 24);
        jPanel4.add(jPanel1, gridBagConstraints);

        maTableEmp.setModel(new javax.swing.table.DefaultTableModel(
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
        maTableEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maTableEmpMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(maTableEmp);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRechEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRechEmpActionPerformed
        String keyword = zoneRechEmp.getText().trim();

        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un mot-clé pour effectuer la recherche.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            GestionEmploye gestionEmploye = new GestionEmploye();
            List<Employe> employes = gestionEmploye.rechercheAleatoire(keyword);

            // Préparer le modèle du tableau
            String[] titre = {"ID", "Nom", "Prénom", "Cin", "Adresse", "Téléphone", "Email", "Poste", "Salaire", "Date Embauche"};
            DefaultTableModel model = new DefaultTableModel(titre, 0);

            for (Employe employe : employes) {
                model.addRow(new Object[]{
                    employe.getId(),
                    employe.getNom(),
                    employe.getPrenom(),
                    employe.getCin(),
                    employe.getAdresse(),
                    employe.getTelephone(),
                    employe.getEmail(),
                    employe.getPosteEmploye(),
                    employe.getSalaire(),
                    employe.getDateEmbauche()
                });
            }

            maTableEmp.setModel(model);

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Aucun employé trouvé pour le mot-clé : " + keyword, "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur SQL lors de la recherche : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Une erreur inattendue s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnRechEmpActionPerformed

    private void btnAjoutEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjoutEmpActionPerformed
        // Récupérer les données saisies dans les champs
        String nom = nomEmp.getText().trim();
        String prenom = prenomEmp.getText().trim();
        String cin = cinEmp.getText().trim();
        String adresseEmploye = adrsEmp.getText().trim();
        String telephone = phoneEmp.getText().trim();
        String email = mailEmp.getText().trim();
        String motDePasseEmp = new String(motDePasse.getPassword()).trim(); // Récupère le mot de passe
        String poste = posteEmp.getText().trim();
        BigDecimal salaire;
        try {
            salaire = new BigDecimal(salaireEmp.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un salaire valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Date dateEmbauche;
        try {
            dateEmbauche = java.sql.Date.valueOf(dateEmbEmp.getText().trim()); // Format attendu : YYYY-MM-DD
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir une date valide au format YYYY-MM-DD.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Vérifier que tous les champs obligatoires sont remplis
        if (nom.isEmpty() || prenom.isEmpty() || cin.isEmpty() || adresseEmploye.isEmpty() || telephone.isEmpty() 
                || email.isEmpty() || motDePasseEmp.isEmpty() || poste.isEmpty() || salaire.compareTo(BigDecimal.ZERO) <= 0) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Créer une instance de GestionEmploye et ajouter l'employé
            GestionEmploye gestionEmploye = new GestionEmploye();
            Employe employe = new Employe(nom, prenom, cin, adresseEmploye, telephone, email, motDePasseEmp, poste, salaire, dateEmbauche);
            gestionEmploye.insert(employe);

            // Réinitialiser les champs du formulaire
            resetForm();

            JOptionPane.showMessageDialog(this, "Employé ajouté avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur SQL lors de l'ajout de l'employé : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Une erreur inattendue s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAjoutEmpActionPerformed

    private void btnModEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModEmpActionPerformed
        // Vérifier qu'un ID employé est sélectionné
        if (idEmp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un employé à modifier (ID requis).", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Récupérer les données saisies dans les champs
            int id = Integer.parseInt(idEmp.getText().trim()); // ID employé sélectionné
            String nom = nomEmp.getText().trim();
            String prenom = prenomEmp.getText().trim();
            String cin = cinEmp.getText().trim();
            String adresseEmploye = adrsEmp.getText().trim();
            String telephone = phoneEmp.getText().trim();
            String email = mailEmp.getText().trim();
            String motDePasseEmp = new String(motDePasse.getPassword()).trim(); // Mot de passe
            String poste = posteEmp.getText().trim();
            BigDecimal salaire;
            try {
                salaire = new BigDecimal(salaireEmp.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir un salaire valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Date dateEmbauche;
            try {
                dateEmbauche = java.sql.Date.valueOf(dateEmbEmp.getText().trim()); // Format attendu : YYYY-MM-DD
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir une date valide au format YYYY-MM-DD.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Vérifier que tous les champs obligatoires sont remplis
            if (nom.isEmpty() || prenom.isEmpty() || cin.isEmpty() || adresseEmploye.isEmpty() || telephone.isEmpty() 
                    || email.isEmpty() || motDePasseEmp.isEmpty() || poste.isEmpty() || salaire.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Créer une instance de GestionEmploye et mettre à jour l'employé
            GestionEmploye gestionEmploye = new GestionEmploye();
            Employe employe = new Employe(id, nom, prenom, cin, adresseEmploye, telephone, email, motDePasseEmp, poste, salaire, dateEmbauche);
            gestionEmploye.update(id, employe);

            // Réinitialiser les champs du formulaire
            resetForm();

            JOptionPane.showMessageDialog(this, "Employé modifié avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur SQL lors de la modification de l'employé : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Une erreur inattendue s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnModEmpActionPerformed

    private void btnDelEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelEmpActionPerformed
        // Vérifier qu'un ID employé est sélectionné
        if (idEmp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un employé à supprimer (ID requis).", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Récupérer l'ID de l'employé
            int id = Integer.parseInt(idEmp.getText().trim());

            // Confirmation avant la suppression
            int confirmation = JOptionPane.showConfirmDialog(this, 
                "Êtes-vous sûr de vouloir supprimer cet employé ?", "Confirmation", 
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirmation == JOptionPane.NO_OPTION) {
                return;
            }

            // Créer une instance de GestionEmploye et supprimer l'employé
            GestionEmploye gestionEmploye = new GestionEmploye();
            gestionEmploye.delete(id);

            // Réinitialiser les champs de formulaire
            resetForm();

            JOptionPane.showMessageDialog(this, "Employé supprimé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID invalide. Veuillez entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur SQL lors de la suppression de l'employé : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Une erreur inattendue s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnDelEmpActionPerformed

    private void btnActEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActEmpActionPerformed
        resetForm();
    }//GEN-LAST:event_btnActEmpActionPerformed

    private void mailEmpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mailEmpFocusLost
        String email = mailEmp.getText().trim();

        if (!ValidationEmail.validateEmail(email, (JFrame) SwingUtilities.getWindowAncestor(this), null)) {
            mailEmp.setBackground(Color.PINK);
            mailEmp.requestFocus();
        } else {
            mailEmp.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_mailEmpFocusLost

    private void cinEmpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cinEmpKeyTyped
        ValidationNumerique validation = new ValidationNumerique(12); // 🔹 Limite à 12 chiffres
        validation.keyTyped(evt); // 🔹 Appliquer la validation numérique
    }//GEN-LAST:event_cinEmpKeyTyped

    private void phoneEmpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneEmpKeyTyped
        ValidationNumerique validation = new ValidationNumerique(10); // 🔹 Limite à 10 chiffres
        validation.keyTyped(evt); // 🔹 Appliquer la validation numérique
    }//GEN-LAST:event_phoneEmpKeyTyped

    private void phoneEmpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phoneEmpFocusLost
        validerNombreChiffres(phoneEmp, 10);
    }//GEN-LAST:event_phoneEmpFocusLost

    private void cinEmpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cinEmpFocusLost
        validerNombreChiffres(cinEmp, 12);
    }//GEN-LAST:event_cinEmpFocusLost

    private void prenomEmpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prenomEmpKeyTyped
        ValidationChaine validation = new ValidationChaine(50); // 🔹 Limite à 50 caractères max
        validation.keyTyped(evt); // 🔹 Appliquer la validation de chaîne
    }//GEN-LAST:event_prenomEmpKeyTyped

    private void nomEmpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomEmpKeyTyped
        ValidationChaine validation = new ValidationChaine(50); // 🔹 Limite à 50 caractères max
        validation.keyTyped(evt); // 🔹 Appliquer la validation de chaîne
    }//GEN-LAST:event_nomEmpKeyTyped

    private void maTableEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maTableEmpMouseClicked
        int ligne = maTableEmp.getSelectedRow();
        idEmp.setText(String.valueOf(maTableEmp.getValueAt(ligne, 0)));
        nomEmp.setText(String.valueOf(maTableEmp.getValueAt(ligne, 1)));
        prenomEmp.setText(String.valueOf(maTableEmp.getValueAt(ligne, 2)));
        // 🔹 Validation cin
        String cin = String.valueOf(maTableEmp.getValueAt(ligne, 3)).trim();
        if (cin.length() != 12 || !cin.matches("\\d+")) { // Vérifie format et longueur
            JOptionPane.showMessageDialog(this, "Le cin est invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            cinEmp.setBackground(Color.PINK);
        } else {
            cinEmp.setBackground(Color.WHITE);
        }
        cinEmp.setText(cin);
        adrsEmp.setText(String.valueOf(maTableEmp.getValueAt(ligne, 4)));
        // 🔹 Validation du numéro de téléphone
        String phone = String.valueOf(maTableEmp.getValueAt(ligne, 5)).trim();
        if (phone.length() != 10 || !phone.matches("\\d+")) { // Vérifie format et longueur
            JOptionPane.showMessageDialog(this, "Le numéro de téléphone est invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            phoneEmp.setBackground(Color.PINK);
        } else {
            phoneEmp.setBackground(Color.WHITE);
        }
        phoneEmp.setText(phone);
        // 🔹 Validation de l’e-mail
        String email = String.valueOf(maTableEmp.getValueAt(ligne, 6)).trim();
        if (!ValidationEmail.validateEmail(email, (JFrame) SwingUtilities.getWindowAncestor(this), "L'adresse e-mail est invalide")) {
            mailEmp.setBackground(Color.PINK);
        } else {
            mailEmp.setBackground(Color.WHITE);
        }
        mailEmp.setText(email);
        posteEmp.setText(String.valueOf(maTableEmp.getValueAt(ligne, 7)));
        salaireEmp.setText(String.valueOf(maTableEmp.getValueAt(ligne, 8)));
        dateEmbEmp.setText(String.valueOf(maTableEmp.getValueAt(ligne, 9)));

    }//GEN-LAST:event_maTableEmpMouseClicked

    private void dateEmbEmpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateEmbEmpKeyReleased
        verifierFormatDate(dateEmbEmp);
    }//GEN-LAST:event_dateEmbEmpKeyReleased

    private void dateEmbEmpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateEmbEmpFocusLost
        String dateStr = dateEmbEmp.getText().trim();
        
        // 🔹 Vérifier si le champ est vide
        if (dateStr.isEmpty()) {
            return; // 🔹 Ne fait rien si le champ est vide (autorise la sortie)
        }

        if (!dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(null, "Format incorrect ! Utilisez YYYY-MM-DD.", "Erreur", JOptionPane.ERROR_MESSAGE);
            dateEmbEmp.requestFocus();
        } 
    }//GEN-LAST:event_dateEmbEmpFocusLost

        private void verifierFormatDate(JTextField champ) {
            String dateStr = champ.getText().trim();

            // 🔹 Autorise un champ vide (aucune vérification)
            if (dateStr.isEmpty()) return;

            // 🔹 Vérifie si le format est incorrect (YYYY-MM-DD)
            if (!dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                champ.setForeground(Color.RED); // 🔹 Change la couleur du texte en rouge
            } else {
                champ.setForeground(Color.BLACK); // 🔹 Retour à la couleur normale si c'est valide
            }
        }
    private void validerNombreChiffres(JTextField field, int maxDigits) {
        String phone = field.getText().trim();

        if (phone.length() < maxDigits) {
            JOptionPane.showMessageDialog(this, "Ce champ doit contenir exactement " + maxDigits + " chiffres.", "Erreur", JOptionPane.WARNING_MESSAGE);
            field.setBackground(Color.PINK);
            field.requestFocus();
        } else {
            field.setBackground(Color.WHITE);
        }
    }

    private void chargerTableau(){
        try {
            // Initialiser la classe GestionEmploye
            GestionEmploye gestionEmploye = new GestionEmploye();

            // Appeler la méthode chargerTableau pour actualiser maTableEmp
            gestionEmploye.chargerTableau(maTableEmp);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'actualisation du tableau des employés : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Une erreur inattendue s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void resetForm() {
        idEmp.setText("");
        nomEmp.setText("");
        prenomEmp.setText("");
        cinEmp.setText("");
        adrsEmp.setText("");
        phoneEmp.setText("");
        mailEmp.setText("");
        motDePasse.setText("");
        posteEmp.setText("");
        salaireEmp.setText("");
        dateEmbEmp.setText("");
        chargerTableau();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea adrsEmp;
    private javax.swing.JButton btnActEmp;
    private javax.swing.JButton btnAjoutEmp;
    private javax.swing.JButton btnDelEmp;
    private javax.swing.JButton btnModEmp;
    private javax.swing.JButton btnRechEmp;
    private javax.swing.JTextField cinEmp;
    private javax.swing.JTextField dateEmbEmp;
    private javax.swing.JTextField idEmp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable maTableEmp;
    private javax.swing.JTextField mailEmp;
    private javax.swing.JPasswordField motDePasse;
    private javax.swing.JTextField nomEmp;
    private javax.swing.JTextField phoneEmp;
    private javax.swing.JTextField posteEmp;
    private javax.swing.JTextField prenomEmp;
    private javax.swing.JTextField salaireEmp;
    private javax.swing.JTextField zoneRechEmp;
    // End of variables declaration//GEN-END:variables
}
