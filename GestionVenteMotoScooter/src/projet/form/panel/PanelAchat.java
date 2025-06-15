package projet.form.panel;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date; // Utilisé pour la date de vente
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import projet.classes.controles.ValidationDecimal;
import projet.classes.controles.ValidationNumerique;
import projet.classes.gestion.GestionAchat;
import projet.classes.gestion.GestionClients;
import projet.classes.gestion.GestionEmploye;
import projet.classes.gestion.GestionFacture;
import projet.classes.gestion.GestionVehicule;
import projet.classes.tables.Achat;
import projet.classes.tables.Clients;
import projet.classes.tables.Employe;
import projet.classes.tables.Facture;
import projet.classes.tables.Vehicule;



public final class PanelAchat extends javax.swing.JPanel {

    GestionFacture gestionFacture = new GestionFacture();
    private int idFactureActuel = gestionFacture.getLastIdFacture() + 1; // ID Facture en cours

    public PanelAchat() throws Exception {
        initComponents();
        actualiserPanel();
        idVehicule.setVisible(false);
        idAchat.setVisible(false);
    }
         
    private void validerQte() {
        String texte = qteAchat.getText().trim();

        if (!ValidationDecimal.isValidNumber(texte, true)) {
            JOptionPane.showMessageDialog(null, "Valeur invalide", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            qteAchat.setBackground(Color.red);
        }
        else {
            qteAchat.setBackground(Color.white);
        }
    }
    private void actualiserPanel(){
        chargerClients();
        chargerEmploye();
        chargerLesCategorie();
        initTableListener();

        // Initialiser et charger les achats pour la facture en cours
        chargerTableAchats(idFactureActuel);
    }

    public void chargerClients() {
        try {
            // Initialiser l'objet de gestion des clients
            GestionClients gestionClient = new GestionClients();

            // Récupérer la liste de tous les clients
            List<Clients> clients = gestionClient.selectAll();

            // Vider les items existants des JComboBox
            comboIdClient.removeAllItems();
            comboCinClient.removeAllItems();

            // Ajouter chaque client dans les JComboBox avec conversion de l'ID en chaîne
            for (Clients client : clients) {
                comboIdClient.addItem(String.valueOf(client.getId())); // Convertir l'ID en chaîne avant de l'ajouter
                comboCinClient.addItem(client.getCin());               // Ajouter le CIN au comboBox
            }
        } catch (SQLException ex) {
            // Afficher une erreur si un problème SQL survient
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des clients : " + ex.getMessage(), "Erreur SQL", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            // Afficher une erreur pour tout autre problème inattendu
            JOptionPane.showMessageDialog(this, "Une erreur inattendue s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    public void chargerEmploye() {
        try {
            // Initialiser l'objet de gestion des employés
            GestionEmploye gestionEmploye = new GestionEmploye();

            // Récupérer la liste de tous les employés
            List<Employe> employes = gestionEmploye.selectAll();

            // Vider les items existants des JComboBox
            comboIdEmp.removeAllItems();

            // Ajouter chaque employé dans les JComboBox
            for (Employe employe : employes) {
                comboIdEmp.addItem(String.valueOf(employe.getId())); // Convertir l'ID en chaîne avant de l'ajouter
            }
        } catch (SQLException ex) {
            // Afficher une erreur si un problème SQL survient
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des employés : " + ex.getMessage(), "Erreur SQL", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            // Afficher une erreur pour tout autre problème inattendu
            JOptionPane.showMessageDialog(this, "Une erreur inattendue s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void chargerLesCategorie() {
        // Charger les catégories automatiquement dans le JComboBox
        try {
            GestionVehicule gestionVehicule = new GestionVehicule();
            List<String> categories = gestionVehicule.getCategories();
            categorieVehicule.removeAllItems(); // Supprime les éléments existants
            for (String categorie : categories) {
                categorieVehicule.addItem(categorie); // Ajoute chaque catégorie au JComboBox
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des catégories : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Une erreur inattendue est survenue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateMontantTotal() {
        try {
            BigDecimal somme = BigDecimal.ZERO;

            for (int i = 0; i < maTableAchat.getRowCount(); i++) {
                Object value = maTableAchat.getValueAt(i, 6); // Index de la colonne prix_total, à ajuster
                if (value != null) {
                    somme = somme.add(new BigDecimal(value.toString()));
                }
            }

            montantTotal.setText(somme.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour du montant total : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void chargerTableAchats(int idFacture) {
        try {
            GestionFacture gestionFacture = new GestionFacture();
            List<Object[]> achats = gestionFacture.getAchatsByFactureId(idFacture);

            String[] colonnes = {"ID", "ID Vehicule", "Catégorie", "Marque", "Modèle", "Quantité", "Prix Total"};
            Object[][] data = new Object[achats.size()][colonnes.length];
            for (int i = 0; i < achats.size(); i++) {
                data[i] = achats.get(i);
            }

            maTableAchat.setModel(new javax.swing.table.DefaultTableModel(data, colonnes));

            // Recalculer le montant total après chargement
            updateMontantTotal();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des achats : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initTableListener() {
        maTableAchat.getModel().addTableModelListener(e -> {
            updateMontantTotal();
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panClient = new javax.swing.JPanel();
        comboIdClient = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        nomClient = new javax.swing.JTextField();
        comboCinClient = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        panVendeur = new javax.swing.JPanel();
        comboIdEmp = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        nomEmp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        comboPosteEmp = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        panVehicule = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        categorieVehicule = new javax.swing.JComboBox<>();
        marqueVehicule = new javax.swing.JComboBox<>();
        modeleVehicule = new javax.swing.JComboBox<>();
        prixVehicule = new javax.swing.JTextField();
        stockVehicule = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        qteAchat = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnAjoutAchat = new javax.swing.JButton();
        btnModAchat = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();
        idAchat = new javax.swing.JTextField();
        idVehicule = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        maTableAchat = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        montantTotal = new javax.swing.JTextField();
        btnValider = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 255));
        setMinimumSize(new java.awt.Dimension(760, 600));
        setPreferredSize(new java.awt.Dimension(760, 600));

        comboIdClient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboIdClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIdClientActionPerformed(evt);
            }
        });

        jLabel1.setText("Client ID :");

        comboCinClient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboCinClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCinClientActionPerformed(evt);
            }
        });

        jLabel2.setText("Nom :");

        jLabel3.setText("Cin n°");

        javax.swing.GroupLayout panClientLayout = new javax.swing.GroupLayout(panClient);
        panClient.setLayout(panClientLayout);
        panClientLayout.setHorizontalGroup(
            panClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panClientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboIdClient, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomClient, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(comboCinClient, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panClientLayout.setVerticalGroup(
            panClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panClientLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboIdClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCinClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        comboIdEmp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboIdEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIdEmpActionPerformed(evt);
            }
        });

        jLabel4.setText("Employe ID :");

        jLabel5.setText("Nom :");

        jLabel6.setText("Poste :");

        comboPosteEmp.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        comboPosteEmp.setEnabled(false);
        comboPosteEmp.setFocusable(false);

        javax.swing.GroupLayout panVendeurLayout = new javax.swing.GroupLayout(panVendeur);
        panVendeur.setLayout(panVendeurLayout);
        panVendeurLayout.setHorizontalGroup(
            panVendeurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panVendeurLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panVendeurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboIdEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panVendeurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panVendeurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(comboPosteEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panVendeurLayout.setVerticalGroup(
            panVendeurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panVendeurLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(panVendeurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panVendeurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboIdEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboPosteEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        categorieVehicule.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        categorieVehicule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorieVehiculeActionPerformed(evt);
            }
        });

        marqueVehicule.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        marqueVehicule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marqueVehiculeActionPerformed(evt);
            }
        });

        modeleVehicule.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        modeleVehicule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modeleVehiculeActionPerformed(evt);
            }
        });

        prixVehicule.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        stockVehicule.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("VEHICULE");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("ACHAT");

        qteAchat.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        qteAchat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                qteAchatFocusLost(evt);
            }
        });
        qteAchat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qteAchatKeyTyped(evt);
            }
        });

        jLabel9.setText("Quantités :");

        btnAjoutAchat.setBackground(new java.awt.Color(0, 180, 219));
        btnAjoutAchat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAjoutAchat.setForeground(new java.awt.Color(227, 227, 227));
        btnAjoutAchat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/shopping_cart_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnAjoutAchat.setText("Ajouter");
        btnAjoutAchat.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAjoutAchat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjoutAchatActionPerformed(evt);
            }
        });

        btnModAchat.setBackground(new java.awt.Color(0, 180, 219));
        btnModAchat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnModAchat.setForeground(new java.awt.Color(227, 227, 227));
        btnModAchat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/autorenew_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnModAchat.setText("Modifier");
        btnModAchat.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnModAchat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModAchatActionPerformed(evt);
            }
        });

        btnSupprimer.setBackground(new java.awt.Color(0, 180, 219));
        btnSupprimer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSupprimer.setForeground(new java.awt.Color(227, 227, 227));
        btnSupprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/remove_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnSupprimer.setText("Retirer");
        btnSupprimer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });

        idAchat.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(idVehicule, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(stockVehicule, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prixVehicule, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modeleVehicule, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(marqueVehicule, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(categorieVehicule, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))
                    .addComponent(qteAchat, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idAchat, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAjoutAchat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnModAchat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSupprimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idVehicule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categorieVehicule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(marqueVehicule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modeleVehicule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prixVehicule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stockVehicule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idAchat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qteAchat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAjoutAchat)
                    .addComponent(btnModAchat))
                .addGap(18, 18, 18)
                .addComponent(btnSupprimer)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAjoutAchat, btnModAchat, btnSupprimer});

        javax.swing.GroupLayout panVehiculeLayout = new javax.swing.GroupLayout(panVehicule);
        panVehicule.setLayout(panVehiculeLayout);
        panVehiculeLayout.setHorizontalGroup(
            panVehiculeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panVehiculeLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        panVehiculeLayout.setVerticalGroup(
            panVehiculeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panVehiculeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        maTableAchat.setModel(new javax.swing.table.DefaultTableModel(
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
        maTableAchat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maTableAchatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(maTableAchat);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        montantTotal.setEditable(false);
        montantTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        montantTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnValider.setBackground(new java.awt.Color(0, 180, 219));
        btnValider.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnValider.setForeground(new java.awt.Color(227, 227, 227));
        btnValider.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projet/form/icons/request_quote_26dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnValider.setText("Valider");
        btnValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValiderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(montantTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnValider, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(montantTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnValider, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panVehicule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator4)))
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addComponent(panVendeur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panVendeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE))
                    .addComponent(jSeparator3)
                    .addComponent(panVehicule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4))
        );
    }// </editor-fold>//GEN-END:initComponents

    private boolean verifierChamps() {
        // Vérifier que les éléments obligatoires sont remplis
        boolean clientSelectionne = comboIdClient.getSelectedItem() != null;
        boolean employeSelectionne = comboIdEmp.getSelectedItem() != null;
        boolean montantNonVide = !montantTotal.getText().trim().isEmpty();

        // ?Vérifier que montantTotal est supérieur à 0.0
        boolean montantValide = false;
        try {
            double montant = Double.parseDouble(montantTotal.getText().trim());
            montantValide = montant > 0.0; 
        } catch (NumberFormatException e) {
            montantValide = false; // Erreur si la saisie n'est pas un nombre
        }

        return clientSelectionne && employeSelectionne && montantNonVide && montantValide;
    }

    private void btnValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValiderActionPerformed
        try {
            // Validation des champs nécessaires
            if (!verifierChamps()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs nécessaires avant de valider.", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Récupération des données
            int idClient = Integer.parseInt(comboIdClient.getSelectedItem().toString());
            int idEmploye = Integer.parseInt(comboIdEmp.getSelectedItem().toString());
            BigDecimal montant = new BigDecimal(montantTotal.getText());
            LocalDate dateFacturation = LocalDate.now(); // Date actuelle
            String modePaiement = "ESPECES";

            // Création de la facture
            Facture facture = new Facture(idFactureActuel, idClient, idEmploye, dateFacturation, Facture.ModePaiement.valueOf(modePaiement.toUpperCase()), montant);
            gestionFacture.insert(facture); // Insérer la facture dans la base

            // Incrémenter l'ID pour la prochaine facture
            idFactureActuel += 1;
            
            // Charger la table avec les achats associés
            chargerTableAchats(idFactureActuel);

            // Recalculer le montant total
            updateMontantTotal();

            // Afficher un message de confirmation
            //JOptionPane.showMessageDialog(this, "Facture créée avec succès ! ID Facture : " + idFactureActuel, "Succès", JOptionPane.INFORMATION_MESSAGE);
            
            //vider les champ
            resetField();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Une erreur est survenue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnValiderActionPerformed

    private void btnAjoutAchatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjoutAchatActionPerformed
        try {
            // Vérifier si une facture active est disponible
            if (idFactureActuel <= 0) {
                JOptionPane.showMessageDialog(this, "Aucune facture active. Veuillez d'abord valider une facture.", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validation des champs nécessaires
            if (marqueVehicule.getSelectedItem() == null || modeleVehicule.getSelectedItem() == null || 
                idVehicule.getText().isEmpty() || qteAchat.getText().isEmpty() || prixVehicule.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs obligatoires.", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if(Integer.parseInt(qteAchat.getText()) == 0) {
                return;
            }

            // Récupérer les données
            String marque = marqueVehicule.getSelectedItem().toString();
            String modele = modeleVehicule.getSelectedItem().toString();
            int idVehiculeValue = Integer.parseInt(idVehicule.getText());
            int quantite = Integer.parseInt(qteAchat.getText());
            BigDecimal prixUnitaire = new BigDecimal(prixVehicule.getText());
            BigDecimal prixTotal = prixUnitaire.multiply(BigDecimal.valueOf(quantite));
            Date dateVente = new Date();

            // Vérification du stock disponible
            GestionAchat gestionAchat = new GestionAchat();
            int stockDisponible = gestionAchat.getStock(idVehiculeValue);
            if (quantite > stockDisponible) {
                JOptionPane.showMessageDialog(this, "Stock insuffisant pour cette commande. Stock disponible : " + stockDisponible, "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Création de l'achat
            Achat achat = new Achat(dateVente, Integer.parseInt(comboIdClient.getSelectedItem().toString()), 
                                    Integer.parseInt(comboIdEmp.getSelectedItem().toString()), idVehiculeValue, 
                                    idFactureActuel, quantite, prixTotal);

            // Insertion et mise à jour du stock
            gestionAchat.insert(achat);

            // Charger les achats liés
            chargerTableAchats(idFactureActuel);

            // Recalculer le montant total
            updateMontantTotal();

            // Afficher un message
            JOptionPane.showMessageDialog(this, "Achat ajouté à la facture ID : " + idFactureActuel + " avec succès !");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Une erreur est survenue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAjoutAchatActionPerformed

    private void categorieVehiculeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categorieVehiculeActionPerformed
        try {
            String selectedCategory = (String) categorieVehicule.getSelectedItem();
            if (selectedCategory != null) {
                GestionVehicule gestionVehicule = new GestionVehicule();

                // Charger les marques
                List<String> marques = gestionVehicule.getMarquesByCategorie(selectedCategory);
                marqueVehicule.removeAllItems();
                for (String marque : marques) {
                    marqueVehicule.addItem(marque);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement des marques : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Une erreur inattendue est survenue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_categorieVehiculeActionPerformed

    private void marqueVehiculeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marqueVehiculeActionPerformed
        try {
            String selectedCategory = (String) categorieVehicule.getSelectedItem();
            String selectedMarque = (String) marqueVehicule.getSelectedItem();
            if (selectedMarque != null) {
                GestionVehicule gestionVehicule = new GestionVehicule();
                
                // Charger les modèles
                List<String> modeles = gestionVehicule.getModelesByMarque(selectedCategory, selectedMarque);
                modeleVehicule.removeAllItems();
                for (String modele : modeles) {
                    modeleVehicule.addItem(modele);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement des modeles : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Une erreur inattendue est survenue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_marqueVehiculeActionPerformed

    private void modeleVehiculeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modeleVehiculeActionPerformed
        try {
            String selectedModel = (String) modeleVehicule.getSelectedItem();
            if (selectedModel != null) {
                GestionVehicule gestionVehicule = new GestionVehicule();
                Vehicule vehicule = gestionVehicule.getVehiculeByModele(selectedModel);

                if (vehicule != null) {
                    // Remplir les champs automatiquement avec les informations du véhicule
                    idVehicule.setText(String.valueOf(vehicule.getIdVehicule())); // ID du véhicule
                    stockVehicule.setText(String.valueOf(vehicule.getStock()));   // Stock disponible
                    prixVehicule.setText(String.valueOf(vehicule.getPrix()));     // Prix du véhicule
                } else {
                    JOptionPane.showMessageDialog(this, "Aucun véhicule trouvé pour ce modèle.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des informations du véhicule : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Une erreur inattendue est survenue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_modeleVehiculeActionPerformed

    private void comboIdClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIdClientActionPerformed
        try {
            // Vérifier si un élément est sélectionné
            Object selectedItem = comboIdClient.getSelectedItem();
            if (selectedItem == null) {
                clearClientFields(); // Vider les champs si aucun élément n'est sélectionné
                return;
            }

            // Récupérer l'ID sélectionné
            Integer selectedId = Integer.parseInt(selectedItem.toString());
            GestionClients gestionClient = new GestionClients();

            // Récupérer le client basé sur l'ID
            Clients client = gestionClient.getClientById(selectedId);

            if (client != null) {
                // Synchroniser les données
                comboCinClient.setSelectedItem(client.getCin()); // Synchroniser le CIN
                nomClient.setText(client.getNom()); // Remplir le champ nomClient avec le nom du client
            } else {
                JOptionPane.showMessageDialog(this, "Aucun client trouvé avec l'ID : " + selectedId, "Avertissement", JOptionPane.WARNING_MESSAGE);
                clearClientFields();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la synchronisation des clients : " + ex.getMessage(), "Erreur SQL", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Une erreur inattendue est survenue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_comboIdClientActionPerformed

    private void comboCinClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCinClientActionPerformed
        try {
            // Vérifier si un élément est sélectionné
            Object selectedItem = comboCinClient.getSelectedItem();
            if (selectedItem == null) {
                clearClientFields(); // Vider les champs si aucun élément n'est sélectionné
                return;
            }

            // Récupérer le CIN sélectionné
            String selectedCin = selectedItem.toString();
            GestionClients gestionClient = new GestionClients();

            // Récupérer le client basé sur le CIN
            Clients client = gestionClient.getClientByCin(selectedCin);

            if (client != null) {
                // Synchroniser les données
                comboIdClient.setSelectedItem(String.valueOf(client.getId())); // Synchroniser l'ID
                nomClient.setText(client.getNom()); // Remplir le champ nomClient avec le nom du client
            } else {
                JOptionPane.showMessageDialog(this, "Aucun client trouvé avec le CIN : " + selectedCin, "Avertissement", JOptionPane.WARNING_MESSAGE);
                clearClientFields();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la synchronisation des clients : " + ex.getMessage(), "Erreur SQL", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Une erreur inattendue est survenue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_comboCinClientActionPerformed

    private void clearClientFields() {
        comboIdClient.setSelectedItem(null);
        comboCinClient.setSelectedItem(null);
        nomClient.setText("");
    }

    private void comboIdEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIdEmpActionPerformed
        try {
            Object selectedItem = comboIdEmp.getSelectedItem();
            if (selectedItem == null) {
                clearFields();
                return;
            }

            Integer selectedId = Integer.parseInt(selectedItem.toString());
            GestionEmploye gestionEmploye = new GestionEmploye();

            Employe employe = gestionEmploye.getEmployeById(selectedId);

            if (employe != null) {
                comboPosteEmp.setText(employe.getPosteEmploye()); // Synchroniser le poste
                nomEmp.setText(employe.getNom()); // Synchroniser le nom
            } else {
                JOptionPane.showMessageDialog(this, "Aucun employé trouvé avec l'ID : " + selectedId, "Avertissement", JOptionPane.WARNING_MESSAGE);
                clearFields();
            }
        } catch (SQLException ex) {
            handleError("Erreur SQL lors de la synchronisation des employés", ex);
        } catch (Exception ex) {
            handleError("Erreur inattendue lors de la synchronisation des employés", ex);
        }
    }//GEN-LAST:event_comboIdEmpActionPerformed

    private void qteAchatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qteAchatKeyTyped
        ValidationNumerique validation = new ValidationNumerique(3);
        validation.keyTyped(evt);
        validerQte();
    }//GEN-LAST:event_qteAchatKeyTyped

    private void maTableAchatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maTableAchatMouseClicked
        int ligne = maTableAchat.getSelectedRow();
        if (ligne == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne valide.", "Erreur", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String id = String.valueOf(maTableAchat.getValueAt(ligne, 0)).trim();
            if (!id.matches("\\d+") || Integer.parseInt(id) <= 0) { 
                JOptionPane.showMessageDialog(this, "L'ID d'achat est invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                idAchat.setBackground(Color.PINK);
            } else {
                idAchat.setBackground(Color.WHITE);
            }
            idAchat.setText(id);

            String vehiculeId = String.valueOf(maTableAchat.getValueAt(ligne, 1)).trim();
            if (!vehiculeId.matches("\\d+") || Integer.parseInt(vehiculeId) <= 0) { 
                JOptionPane.showMessageDialog(this, "L'ID du véhicule est invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                idVehicule.setBackground(Color.PINK);
            } else {
                idVehicule.setBackground(Color.WHITE);
            }
            idVehicule.setText(vehiculeId);

            categorieVehicule.setSelectedItem(String.valueOf(maTableAchat.getValueAt(ligne, 2)).trim());
            marqueVehicule.setSelectedItem(String.valueOf(maTableAchat.getValueAt(ligne, 3)).trim());
            modeleVehicule.setSelectedItem(String.valueOf(maTableAchat.getValueAt(ligne, 4)).trim());

            String quantite = String.valueOf(maTableAchat.getValueAt(ligne, 5)).trim();
            if (!quantite.matches("\\d+") || Integer.parseInt(quantite) <= 0) { 
                JOptionPane.showMessageDialog(this, "La quantité achetée est invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                qteAchat.setBackground(Color.PINK);
            } else {
                qteAchat.setBackground(Color.WHITE);
            }
            qteAchat.setText(quantite);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur inattendue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_maTableAchatMouseClicked

    private void btnModAchatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModAchatActionPerformed
        try {
            if (idAchat.getText().isEmpty() || idVehicule.getText().isEmpty() || qteAchat.getText().isEmpty() || montantTotal.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs obligatoires.", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idAchatValue = Integer.parseInt(idAchat.getText().trim());
            int idVehiculeValue = Integer.parseInt(idVehicule.getText().trim());
            int nouvelleQuantite = Integer.parseInt(qteAchat.getText().trim());
            BigDecimal nouveauMontant = new BigDecimal(montantTotal.getText().trim());

            GestionAchat gestionAchat = null;
            try {
                gestionAchat = new GestionAchat();
            } catch (Exception ex) {
                Logger.getLogger(PanelAchat.class.getName()).log(Level.SEVERE, null, ex);
            }
            GestionVehicule gestionVehicule = null;
            try {
                gestionVehicule = new GestionVehicule();
            } catch (Exception ex) {
                Logger.getLogger(PanelAchat.class.getName()).log(Level.SEVERE, null, ex);
            }

            Achat achatActuel = gestionAchat.selectById(idAchatValue);
            if (achatActuel == null) {
                JOptionPane.showMessageDialog(this, "Achat introuvable !", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int ancienneQuantite = achatActuel.getQuantite();

            int differenceQuantite = nouvelleQuantite - ancienneQuantite;

            int stockActuel = gestionVehicule.getStock(idVehiculeValue);
            if (stockActuel - differenceQuantite < 0) {
                JOptionPane.showMessageDialog(this, "Stock insuffisant pour cette modification ! Stock actuel : " + stockActuel, "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Achat achatModifie = new Achat(
                idAchatValue,
                achatActuel.getDateVente(),
                achatActuel.getIdClient(),
                achatActuel.getIdEmploye(),
                idVehiculeValue,
                achatActuel.getIdFacture(),
                nouvelleQuantite,
                nouveauMontant
            );
            gestionAchat.updateAchat(achatModifie);

            gestionVehicule.updateStock(idVehiculeValue, -differenceQuantite);

            JOptionPane.showMessageDialog(this, "Achat modifié et stock mis à jour avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);

            chargerTableAchats(idFactureActuel);
            updateMontantTotal();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs valides (nombres uniquement).", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour de l'achat : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnModAchatActionPerformed

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        try {
            if (idAchat.getText().isEmpty() || idVehicule.getText().isEmpty() || qteAchat.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un achat à supprimer.", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idAchatValue = Integer.parseInt(idAchat.getText().trim());
            int idVehiculeValue = Integer.parseInt(idVehicule.getText().trim());
            int quantite = Integer.parseInt(qteAchat.getText().trim());

            int confirmation = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer cet achat ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation != JOptionPane.YES_OPTION) {
                return; 
            }

            GestionAchat gestionAchat = null;
                try {
                    gestionAchat = new GestionAchat();
                } catch (Exception ex) {
                    Logger.getLogger(PanelAchat.class.getName()).log(Level.SEVERE, null, ex);
                }
            gestionAchat.delete(idAchatValue, idVehiculeValue, quantite);

            JOptionPane.showMessageDialog(this, "Achat supprimé avec succès et stock mis à jour.", "Succès", JOptionPane.INFORMATION_MESSAGE);

            chargerTableAchats(idFactureActuel);
            updateMontantTotal();

            idAchat.setText("");
            idVehicule.setText("");
            qteAchat.setText("");
            montantTotal.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs valides (nombres uniquement).", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppression de l'achat : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSupprimerActionPerformed

    private void qteAchatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_qteAchatFocusLost
        validerQte();
    }//GEN-LAST:event_qteAchatFocusLost

    private void clearFields() {
        nomEmp.setText("");
        comboIdEmp.setSelectedItem(null);
        comboPosteEmp.setText("");
    }

    private void handleError(String message, Exception ex) {
        JOptionPane.showMessageDialog(this, message + " : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
    
    private void resetField() {
        // 🔹 Vider tous les champs texte
        idAchat.setText("");
        idVehicule.setText("");
        comboPosteEmp.setText("");
        montantTotal.setText("0");
        nomClient.setText("");
        nomEmp.setText("");
        prixVehicule.setText("");
        qteAchat.setText("");
        stockVehicule.setText("");

        // 🔹 Réinitialiser les ComboBox en sélectionnant le premier élément (index 0)
        if (categorieVehicule.getItemCount() > 0) categorieVehicule.setSelectedIndex(0);
        if (comboCinClient.getItemCount() > 0) comboCinClient.setSelectedIndex(0);
        if (comboIdClient.getItemCount() > 0) comboIdClient.setSelectedIndex(0);
        if (comboIdEmp.getItemCount() > 0) comboIdEmp.setSelectedIndex(0);
        if (marqueVehicule.getItemCount() > 0) marqueVehicule.setSelectedIndex(0);
        if (modeleVehicule.getItemCount() > 0) modeleVehicule.setSelectedIndex(0);

        // 🔹 Optionnel : Réinitialiser la sélection du tableau
        maTableAchat.clearSelection();
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjoutAchat;
    private javax.swing.JButton btnModAchat;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JButton btnValider;
    private javax.swing.JComboBox<String> categorieVehicule;
    private javax.swing.JComboBox<String> comboCinClient;
    private javax.swing.JComboBox<String> comboIdClient;
    private javax.swing.JComboBox<String> comboIdEmp;
    private javax.swing.JTextField comboPosteEmp;
    private javax.swing.JTextField idAchat;
    private javax.swing.JTextField idVehicule;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable maTableAchat;
    private javax.swing.JComboBox<String> marqueVehicule;
    private javax.swing.JComboBox<String> modeleVehicule;
    private javax.swing.JTextField montantTotal;
    private javax.swing.JTextField nomClient;
    private javax.swing.JTextField nomEmp;
    private javax.swing.JPanel panClient;
    private javax.swing.JPanel panVehicule;
    private javax.swing.JPanel panVendeur;
    private javax.swing.JTextField prixVehicule;
    private javax.swing.JTextField qteAchat;
    private javax.swing.JTextField stockVehicule;
    // End of variables declaration//GEN-END:variables
}
