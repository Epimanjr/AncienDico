/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.activerecord;

import base.BaseSetting;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import main.Affichage;
import main.donnees.Donnees;
import main.donnees.Language;
import main.donnees.ListLanguages;

/**
 *
 * @author Maxime
 */
public class Word {

    public static void modifierUnMot(Word mot) {
        // Lecture pour écriture
        ArrayList<Word> liste = Word.recupArrayListMots();

        if (Donnees.isModeBdd()) {

        } else {
            // Pour l'écriture
            PrintWriter pw;
            try {
                pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("donnees/mot.txt"), "utf8"));

                for (Word m : liste) {
                    if (m.getId() == mot.getId()) {
                        pw.println(mot.toStringPourFichier());
                    } else {
                        pw.println(m.toStringPourFichier());
                    }
                }

                // Fermeture
                pw.close();
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Identidiant du mot dans la base.
     */
    private int id;

    /**
     * Type du mot (fr;de;en;ch)
     */
    private String type;

    /**
     * Intitulé du mot.
     */
    private String nom;

    /**
     * Phonétique du mot.
     */
    private String phonetique = "";

    /**
     * Genre du mot (un/une...)
     */
    private String genre = "";
    
    public static int sizeWordInTerminal = 40;

    /**
     * Constructeur.
     *
     * @param id .
     * @param type .
     * @param nom .
     * @param phonetique .
     * @param genre .
     */
    public Word(int id, String type, String nom, String phonetique, String genre) {
        this.id = id;
        this.type = type;
        this.nom = nom;
        this.phonetique = phonetique;
        this.genre = genre;
    }

    /**
     * Constructeur.
     *
     * @param type .
     * @param nom .
     * @param phonetique .
     * @param genre .
     */
    public Word(String type, String nom, String phonetique, String genre) {
        this.type = type;
        this.nom = nom;
        this.phonetique = phonetique;
        this.genre = genre;
    }

    /**
     * Constructeur.
     *
     * @param type .
     * @param nom .
     */
    public Word(String type, String nom) {
        this.type = type;
        this.nom = nom;
    }

    /**
     * Constructeur.
     *
     * @param nom .
     */
    public Word(String nom) {
        this.nom = nom;
    }

    /**
     * On met à jour les informations du mot à partir du nom.
     */
    public void setInformation() {
        if (Donnees.isModeBdd()) {
            // Recherche des informations dans la base de données
            // Requête de sélection
            BaseSetting.getInstante().select("SELECT * FROM mot WHERE type='" + type + "' AND nom='" + correct(nom) + "'");

            try {
                // Si résultat
                if (BaseSetting.getInstante().getResult_set().next()) {
                    // Récupération des variables
                    this.id = BaseSetting.getInstante().getResult_set().getInt("id");
                    this.genre = BaseSetting.getInstante().getResult_set().getString("genre");
                    this.phonetique = BaseSetting.getInstante().getResult_set().getString("phonetique");
                }
            } catch (SQLException ex) {
                // Affichage d'un message d'erreur
                if (Donnees.isModeVerbeux()) {
                    System.out.println("SQLException lors de la récupération des données");
                }
            }
        } else {
            // Parcours du fichier mot
            // Buffer de lecture
            BufferedReader br;
            String nomFichier = "mot.txt";
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream("donnees/" + nomFichier), "utf8"));

                // Boucle de lecture
                boolean trouve = false;
                while (br.ready() && !trouve) {
                    // Lecture de la ligne
                    String line = br.readLine();
                    String[] lines = line.split(";");

                    try {
                        if (lines[1].equals(type) && lines[2].equals(nom)) {
                            // On a trouvé le bon mot
                            trouve = true;
                            this.id = new Integer(lines[0]);
                            this.phonetique = lines[3];
                            this.genre = lines[4];
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {

                    }
                }

                // Fermeture
                br.close();
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                // Affichage d'un message d'erreur sur le fichier
                Affichage.afficherMessageErreur("Le fichier " + nomFichier + "n'existe pas\nOu l'encodage n'est pas bon.");
            } catch (IOException ex) {
                // Affichage d'un message d'erreur pour IOException
                Affichage.afficherMessageErreur("IOException, contactez l'administrateur.");
            }

        }

    }

