/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique.liste;

import graphique.Fenetre;
import javazoom.jl.decoder.JavaLayerException;
import main.donnees.Options;

/**
 *
 * @author Maxime
 */
public class TraitementVoix implements Runnable {

    public String su;

    /**
     * Constructeur vide
     *
     * @param suffixe suffixe
     */
    public TraitementVoix(String suffixe) {
        this.su = suffixe;
    }

    /**
     * Lancement de la piste audio
     */
    @Override
    public void run() {

        // Initialisation
        String filename = Options.listOptions.get("dossier") + "/" + su;
        Fenetre.mp.init(filename);

        try {
            // Lecture !
            Fenetre.mp.play();
        } catch (JavaLayerException ex) {
            // Sûrement le fichier est inexistant
            //Logger.getLogger(PanChercher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
