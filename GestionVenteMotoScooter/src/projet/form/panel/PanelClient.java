package projet.form.panel;

import java.awt.Color;
import javax.swing.JOptionPane; // Pour afficher des boîtes de dialogue
import javax.swing.table.DefaultTableModel;
import projet.classes.gestion.GestionClients; // Pour accéder à la gestion des clients
import projet.classes.tables.Clients; // Pour manipuler les objets Clients
import java.sql.ResultSet; // Pour manipuler les résultats des requêtes SQL
import java.sql.SQLException; // Pour gérer les exceptions liées aux bases de données
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import projet.classes.controles.ValidationChaine;
import projet.classes.controles.ValidationEmail;
import projet.classes.controles.ValidationMix;
import projet.classes.controles.ValidationNumerique;

public class PanelClient extends javax.swing.JPanel {

    public PanelClient() {
        initComponents();
        chargerTableau();
        
        idCli.setVisible(false);
        ((AbstractDocument) adrsCli.getDocument()).setDocumentFilter(new ValidationMix(100)); // 🔹 Limite à 100 caractères max
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        maTableCli = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        idCli = new javax.swing.JTextField();
        nomCli = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnAjoutCli = new javax.swing.JButton();
        btnModCli = new javax.swing.JButton();
        btnDelCli = new javax.swing.JButton();
        btnActCli = new javax.swing.JButton();
        prenomCli = new javax.swing.JTextField();
        cinCli = new javax.swing.JTextField();
        phoneCli = new javax.swing.JTextField();
        mailCli = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        adrsCli = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        zoneRechCli = new javax.swing.JTextField();
        btnRechCli = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 255));
        setMinimumSize(new java.awt.Dimension(760, 600));
        setPreferredSize(new java.awt.Dimension(760, 600));

        maTableCli.setModel(new javax.swing.table.DefaultTableModel(
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
        maTableCli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maTableCliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(maTableCli);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        nomCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomCliKeyTyped(evt);
            }
        });

        jLabel1.setText("Nom:");

        btnAjoutCli.setBackground(new java.awt.Color(0, 180, 219));
        btnAjoutCli.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAjoutCli.setForeground(new java.awt.Color(227, 227, 227));
        btnAjoutCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/person_add_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnAjoutCli.setText("Ajouter");
        btnAjoutCli.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAjoutCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjoutCliActionPerformed(evt);
            }
        });

        btnModCli.setBackground(new java.awt.Color(0, 180, 219));
        btnModCli.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnModCli.setForeground(new java.awt.Color(227, 227, 227));
        btnModCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/refresh_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnModCli.setText("Modifier");
        btnModCli.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnModCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModCliActionPerformed(evt);
            }
        });

        btnDelCli.setBackground(new java.awt.Color(0, 180, 219));
        btnDelCli.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelCli.setForeground(new java.awt.Color(227, 227, 227));
        btnDelCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/person_remove_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnDelCli.setText("Supprimer");
        btnDelCli.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDelCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelCliActionPerformed(evt);
            }
        });

        btnActCli.setBackground(new java.awt.Color(0, 180, 219));
        btnActCli.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActCli.setForeground(new java.awt.Color(227, 227, 227));
        btnActCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/autorenew_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnActCli.setText("Vider Champ");
        btnActCli.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnActCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActCliActionPerformed(evt);
            }
        });

        prenomCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prenomCliKeyTyped(evt);
            }
        });

        cinCli.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cinCliFocusLost(evt);
            }
        });
        cinCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cinCliKeyTyped(evt);
            }
        });

        phoneCli.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                phoneCliFocusLost(evt);
            }
        });
        phoneCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneCliKeyTyped(evt);
            }
        });

        mailCli.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                mailCliFocusLost(evt);
            }
        });

        adrsCli.setColumns(20);
        adrsCli.setRows(5);
        jScrollPane3.setViewportView(adrsCli);

        jLabel2.setText("Prénom :");

        jLabel3.setText("Cin :");

        jLabel4.setText("Adresse :");

        jLabel5.setText("Téléphone :");

        jLabel6.setText("EMail :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomCli)
                            .addComponent(idCli)
                            .addComponent(prenomCli)
                            .addComponent(cinCli)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(phoneCli)
                            .addComponent(mailCli)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDelCli)
                            .addComponent(btnAjoutCli))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnModCli)
                            .addComponent(btnActCli))))
                .addGap(57, 57, 57))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnActCli, btnAjoutCli, btnDelCli, btnModCli});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(idCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nomCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(prenomCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cinCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 27, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(phoneCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(mailCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModCli)
                    .addComponent(btnAjoutCli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelCli)
                    .addComponent(btnActCli))
                .addGap(33, 33, 33))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.ipady = 27;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 26, 0, 24);
        jPanel4.add(jPanel1, gridBagConstraints);

        jPanel2.setOpaque(false);

        zoneRechCli.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnRechCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/search_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnRechCli.setBorder(null);
        btnRechCli.setBorderPainted(false);
        btnRechCli.setContentAreaFilled(false);
        btnRechCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRechCliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(429, Short.MAX_VALUE)
                .addComponent(zoneRechCli, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRechCli)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRechCli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(zoneRechCli)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRechCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRechCliActionPerformed
        // Récupérer le mot-clé saisi
        String keyword = zoneRechCli.getText().trim();

        // Vérifier que le mot-clé n'est pas vide
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un mot-clé pour effectuer la recherche.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Instance de GestionClients
            GestionClients gestionClients = new GestionClients();

            // Récupérer les résultats de la recherche
            List<Clients> clients = gestionClients.rechercheAleatoire(keyword);

            // Préparer le modèle pour le tableau
            String[] titre = {"ID", "Nom", "Prénom", "Cin", "Adresse", "Téléphone", "Email"};
            DefaultTableModel model = new DefaultTableModel(titre, 0);

            for (Clients client : clients) {
                model.addRow(new Object[]{
                    client.getId(),
                    client.getNom(),
                    client.getPrenom(),
                    client.getCin(),
                    client.getAdresse(),
                    client.getTelephone(),
                    client.getEmail()
                });
            }

            // Mettre à jour le tableau
            maTableCli.setModel(model);

            // Message si aucun résultat n'est trouvé
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Aucun client trouvé pour le mot-clé : " + keyword, "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur SQL : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur générale : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnRechCliActionPerformed

    private void btnAjoutCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjoutCliActionPerformed
        try {
            // Récupérer les informations saisies
            String nom = nomCli.getText();
            String prenom = prenomCli.getText();
            String cin = cinCli.getText();
            String adresse = adrsCli.getText();
            String telephone = phoneCli.getText();
            String email = mailCli.getText();

            // Créer une instance du client
            Clients client = new Clients(nom, prenom, cin, adresse, telephone, email);

            // Ajouter le client dans la base
            GestionClients gestionClients = new GestionClients();
            gestionClients.insert(client);

            // Afficher un message de succès
            JOptionPane.showMessageDialog(this, "Client ajouté avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);

            // Recharger le tableau
            chargerTableau();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du client : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur inattendue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAjoutCliActionPerformed

    private void btnModCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModCliActionPerformed
        try {
            // Vérifier que l'ID du client est renseigné
            String idText = idCli.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir l'ID du client à modifier.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = Integer.parseInt(idText); // Convertir l'ID en entier

            // Récupérer les informations des champs de modification
            String nom = nomCli.getText().trim();
            String prenom = prenomCli.getText().trim();
            String cin = cinCli.getText().trim();
            String adresse = adrsCli.getText().trim();
            String telephone = phoneCli.getText().trim();
            String email = mailCli.getText().trim();

            // Vérifier que les champs obligatoires sont remplis
            if (nom.isEmpty() || prenom.isEmpty() || cin.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Créer un objet Clients avec les nouvelles données
            Clients client = new Clients(id, nom, prenom, cin, adresse, telephone, email);

            // Mettre à jour les informations dans la base
            GestionClients gestionClients = new GestionClients();
            gestionClients.update(id, client);

            // Message de succès
            JOptionPane.showMessageDialog(this, "Client mis à jour avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);

            // Recharger le tableau avec les nouvelles données
            chargerTableau();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "L'ID du client doit être un nombre entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur inattendue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnModCliActionPerformed

    private void btnDelCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelCliActionPerformed
        try {
            // Vérifier que l'ID du client est renseigné
            String idText = idCli.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir l'ID du client à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = Integer.parseInt(idText); // Convertir l'ID en entier

            // Confirmation avant suppression
            int confirmation = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer le client avec l'ID " + id + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation != JOptionPane.YES_OPTION) {
                return; // Annuler la suppression
            }

            // Suppression dans la base de données
            GestionClients gestionClients = new GestionClients();
            gestionClients.delete(id);

            // Message de succès
            JOptionPane.showMessageDialog(this, "Client supprimé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);

            // Recharger le tableau
            chargerTableau();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "L'ID du client doit être un nombre entier valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppression : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur inattendue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnDelCliActionPerformed

    private void btnActCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActCliActionPerformed
        try {
            // Recharger le tableau avec les données à jour
            chargerTableau();

            // Réinitialiser tous les champs du formulaire
            idCli.setText("");
            nomCli.setText("");
            prenomCli.setText("");
            cinCli.setText("");
            adrsCli.setText("");
            phoneCli.setText("");
            mailCli.setText("");

            // Afficher un message indiquant que le tableau a été actualisé et les champs réinitialisés
            JOptionPane.showMessageDialog(this, "Tableau des clients actualisé et formulaire réinitialisé avec succès !", "Information", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            // Gestion des erreurs lors de l'actualisation ou de la réinitialisation
            JOptionPane.showMessageDialog(this, "Erreur lors de l'actualisation des données : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnActCliActionPerformed

    private void mailCliFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mailCliFocusLost
        String email = mailCli.getText().trim();

        if (!ValidationEmail.validateEmail(email, (JFrame) SwingUtilities.getWindowAncestor(this), null)) {
            mailCli.setBackground(Color.PINK);
            mailCli.requestFocus();
        } else {
            mailCli.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_mailCliFocusLost

    private void phoneCliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneCliKeyTyped
        ValidationNumerique validation = new ValidationNumerique(10); // 🔹 Limite à 12 chiffres
        validation.keyTyped(evt); // 🔹 Appliquer la validation numérique
    }//GEN-LAST:event_phoneCliKeyTyped

    private void phoneCliFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phoneCliFocusLost
        validerNombreChiffres(phoneCli, 10);
    }//GEN-LAST:event_phoneCliFocusLost

    private void cinCliFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cinCliFocusLost
        validerNombreChiffres(cinCli, 10);
    }//GEN-LAST:event_cinCliFocusLost

    private void cinCliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cinCliKeyTyped
        ValidationNumerique validation = new ValidationNumerique(12); // 🔹 Limite à 12 chiffres
        validation.keyTyped(evt); // 🔹 Appliquer la validation numérique
    }//GEN-LAST:event_cinCliKeyTyped

    private void nomCliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomCliKeyTyped
        ValidationChaine validation = new ValidationChaine(50); // 🔹 Limite à 50 caractères max
        validation.keyTyped(evt); // 🔹 Appliquer la validation de chaîne
    }//GEN-LAST:event_nomCliKeyTyped

    private void prenomCliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prenomCliKeyTyped
        ValidationChaine validation = new ValidationChaine(50); // 🔹 Limite à 50 caractères max
        validation.keyTyped(evt); // 🔹 Appliquer la validation de chaîne
    }//GEN-LAST:event_prenomCliKeyTyped

    private void maTableCliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maTableCliMouseClicked
        int ligne = maTableCli.getSelectedRow();

        // 🔹 Remplissage des champs avec les valeurs de la ligne sélectionnée
        idCli.setText(String.valueOf(maTableCli.getValueAt(ligne, 0)));
        nomCli.setText(String.valueOf(maTableCli.getValueAt(ligne, 1)));
        prenomCli.setText(String.valueOf(maTableCli.getValueAt(ligne, 2)));
        cinCli.setText(String.valueOf(maTableCli.getValueAt(ligne, 3)));
        adrsCli.setText(String.valueOf(maTableCli.getValueAt(ligne, 4)));

        // 🔹 Validation du numéro de téléphone
        String phone = String.valueOf(maTableCli.getValueAt(ligne, 5)).trim();
        if (phone.length() < 8 || phone.length() > 15 || !phone.matches("\\d+")) { // Vérifie format et longueur
            JOptionPane.showMessageDialog(this, "Le numéro de téléphone est invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            phoneCli.setBackground(Color.PINK); // 🔹 Indique une erreur visuelle
        } else {
            phoneCli.setBackground(Color.WHITE);
        }
        phoneCli.setText(phone);

        // 🔹 Validation de l’e-mail
        String email = String.valueOf(maTableCli.getValueAt(ligne, 6)).trim();
        if (!ValidationEmail.validateEmail(email, (JFrame) SwingUtilities.getWindowAncestor(this), "L'adresse e-mail est invalide")) {
            mailCli.setBackground(Color.PINK); // 🔹 Indique une erreur visuelle
        } else {
            mailCli.setBackground(Color.WHITE);
        }
        mailCli.setText(email);
    }//GEN-LAST:event_maTableCliMouseClicked

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
    private void chargerTableau() {
    try {
        // Crée une instance de GestionClients pour accéder à chargerTableau
        GestionClients gestionClients = new GestionClients();
        // Appel de chargerTableau pour remplir maTableCli
        gestionClients.chargerTableau(maTableCli);
    } catch (Exception ex) {
        // Gestion des erreurs
        JOptionPane.showMessageDialog(this, "Erreur lors du chargement des données : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea adrsCli;
    private javax.swing.JButton btnActCli;
    private javax.swing.JButton btnAjoutCli;
    private javax.swing.JButton btnDelCli;
    private javax.swing.JButton btnModCli;
    private javax.swing.JButton btnRechCli;
    private javax.swing.JTextField cinCli;
    private javax.swing.JTextField idCli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable maTableCli;
    private javax.swing.JTextField mailCli;
    private javax.swing.JTextField nomCli;
    private javax.swing.JTextField phoneCli;
    private javax.swing.JTextField prenomCli;
    private javax.swing.JTextField zoneRechCli;
    // End of variables declaration//GEN-END:variables
}
