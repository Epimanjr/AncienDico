package internet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime BLAISE
 */
public class Internet {
    
    /**
     * Récupère le code source d'une page Internet
     * @param url l'URL de la page
     * @return le code source
     * @throws MalformedURLException .
     * @throws IOException .
     */
    public static String recupererCodeSource(String url) throws MalformedURLException, IOException {
        // Création de la connexion
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
    
        // Connexion 
        BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
    
        // Initialisation des variables
        byte[] bytes = new byte[1024];
        int tmp;
        String res = "";
        
        // Parcours
        while( (tmp = bis.read(bytes)) != (-1)) {
            // Concaténation
            res += new String(bytes, 0, tmp);
        }
        
        // Retour
        return res;
    }
    
    // Méthode principale
    public static void main(String[] args) {
        try {
            // Récupération du code source
            Scanner sc = new Scanner(System.in);
            System.out.print("Entrer un mot français : ");
            String motfr = sc.nextLine();
            
            String source = recupererCodeSource("http://www.wordreference.com/fren/"+motfr);
            
            // Affichage
            // System.out.println(source);
            
            
        } catch (IOException ex) {
            Logger.getLogger(Internet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
