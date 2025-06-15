package projet.form.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import projet.form.panel.*;
import projet.form.statistiques.PanelStatistiques;

import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import projet.form.authentification.Authentification;


public class Main extends javax.swing.JFrame {
    private String posteEmploye;
    private String nomEmploye;
    
    private static boolean isActive = false;

    public static boolean isInstanceActive() {
        return isActive;
    }
    
    public void setEmployeInfo(String nom, String poste) {
        System.out.println("Appel de setEmployeInfo() avec : " + nom + " - " + poste);

        this.nomEmploye = nom;
        this.posteEmploye = poste;

        lblEmployeInfo.setText("Bienvenue " + nomEmploye + " - Poste : " + posteEmploye);
        lblEmployeInfo.repaint();
    }

    private boolean isMaximized; // État : agrandi ou réduit
    private Dimension defaultSize; // Taille par défaut
    
    private PanelAchat form1;
    private PanelClient form2;
    private PanelEmploye form3;
    private PanelFacture form4;
    private PanelVehicule form5;
    private PanelHistorique form6;
    private PanelStatistiques form7;
    
    public Main() {
        try {
            initComponents();
            
            isActive = true;
            init();
            
            defaultSize = new Dimension(900, 500);
            setSize(defaultSize);
            
            //backPanel1.initMoving(Main.this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Méthode pour basculer entre agrandir et réduire
    private void toggleSize() {
        if (isMaximized) {
            setSize(defaultSize);
            setLocationRelativeTo(null);
            btnChangeSize.setSelected(false);
        } else {
            setExtendedState(Main.MAXIMIZED_BOTH);
            setLocation(0, 0);
            btnChangeSize.setSelected(true);
        }
        isMaximized = !isMaximized;
    }

    private void init() {
        setBackground(new Color(0, 0, 0, 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backPanel1 = new projet.form.control.BackPanel();
        jPanel1 = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        btnChangeSize = new javax.swing.JButton();
        btnResize = new javax.swing.JButton();
        titre = new javax.swing.JLabel();
        lblEmployeInfo = new javax.swing.JLabel();
        conteneur = new javax.swing.JPanel();
        panCtrl = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        openAchat = new javax.swing.JButton();
        btnOpenVehicule = new javax.swing.JButton();
        openClient = new javax.swing.JButton();
        openEmploye = new javax.swing.JButton();
        openFacture = new javax.swing.JButton();
        btnHistory = new javax.swing.JButton();
        btnStat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setUndecorated(true);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setOpaque(false);

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/close_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnClose.setBorder(null);
        btnClose.setBorderPainted(false);
        btnClose.setContentAreaFilled(false);
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnChangeSize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/square_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnChangeSize.setBorder(null);
        btnChangeSize.setBorderPainted(false);
        btnChangeSize.setContentAreaFilled(false);
        btnChangeSize.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/restore_line.png"))); // NOI18N
        btnChangeSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeSizeActionPerformed(evt);
            }
        });

        btnResize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/remove_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnResize.setBorder(null);
        btnResize.setBorderPainted(false);
        btnResize.setContentAreaFilled(false);
        btnResize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResizeActionPerformed(evt);
            }
        });

        titre.setFont(new java.awt.Font("Segoe UI", 3, 26)); // NOI18N
        titre.setForeground(new java.awt.Color(227, 227, 227));
        titre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/apartment_40dp_E3E3E3_FILL0_wght400_GRAD0_opsz40.png"))); // NOI18N
        titre.setText("Gestion Vente Pro");

        lblEmployeInfo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmployeInfo.setForeground(new java.awt.Color(227, 227, 227));
        lblEmployeInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/person_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        lblEmployeInfo.setText("Personne");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titre)
                .addGap(233, 233, 233)
                .addComponent(lblEmployeInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
                .addComponent(btnResize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChangeSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmployeInfo)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChangeSize)
                    .addComponent(titre)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btnResize, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        conteneur.setMinimumSize(new java.awt.Dimension(759, 602));
        conteneur.setOpaque(false);
        conteneur.setPreferredSize(new java.awt.Dimension(759, 602));
        conteneur.setLayout(new javax.swing.BoxLayout(conteneur, javax.swing.BoxLayout.LINE_AXIS));

        panCtrl.setOpaque(false);
        panCtrl.setLayout(new java.awt.GridBagLayout());

        jPanel3.setOpaque(false);

        openAchat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        openAchat.setForeground(new java.awt.Color(227, 227, 227));
        openAchat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/shop_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        openAchat.setText("Achat");
        openAchat.setContentAreaFilled(false);
        openAchat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        openAchat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openAchatActionPerformed(evt);
            }
        });

        btnOpenVehicule.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnOpenVehicule.setForeground(new java.awt.Color(227, 227, 227));
        btnOpenVehicule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/scooter_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnOpenVehicule.setText("Articles");
        btnOpenVehicule.setContentAreaFilled(false);
        btnOpenVehicule.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpenVehicule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenVehiculeActionPerformed(evt);
            }
        });

        openClient.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        openClient.setForeground(new java.awt.Color(227, 227, 227));
        openClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/person_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        openClient.setText("Clients");
        openClient.setContentAreaFilled(false);
        openClient.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        openClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openClientActionPerformed(evt);
            }
        });

        openEmploye.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        openEmploye.setForeground(new java.awt.Color(227, 227, 227));
        openEmploye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/person_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        openEmploye.setText("Employe");
        openEmploye.setContentAreaFilled(false);
        openEmploye.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        openEmploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openEmployeActionPerformed(evt);
            }
        });

        openFacture.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        openFacture.setForeground(new java.awt.Color(227, 227, 227));
        openFacture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/request_quote_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        openFacture.setText("Facture");
        openFacture.setContentAreaFilled(false);
        openFacture.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        openFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFactureActionPerformed(evt);
            }
        });

        btnHistory.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHistory.setForeground(new java.awt.Color(227, 227, 227));
        btnHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/info_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnHistory.setText("Historique");
        btnHistory.setContentAreaFilled(false);
        btnHistory.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });

        btnStat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnStat.setForeground(new java.awt.Color(227, 227, 227));
        btnStat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/monitoring_40dp_E3E3E3_FILL0_wght400_GRAD0_opsz40.png"))); // NOI18N
        btnStat.setText("Statistiques");
        btnStat.setContentAreaFilled(false);
        btnStat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnStat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnStat)
                    .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openClient, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpenVehicule, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openAchat, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHistory, btnOpenVehicule, btnStat, openAchat, openClient, openEmploye, openFacture});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(openAchat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOpenVehicule, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(openClient)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(openEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(openFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStat, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHistory, btnOpenVehicule, btnStat, openAchat, openClient, openEmploye, openFacture});

        panCtrl.add(jPanel3, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout backPanel1Layout = new javax.swing.GroupLayout(backPanel1);
        backPanel1.setLayout(backPanel1Layout);
        backPanel1Layout.setHorizontalGroup(
            backPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(backPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panCtrl, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(conteneur, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        backPanel1Layout.setVerticalGroup(
            backPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backPanel1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(conteneur, javax.swing.GroupLayout.PREFERRED_SIZE, 419, Short.MAX_VALUE)
                    .addComponent(panCtrl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(backPanel1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnChangeSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeSizeActionPerformed
        toggleSize();
    }//GEN-LAST:event_btnChangeSizeActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        int choix = JOptionPane.showConfirmDialog(null, "Vous allez âtre déconnecté.\nVoulez-vous continuer la ferméture ?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choix == JOptionPane.YES_OPTION) {
            dispose();
            Authentification auth = new Authentification();
            auth.setVisible(true);
        } else {
        }
        
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnOpenVehiculeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenVehiculeActionPerformed
        if (form5 == null) {
            form5 = new PanelVehicule();
        }
        showPanel(form5);
    }//GEN-LAST:event_btnOpenVehiculeActionPerformed

    private void openAchatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openAchatActionPerformed
        try {
            if (form1 == null) {
                form1 = new PanelAchat();
            }
            showPanel(form1);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Erreur lors du chargement de PanelAchat", ex);
            JOptionPane.showMessageDialog(this, "Une erreur s'est produite lors de l'ouverture du panel Achat.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_openAchatActionPerformed

    private void openClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openClientActionPerformed
        if (form2 == null) {
            form2 = new PanelClient();
        }
        showPanel(form2);
    }//GEN-LAST:event_openClientActionPerformed

    private void openEmployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openEmployeActionPerformed
        if (!"Admin".equalsIgnoreCase(posteEmploye)) {
            JOptionPane.showMessageDialog(this, "Accès refusé !", "Permission insuffisante", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (form3 == null) {
            form3 = new PanelEmploye();
        }
        showPanel(form3);
    }//GEN-LAST:event_openEmployeActionPerformed

    private void openFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFactureActionPerformed
        if (form4 == null) {
            form4 = new PanelFacture();
        }
        showPanel(form4);
    }//GEN-LAST:event_openFactureActionPerformed

    private void btnResizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResizeActionPerformed
        this.setState(Main.ICONIFIED);
    }//GEN-LAST:event_btnResizeActionPerformed

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        if (!"Admin".equalsIgnoreCase(posteEmploye) && !"Manager".equalsIgnoreCase(posteEmploye)) {
            JOptionPane.showMessageDialog(this, "Accès refusé !", "Permission insuffisante", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (form6 == null) {
            form6 = new PanelHistorique();
        }
        showPanel(form6);
    }//GEN-LAST:event_btnHistoryActionPerformed

    private void btnStatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatActionPerformed
        if (!"Admin".equalsIgnoreCase(posteEmploye) && !"Manager".equalsIgnoreCase(posteEmploye)) {
            JOptionPane.showMessageDialog(this, "Accès refusé ! Vous n'avez pas l'autorisation d'accéder à ce panel.", "Permission insuffisante", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (form7 == null) {
            form7 = new PanelStatistiques();
        }
        showPanel(form7);
    }//GEN-LAST:event_btnStatActionPerformed

    // Méthode générique pour afficher un panneau
    private void showPanel(JPanel panel) {
        if (panel != null) {
            conteneur.removeAll(); // Supprime les anciens composants
            conteneur.setLayout(new BorderLayout()); // Assure un bon affichage
            conteneur.add(panel, BorderLayout.CENTER); // Ajoute le nouveau panel

            SwingUtilities.invokeLater(() -> {
                conteneur.revalidate(); // Met à jour la mise en page
                conteneur.repaint(); // Redessine proprement
            });
        } else {
            System.err.println("Erreur : Le panel est null !");
        }
    }

    @Override
    public void dispose() {
        isActive = false;
        super.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private projet.form.control.BackPanel backPanel1;
    private javax.swing.JButton btnChangeSize;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnOpenVehicule;
    private javax.swing.JButton btnResize;
    private javax.swing.JButton btnStat;
    private javax.swing.JPanel conteneur;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblEmployeInfo;
    private javax.swing.JButton openAchat;
    private javax.swing.JButton openClient;
    private javax.swing.JButton openEmploye;
    private javax.swing.JButton openFacture;
    private javax.swing.JPanel panCtrl;
    private javax.swing.JLabel titre;
    // End of variables declaration//GEN-END:variables
}
