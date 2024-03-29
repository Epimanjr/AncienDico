/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique.liste;

import base.activerecord.Link;
import graphique.Fenetre;
import graphique.chercher.PanChercher;
import static graphique.chercher.PanChercher.applicationCouleur;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author Maxime
 */
public class PanListe extends javax.swing.JPanel {

    /**
     * Fenêtre du projet.
     */
    public Fenetre fen;

    /**
     * Indice de la liste chargée.
     */
    public int indiceListe;

    /**
     * Premier type.
     */
    private String type1;

    /**
     * Deuxième type.
     */
    private String type2;

    /**
     * Liste associée.
     */
    ListeGenerale lg;

    /**
     * Création.
     *
     * @param type1 .
     * @param type2 .
     * @param fen la fenêtre
     */
    public PanListe(String type1, String type2, Fenetre fen) {
        this.type1 = type1;
        this.type2 = type2;
        this.fen = fen;
        lg = new ListeGenerale(type1, type2);

        initComponents();

        jList1.addListSelectionListener((ListSelectionEvent lse) -> {
            // System.out.println(e.getPath().getLastPathComponent());
            int indice = jList1.getSelectedIndex();

            //System.out.println("Affichage de " + nomListe);
            indiceListe = indice;
            afficherListe(indice);
        });

        this.chargerListeS();

        activerContraste();

    }

    /**
     * Chargement de la JList
     */
    public final void chargerListeS() {
        if (!lg.chargerTout()) {
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement.", "Information", JOptionPane.ERROR_MESSAGE);
        } else {

            // Modification de la liste
            jList1.setModel(new javax.swing.AbstractListModel() {
                String[] strings = lg.recupererNoms();

                @Override
                public int getSize() {
                    return strings.length;
                }

                @Override
                public Object getElementAt(int i) {
                    if (i >= getSize()) {
                        return null;
                    } else {
                        try {
                            Object o = strings[i];
                            return o;
                        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                            // e.printStackTrace();
                            return null;
                        }
                    }

                }

            });

        }
    }

    /**
     * Affiche une liste
     *
     * @param indice Indice sélectionné
     * @return liste bien affiché ou non
     */
    public final boolean afficherListe(int indice) {
        if (indice < 0 || indice >= lg.getLinks().size()) {
            return false;
        }

        for (int i = 0; i < 40; i++) {
            // Initialisation de toutes les colonnes
            jTable1.setValueAt("", i, 0);
            jTable1.setValueAt("", i, 1);
        }

        /* Récupération */
        ArrayList<Link> liste = lg.getListLinksWithId(indice);

        /* Remplissage */
        for (int i = 0; i < liste.size(); i++) {
            jTable1.setValueAt(liste.get(i).getMot1(), i, 0);
            jTable1.setValueAt(liste.get(i).getMot2(), i, 1);
        }
        return true;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    /**
     * Creates new form PanListe2
     */
    public PanListe() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        saisie = new javax.swing.JTextField();
        creerListe = new javax.swing.JButton();
        sauve = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        ajout = new javax.swing.JButton();
        apprendre = new javax.swing.JButton();
        pratiquer = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(600, 450));

