/*
 * Console version of this translator.
 */
package main;

import base.activerecord.Word;
import base.activerecord.Link;
import base.activerecord.Links;
import java.util.ArrayList;
import java.util.Scanner;
import main.donnees.Donnees;
import graphique.liste.ListeGenerale;

/**
 *
 * @author Maxime BLAISE
 * @version 2.0
 */
public class Shell {

    private final int etat_selection_mot = 1;
    private final int etat_neutre = 0;
    private final int etat_selection_list = 2;
    private int etat = etat_neutre;

    private ArrayList<Word> listeMots;
    private Links listLinks;
    private ListeGenerale lg;
    private Word mot;

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
            case etat_selection_list:
                traitementCasSelectionList(lines);
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
                    if (lines.length == 3) {
                        rechercheListes(lines[1], lines[2]);
                    } else {
                        rechercheMots(lines[1]);
                    }
                }
                break;
            case "select":
                if (lines.length >= 3) {
                    if (lines[1].equals("mot")) {
                        selectionMot(lines[2]);
                    }
                    if (lines[1].equals("list")) {
                        selectionList(lines[2]);
                    }
                }
                break;
        }
    }

    /**
     * Reserch all lists with the languages in parameters.
     *
     * @param type1 language 1
     * @param type2 language 2
     */
    private void rechercheListes(String type1, String type2) {
        // Init list
        lg = new ListeGenerale(type1, type2);
        lg.chargerTout();
        // Print lists available
        String[] names = lg.recupererNoms();
        for (int i = 0; i < names.length; i++) {
            System.out.println((i + 1) + " " + names[i]);
        }
    }

    /**
     * Méthode de recherche d'un mot.
     *
     * @param line nom.
     */
    private void rechercheMots(String line) {
        this.listeMots = new ArrayList<>();
        Word[] tmp = Word.tousLesMotsBis();
        int iterateur = 1;
        String message = "Liste des mots " + line + "\n";
        for (Word m : tmp) {
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

    private void selectionList(String line) {
        // Init message
        String message = "";
        // Convert line to Integer
        int indice = new Integer(line) - 1;
        // Research
        if (this.lg != null) {
            if (indice < 0 || indice >= this.lg.getLinks().size()) {
                message += "Error: wrong indice.";
            } else {
                // Correct here
                this.listLinks = lg.getLinks().get(indice);
                this.listLinks.setInformation();
                // Change state
                etat = etat_selection_list;
                message += "OK";
            }
        } else {
            message += "Error: List non initialized.";
        }

        incrementerResultat(message + "\n");
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

    private void traitementCasSelectionList(String[] lines) {
        switch (lines[0]) {
            case "ls":
                System.out.println(listLinks);
                break;
        }
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
            Word.modifierUnMot(this.mot);
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
        Word m = new Word(line);
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
