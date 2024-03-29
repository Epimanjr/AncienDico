/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique.chercher;

import base.activerecord.Word;
import graphique.Panel;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;
import main.donnees.Donnees;
import main.donnees.ListLanguages;

/**
 *
 * @author blaise
 */
public class PanChercher extends Panel {

    /**
     * Creates new form PanChercher
     */
    public PanChercher() {
        super();

        initComponents();
        initLanguages();
        initListe();

        stat.setHorizontalAlignment(JLabel.RIGHT);

        // Activation du contraste
        //activerContraste();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        labLangue1bis = new javax.swing.JLabel();
        labLangue1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        afficheMotCherche = new javax.swing.JLabel();
        lab0 = new javax.swing.JLabel();
        labTime = new javax.swing.JLabel();
        stat = new javax.swing.JLabel();
        choixLangue1 = new javax.swing.JComboBox();

        setPreferredSize(new java.awt.Dimension(600, 450));

        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        labLangue1bis.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labLangue1bis.setForeground(new java.awt.Color(0, 102, 0));
        labLangue1bis.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labLangue1bis.setOpaque(true);

        labLangue1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labLangue1.setForeground(new java.awt.Color(204, 0, 0));
        labLangue1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labLangue1.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(104, 0, 0));
        jLabel1.setText("Recherche d'un mot");

        stat.setForeground(new java.awt.Color(0, 0, 150));

        choixLangue1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choixLangue1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(afficheMotCherche, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(choixLangue1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labTime, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lab0, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labLangue1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labLangue1bis, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140)
                        .addComponent(stat, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stat, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(afficheMotCherche, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(choixLangue1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab0, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labLangue1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labLangue1bis, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(141, 141, 141))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:


    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:

        //Completion.getInstance().relacherTouche(evt, jTextField1.getText());
        if (Donnees.isModeBdd()) {
            String res = Completion.getInstance().appuyerTouche(evt);
            afficheMotCherche.setText(Completion.getInstance().getSmotcherche());

            if (!res.equals("")) {
                jTextField1.setText(res);
            }
        }

    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        // TODO add your handling code here:

        // Enlever
        enleverTexte();
    }//GEN-LAST:event_jTextField1MouseClicked

    private void choixLangue1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choixLangue1ActionPerformed
        // TODO add your handling code here:

        if (!jTextField1.getText().equals("")) {
            actionChercher();
        }

    }//GEN-LAST:event_choixLangue1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        // Action de chercher
        actionChercher();

        this.setFocusable(true);
        this.requestFocus();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel afficheMotCherche;
    private javax.swing.JComboBox choixLangue1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lab0;
    private javax.swing.JLabel labLangue1;
    private javax.swing.JLabel labLangue1bis;
    private javax.swing.JLabel labTime;
    private javax.swing.JLabel stat;
    // End of variables declaration//GEN-END:variables

    /**
     * Chercher un mot.
     */
    public void actionChercher() {
        // Initialisation temps
        long tempsDepart = System.currentTimeMillis();

        /* Création d'un nouveau mot */
        Word mot = new Word(jTextField1.getText());
        /* On cherche avec les base de données */
        mot.chercherMaxime(lab0, labLangue1, labLangue1bis, choixLangue1.getSelectedItem().toString());

        // Temps d'arrivée
        long diff = System.currentTimeMillis() - tempsDepart;
        long sec = diff / 1000;
        diff %= 1000;
        // Affichage
        if (!lab0.getText().equals("")) {
            labTime.setText(sec + "s" + diff);
            // Mode verbeux
            if (Donnees.isModeVerbeux()) {
                System.out.println("Temps de recherche : " + sec + "s." + diff);
            }
        } else {
            labTime.setText("");
        }

    }

    /**
     * Activation du contraste
     */
    @Override
    public final void activerContraste() {
        // Activation générale
        this.activerContrasteGeneral();

        // Gestion des exceptions
        ArrayList<JComponent> liste;

        // Pour la couleur des labels titres
        liste = new ArrayList<>();
        liste.add(jLabel1);
        liste.add(afficheMotCherche);
        liste.add(labTime);
        applicationCouleur("labelTitre", liste, false);

        // Pour la couleur des labels langues
        liste = new ArrayList<>();
        liste.add(labLangue1);
        liste.add(lab0);
        applicationCouleur("labelLangue", liste, false);

        // Pour la couleur des labels résultats
        liste = new ArrayList<>();
        liste.add(labLangue1bis);
        applicationCouleur("labelResultats", liste, false);

    }

    public void setValue(String txt) {
        jTextField1.setText(txt);
        actionChercher();
    }

    public String getValue() {
        return jTextField1.getText();
    }

    /**
     * Enlève le texte
     */
    private void enleverTexte() {
        /* On enlève le texte */
        jTextField1.setText("");

        Completion.getInstance().setSmotcherche("");
        afficheMotCherche.setText("");
    }

    @Override
    public final void initListe() {
        // Initialisation de la liste des panels
        super.getListePanels().add(this);

        // Initialisation de la liste des labels
        super.getListeLabels().add(labLangue1);
        super.getListeLabels().add(labLangue1bis);
        super.getListeLabels().add(jLabel1);
        super.getListeLabels().add(afficheMotCherche);
        super.getListeLabels().add(labTime);
        super.getListeLabels().add(stat);
        super.getListeLabels().add(lab0);

        // Initialisation des textfields
        super.getListeTextFields().add(jTextField1);
    }

    private void initLanguages() {
        // Create Array With Languages
        String[] array = new String[ListLanguages.listLanguages.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = ListLanguages.listLanguages.get(i).getName();
        }
        // Set Models
        choixLangue1.setModel(new javax.swing.DefaultComboBoxModel(array));
    }
}
