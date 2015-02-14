/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique.liste;

import base.activerecord.Liaison;
import graphique.chercher.PanChercher;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxime
 */
public class PanPratiquer extends javax.swing.JPanel {

    /**
     * Type des liaisons
     */
    private final String type;

    /**
     * Réponses.
     */
    private HashMap<String, String> reponses;

    /**
     * PanListe.
     */
    PanListe p;

    /**
     * Creates new form PanPratiquer
     *
     * @param nomListe Le nom de la liste
     * @param liaisons la liste des liaisons
     * @param type .
     * @param p .
     */
    public PanPratiquer(String nomListe, ArrayList<Liaison> liaisons, String type, PanListe p) {
        initComponents();

        this.p = p;
        this.type = type;

        initialiserHashMap(liaisons);

        setTitre(nomListe);
        setLiaisons(liaisons);
        this.p = p;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        verifier = new javax.swing.JButton();
        back = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(600, 450));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(150, 0, 0));
        jLabel1.setText("Exercice sur la liste ");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mot donné", "Equivalent"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        verifier.setText("Vérifier");
        verifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifierActionPerformed(evt);
            }
        });

        back.setText("<<<");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154)
                        .addComponent(verifier, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(verifier)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(back)
                        .addGap(19, 19, 19))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void verifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifierActionPerformed
        // TODO add your handling code here:

        // Vérification
        actionVerifier();
    }//GEN-LAST:event_verifierActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:

        p.fen.setContentPane(p);
        p.fen.pack();
    }//GEN-LAST:event_backActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton verifier;
    // End of variables declaration//GEN-END:variables

    /**
     * Mise en place du titre.
     *
     * @param nomListe Nom de la liste
     */
    private void setTitre(String nomListe) {
        jLabel1.setText("Exercice sur la liste " + nomListe);
    }

    private void setLiaisons(ArrayList<Liaison> liaisons) {
        // Création de l'objet en fonction de la liste
        Object[][] o = new Object[liaisons.size()][2];
        for (int i = 0; i < liaisons.size(); i++) {
            // L'énoncé
            o[i][0] = liaisons.get(i).getMot1();

            // Vide pour laisser l'utilisateur répondre
            o[i][1] = "";
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                o,
                new String[]{
                    type.substring(0, 2), type.substring(2, 4)
                }
        ));
    }

    /**
     * Méthode qui vérifie les réponses de l'utilisateur
     */
    private void actionVerifier() {
        // Initialisation
        int nombreMauvaisesReponses = 0;
        String affichage = "";

        // Parcours de la table
        for (int i = 0; i < this.reponses.size(); i++) {
            // Récupération de la vrai réponse
            String reponse = this.reponses.get(jTable1.getValueAt(i, 0).toString());

            // Comparaison
            boolean correct = reponse.equals(jTable1.getValueAt(i, 1).toString());
            if (!correct) {
                // Incrémentation
                nombreMauvaisesReponses++;

                // Pour affichage
                affichage += jTable1.getValueAt(i, 0).toString() + " <=> " + reponse + "\n";
            }
        }

        // Option de l'affichage
        int option = (nombreMauvaisesReponses == 0) ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE;
        String messageTitre = (nombreMauvaisesReponses == 0) ? "Bravo, aucune mauvaise réponse !" : "Attention, tu as " + nombreMauvaisesReponses + " mauvaises réponses !";

        // Affichage
        JOptionPane.showMessageDialog(null, messageTitre + "\n\n" + affichage, "Résultats", option);
    }

    /**
     * Initialisation des réponses
     *
     * @param liaisons liste des liaisons
     */
    private void initialiserHashMap(ArrayList<Liaison> liaisons) {
        // Création
        this.reponses = new HashMap<>();

        // Parcours des liaisons
        liaisons.stream().forEach((l) -> {
            this.reponses.put(l.getMot1(), l.getMot2());
        });
    }

    /**
     * Activation du contraste
     */
    public final void activerContraste() {
        // Liste qui va servir pour tous les composants
        ArrayList<JComponent> l;

        // Pour la couleur de fond général
        l = new ArrayList<>();
        l.add(this);
        l.add(jTable1);
        PanChercher.applicationCouleur("fond", l, true);

        // Pour la couleur du premier label
        l = new ArrayList<>();
        l.add(jTable1);
        PanChercher.applicationCouleur("table", l, false);

        // Pour la couleur des boutons
        l = new ArrayList<>();
        l.add(back);
        l.add(verifier);
        PanChercher.applicationCouleur("bouton", l, false);

        // Pour la couleur du fond des boutons
        PanChercher.applicationCouleur("boutonFond", l, true);
    }

}