    /**
     * Met à jour la différence
     *
     * @param tabNouveau .
     * @return liste des résultats
     */
    public static ArrayList<String> updateDifference(Word[] tabNouveau) {
        if (Donnees.isModeBdd()) {
            // Récupération de l'existent
            Object[] tabActuel = tousLesMots();

            // Initialisation du résultat
            ArrayList<String> res = new ArrayList<>();

            // Parcours
            for (int i = 0; i < tabActuel.length; i++) {
                Word motActuel = (Word) tabActuel[i];
                Word motNouveau = tabNouveau[i];

                // Est-ce différent ?
                if (!motActuel.equals(motNouveau)) {
                    // Si oui, Mise à jour
                    motNouveau.update();

                    // Ajout à la liste
                    res.add(motNouveau.getNom());
                }
            }

            return res;
        } else {
            // Mode hors ligne, on sauvegarde TOUT !
            // Pour l'écriture
            PrintWriter pw;
            try {
                pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("donnees/mot.txt"), "utf8"));

                // Parcours
                for (Word m : tabNouveau) {
                    // Ecriture
                    pw.println(m.toStringPourFichier());
                }

                // Fermeture
                pw.close();
            } catch (UnsupportedEncodingException | FileNotFoundException ex) {
                Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
            }

            return null;
        }

    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Word other = (Word) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.phonetique, other.phonetique)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        return true;
    }

    /**
     * Récupère le tableau des mots qui existent
     *
     * @return tab
     */
    public static Word[] tousLesMotsBis() {
        // Initialisation
        Object[] o = tousLesMots();
        Word[] res = new Word[o.length];

        // Parcours pour cast
        for (int i = 0; i < res.length; i++) {
            res[i] = (Word) o[i];
        }

        // Fin
        return res;
    }

    /**
     * Récupère la liste des mots
     *
     * @return liste
     */
    public static ArrayList<Word> recupArrayListMots() {
        // Initialisation du résultat
        ArrayList<Word> listeTmp = new ArrayList<>();

        // Requête de sélection
        if (Donnees.isModeBdd()) {
            BaseSetting.getInstante().select("SELECT * FROM mot");

            try {
                while (BaseSetting.getInstante().getResult_set().next()) {
                    // Récupération
                    int sid = BaseSetting.getInstante().getResult_set().getInt("id");
                    String stype = BaseSetting.getInstante().getResult_set().getString("type");
                    String snom = BaseSetting.getInstante().getResult_set().getString("nom");
                    String sphonetique = BaseSetting.getInstante().getResult_set().getString("phonetique");
                    String sgenre = BaseSetting.getInstante().getResult_set().getString("genre");

                    // Création du mot
                    Word mot = new Word(sid, stype, snom, sphonetique, sgenre);

                    // Ajour du mot à la liste
                    listeTmp.add(mot);
                }
                // Fin
                return listeTmp;
            } catch (SQLException ex) {
                // Exception

            }
        } else {
            // Parcours du fichier
            // Buffer de lecture
            BufferedReader br;
            String nomFichier = "mot.txt";
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream("donnees/" + nomFichier), "utf8"));

                // Boucle de lecture
                boolean trouve = false;
                int compteurLigne = 0;
                while (br.ready() && !trouve) {
                    // Lecture de la ligne
                    String line = br.readLine();
                    compteurLigne++;
                    String[] lines = line.split(";");

                    // Récupération variables
                    int sid = 0;
                    try {
                        sid = new Integer(lines[0]);
                    } catch (java.lang.NumberFormatException ex) {
                        // Affichage
                        Affichage.afficherMessageErreur("Erreur dans le fichier " + nomFichier + " à la ligne " + compteurLigne);
                    }
                    String stype = lines[1];
                    String snom = lines[2];
                    String sphonetique = (lines.length > 3) ? lines[3] : "";
                    String sgenre = (lines.length > 4) ? lines[4] : "";

                    // Création du mot
                    Word mot = new Word(sid, stype, snom, sphonetique, sgenre);

                    // Ajout
                    listeTmp.add(mot);
                }

                // Fermeture
                br.close();

                return listeTmp;
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                // Affichage d'un message d'erreur sur le fichier
                Affichage.afficherMessageErreur("Le fichier " + nomFichier + "n'existe pas\nOu l'encodage n'est pas bon.");
            } catch (IOException ex) {
                // Affichage d'un message d'erreur pour IOException
                Affichage.afficherMessageErreur("IOException, contactez l'administrateur.");
            }
        }

        return null;
    }

    /**
     * Recherche tous les mots de la base
     *
     * @return tab
     */
    public static Object[] tousLesMots() {
        return recupArrayListMots().toArray();
    }

    /**
     * Ma version de chercher en ligne
     *
     * @param lab0 .
     * @param lab1 .
     * @param lab2 .
     * @param language .
     */
    public void chercherMaxime(JLabel lab0, JLabel lab1, JLabel lab2, String language) {
        // On cherche le type
        this.setType();

        // Initialisation
        lab0.setText("");
        lab1.setText("");
        lab2.setText("");

        if (type != null && !type.equals("") && !type.equals(" ")) {
            // Initialisation du label du mot cherché
            this.setInformation();
            lab0.setText(Donnees.associationTypeTexte.get(type) + ": " + this.toString());

            String abbr = "";
            for (Language l : ListLanguages.listLanguages) {
                if (l.getName().equals(language)) {
                    abbr = l.getAbbr();
                    break;
                }
            }

            if (!type.equals(abbr)) {
                chercher(language, type, abbr, lab1, lab2);
            }
        } else {
            // Affichage d'un message d'erreur
            Affichage.afficherMessageErreur("Le mot est introuvable !");
        }

    }

    /**
     * Récupère le type d'un mot, en mode hors ligne
     *
     * @return le type du mot
     */
    public String recupererTypeHorsLigne() {
        BufferedReader br;
        String nomFichier = "mot.txt";
        int compteurLigne = 0;
        try {
            // Initialisation
            br = new BufferedReader(new InputStreamReader(new FileInputStream("donnees/" + nomFichier), "utf8"));

            // Boucle de lecture
            while (br.ready()) {
                // Lecture
                String line = br.readLine();
                compteurLigne++;
                String[] lines = line.split(";");

                // Pour éviter le débordement
                if (lines.length >= 3) {
                    // Si c'est le nom qu'on cherche, on retourne le type
                    if (lines[2].equals(this.nom)) {
                        return lines[1];
                    }
                }
            }
        } catch (UnsupportedEncodingException ex) {
            // Affichage d'un message d'erreur
            Affichage.afficherMessageErreur("Codage non supporté.");
        } catch (FileNotFoundException ex) {
            // Affichage d'un message d'erreur
            Affichage.afficherMessageErreur("Fichier " + nomFichier + " non trouvé.");
        } catch (IOException ex) {
            // Affichage d'un message d'erreur
            Affichage.afficherMessageErreur("Erreur de lecture dans " + nomFichier + "(ligne " + compteurLigne + ")");
        }

        // On retourne une chaîne vide
        return "";
    }

    /**
     * Cherche le code HTML quand on cherche un mot.
     *
     * @return Le code HTML
     */
    public String chercherHtml() {
        // Initialisation du mot
        this.setInformation();
        this.setType();

        String html = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\" style=\"background-color: #cce6ff;\">";

        switch (type) {
            case "fr":
                html += genererHtml("Anglais", "en");
                html += genererHtml("Allemand", "de");
                html += genererHtml("Chinois", "ch");
                break;
            case "en":
                html += genererHtml("Français", "fr");
                break;
        }

        html += "</body></html>";
        return html;
    }

    public String genererHtml(String texte, String typeArrivee) {
        String html = "<font color=\"#b31a1a\">" + texte + "</font> : ";

        // Parcours des mots trouvés pour affichage
        ArrayList<String> motsch = Link.chercherMots(this.getType() + typeArrivee, nom);
        if (motsch != null) {
            if (motsch.isEmpty()) {
                html += "inconnu";
            }
            for (String s : motsch) {
                Word mot = new Word(typeArrivee, s);
                mot.setInformation();

                html += "<font color=\"#4d804d\">" + mot.getNom() + "</font> <i>/<font color=\"#4d66cc\">" + mot.getPhonetique() + "</font>/</i>";
            }
        } else {
            html += "inconnu";
        }

        html += "<div><hr></div>";

        return html;
    }

    /**
     * Cherche la corresponsance d'un mot en ligne
     *
     * @param texte texte à afficher
     * @param typeMotDepart exemple fr
     * @param typeMotArrive exemple ch
     * @param lab1 label d'affichage
     * @param lab2 label d'affichage
     * @return pour le shell
     */
    public String chercher(String texte, String typeMotDepart, String typeMotArrive, JLabel lab1, JLabel lab2) {
        // Initialisation
        if (lab1 != null) {
            lab1.setText(texte);
        }
        String res = "<html>";
        String resShell = texte + " : ";

        // Parcours des mots trouvés pour affichage
        ArrayList<String> motsch = Link.chercherMots(typeMotDepart + typeMotArrive, nom);
        if (motsch != null) {
            for (String s : motsch) {
                Word mot = new Word(typeMotArrive, s);
                mot.setInformation();
                res += "<span>" + mot.getGenre() + " " + mot.getNom() + " (" + mot.getPhonetique() + ")</span><br/>";
                resShell += mot.getNom() + " (" + mot.getPhonetique() + ") ";
            }

        } else {
            res += "<span>Inconnu</span>";
        }

        // Fin
        res += "</html>";
        if (lab2 != null) {
            lab2.setText(res);
        }

        return resShell + "\n";
    }

    /**
     * Méthode qui test si le mot existe déjà dans la base ou pas.
     *
     * @return vrai si le mot existe déjà
     */
    public boolean existe() {
        if (Donnees.isModeBdd()) {
            // Construction de la requête
            String query = "SELECT * FROM mot WHERE type='" + type + "' AND nom='" + correct(nom) + "'";

            // Exécution
            BaseSetting.getInstante().select(query);

            try {
                // Test du résultat
                if (BaseSetting.getInstante().getResult_set().next()) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Buffer de lecture
            BufferedReader br;
            String nomFichier = "mot.txt";
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream("donnees/" + nomFichier), "utf8"));

                // Boucle de lecture
                int compteurLigne = 0;
                while (br.ready()) {
                    // Lecture de la ligne
                    String line = br.readLine();
                    compteurLigne++;
                    String lines[] = line.split(";");

                    // Utilisation des ternaires pour initialiser.
                    String sgenre = (lines.length == 5) ? lines[4] : "";
                    String sphonetique = (lines.length == 4) ? lines[3] : "";

                    try {
                        // Construction du mot
                        Word mot = new Word(new Integer(lines[0]), lines[1], lines[2], sphonetique, sgenre);

                        // Si égal, return true
                        /*if(this.equals(mot)) {
                         return true;
                         }*/
                        if (this.nom.equals(lines[2])) {
                            return true;
                        }

                    } catch (ArrayIndexOutOfBoundsException ex) {
                        // Affichage d'un message d'erreur spécifiant la bonne ligne.
                        Affichage.afficherMessageErreur("La ligne " + compteurLigne + " du fichier " + nomFichier + " est incorrecte.");
                    } catch (Exception e) {

                    }
                }
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                // Exception
                System.out.println("Erreur: Fichier " + nomFichier + " non trouvé.");
            } catch (IOException ex) {
                Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return false;
    }

    /**
     * Insère un mot dans la base.
     *
     * @param b insérer avec id
     * @return vrai si le mot a bien été inséré.
     */
    public boolean insert(boolean b) {
        if (Donnees.isModeBdd()) {
            // Construction de la requête
            String query;
            if (b) {
                query = "INSERT INTO mot(id, type, nom, phonetique, genre) VALUES('" + id + "', '" + type + "', '" + correct(nom) + "', '" + correct(phonetique) + "', '" + correct(genre) + "')";

            } else {
                query = "INSERT INTO mot(type, nom, phonetique, genre) VALUES('" + type + "', '" + correct(nom) + "', '" + correct(phonetique) + "', '" + correct(genre) + "')";
            }

            // Exécution de la requête
            if (this.existe()) {
                return false;
            } else {
                return BaseSetting.getInstante().insert(query);
            }
        } else {
            if (this.existe()) {
                return false;
            }
            // Pour l'écriture
            PrintWriter pw;
            try {
                pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("donnees/mot.txt", true), "utf8"));

                // Ecriture
                if (this.existe()) {
                    if (Donnees.isModeVerbeux()) {
                        System.out.print(" -mot déjà existant- ");
                    }
                    return false;
                }

                if (Donnees.isModeVerbeux()) {
                    System.out.print(" -mot inexistant- ");
                }

                this.setIdHorsLigne();
                pw.println(this.toStringPourFichier());

                // Fermeture
                pw.close();

                return true;
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                Logger.getLogger(Link.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;

    }

    /**
     * Modifie une chaîne pour remplacer les ' par des \'
     *
     * @param str chaîne
     * @return chaîne correcte
     */
    public static String correct(String str) {
        /* Initialisation du résultat */
        String res = "";

        /* Parcours de la chaîne pour modification */
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\'') {
                /* On ajoute un \ */
                res += "\\'";
            } else {
                /* On ne fait rien */
                res += str.charAt(i);
            }
        }

        return res;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * ID incrémenté automatiquement dans les fichiers
     */
    public void setIdHorsLigne() {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("donnees/mot.txt"));

            int compteur = 0;
            while (br.ready()) {
                br.readLine();
                compteur++;
            }

            // Fermeture
            br.close();
            this.id = compteur + 1;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setType() {
        if (Donnees.isModeBdd()) {
            // Recherche du type en ligne
            // Requête de sélection
            BaseSetting.getInstante().select("SELECT type FROM mot WHERE nom='" + correct(nom) + "'");

            try {
                // Si résultat
                if (BaseSetting.getInstante().getResult_set().next()) {
                    this.type = BaseSetting.getInstante().getResult_set().getString("type");
                }
            } catch (SQLException ex) {
                // Si verbeux, affichage
                if (Donnees.isModeVerbeux()) {
                    System.out.println("Erreur lors de la recherche du type d'un mot.");
                }
            }
        } else {
            // Recherche dans le fichier
            this.type = recupererTypeHorsLigne();
        }

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPhonetique() {
        return phonetique;
    }

    public void setPhonetique(String phonetique) {
        this.phonetique = phonetique;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Met à jour le mot dans la base
     *
     * @return .
     */
    public boolean update() {
        // Construction de la requête
        String query = "UPDATE mot SET type='" + type + "', nom='" + correct(nom) + "', phonetique='" + correct(phonetique) + "', genre='" + correct(genre) + "' WHERE id=" + id;

        // Exécution de la requête
        return BaseSetting.getInstante().insert(query);

    }

    /**
     * Pour l'écriture dans les fichiers
     *
     * @return String
     */
    public String toStringPourFichier() {
        return (this.id + ";" + this.type + ";" + this.nom + ";" + this.phonetique + ";" + this.genre);
    }

    @Override
    public String toString() {
        String res = this.genre + " " + this.nom + " (" + this.phonetique + ")";
        
        for(int i=res.length();i<sizeWordInTerminal;i++) {
            res+=" ";
        }
        return res;
    }

    /**
     * Méthode qui récupère le nombre de mots contenus dans le logiciel.
     *
     * @return nombre.
     */
    public static int nombreMots() {
        if (Donnees.isModeBdd()) {

        } else {
            try {
                /* Lecture du nombre de ligne dans le fichier mot.txt */

                // Buffer de lecture
                BufferedReader br = new BufferedReader(new FileReader("donnees/mot.txt"));

                // Parcours
                int compteur = 0;
                while (br.ready()) {
                    // Lecture
                    br.readLine();

                    // Incrémentation
                    compteur++;
                }

                // Fermeture
                br.close();

                return compteur;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return 0;
    }

}
