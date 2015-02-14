/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.donnees;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxime
 */
public class Contraste {

    // Hashmap qui rassemble toutes les couleurs
    public HashMap<String, Color> couleurs;

    /**
     * Contraste élevé ou non
     *
     * @param eleve .
     */
    public Contraste(boolean eleve) {
        // Nom du fichier
        couleurs = new HashMap<>();
        String nomFichier;

        if (eleve) {
            // Mode contraste élevé
            nomFichier = "eleve.txt";

        } else {

            // Mode contraste normal
            nomFichier = "default.txt";
        }

        // Lecture des informations
        lireInformations(nomFichier);

        if (!verifier()) {
            JOptionPane.showMessageDialog(null, "Erreur, contraste incorrect.\nValeurs par défaut activées.", "Erreur contraste", JOptionPane.ERROR_MESSAGE);

            lireInformations("eleve.txt");
            if (!verifier()) {
                // Valeurs par défauts
                couleurs.put("fond", new Color(0, 0, 0));
                couleurs.put("label", new Color(255, 255, 255));
                couleurs.put("labelTitre", new Color(255, 255, 255));
                couleurs.put("labelPratiquer1", new Color(255, 255, 255));
                couleurs.put("labelPratiquer2", new Color(255, 255, 255));
                couleurs.put("bouton", new Color(254, 219, 9));
                couleurs.put("boutonFond", new Color(55, 55, 51));
                couleurs.put("textField", new Color(255, 255, 255));
                couleurs.put("textFieldFond", new Color(0, 0, 0));
                couleurs.put("labelLangue", new Color(255, 255, 255));
                couleurs.put("labelResultats", new Color(255, 255, 255));
                couleurs.put("list", new Color(255, 255, 255));
                couleurs.put("table", new Color(255, 255, 255));
            }

        }
    }

    /**
     * Lit les informations à partir d'un fichier.
     *
     * @param nomFichier nom du fichier
     */
    public final void lireInformations(String nomFichier) {
        try {
            // Nom total du fichier
            String nomTotalFichier = "donnees/contraste/" + nomFichier;

            if (Donnees.isModeVerbeux()) {
                System.out.println("Lecture des informations à partir de " + nomTotalFichier + " ... ");
            }

            // Buffer de lecture
            BufferedReader br = new BufferedReader(new FileReader(nomTotalFichier));

            // Boucle de lecture
            while (br.ready()) {
                // Lecture de la ligne
                String line = br.readLine();
                String[] lines = line.split("=");

                if (lines.length == 2) {
                    // Nom = partie gauche de la ligne
                    String nom = lines[0];

                    // Partie droite = couleur
                    String[] lines2 = lines[1].split(";");
                    // Création de la couleur
                    if (lines2.length == 3) {
                        try {
                            Color c = new Color(new Integer(lines2[0]), new Integer(lines2[1]), new Integer(lines2[2]));

                            // Ajout à la liste
                            couleurs.put(nom, c);

                            if (Donnees.isModeVerbeux()) {
                                System.out.println(nom + " => {" + c.getRed() + ";" + c.getGreen() + ";" + c.getBlue() + "}");
                            }
                        } catch (Exception e) {

                        }

                    }

                }
            }
        } catch (IOException ex) {
            // Catch

            System.out.println("Erreur de lecture!");
        }
    }

    /**
     * Méthode qui vérifie si le contraste est OK.
     *
     * @return booléen
     */
    public final boolean verifier() {
        if (!couleurs.containsKey("fond")) {
            return false;
        }
        if (!couleurs.containsKey("label")) {
            return false;
        }
        if (!couleurs.containsKey("labelTitre")) {
            return false;
        }
        if (!couleurs.containsKey("labelPratiquer1")) {
            return false;
        }
        if (!couleurs.containsKey("labelPratiquer2")) {
            return false;
        }
        if (!couleurs.containsKey("bouton")) {
            return false;
        }
        if (!couleurs.containsKey("boutonFond")) {
            return false;
        }
        if (!couleurs.containsKey("textField")) {
            return false;
        }
        if (!couleurs.containsKey("textFieldFond")) {
            return false;
        }
        if (!couleurs.containsKey("labelLangue")) {
            return false;
        }
        if (!couleurs.containsKey("labelResultats")) {
            return false;
        }

        if (!couleurs.containsKey("list")) {
            return false;
        }

        return couleurs.containsKey("table");
    }
}
