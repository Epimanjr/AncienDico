/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.io;

import base.BaseSetting;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import main.Affichage;
import main.donnees.Donnees;

/**
 *
 * @author blaise
 */
public class Exporter {

    /**
     * Exportation générale.
     */
    public static void exporter() {
        try {
            // Exportation des mots
            exporterMot();

            // Exportation des liaisons
            exporterLiaison();
        } catch (UnsupportedEncodingException | FileNotFoundException | SQLException ex) {
            // Affichage d'un message d'erreur pour IOException
            Affichage.afficherMessageErreur("IOException, contactez l'administrateur.");
        }
    }

    /**
     * Exportation de tous les mots de la base
     *
     * @throws java.io.UnsupportedEncodingException .
     * @throws java.io.FileNotFoundException .
     * @throws java.sql.SQLException .
     */
    public static void exporterMot() throws UnsupportedEncodingException, FileNotFoundException, SQLException {
        // Pour l'écriture
        PrintWriter pw;
        pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("donnees/mot.txt"), "utf8"));

        // Tous les mots de la base
        BaseSetting.getInstante().select("SELECT * FROM mot");

        // parcours
        while (BaseSetting.getInstante().getResult_set().next()) {
            // Récupération des variables
            String id = "" + BaseSetting.getInstante().getResult_set().getInt("id");
            String type = BaseSetting.getInstante().getResult_set().getString("type");
            String nom = BaseSetting.getInstante().getResult_set().getString("nom");
            String phonetique = BaseSetting.getInstante().getResult_set().getString("phonetique");
            String genre = BaseSetting.getInstante().getResult_set().getString("genre");

            // Ecriture
            pw.println(id + ";" + type + ";" + nom + ";" + phonetique + ";" + genre);
            Donnees.nombreTotalRequetes++;
        }

        // Fermeture
        pw.close();
    }

    /**
     * Exportation de toutes les liaisons de la base
     *
     * @throws java.io.UnsupportedEncodingException .
     * @throws java.io.FileNotFoundException .
     * @throws java.sql.SQLException .
     */
    public static void exporterLiaison() throws UnsupportedEncodingException, FileNotFoundException, SQLException {
        // Pour l'écriture
        PrintWriter pw;
        pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("donnees/liaison.txt"), "utf8"));

        // Tous les mots de la base
        BaseSetting.getInstante().select("SELECT * FROM liaison");

        // parcours
        while (BaseSetting.getInstante().getResult_set().next()) {
            // Récupération des variables
            String id = "" + BaseSetting.getInstante().getResult_set().getInt("id");
            String type = BaseSetting.getInstante().getResult_set().getString("type");
            String mot1 = BaseSetting.getInstante().getResult_set().getString("mot1");
            String mot2 = BaseSetting.getInstante().getResult_set().getString("mot2");

            // Ecriture
            pw.println(id + ";" + type + ";" + mot1 + ";" + mot2);
            Donnees.nombreTotalRequetes++;
        }

        // Fermeture
        pw.close();
    }
}
