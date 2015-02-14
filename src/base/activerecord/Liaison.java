package base.activerecord;

import base.BaseSetting;
import static base.activerecord.Mot.correct;
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
import main.Affichage;
import main.donnees.Donnees;

/**
 *
 * @author Maxime BLAISE
 */
public class Liaison {

    public static int nombreLiaisons() {
        if (Donnees.isModeBdd()) {

        } else {
            try {
                /* Lecture du nombre de ligne dans le fichier liaison.txt */

                // Buffer de lecture
                BufferedReader br = new BufferedReader(new FileReader("donnees/liaison.txt"));

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
                Logger.getLogger(Mot.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Mot.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return 0;
    }

    /**
     * Identifiant de la liaison.
     */
    private int id;

    /**
     * Type de la liaison (fren, frde, frch, ...).
     */
    private String type;

    /**
     * Premier mot de la liaison.
     */
    private String mot1;

    /**
     * Deuxième mot de la liaison.
     */
    private String mot2;

    /**
     * Objet du mot1.
     */
    private Mot instancemot1;

    /**
     * Objet du mot2.
     */
    private Mot instancemot2;

    /**
     * Constructeur.
     *
     * @param id identifiant
     * @param type type
     * @param mot1 premier mot
     * @param mot2 deuxième mot
     */
    public Liaison(int id, String type, String mot1, String mot2) {
        this.id = id;
        this.type = type;
        this.mot1 = mot1;
        this.mot2 = mot2;
    }

    /**
     * Constructeur.
     *
     * @param type type
     * @param mot1 premier mot
     * @param mot2 deuxième mot
     */
    public Liaison(String type, String mot1, String mot2) {
        this.type = type;
        this.mot1 = mot1;
        this.mot2 = mot2;
    }

    /**
     * On met toutes les informations dans les mots.
     */
    public void setInformations() {
        // Création des mots
        this.instancemot1 = new Mot(type.substring(0, 2), mot1);
        this.instancemot2 = new Mot(type.substring(2, 4), mot2);

        // Mise à jour des informations
        this.instancemot1.setInformation();
        this.instancemot2.setInformation();
    }

    /**
     * Récupère la liste des mots
     *
     * @return liste
     */
    public static ArrayList<Liaison> recupArrayListLiaisons() {
        ArrayList<Liaison> listeTmp = new ArrayList<>();

        if (Donnees.isModeBdd()) {
            // Sélection
            BaseSetting.getInstante().select("SELECT * FROM liaison");

            try {
                // Boucle de lecture
                while (BaseSetting.getInstante().getResult_set().next()) {
                    // Récupération des variables
                    int sid = BaseSetting.getInstante().getResult_set().getInt("id");
                    String stype = BaseSetting.getInstante().getResult_set().getString("type");
                    String smot1 = BaseSetting.getInstante().getResult_set().getString("mot1");
                    String smot2 = BaseSetting.getInstante().getResult_set().getString("mot2");

                    // Création du mot
                    Liaison l = new Liaison(sid, stype, smot1, smot2);

                    // Ajour du mot à la liste
                    listeTmp.add(l);
                }

                // Résultat
                return listeTmp;
            } catch (SQLException ex) {
                // Affichage d'un message d'erreur
                if (Donnees.isModeVerbeux()) {
                    System.out.println("SQLException lors de la récupération des données");
                }
            }
        } else {
            // Parcours du fichier
            // Buffer de lecture
            BufferedReader br;
            String nomFichier = "liaison.txt";
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
                    String smot1 = lines[2];
                    String smot2 = lines[3];

                    // Création de la liaison
                    Liaison l = new Liaison(sid, stype, smot1, smot2);

                    // Ajout
                    listeTmp.add(l);
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

        // Erreur
        return null;
    }

    /**
     * Met à jour la différence
     *
     * @param tabNouveau tableau des nouvelles liaisons
     * @return liste des résultats
     */
    public static ArrayList<String> updateDifference(Liaison[] tabNouveau) {
        if (Donnees.isModeBdd()) {
            // Récupération de l'existant
            Object[] tabActuel = toutesLesLiaisons();

            // Initialisation du résultat
            ArrayList<String> res = new ArrayList<>();

            // Parcours
            for (int i = 0; i < tabActuel.length; i++) {
                Liaison liaisonActuelle = (Liaison) tabActuel[i];
                Liaison LiaisonNouvelle = tabNouveau[i];

                // Est-ce différent ?
                if (!liaisonActuelle.equals(LiaisonNouvelle)) {
                    // Si oui, mise à jour
                    LiaisonNouvelle.update();

                    // Ajout à la liste
                    res.add(LiaisonNouvelle.getMot1() + ";" + LiaisonNouvelle.getMot2());
                }
            }

            return res;
        } else {
            // Mode hors ligne, on sauvegarde TOUT !
            // Pour l'écriture
            PrintWriter pw;
            try {
                pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("donnees/liaison.txt"), "utf8"));

                // Parcours
                for (Liaison l : tabNouveau) {
                    // Ecriture
                    pw.println(l.toStringPourFichier());
                }

                // Fermeture
                pw.close();
            } catch (UnsupportedEncodingException | FileNotFoundException ex) {
                Logger.getLogger(Mot.class.getName()).log(Level.SEVERE, null, ex);
            }

            return null;
        }

    }

    /**
     * Récupère toutes les liaisons
     *
     * @return tableau des liaisons
     */
    public static Object[] toutesLesLiaisons() {
        return recupArrayListLiaisons().toArray();
    }

    /**
     * Met à jour le mot dans la base
     *
     * @return vrai si bien mis à jour
     */
    public boolean update() {
        // Construction de la requête
        String query = "UPDATE liaison SET type='" + type + "', mot1='" + correct(mot1) + "', mot2='" + correct(mot2) + "' WHERE id=" + id;

        // Exécution de la requête
        return BaseSetting.getInstante().insert(query);
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
        final Liaison other = (Liaison) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.mot1, other.mot1)) {
            return false;
        }
        if (!Objects.equals(this.mot2, other.mot2)) {
            return false;
        }
        return true;
    }

