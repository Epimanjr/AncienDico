/*
 * Cette classe lit les informations à partir des fichiers,
 * et les injecte dans la base de données.
 */
package base.io;

import base.activerecord.Link;
import base.activerecord.Word;
import graphique.Fenetre;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import main.Affichage;
import main.donnees.Donnees;

/**
 *
 * @author Maxime BLAISE
 */
public class Importer {

    /**
     * Compte le nombre de requêtes.
     */
    public static int compteur;

    /**
     * Début de l'importation.
     */
    public static void importer() {
        try {
            // Importations des liaisons
            importerLiaisons();

            // Importations des mots
            importerMots();
        } catch (UnsupportedEncodingException | FileNotFoundException ex) {
            // Affichage d'un message d'erreur sur le fichier
            Affichage.afficherMessageErreur("Le fichier n'existe pas\nOu l'encodage n'est pas bon.");
        } catch (IOException ex) {
            // Affichage d'un message d'erreur pour IOException
            Affichage.afficherMessageErreur("IOException, contactez l'administrateur.");
        }
    }

    /**
     * Importe tous les mots.
     *
     * @throws UnsupportedEncodingException exception sur l'encodage
     * @throws FileNotFoundException fichier non trouvé
     * @throws IOException IOException
     */
    public static void importerMots() throws UnsupportedEncodingException, FileNotFoundException, IOException {
        // Buffer de lecture
        BufferedReader br;
        String nomFichier = "mot.txt";
        br = new BufferedReader(new InputStreamReader(new FileInputStream("donnees/" + nomFichier), "utf8"));

        // On récupère la liste des mots, pour tester leur existence
        ArrayList<Word> listeMots = Word.recupArrayListMots();

        // Boucle de lecture
        int compteurLigne = 0;
        while (br.ready()) {
            // Lecture de la ligne
            String line = br.readLine();
            compteurLigne++;
            String lines[] = line.split(";");

            // Utilisation des ternaires pour initialiser.
            String genre = (lines.length == 5) ? lines[4] : "";
            String phonetique = (lines.length >= 4) ? lines[3] : "";

            try {
                // Construction du mot
                Word mot = new Word(new Integer(lines[0]), lines[1], lines[2], phonetique, genre);
                if (!listeMots.contains(mot)) {
                    // S'il n'est pas dans la base, on l'insère
                    mot.insert(true);

                    // On affiche un message d'insertion
                    Fenetre.panImportExport.setLabQueryText("Insertion du mot " + mot.getNom());
                }

                // On incrémente le compteur pour la barre de progression
                incrementer();
            } catch (ArrayIndexOutOfBoundsException ex) {
                // Affichage d'un message d'erreur spécifiant la bonne ligne.
                Affichage.afficherMessageErreur("La ligne " + compteurLigne + " du fichier " + nomFichier + " est incorrecte.");
            }
        }
    }

    /**
     * Importe toutes les liaisons.
     *
     * @throws UnsupportedEncodingException exception sur l'encodage
     * @throws FileNotFoundException fichier non trouvé
     * @throws IOException IOException
     */
    public static void importerLiaisons() throws UnsupportedEncodingException, FileNotFoundException, IOException {
        // Buffer de lecture
        BufferedReader br;
        String nomFichier = "liaison.txt";
        br = new BufferedReader(new InputStreamReader(new FileInputStream("donnees/" + nomFichier), "utf8"));

        // On récupère la liste des liaisons, pour tester leur existence
        ArrayList<Link> listeLiaisons = Link.recupArrayListLiaisons();

        // Boucle de lecture
        int compteurLigne = 0;
        while (br.ready()) {
            // Lecture de la ligne
            String line = br.readLine();
            compteurLigne++;
            String lines[] = line.split(";");

            try {
                // Construction de la liaison
                Link l = new Link(new Integer(lines[0]), lines[1], lines[2], lines[3]);
                if (!listeLiaisons.contains(l)) {
                    // Si elle n'existe pas, on l'insère
                    l.insert(true);

                    // On affiche un message d'insertion
                    Fenetre.panImportExport.setLabQueryText("Insertion de la liaison " + l.getType() + " : " + l.getMot1() + "/" + l.getMot2());
                }

                // Incrémentation pour la barre de progression
                incrementer();
            } catch (ArrayIndexOutOfBoundsException ex) {
                // Affichage d'un message d'erreur spécifiant la bonne ligne.
                Affichage.afficherMessageErreur("La ligne " + compteurLigne + " du fichier " + nomFichier + " est incorrecte.");
            }
        }
    }

    /**
     * Incrémentation du compteur Et mise à jour de la barre de prograssion
     */
    public static void incrementer() {
        // Incrémentation
        compteur++;

        // Calcul du pourcentage
        int pourcent = 100 * compteur / Donnees.nombreTotalRequetes;

        // Mise à jour de la barre de progression
        Fenetre.panImportExport.setValueBar(pourcent);
    }
}
