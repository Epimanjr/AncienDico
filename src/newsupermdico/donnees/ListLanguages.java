/*
 * List of available languages.
 */
package newsupermdico.donnees;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime BLAISE
 */
public class ListLanguages extends AbstractData {

    /**
     * List of languages.
     */
    public static ArrayList<Language> listLanguages;
    
    /**
     * Constructor.
     */
    public ListLanguages() {
        this.setFilename("donnees/languages.txt");
    }
    
    @Override
    public void generateDefaultFile() {
        PrintWriter pw;
        try {
            // Init
            pw = new PrintWriter(new FileWriter(this.getFilename()));
            
            // Write
            pw.println("English-en");
            pw.println("Français-fr");
            pw.println("Deutsch-de");
            
            // Close
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(ListLanguages.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @Override
    public void read() {
        // Init ArrayList
        ListLanguages.listLanguages = new ArrayList<>();
        
        BufferedReader br;
        try {
            // Init reader
            br = new BufferedReader(new FileReader(this.getFilename()));
            
            // Loop for read
            while(br.ready()) {
                String line = br.readLine();
                String[] arrayLine = line.split("-");
                if(arrayLine.length == 2) {
                    ListLanguages.listLanguages.add(new Language(arrayLine[0], arrayLine[1]));
                }
            }
            
            // Close
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListLanguages.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListLanguages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
