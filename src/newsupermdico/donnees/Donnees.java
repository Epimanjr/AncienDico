/*
 * Données nécessaires au bon fonctionnement du programme.
 */
package newsupermdico.donnees;

import base.BaseSetting;
import java.util.HashMap;

/**
 *
 * @author Maxime BLAISE
 */
public class Donnees {

    /**
     * Stock des informations relatives aux recherches de liaisons.
     */
    public static HashMap<String, String> listeRechercheLiaisons;

    /**
     * Stock des informations sur la langue
     */
    public static HashMap<String, String> associationTypeTexte;

    /**
     * Type de contraste
     */
    public static Contraste contraste;

    /**
     * Nombre total de requêtes
     */
    public static int nombreTotalRequetes = 0;

    /**
     * Méthodes qui initialise les données
     */
    public static void init() {
        initialisationMaps();
        AbstractData.main(null);

        // Initialisation de la connexion à la base de données
        if (isModeBdd()) {
            if (!BaseSetting.getInstante().testerConnexion()) {
                // Changement de la valeur
                Options.addValue("modebdd", "non");

            }
        }

    }

   
    private static void initialisationMaps() {
        /* Initialisation des liaisons */
        listeRechercheLiaisons = new HashMap<>();
        listeRechercheLiaisons.put("frch", "mot2;frch;mot1");
        listeRechercheLiaisons.put("fren", "mot2;fren;mot1");
        listeRechercheLiaisons.put("frde", "mot2;frde;mot1");
        listeRechercheLiaisons.put("enfr", "mot1;fren;mot2");
        listeRechercheLiaisons.put("defr", "mot1;frde;mot2");
        listeRechercheLiaisons.put("chfr", "mot1;frch;mot2");

        associationTypeTexte = new HashMap<>();
        associationTypeTexte.put("fr", "Français");
        associationTypeTexte.put("en", "English");
        associationTypeTexte.put("de", "Deutch");
        associationTypeTexte.put("ch", "中国文");
    }

    /**
     * Retourne vrai si on est en mode graphique.
     *
     * @return un booléen
     */
    public static boolean isModeGraphique() {
        return testerCle("modegraphique");
    }
    
    public static boolean isVoice() {
        return testerCle("voice");
    }

    /**
     * Retourne vrai si on est en mode en ligne.
     *
     * @return un booléen
     */
    public static boolean isModeBdd() {
        return testerCle("modebdd");
    }

    /**
     * Retourne vrai si on est en mode verbeux.
     *
     * @return un booléen
     */
    public static boolean isModeVerbeux() {
        return testerCle("modeverbeux");
    }

    private static boolean testerCle(String cle) {
        if (Options.listOptions.containsKey(cle)) {
            String res = Options.listOptions.get(cle);

            switch (res) {
                case "oui":
                case "yes":
                case "1":
                    return true;
            }
        }

        return false;
    }
}
