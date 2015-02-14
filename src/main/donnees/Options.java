/*
 * All options.
 */
package main.donnees;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 *
 * @author Maxime BLAISE
 */
public class Options extends AbstractData {

    /**
     * List of options.
     */
    public static HashMap<String, String> listOptions;

    /**
     * Add data into HashMap.
     *
     * @param key key
     * @param value value
     */
    public static void addValue(String key, String value) {
        if (listOptions != null) {
            listOptions.put(key, value);
        }
    }

    public Options() {
        this.setFilename("donnees/options.txt");
    }

    @Override
    public void generateDefaultFile() {
        PrintWriter pw;
        try {
            // Init
            pw = new PrintWriter(new FileWriter(this.getFilename()));

            // Write
            pw.println("modeverbeux=non");
            pw.println("activervoix=oui");
            pw.println("modegraphique=oui");
            pw.println("dossier=path");
            pw.println("modebdd=non");

            // Close
            pw.close();
        } catch (IOException ex) {
            //
        }
    }

    @Override
    public void read() {
        // Init Hashmap
        Options.listOptions = new HashMap<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(this.getFilename()));

            while (br.ready()) {
                String line = br.readLine();
                String[] lineArray = line.split("=");
                if (lineArray.length == 2) {
                    Options.listOptions.put(lineArray[0], lineArray[1]);
                }
            }

            // Close
            br.close();
        } catch (FileNotFoundException ex) {
            //
        } catch (IOException ex) {
            //
        }
    }

}