    /**
     * Méthode qui test si le mot existe déjà dans la base ou pas.
     *
     * @return vrai si le mot existe déjà
     */
    public boolean existe() {
        if (Donnees.isModeBdd()) {
            // Construction de la requête
            String query = "SELECT * FROM liaison WHERE type='" + type + "' AND mot1='" + correct(mot1) + "' AND mot2='" + correct(mot2) + "'";

            // Exécution
            BaseSetting.getInstante().select(query);

            try {
                // Test du résultat
                if (BaseSetting.getInstante().getResult_set().next()) {
                    return true;
                }
            } catch (SQLException ex) {
                // Affichage d'un message d'erreur
                if (Donnees.isModeVerbeux()) {
                    System.out.println("SQLException lors de la récupération des données");
                }
            }
        } else {
            // Buffer de lecture
            BufferedReader br;
            String nomFichier = "liaison.txt";
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream("donnees/" + nomFichier), "utf8"));

                // Boucle de lecture
                int compteurLigne = 0;
                while (br.ready()) {
                    // Lecture de la ligne
                    String line = br.readLine();
                    compteurLigne++;
                    String lines[] = line.split(";");

                    try {
                        // Construction de la liaison
                        Liaison l = new Liaison(new Integer(lines[0]), lines[1], lines[2], lines[3]);

                        // Si égal, return true
                        if (this.type.equals(lines[1]) && this.mot1.equals(lines[2]) && this.mot2.equals(lines[3])) {
                            return true;
                        }

                    } catch (ArrayIndexOutOfBoundsException ex) {
                        // Affichage d'un message d'erreur spécifiant la bonne ligne.
                        Affichage.afficherMessageErreur("La ligne " + compteurLigne + " du fichier " + nomFichier + " est incorrecte.");
                    } catch (NumberFormatException e) {
                        // ...

                    }
                }
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                // Exception
                System.out.println("Erreur: Fichier " + nomFichier + " non trouvé.");
            } catch (IOException ex) {
                Logger.getLogger(Mot.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    /**
     * Insère une liaison dans la base.
     *
     * @param b true si on prend en compte l'id.
     * @return vrai si la liaison a bien été inséré.
     */
    public boolean insert(boolean b) {
        if (Donnees.isModeBdd()) {
            // Construction de la requête
            String query;
            if (b) {
                query = "INSERT INTO liaison(id, type, mot1, mot2) VALUES('" + id + "', '" + type + "', '" + correct(mot1) + "', '" + correct(mot2) + "')";
            } else {
                query = "INSERT INTO liaison(type, mot1, mot2) VALUES('" + type + "', '" + correct(mot1) + "', '" + correct(mot2) + "')";
            }

            // Exécution de la requête
            if (this.existe()) {
                return false;
            } else {
                return BaseSetting.getInstante().insert(query);
            }
        } else {
            // Pour l'écriture
            PrintWriter pw;
            try {
                pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("donnees/liaison.txt", true), "utf8"));

                // Ecriture
                if (this.existe()) {
                    if(Donnees.isModeVerbeux()) {
                        System.out.print(" -existe déjà- ");
                    }
                    return false;
                }

                this.setIdHorsLigne();
                pw.println(this.toStringPourFichier());

                // Fermeture
                pw.close();

                return true;
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                Logger.getLogger(Liaison.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    /**
     * Méthode qui cherche un mot.
     *
     * @param type frch, fren, frde, ...
     * @param mot mot cherché
     * @return une liste de mots
     */
    public static ArrayList<String> chercherMots(String type, String mot) {
        // Initialisation du résultat
        ArrayList<String> res = new ArrayList<>();

        // Construction de la requête
        String donnees = Donnees.listeRechercheLiaisons.get(type);
        String[] tmp = donnees.split(";");
        if (tmp.length < 3) {
            return null;
        }

        if (Donnees.isModeBdd()) {
            // Recherche dans base de données
            String query = "SELECT " + tmp[0] + " FROM liaison WHERE type='" + tmp[1] + "' AND " + tmp[2] + "='" + mot + "'";

            // Exécution de la requête
            BaseSetting.getInstante().select(query);

            try {
                // Parcours
                while (BaseSetting.getInstante().getResult_set().next()) {
                    // Récupération du mot
                    String motRes = BaseSetting.getInstante().getResult_set().getString(tmp[0]);

                    // Ajout à la liste
                    res.add(motRes);
                }
            } catch (SQLException ex) {
                // Affichage d'un message d'erreur
                if (Donnees.isModeVerbeux()) {
                    System.out.println("SQLException lors de la récupération des données");
                }
            }
        } else {
            // Recherche dans fichier
            // Buffer de lecture
            BufferedReader br;
            String nomFichier = "liaison.txt";
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream("donnees/" + nomFichier), "utf8"));

                // Boucle de lecture
                while (br.ready()) {
                    // Lecture de la ligne
                    String line = br.readLine();
                    String[] lines = line.split(";");

                    if (lines[1].equals(tmp[1])) {
                        if (tmp[2].equals("mot1") && lines[2].equals(mot)) {
                            // Liaison trouvé !
                            // Ajout à la liste
                            res.add(lines[3]);
                        }
                        if (tmp[2].equals("mot2") && lines[3].equals(mot)) {
                            // Liaison trouvé !
                            // Ajout à la liste
                            res.add(lines[2]);
                        }
                    }
                }

                // Fermeture
                br.close();
            } catch (UnsupportedEncodingException | FileNotFoundException ex) {
                // Affichage d'un message d'erreur sur le fichier
                Affichage.afficherMessageErreur("Le fichier " + nomFichier + "n'existe pas\nOu l'encodage n'est pas bon.");
            } catch (IOException ex) {
                // Affichage d'un message d'erreur pour IOException
                Affichage.afficherMessageErreur("IOException, contactez l'administrateur.");
            }
        }

        // Fin
        return res;
    }

    public int getId() {
        return id;
    }

    /**
     * ID incrémenté automatiquement dans les fichiers
     */
    public void setIdHorsLigne() {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("donnees/liaison.txt"));

            int compteur = 0;
            while (br.ready()) {
                br.readLine();
                compteur++;
            }

            // Fermeture
            br.close();
            this.id = compteur + 1;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Mot.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Mot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMot1() {
        return mot1;
    }

    public void setMot1(String mot1) {
        this.mot1 = mot1;
    }

    public String getMot2() {
        return mot2;
    }

    public void setMot2(String mot2) {
        this.mot2 = mot2;
    }

    public Mot getInstancemot1() {
        return instancemot1;
    }

    public void setInstancemot1(Mot instancemot1) {
        this.instancemot1 = instancemot1;
    }

    public Mot getInstancemot2() {
        return instancemot2;
    }

    public void setInstancemot2(Mot instancemot2) {
        this.instancemot2 = instancemot2;
    }

    /**
     * Pour l'écriture dans les fichiers
     *
     * @return String
     */
    public String toStringPourFichier() {
        return (this.id + ";" + this.type + ";" + this.mot1 + ";" + this.mot2);
    }

}
