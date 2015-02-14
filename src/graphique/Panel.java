/*
 * Panel général.
 */
package graphique;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;
import newsupermdico.donnees.Donnees;

/**
 *
 * @author Maxime BLAISE
 */
public abstract class Panel extends JPanel {

    /**
     * Liste des composants du type JLabel dans le Panel.
     */
    private ArrayList<JComponent> listeLabels;

    /**
     * Liste des composants du type JButton dans le Panel.
     */
    private ArrayList<JComponent> listeButtons;

    /**
     * Liste des composants du type JTextField dans le Panel.
     */
    private ArrayList<JComponent> listeTextFields;
    
     /**
     * Liste des composants du type JTextField dans le Panel.
     */
    private ArrayList<JComponent> listePanels;

    /**
     * Constructeur vide.
     */
    public Panel() {
        super();
        creationListes();
    }

    /**
     * Création (pour être différent de null) des listes.
     */
    public final void creationListes() {
        listePanels = new ArrayList<>();
        listeLabels = new ArrayList<>();
        listeButtons = new ArrayList<>();
        listeTextFields = new ArrayList<>();
    }

    /**
     * Initialisation des listes.
     */
    public abstract void initListe();

    public abstract void activerContraste();

    /**
     * Méthode d'activation du contraste.
     */
    public void activerContrasteGeneral() {
        // Application du fond sur le panel
        applicationCouleur("fond", listePanels, true);
        
        // Application sur les JLabels
        applicationCouleur("fond", listeLabels, true);
        applicationCouleur("label", listeLabels, false);

        // Application sur les JButtons
        applicationCouleur("bouton", listeButtons, false);
        applicationCouleur("boutonFond", listeButtons, true);

        // Application sur les JTextFields
        applicationCouleur("textField", listeTextFields, false);
        applicationCouleur("textFieldFond", listeTextFields, true);
    }

    /**
     * Application d'une couleur à une liste de composants
     *
     * @param nom nom de la couleurs
     * @param liste liste des composants
     * @param fond fond ou pas
     */
    public static void applicationCouleur(String nom, ArrayList<JComponent> liste, boolean fond) {

        if (Donnees.contraste.couleurs.containsKey(nom)) {
            // Récupération de la couleur
            Color c = Donnees.contraste.couleurs.get(nom);

            if (Donnees.isModeVerbeux()) {
                System.out.print("Récupération de la couleur \"" + nom + "\" OK {" + c.getRed() + ";" + c.getGreen() + ";" + c.getBlue() + "} ");
            }

            // Si != null, on applique
            if (c != null && liste != null) {
               // System.out.println(liste.size());
                if (liste.size() > 0) {
                    liste.stream().forEach((jc) -> {
                        if (fond) {
                            jc.setBackground(c);
                        } else {
                            jc.setForeground(c);
                        }
                    });

                    if (Donnees.isModeVerbeux()) {
                        System.out.println("&& Application de la couleur OK.");
                    }
                }

            }
        }
    }

    public ArrayList<JComponent> getListeLabels() {
        return listeLabels;
    }

    public void setListeLabels(ArrayList<JComponent> listeLabels) {
        this.listeLabels = listeLabels;
    }

    public ArrayList<JComponent> getListeButtons() {
        return listeButtons;
    }

    public void setListeButtons(ArrayList<JComponent> listeButtons) {
        this.listeButtons = listeButtons;
    }

    public ArrayList<JComponent> getListeTextFields() {
        return listeTextFields;
    }

    public void setListeTextFields(ArrayList<JComponent> listeTextFields) {
        this.listeTextFields = listeTextFields;
    }

    public ArrayList<JComponent> getListePanels() {
        return listePanels;
    }

    public void setListePanels(ArrayList<JComponent> listePanels) {
        this.listePanels = listePanels;
    }
    
    

}
