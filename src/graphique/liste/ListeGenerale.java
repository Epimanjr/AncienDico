/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique.liste;

import base.activerecord.Liaison;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime
 */
public class ListeGenerale {

    /**
     * Liste
     */
    private ArrayList<Liste> liste;

    /**
     * Type 1.
     */
    private final String type1;

    /**
     * Type 2.
     */
    private final String type2;

    /**
     * Constructeur.
     *
     * @param type1 type1
     * @param type2 type2
     */
    public ListeGenerale(String type1, String type2) {
        // Initialisation de la liste
        liste = new ArrayList<>();
        // Init types of link.
        this.type1 = type1;
        this.type2 = type2;
    }

    /**
     * Constructor.
     *
     * @param type type
     */
    public ListeGenerale(String type) {
      // Init list
      liste = new ArrayList<>();
      // Split type
      if(type.length() < 4) {
        this.type1 = type.substring(0,2);
        this.type2 = type.substring(2,4);
      } else {
        this.type1 = null;
        this.type2 = null;
      }
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    /**
     * Méthode qui lit le fichier pour charger.
     *
     * @return si bien chargé ou non
     */
    public boolean chargerTout() {
        liste = new ArrayList<>();
        BufferedReader br;
        try {
            // Lecture du fichier
            br = new BufferedReader(new InputStreamReader(new FileInputStream("donnees/liste" + type1 + type2 + ".txt"), "utf8"));

            // Boucle de lecture
            Liste derniereListe = null;
            while (br.ready()) {
                // Lecture
                String line = br.readLine();
                String[] lines = line.split(";");

                if (lines.length == 1) {
                    // Création d'une nouvelle liste
                    derniereListe = new Liste(line);
                    liste.add(derniereListe);
                } else {
                    if (derniereListe == null) {
                        return false;
                    } else {
                        // Ajout de la liaison à la dernière liste
                        if (lines.length != 2) {
                            return false;
                        } else {
                            derniereListe.liaisons.add(new Liaison(type1 + type2, lines[0], lines[1]));
                        }
                    }
                }
            }

            return true;
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            // Pas besoin de traiter

        } catch (IOException ex) {
            // Pas besoin de traiter

        }

        return false;
    }

    /**
     * Méthode de sauvegarde
     *
     * @return drapeau
     */
    public boolean ecrireTout() {
        PrintWriter pw;
        try {
            // Pour écriture
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("donnees/liste" + type1 + type2 + ".txt"), "utf8"));

            // Parcours des listes
            for (Liste l : this.liste) {
                // Ecriture du nom
                pw.println(l.getNom());

                // Ecriture des liaisons
                for (Liaison liaison : l.getLiaisons()) {
                    pw.println(liaison.getMot1() + ";" + liaison.getMot2());
                }
            }

            // Fermeture
            pw.close();

            return true;
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(ListeGenerale.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public ArrayList<Liste> getListe() {
        return liste;
    }

    /**
     * Permet de récupérer les noms des listes.
     *
     * @return tableau
     */
    public String[] recupererNoms() {
        String[] res = new String[liste.size()];
        for (int i = 0; i < liste.size(); i++) {
            res[i] = liste.get(i).getNom();
        }
        return res;
    }

    /**
     * Méthode qui permet de retourner le nom d'une liste.
     *
     * @param indice Indice de la liste
     * @return le nom de la liste
     */
    public String recupererNom(int indice) {
        return recupererNoms()[indice];
    }

    /**
     * Récupère la liste des liaisons
     *
     * @param indice indice de la liste
     * @return toutes les liaisons
     */
    public ArrayList<Liaison> recupererListeLiaisons(int indice) {
        return this.liste.get(indice).liaisons;
    }

    /**
     * Création d'une nouvelle liste
     *
     * @param text ..
     */
    public void creerListe(String text) {
        this.liste.add(new Liste(text));
    }

    /**
     * Ajoute une valeur dans la bonne liste
     *
     * @param indice indice de la liste
     * @param mot1 .
     * @param mot2 .
     */
    public void ajouterValeur(int indice, String mot1, String mot2) {
        /* Création de la liaison */
        Liaison lfrch = new Liaison(type1 + type2, mot1, mot2);

        /* Ajout à la bonne liste */
        this.liste.get(indice).liaisons.add(lfrch);
    }

    /**
     * Enlève tout de la liste.
     *
     * @param indiceListe indice
     */
    public void enleverTout(int indiceListe) {
        /* Création d'une nouvelle liste */
        this.liste.get(indiceListe).liaisons = new ArrayList<>();
    }

    /**
     * Suppression d'une liste.
     *
     * @param indiceListe indice.
     */
    public void supprimerListe(int indiceListe) {
        this.liste.remove(indiceListe);
    }

    class Liste {

        private final String nom;
        public ArrayList<Liaison> liaisons;

        public Liste(String nom) {
            this.nom = nom;
            this.liaisons = new ArrayList<>();
        }

        public String getNom() {
            return nom;
        }

        public ArrayList<Liaison> getLiaisons() {
            return liaisons;
        }

    }
}
