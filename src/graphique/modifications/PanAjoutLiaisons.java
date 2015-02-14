/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique.modifications;

import base.ModifPressPap;
import base.activerecord.Liaison;
import javax.swing.JOptionPane;
import newsupermdico.donnees.Donnees;

/**
 *
 * @author Maxime
 */
public class PanAjoutLiaisons extends javax.swing.JPanel {

    /**
     * Creates new form PanAjout
     */
    public PanAjoutLiaisons() {
        initComponents();
        
        /* Activer le contraste */
        activerContraste();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        saisieEn = new javax.swing.JTextField();
        saisieFr = new javax.swing.JTextField();
        saisieDe = new javax.swing.JTextField();
        saisieCh = new javax.swing.JTextField();
        jButtonAddFrEn = new javax.swing.JButton();
        jButtonAddFrDe = new javax.swing.JButton();
        jButtonAddFrCh = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        vider = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(600, 450));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jLabel1.setText("Anglais");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jLabel2.setText("Français");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jLabel3.setText("Allemand");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jLabel4.setText("Chinois");

        saisieEn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saisieEnActionPerformed(evt);
            }
        });

        jButtonAddFrEn.setText("Ajouter liaisons Français-Anglais");
        jButtonAddFrEn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddFrEnActionPerformed(evt);
            }
        });

        jButtonAddFrDe.setText("Ajouter liaisons Français-Allemand");
        jButtonAddFrDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddFrDeActionPerformed(evt);
            }
        });

        jButtonAddFrCh.setText("Ajouter liaisons Français-Chinois");
        jButtonAddFrCh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddFrChActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 0));
        jLabel5.setText("Ajout de liaisons : ");

        vider.setText("Vider");
        vider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viderActionPerformed(evt);
            }
        });

        jButton1.setText("Coller");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Coller");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Coller");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Coller");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(saisieDe)
                            .addComponent(saisieCh)
                            .addComponent(saisieEn)
                            .addComponent(saisieFr, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButtonAddFrEn, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(37, 37, 37)
                                    .addComponent(jButtonAddFrDe, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(144, 144, 144)
                                    .addComponent(jButtonAddFrCh, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(126, 126, 126)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(108, 108, 108)
                                .addComponent(vider)))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vider, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saisieFr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(saisieEn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saisieDe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(saisieCh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAddFrEn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAddFrDe, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonAddFrCh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddFrEnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddFrEnActionPerformed
        // TODO add your handling code here:

        // Récupération
        String smot1 = saisieFr.getText();
        String smot2 = saisieEn.getText();

        // Ajout de la liaison
        actionAjouter("fren", smot1, smot2);
    }//GEN-LAST:event_jButtonAddFrEnActionPerformed

    /**
     * Ajout d'une nouvelle liaison
     * 
     * @param type type
     * @param smot1 mot1
     * @param smot2 mot2
     */
    public void actionAjouter(String type, String smot1, String smot2) {
        // Création de la liaison
        if (!smot1.equals("") && !smot2.equals("")) {
            Liaison l = new Liaison(type, smot1, smot2);

            // Insertion de la liaison
            if (l.insert(false)) {
                JOptionPane.showMessageDialog(null, "Liaison ajoutée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "La liaison existe déjà ou erreur.", "Information", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Champs vides.", "Information", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void jButtonAddFrDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddFrDeActionPerformed
        // TODO add your handling code here:

        // Récupération
        String smot1 = saisieFr.getText();
        String smot2 = saisieDe.getText();

        // Ajout de la liaison
        actionAjouter("frde", smot1, smot2);

    }//GEN-LAST:event_jButtonAddFrDeActionPerformed

    private void jButtonAddFrChActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddFrChActionPerformed
        // TODO add your handling code here:

        // Récupération
        String smot1 = saisieFr.getText();
        String smot2 = saisieCh.getText();

        // Ajout de la liaison
        actionAjouter("frch", smot1, smot2);
    }//GEN-LAST:event_jButtonAddFrChActionPerformed

    private void viderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viderActionPerformed
        // TODO add your handling code here:

        // On remet tout à 0
        saisieCh.setText("");
        saisieFr.setText("");
        saisieDe.setText("");
        saisieEn.setText("");


    }//GEN-LAST:event_viderActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        // Contenu du press papier
        ModifPressPap mpp = new ModifPressPap();
        saisieFr.setText(mpp.getClipboardContents());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        /* Contenu du press papier */
        ModifPressPap mpp = new ModifPressPap();
        saisieEn.setText(mpp.getClipboardContents());


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        /* Contenu du press papier */
        ModifPressPap mpp = new ModifPressPap();
        saisieDe.setText(mpp.getClipboardContents());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        ModifPressPap mpp = new ModifPressPap();
        saisieCh.setText(mpp.getClipboardContents());

    }//GEN-LAST:event_jButton5ActionPerformed

    private void saisieEnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saisieEnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saisieEnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonAddFrCh;
    private javax.swing.JButton jButtonAddFrDe;
    private javax.swing.JButton jButtonAddFrEn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField saisieCh;
    private javax.swing.JTextField saisieDe;
    private javax.swing.JTextField saisieEn;
    private javax.swing.JTextField saisieFr;
    private javax.swing.JButton vider;
    // End of variables declaration//GEN-END:variables

    /*
     * Activation du bon contraste
     */
    public final void activerContraste() {
        /* Modification PANEL */
        this.setBackground(Donnees.contraste.couleurs.get("fond"));

        /* LABEL TITRE */
        jLabel5.setForeground(Donnees.contraste.couleurs.get("labelTitre"));
        jLabel5.setBackground(Donnees.contraste.couleurs.get("fond"));

        /* LABEL */
        jLabel1.setBackground(Donnees.contraste.couleurs.get("fond"));
        jLabel1.setForeground(Donnees.contraste.couleurs.get("label"));
        jLabel2.setBackground(Donnees.contraste.couleurs.get("fond"));
        jLabel2.setForeground(Donnees.contraste.couleurs.get("label"));
        jLabel3.setBackground(Donnees.contraste.couleurs.get("fond"));
        jLabel3.setForeground(Donnees.contraste.couleurs.get("label"));
        jLabel4.setBackground(Donnees.contraste.couleurs.get("fond"));
        jLabel4.setForeground(Donnees.contraste.couleurs.get("label"));

        /* TEXT FIELD */
        saisieFr.setForeground(Donnees.contraste.couleurs.get("textField"));
        saisieFr.setBackground(Donnees.contraste.couleurs.get("textFieldFond"));
        saisieDe.setForeground(Donnees.contraste.couleurs.get("textField"));
        saisieDe.setBackground(Donnees.contraste.couleurs.get("textFieldFond"));
        saisieEn.setForeground(Donnees.contraste.couleurs.get("textField"));
        saisieEn.setBackground(Donnees.contraste.couleurs.get("textFieldFond"));
        saisieCh.setForeground(Donnees.contraste.couleurs.get("textField"));
        saisieCh.setBackground(Donnees.contraste.couleurs.get("textFieldFond"));

        /* BOUTONS */
        jButtonAddFrCh.setForeground(Donnees.contraste.couleurs.get("bouton"));
        jButtonAddFrCh.setBackground(Donnees.contraste.couleurs.get("boutonFond"));
        jButtonAddFrDe.setForeground(Donnees.contraste.couleurs.get("bouton"));
        jButtonAddFrDe.setBackground(Donnees.contraste.couleurs.get("boutonFond"));
        jButtonAddFrEn.setForeground(Donnees.contraste.couleurs.get("bouton"));
        jButtonAddFrEn.setBackground(Donnees.contraste.couleurs.get("boutonFond"));
        
        jButton1.setForeground(Donnees.contraste.couleurs.get("bouton"));
        jButton1.setBackground(Donnees.contraste.couleurs.get("boutonFond"));
        jButton2.setForeground(Donnees.contraste.couleurs.get("bouton"));
        jButton2.setBackground(Donnees.contraste.couleurs.get("boutonFond"));
        jButton4.setForeground(Donnees.contraste.couleurs.get("bouton"));
        jButton4.setBackground(Donnees.contraste.couleurs.get("boutonFond"));
        jButton5.setForeground(Donnees.contraste.couleurs.get("bouton"));
        jButton5.setBackground(Donnees.contraste.couleurs.get("boutonFond"));
        vider.setForeground(Donnees.contraste.couleurs.get("bouton"));
        vider.setBackground(Donnees.contraste.couleurs.get("boutonFond"));
    }
}
