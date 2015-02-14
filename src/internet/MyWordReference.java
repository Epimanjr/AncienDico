/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internet;

import base.activerecord.Liaison;
import base.activerecord.Mot;
import static internet.Internet.recupererCodeSource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import main.donnees.Donnees;

/**
 *
 * @author Maxime
 */
public class MyWordReference {

    public static void chercherTousLesMots() throws IOException {
        // Buffer de lecture
        BufferedReader br;
        String nomFichier = "dictionnaire.txt";
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("donnees/" + nomFichier), "utf8"));

            BufferedReader br2 = new BufferedReader(new FileReader("donnees/nbfren.txt"));
            int nb = new Integer(br2.readLine());

            // parcours
            MyWordReference wr = new MyWordReference();
            while (br.ready()) {
                for (int i = 0; i < nb; i++) {
                    br.readLine();
                }
                String motfr = br.readLine();
                System.out.println("Recherche du mot " + motfr);

                // Recherche
                wr.chercherFrEn(motfr);
                wr.ajouter(false);
                wr.ecrireTest();

                // Inscription nb
                nb++;
                PrintWriter pw = new PrintWriter(new FileWriter("donnees/nbfren.txt"));
                pw.println(nb);
                pw.close();
            }

            // Fermeture
            br.close();
        } catch (UnsupportedEncodingException | FileNotFoundException ex) {
            Logger.getLogger(MyWordReference.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void chercherUnMot() throws IOException {
        // Récupération du code source
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrer un mot français : ");
        String motfr = sc.nextLine();

        // Recherche
        MyWordReference wr = new MyWordReference();
        wr.chercherFrEn(motfr);
        wr.afficher();
        if (wr.demanderConfirmation()) {
            // Ajout !!
            wr.ajouter(true);
        }
    }

    /**
     * Intitulé du mot cherché.
     */
    private String nom;

    /**
     * Prononciation du mot cherché.
     */
    private String prononciation;

    /**
     * Genre du mot cherché.
     */
    private String genre;

    /**
     * Association du mot cherché.
     */
    private String association;

    /**
     * Prononciation de l'association du mot cherché.
     */
    private String prononciationAssociation;

    /**
     * Genre de l'association du mot cherché.
     */
    private String genreAssociation;

    /**
     * Association DE.
     */
    private String associationDe;

    /**
     * Prononciation DE
     */
    private String prononciationDe;

    /**
     * Genre DE
     */
    private String genreDe;

    /**
     * Constructeur vide.
     */
    public MyWordReference() {
        //
    }

    /**
     * On cherche un mot.
     *
     * @param motfr source de la page
     * @throws java.io.IOException exception
     */
    public void chercherFrEn(String motfr) throws IOException {
        this.nom = motfr;
        prononciation = "";
        genre = "";
        association = "";
        prononciationAssociation = "";
        genreAssociation = "";

        // Récupération de la source
        String motfrencode = URLEncoder.encode(motfr, "UTF-8");
        String url = "http://www.wordreference.com/fren/" + motfrencode;
        if (Donnees.isModeVerbeux()) {
            System.out.print("Récupération du code de " + url + " ... ");
        }
        String source = recupererCodeSource(url);
        if (Donnees.isModeVerbeux()) {
            System.out.println("OK");
            //System.out.println(source);
        }

        // Récupération de la prononciation
        String[] tabPourPrononciation = source.split("title='pronunciation'>");
        if (tabPourPrononciation.length >= 2) {
            for (int i = 0; i < tabPourPrononciation[1].length(); i++) {
                char c = tabPourPrononciation[1].charAt(i);

                if (c == '<') {
                    break;
                } else {
                    prononciation += c;
                }
            }
        }

        // Récupération du genre
        String[] tabPourGenre = source.split("<em class='tooltip POS2'>");
        if (tabPourGenre.length >= 2) {
            for (int i = 0; i < tabPourGenre[1].length(); i++) {
                char c = tabPourGenre[1].charAt(i);

                if (c == '<') {
                    break;
                } else {
                    genre += c;
                }
            }
        }

        // Récupération de l'association
        String[] tabPourAssociation = source.split("<td class='ToWrd' >");
        if (tabPourAssociation.length >= 2) {
            for (int i = 0; i < tabPourAssociation[1].length(); i++) {
                char c = tabPourAssociation[1].charAt(i);

                if (c == '<' || c == ',') {
                    break;
                } else {
                    if (c != ' ') {
                        association += c;
                    }
                }
            }
        }

        if (!association.equals("")) {
            // Récupération de la source de l'association
            source = recupererCodeSource("http://www.wordreference.com/enfr/" + association);

            // Récupération de la prononciation
            String[] tabPourPrononciation2 = source.split("title='pronunciation'>");
            if (tabPourPrononciation2.length >= 2) {
                for (int i = 0; i < tabPourPrononciation2[1].length(); i++) {
                    char c = tabPourPrononciation2[1].charAt(i);

                    if (c == '<') {
                        break;
                    } else {
                        prononciationAssociation += c;
                    }
                }
            }

            // Récupération du genre
            String[] tabPourGenre2 = source.split("<em class='tooltip POS2'>");
            if (tabPourGenre2.length >= 2) {
                for (int i = 0; i < tabPourGenre2[1].length(); i++) {
                    char c = tabPourGenre2[1].charAt(i);

                    if (c == '<') {
                        break;
                    } else {
                        genreAssociation += c;
                    }
                }
            }
        }

    }

    /**
     * On cherche un mot DE
     *
     * @param moten source de la page
     * @throws java.io.IOException exception
     */
    public void chercherEnDe(String moten) throws IOException {
        associationDe = "";
        prononciationDe = "";
        genreDe = "";

        // Récupération de la source
        String url = "http://www.wordreference.com/ende/" + moten;
        if (Donnees.isModeVerbeux()) {
            System.out.print("Récupération du code de " + url + " ... ");
        }
        String source = recupererCodeSource(url);
        if (Donnees.isModeVerbeux()) {
            System.out.println("OK");
            //System.out.println(source);
        }

        // Récupération de l'association
        String[] tabPourAssociation = source.split("<span class='roman'>");
        if (tabPourAssociation.length >= 2) {
            for (int i = 0; i < tabPourAssociation[1].length(); i++) {
                char c = tabPourAssociation[1].charAt(i);

                if (c == '<' || c == ',') {
                    break;
                } else {
                    if (c != ' ') {
                        associationDe += c;
                    }
                }
            }
        }

        // Récupération du genre
        String[] tabPourGenre2 = source.split("<span class='ital'>");
        if (tabPourGenre2.length >= 3) {
            for (int i = 0; i < tabPourGenre2[2].length(); i++) {
                char c = tabPourGenre2[2].charAt(i);

                if (c == '<' || c == ',' || c == '(') {
                    break;
                } else {
                    genreDe += c;
                }
            }
        }
        genreDe = genreDe.trim();
    }

    /**
     * Ajout des deux mots + liaisons
     *
     * @param force forcer l'ajout
     */
    public void ajouter(boolean force) {
        if (this.verifier() || force) {
            Mot motfr = new Mot("fr", nom, prononciation, genre);
            Mot moten = new Mot("en", association, prononciationAssociation, genreAssociation);
            Mot motde = new Mot("de", associationDe, prononciationDe, genreDe);
            Liaison l = new Liaison("fren", nom, association);
            Liaison l2 = new Liaison("frde", nom, associationDe);

            System.out.print("Ajout du motfr " + nom + " ... ");
            boolean b = motfr.insert(false);
            if (b) {
                System.out.println("OK.");
            } else {
                System.out.println("NON OK.");
            }

            System.out.print("Ajout du moten " + association + " ... ");
            boolean b2 = moten.insert(false);
            if (b2) {
                System.out.println("OK.");
            } else {
                System.out.println("NON OK.");
            }

            System.out.print("Ajout du motde " + associationDe + " ... ");
            boolean b22 = motde.insert(false);
            if (b22) {
                System.out.println("OK.");
            } else {
                System.out.println("NON OK.");
            }

            System.out.print("Ajout de la liaison " + nom + "/" + association + " ... ");
            boolean b3 = l.insert(false);
            if (b3) {
                System.out.println("OK.");
            } else {
                System.out.println("NON OK.");
            }

            System.out.print("Ajout de la liaison " + nom + "/" + associationDe + " ... ");
            boolean b4 = l2.insert(false);
            if (b4) {
                System.out.println("OK.");
            } else {
                System.out.println("NON OK.");
            }

        }

    }

    /**
     * Vérifie si les mots et l'association est correcte.
     *
     * @return vrai si correct
     */
    public boolean verifier() {
        return !nom.equals("") && !genre.equals("") && !prononciation.equals("") && !association.equals("") && !prononciationAssociation.equals("") && !genreAssociation.equals("");
    }

    /**
     * Demande la confirmation
     *
     * @return réponse
     */
    public boolean demanderConfirmation() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ajout de cette association dans le logiciel ? (Y-n)");
        String confirm = sc.nextLine();

        return confirm.equals("y") || confirm.equals("Y");
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPrononciation() {
        return prononciation;
    }

    public void setPrononciation(String prononciation) {
        this.prononciation = prononciation;
    }

    /**
     * Affichage du résultat obtenu
     */
    public void afficher() {
        System.out.println("Genre                     : " + genre);
        System.out.println("Prononciation             : " + prononciation);
        System.out.println("Association               : " + association);
        System.out.println("Genre Association         : " + genreAssociation);
        System.out.println("Prononciation Association : " + prononciationAssociation);
    }

    // Méthode principale
    public static void main(String[] args) {
        try {
            // Initialisation des données
            Donnees.init();

            //chercherTousLesMots();
            while (true) {
                chercherUnMot();

            }

        } catch (IOException ex) {
            Logger.getLogger(Internet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return (nom + ";" + prononciation + ";" + genre + "-->" + association + ";" + prononciationAssociation + ";" + genreAssociation);
    }

    public void ecrireTest() {
        // Pour l'écriture
        PrintWriter pw;
        try {
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("donnees/testMots.txt", true), "utf8"));

            if (this.verifier()) {
                System.out.println("OK! écriture de " + this);
                pw.println(this);
            }
            // Fermeture
            pw.close();

        } catch (UnsupportedEncodingException | FileNotFoundException ex) {
            Logger.getLogger(MyWordReference.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Méthode appelée quand on est dans l'interface graphique.
     *
     * @param text mot cherché.
     * @param jTable1 table.
     */
    public void actionChercherGraphique(String text, JTable jTable1) {
        try {
            // On cherche le mot en paramètre
            this.chercherFrEn(text);
            this.chercherEnDe(association);

            // On affiche le résultat dans la JTable
            jTable1.setValueAt(text, 0, 1);
            jTable1.setValueAt(prononciation, 1, 1);
            jTable1.setValueAt(genre, 2, 1);
            jTable1.setValueAt(association, 3, 1);
            jTable1.setValueAt(prononciationAssociation, 4, 1);
            jTable1.setValueAt(genreAssociation, 5, 1);
            jTable1.setValueAt(associationDe, 6, 1);
            jTable1.setValueAt(prononciationDe, 7, 1);
            jTable1.setValueAt(genreDe, 8, 1);

            // A partir de l'association (anglais) on recherche l'association allemande
            if (!association.equals("")) {

            }
        } catch (IOException ex) {

        }

    }

    /**
     * Méthode appelée lorsqu'on clique sur valider dans l'interface graphique.
     *
     * @param jTable1 .
     */
    public void actionValiderGraphique(JTable jTable1) {
        // Récupération des variables
        this.prononciation = jTable1.getValueAt(1, 1).toString();
        this.genre = jTable1.getValueAt(2, 1).toString();
        this.association = jTable1.getValueAt(3, 1).toString();
        this.prononciationAssociation = jTable1.getValueAt(4, 1).toString();
        this.genreAssociation = jTable1.getValueAt(5, 1).toString();
        this.associationDe = jTable1.getValueAt(6, 1).toString();
        this.prononciationDe = jTable1.getValueAt(7, 1).toString();
        this.genreDe = jTable1.getValueAt(8, 1).toString();

        // Ajout
        this.ajouter(true);

        // Affichage d'un message pour indiquer la fin.
        JOptionPane.showMessageDialog(null, "Fini.", "Succès", JOptionPane.INFORMATION_MESSAGE);
    }

}
