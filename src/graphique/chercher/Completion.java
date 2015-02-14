package graphique.chercher;

import base.BaseSetting;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import newsupermdico.donnees.Donnees;

/**
 *
 * @author blaise
 */
public class Completion {

    /**
     * Le mot courant.
     */
    private String smotcherche = "";

    /**
     * Le dernier mot cherché pour complétion.
     */
    private String dernierCompletion = "";

    /**
     * Liste des complétions disponible.
     */
    private ArrayList<String> listeCompletion = null;

    /**
     * L'indice parcouru dans le tableau des complétions.
     */
    private int indiceCompletion;

    /**
     * Le dernier résultat de l'autocompletion
     */
    private String dernierResultat = "";

    /**
     * Instance de Completion.
     */
    private static final Completion instance = new Completion();

    /**
     * Constructeur privé pour avoir qu'une instance
     */
    private Completion() {

    }

    /**
     * Récupère une instance de la completion
     *
     * @return Instance
     */
    public static Completion getInstance() {
        return instance;
    }

    /**
     * Méthode appelé lorsque l'utilisateur appui sur une touche
     *
     * @param evt événement
     * @return chaîne
     */
    public String appuyerTouche(KeyEvent evt) {

        // Cas particulier, il ne faut rien faire 
        if (evt.getKeyCode() == KeyEvent.VK_ALT) {
            return "";
        }

        // Initialisation
        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            try {
                smotcherche = smotcherche.substring(0, smotcherche.length() - 1);
            } catch (Exception e) {
                smotcherche = "";
            }
            return smotcherche;
        } else {
            // Si alt on annule l'autocomplétion
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                return smotcherche.substring(0, smotcherche.length());
            } else {
                // Incrémentation du mot cherché
                smotcherche += evt.getKeyChar();
            }
        }

        // Si y'a rien, on retourne rien
        if (smotcherche.length() < 1) {
            return "";
        }

        try {
            // Sélection 
            if (!smotcherche.equals(dernierCompletion)) {
                // Initialisation
                indiceCompletion = 0;
                listeCompletion = new ArrayList<>();
                dernierCompletion = smotcherche;

                // Recherche d'un mot similaire
                BaseSetting.getInstante().select("SELECT * FROM mot WHERE nom LIKE '" + smotcherche + "%'");

                // Ajout à la liste
                while (BaseSetting.getInstante().getResult_set().next()) {
                    listeCompletion.add(BaseSetting.getInstante().getResult_set().getString("nom"));
                }
            } else {
                // Simple incrémentation
                indiceCompletion++;
                if (indiceCompletion == listeCompletion.size()) {
                    indiceCompletion = 0;
                }
            }

            // Résultat
            try {
                String res = listeCompletion.get(indiceCompletion);
                if (!res.equals(dernierResultat)) {
                    return res;
                }
            } catch (Exception e) {
                return "";
            }
        } catch (SQLException ex) {
            // Si verbeux
            if (Donnees.isModeVerbeux()) {
                System.out.println("SQL Exception lors de la complétion");
            }
        }

        return "";
    }

    /**
     * Quand on relâche une touche
     *
     * @param evt event
     * @param str texte du champ de saisie
     */
    public void relacherTouche(KeyEvent evt, String str) {
        // Si n'importe quel touche sauf SHIFT, on sauvegarde le mot cherché
        if (evt.getKeyCode() != KeyEvent.VK_SHIFT) {
            smotcherche += str;
        }
    }

    public String getSmotcherche() {
        return smotcherche;
    }

    public void setSmotcherche(String smotcherche) {
        this.smotcherche = smotcherche;
    }

    public String getDernierCompletion() {
        return dernierCompletion;
    }

    public void setDernierCompletion(String dernierCompletion) {
        this.dernierCompletion = dernierCompletion;
    }

    public ArrayList<String> getListeCompletion() {
        return listeCompletion;
    }

    public void setListeCompletion(ArrayList<String> listeCompletion) {
        this.listeCompletion = listeCompletion;
    }

    public int getIndiceCompletion() {
        return indiceCompletion;
    }

    public void setIndiceCompletion(int indiceCompletion) {
        this.indiceCompletion = indiceCompletion;
    }

    public String getDernierResultat() {
        return dernierResultat;
    }

    public void setDernierResultat(String dernierResultat) {
        this.dernierResultat = dernierResultat;
    }

}
