/*
 * Console version of this translator.
 */
package newsupermdico;

import base.activerecord.Mot;
import java.util.ArrayList;
import java.util.Scanner;
import newsupermdico.donnees.Donnees;

/**
 *
 * @author Maxime BLAISE
 * @version 2.0
 */
public class Shell {
    
    private final int etat_selection_mot = 1;
    private final int etat_neutre = 0;
    private int etat = etat_neutre;
    
    private ArrayList<Mot> listeMots;
    private Mot mot;
    
    private final javax.swing.JTextPane jTextPane1;

    /**
     * Constructeur vide.
     *
     * @param t .
     */
    public Shell(javax.swing.JTextPane t) {
        this.jTextPane1 = t;
    }
    
    public void traiterLigne(String line) {
        // Récupération des valeurs saisies par l'utilisateur
        String[] lines = line.split(" ");

        // Traitement du cas neutre
        traitementCasNeutre(lines);
        
        switch (etat) {
            case etat_selection_mot:
                traitementCasSelectionMot(lines);
                break;
        }
    }
    
    private void traitementCasNeutre(String[] lines) {
        // Test sur le premier mot
        switch (lines[0]) {
            case "exit":
                System.exit(0);
                break;
            case "get":
                if (lines.length >= 2) {
                    getMotEntierAvecTraduction(lines[1]);
                }
                break;
            case "list":
                if (lines.length >= 2) {
                    rechercheMots(lines[1]);
                }
                break;
            case "select":
                if (lines.length >= 3) {
                    if (lines[1].equals("mot")) {
                        selectionMot(lines[2]);
                    }
                }
                break;
        }
    }

    /**
     * Méthode de recherche d'un mot.
     *
     * /@param line nom.
     */
    private void rechercheMots(String line) {
        this.listeMots = new ArrayList<>();
        Mot[] tmp = Mot.tousLesMotsBis();
        int iterateur = 1;
        String message = "Liste des mots " + line + "\n";
        for (Mot m : tmp) {
            if (m.getNom().equals(line)) {
                this.listeMots.add(m);
                message += iterateur + "/ " + m.toString() + " \n";
                iterateur++;
            }
        }
        
        incrementerResultat(message);
    }
    
    private void incrementerResultat(String message) {
        if (jTextPane1 == null) {
            System.out.print(message);
        } else {
            if (jTextPane1.getText().equals("")) {
                jTextPane1.setText(message);
            } else {
                jTextPane1.setText(jTextPane1.getText() + message);
            }
        }
        
    }

    /**
     * Sélection d'un mot selon l'indice dans la liste
     *
     * @param line
     */
    private void selectionMot(String line) {
        // Initialisation 
        String message = "";

        // Conversion en entier
        int indice = new Integer(line) - 1;

        // Recherche dans la liste
        if (indice < 0 || indice >= this.listeMots.size()) {
            message += "Erreur dans la sélection du mot.\n";
        } else {
            // Enregistrement du mot sélectionner
            this.mot = this.listeMots.get(indice);

            // Changement d'état
            etat = etat_selection_mot;
            
            message += "mot " + this.mot + " sélectionné.\n";
        }
        
        incrementerResultat(message);
    }
    
    private void traitementCasSelectionMot(String[] lines) {
        // Flag.
        boolean modification = false;
        String message = "";

        // Test sur le premier mot
        switch (lines[0]) {
            case "set":
                if (lines.length >= 3) {
                    switch (lines[1]) {
                        case "phonetique":
                            // Modification de la phonétique
                            this.mot.setPhonetique(lines[2]);
                            message += "Modification de la phonétique de " + this.mot.getNom() + "... ";
                            modification = true;
                            break;
                        case "genre":
                            // Modification du genre
                            this.mot.setGenre(lines[2]);
                            message += "Modification du genre de " + this.mot.getNom() + "... ";
                            modification = true;
                            break;
                    }
                }
                break;
        }

        // On doit mettre à jour la liste.
        if (modification) {
            Mot.modifierUnMot(this.mot);
            message += "OK\n";
            incrementerResultat(message);
        }
    }
    
    public static void main(String[] args) {
        Shell s = new Shell(null);

        // Initialisation
        Donnees.init();
        
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("YiruMaxDico:$ ");
            String line = sc.nextLine();
            s.traiterLigne(line);
        }
    }
    
    private void getMotEntierAvecTraduction(String line) {
        // Initialisation
        Mot m = new Mot(line);
        m.setType();
        m.setInformation();
        String message = m.toString() + "\n";
        
        switch (m.getType()) {
            case "fr":
                message += m.chercher("Chinois", "fr", "ch", null, null);
                message += m.chercher("Anglais", "fr", "en", null, null);
                message += m.chercher("Allemand", "fr", "de", null, null);
                break;
            case "en":
                message += m.chercher("Français", "en", "fr", null, null);
                
                break;
            case "de":
                message += m.chercher("Français", "de", "fr", null, null);
                break;
            case "ch":
                message += m.chercher("Français", "ch", "fr", null, null);
                break;
        }
        
        incrementerResultat(message);
    }
}
