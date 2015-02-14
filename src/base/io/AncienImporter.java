/*
 * Gère l'ancienne version.
 * N'est plus maintenu.
 */
package base.io;

import base.activerecord.Liaison;
import base.activerecord.Mot;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import newsupermdico.donnees.Donnees;

/**
 *
 * @author Maxime BLAISE
 */
public class AncienImporter {
    
     /**
     * On importe les anciennes données
     *
     * @return vrai si tout se passe bien
     */
    public static boolean importerAnciennesLiaisons() {
        try {
            /* Importation des anciennes liaisons frch */
            ancienImporterLiaisonsFrch();

            /* Importation des anciennes liaisons fren */
            ancienImporterLiaisonsFren();

            /* Importation des anciennes liaisons frde */
            ancienImporterLiaisonsFrde();
            return true;
        } catch (UnsupportedEncodingException | FileNotFoundException ex) {
            Logger.getLogger(Importer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Importer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
    /**
     * On importe les anciennes données
     *
     * @return vrai si tout se passe bien
     */
    public static boolean importerAnciensMots() {
        try {
            /* Importation des anciens mots fr */
            ancienImporterMotsFr();

            /* Importation des anciens mots en */
            ancienImporterMotsEn();

            /* Importation des anciens mots de */
            ancienImporterMotsDe();

            /* Importation des anciens mots ch */
            ancienImporterMotsCh();
            return true;
        } catch (UnsupportedEncodingException | FileNotFoundException ex) {
            Logger.getLogger(Importer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Importer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
    
    /**
     * Importation des anciens mots fr
     *
     * @throws UnsupportedEncodingException .
     * @throws FileNotFoundException .
     * @throws IOException .
     */
    public static void ancienImporterMotsFr() throws UnsupportedEncodingException, FileNotFoundException, IOException {
        /* Buffer de lecture */
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new FileInputStream("donnees/motsfr.txt"), "utf8"));

        /* Boucle de lecture */
        while (br.ready()) {
            /* Lecture */
            String line = br.readLine();
            String lines[] = line.split(";");

            String genre = "";
            if (lines.length == 3) {
                genre = lines[2];
            }
            /* Construction du mot  + Insertion */
            Mot mot = new Mot(new Integer(lines[0]), "fr", lines[1], "", genre);
            mot.insert(false);
        }
    }

    /**
     * Importation des anciens mots en
     *
     * @throws UnsupportedEncodingException .
     * @throws FileNotFoundException .
     * @throws IOException .
     */
    public static void ancienImporterMotsEn() throws UnsupportedEncodingException, FileNotFoundException, IOException {
        /* Buffer de lecture */
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new FileInputStream("donnees/motsen.txt"), "utf8"));

        /* Boucle de lecture */
        while (br.ready()) {
            /* Lecture */
            String line = br.readLine();
            String lines[] = line.split(";");

            String phonetique = "";
            if (lines.length == 3) {
                phonetique = lines[2];
            }
            /* Construction du mot  + Insertion */
            Mot mot = new Mot(new Integer(lines[0]), "en", lines[1], phonetique, "");
            mot.insert(false);
        }
    }

    /**
     * Importation des anciens mots de
     *
     * @throws UnsupportedEncodingException .
     * @throws FileNotFoundException .
     * @throws IOException .
     */
    public static void ancienImporterMotsDe() throws UnsupportedEncodingException, FileNotFoundException, IOException {
        /* Buffer de lecture */
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new FileInputStream("donnees/motsde.txt"), "utf8"));

        /* Boucle de lecture */
        while (br.ready()) {
            /* Lecture */
            String line = br.readLine();
            String lines[] = line.split(";");

            String genre = "";
            if (lines.length == 3) {
                genre = lines[2];
            }
            /* Construction du mot  + Insertion */
            Mot mot = new Mot(new Integer(lines[0]), "de", lines[1], "", genre);
            mot.insert(false);
        }
    }

    /**
     * Importation des anciens mots ch
     *
     * @throws UnsupportedEncodingException .
     * @throws FileNotFoundException .
     * @throws IOException .
     */
    public static void ancienImporterMotsCh() throws UnsupportedEncodingException, FileNotFoundException, IOException {
        /* Buffer de lecture */
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new FileInputStream("donnees/motsch.txt"), "utf8"));

        /* Boucle de lecture */
        while (br.ready()) {
            /* Lecture */
            String line = br.readLine();
            String lines[] = line.split(";");

            /* Construction du mot  + Insertion */
            Mot mot = new Mot(new Integer(lines[0]), "ch", lines[2], lines[1], "");
            mot.insert(false);
        }
    }

    /**
     * Importation des anciennes liaisons frch
     *
     * @throws UnsupportedEncodingException .
     * @throws FileNotFoundException .
     * @throws IOException .
     */
    public static void ancienImporterLiaisonsFrch() throws UnsupportedEncodingException, FileNotFoundException, IOException {
        /* Buffer de lecture */
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new FileInputStream("donnees/liaisonsfrch.txt"), "utf8"));

        /* Boucle de lecture */
        while (br.ready()) {
            /* Lecture */
            String line = br.readLine();
            String lines[] = line.split(";");

            /* Construction de la liaison  + Insertion */
            Liaison l = new Liaison(new Integer(lines[0]), "frch", lines[1], lines[3]);
            l.insert(true);
        }
    }

    /**
     * Importation des anciennes liaisons fren
     *
     * @throws UnsupportedEncodingException .
     * @throws FileNotFoundException .
     * @throws IOException .
     */
    public static void ancienImporterLiaisonsFren() throws UnsupportedEncodingException, FileNotFoundException, IOException {
        /* Buffer de lecture */
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new FileInputStream("donnees/liaisonsfren.txt"), "utf8"));

        /* Boucle de lecture */
        while (br.ready()) {
            /* Lecture */
            String line = br.readLine();
            String lines[] = line.split(";");

            /* Construction de la liaison  + Insertion */
            Liaison l = new Liaison(new Integer(lines[0]), "fren", lines[1], lines[2]);
            l.insert(false);
        }
    }

    /**
     * Importation des anciennes liaisons frde
     *
     * @throws UnsupportedEncodingException .
     * @throws FileNotFoundException .
     * @throws IOException .
     */
    public static void ancienImporterLiaisonsFrde() throws UnsupportedEncodingException, FileNotFoundException, IOException {
        /* Buffer de lecture */
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new FileInputStream("donnees/liaisonsfrde.txt"), "utf8"));

        /* Boucle de lecture */
        while (br.ready()) {
            /* Lecture */
            String line = br.readLine();
            String lines[] = line.split(";");

            /* Construction de la liaison  + Insertion */
            Liaison l = new Liaison(new Integer(lines[0]), "frde", lines[1], lines[2]);
            l.insert(false);
        }
    }
    
    
    public static void main(String[] args) {
        /* Initialisation données */
        Donnees.init();

        System.out.print("Importation des anciennes données ... ");
        if (importerAnciennesLiaisons()) {
            System.out.println("OK");
        } else {
            System.out.println("NON OK");
        }
    }
    
}
