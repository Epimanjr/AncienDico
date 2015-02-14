/*
 * This class manage error message, in graphic mode or in console mode.
 */
package newsupermdico;

import newsupermdico.donnees.Donnees;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxime BLAISE
 */
public class Affichage {
    
    
    /**
     * Affiche un message d'erreur à l'écran.
     * 
     * @param message message
     */
    public static void afficherMessageErreur(String message) {
        // On test si on est en mode graphique
        if(Donnees.isModeGraphique()) {
            // On affiche une popup avec le message
            JOptionPane.showMessageDialog(null, "Erreur: " + message, "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            // On affiche le message dans la console
            System.out.println("Erreur: " + message);
        }
    }
}