        jScrollPane1.setViewportView(jList1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mot 1", "Mot 2"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        saisie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saisieMouseClicked(evt);
            }
        });
        saisie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saisieActionPerformed(evt);
            }
        });

        creerListe.setText("Créer");
        creerListe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creerListeActionPerformed(evt);
            }
        });

        sauve.setText("Sauvegarder");
        sauve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sauveActionPerformed(evt);
            }
        });

        jButton1.setText("Supprimer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ajout.setText("Ajouter");
        ajout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutActionPerformed(evt);
            }
        });

        apprendre.setText("Apprendre");
        apprendre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apprendreActionPerformed(evt);
            }
        });

        pratiquer.setText("Pratiquer");
        pratiquer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pratiquerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(saisie, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(creerListe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ajout)
                        .addGap(34, 34, 34)
                        .addComponent(sauve, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(apprendre, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(pratiquer, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sauve)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(creerListe)
                            .addComponent(saisie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(ajout))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apprendre)
                    .addComponent(pratiquer))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void creerListeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creerListeActionPerformed
        // TODO add your handling code here:

        lg.createLinks(saisie.getText());

        // Modification de la liste
        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = lg.recupererNoms();

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public Object getElementAt(int i) {
                if (i >= getSize()) {
                    return null;
                } else {
                    try {
                        Object o = strings[i];
                        return o;
                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                        return null;
                    }
                }

            }

        });
    }//GEN-LAST:event_creerListeActionPerformed

    private void sauveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sauveActionPerformed
        // TODO add your handling code here:

        if (indiceListe < 0) {
            JOptionPane.showMessageDialog(null, "Erreur sur liste.", "Information", JOptionPane.ERROR_MESSAGE);
        } else {
            /* On enlève tout de la liste */
            lg.enleverTout(indiceListe);

            for (int i = 0; i < 40; i++) {
                /* Lecture de toutes les colonnes */
                String smot1 = jTable1.getValueAt(i, 0).toString();
                String smot2 = jTable1.getValueAt(i, 1).toString();

                if (!smot1.equals("")) {
                    /* Ajout */
                    lg.ajouterValeur(indiceListe, smot1, smot2);
                }

            }
        }

        /* Actualisation de la liste */
        afficherListe(indiceListe);

        /* Sauvegarde */
        if (!lg.ecrireTout()) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la sauvegarde.", "Information", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Sauvegarde effectuée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

        }

    }//GEN-LAST:event_sauveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (indiceListe < 0) {
            JOptionPane.showMessageDialog(null, "Erreur sur liste.", "Information", JOptionPane.ERROR_MESSAGE);
        } else {
            /* Suppression de la liste */
            lg.removeLinksWithId(indiceListe);

            /* Réactualisation de la liste */
            // Modification de la liste
            jList1.setModel(new javax.swing.AbstractListModel() {
                String[] strings = lg.recupererNoms();

                @Override
                public int getSize() {
                    return strings.length;
                }

                @Override
                public Object getElementAt(int i) {
                    if (i >= getSize()) {
                        return null;
                    } else {
                        try {
                            Object o = strings[i];
                            return o;
                        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                            // e.printStackTrace();
                            return null;
                        }
                    }

                }

            });

            /* Affichage de la première liste */
            jList1.setSelectedIndex(0);
            afficherListe(0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ajoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutActionPerformed
        // TODO add your handling code here:

        /* Chercher */
        actionChercher();
    }//GEN-LAST:event_ajoutActionPerformed

    private void saisieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saisieActionPerformed
        // TODO add your handling code here:

        /* Chercher */
        actionChercher();
    }//GEN-LAST:event_saisieActionPerformed

    private void apprendreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apprendreActionPerformed
        // TODO add your handling code here:

        // Apprendre
        actionApprendre();
    }//GEN-LAST:event_apprendreActionPerformed

    private void pratiquerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pratiquerActionPerformed
        // TODO add your handling code here:
        
        // Pratiquer
        actionPratiquer();
    }//GEN-LAST:event_pratiquerActionPerformed

    private void saisieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saisieMouseClicked
        // TODO add your handling code here:
        
        // Clear out this TextField.
        saisie.setText("");
    }//GEN-LAST:event_saisieMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajout;
    private javax.swing.JButton apprendre;
    private javax.swing.JButton creerListe;
    private javax.swing.JButton jButton1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton pratiquer;
    private javax.swing.JTextField saisie;
    private javax.swing.JButton sauve;
    // End of variables declaration//GEN-END:variables

    public void actionChercher() {
        if (indiceListe < 0) {
            JOptionPane.showMessageDialog(null, "Erreur sur liste.", "Information", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String> mots = Link.chercherMots(type1 + type2, saisie.getText());
            /* Ajout de la valeur */
            lg.ajouterValeur(indiceListe, saisie.getText(), mots.get(0));

            /* Actualisation de la liste */
            afficherListe(indiceListe);
        }
    }

    public final void activerContraste() {

        // Liste qui va servir pour tous les composants
        ArrayList<JComponent> liste;

        // Pour la couleur de fond général
        liste = new ArrayList<>();
        liste.add(this);
        liste.add(jList1);
        liste.add(jTable1);
        PanChercher.applicationCouleur("fond", liste, true);

        // Pour la couleur des listes
        liste = new ArrayList<>();
        liste.add(jList1);
        PanChercher.applicationCouleur("list", liste, false);

        // Pour la couleur des tables
        liste = new ArrayList<>();
        liste.add(jTable1);
        PanChercher.applicationCouleur("table", liste, false);

        // Pour la couleur des boutons
        liste = new ArrayList<>();
        liste.add(jButton1);
        liste.add(apprendre);
        liste.add(pratiquer);
        liste.add(sauve);
        liste.add(ajout);
        liste.add(creerListe);
        applicationCouleur("bouton", liste, false);

        // Pour la couleur du fond des boutons
        applicationCouleur("boutonFond", liste, true);

        // Pour la couleur des TextFields
        liste = new ArrayList<>();
        liste.add(saisie);
        applicationCouleur("textField", liste, false);

        // Pour la couleur du fond des TextFields
        applicationCouleur("textFieldFond", liste, true);

    }

    /**
     * Méthode appelée quand on appuie sur le bouton apprendre
     */
    public void actionApprendre() {
        PanApprendre pp = new PanApprendre(recupererLiaisonsEntieres(), this);
        Fenetre.panApprendre = pp;
        fen.setContentPane(pp);
        fen.pack();
    }
    
    /**
     * Méthode appelée quand on appuie sur le bouton pratiquer
     */
    public void actionPratiquer() {
        String nomListe = lg.recupererNom(indiceListe);
        PanPratiquer pp = new PanPratiquer(nomListe, recupererLiaisonsEntieres(), type1+type2, this);
        fen.setContentPane(pp);
        fen.pack();
    }

    /**
     * Récupère toutes les liaisons avec toutes les informations
     *
     * @return liste
     */
    public ArrayList<Link> recupererLiaisonsEntieres() {
        // Toutes les liaisons
        ArrayList<Link> liaisons = lg.getListLinksWithId(indiceListe);
        liaisons.stream().forEach((l) -> {
            l.setInformations();
        });

        return liaisons;
    }

}
