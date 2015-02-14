/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.donnees;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Maxime BLAISE
 */
public abstract class AbstractData {

    /**
     * Filename in order to save information.
     */
    private String filename;

    /**
     * Check the file.
     */
    public final void check() {
        File f = new File(filename);
        System.out.print("Check " + f.getName() + " ... ");
        if (!f.exists()) {
            System.out.println("Error!");

            System.out.print("Generating new default file ... ");
            generateDefaultFile();
            System.out.println("OK");

        } else {
            System.out.println("OK");
        }
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public abstract void generateDefaultFile();

    public abstract void read();

    public static void main(String[] args) {
        AbstractData[] data = new AbstractData[2];
        data[0] = new ListLanguages();
        data[1] = new Options();

        System.out.println("Check for data's file ... ");
        for (AbstractData data1 : data) {
            // First check, and then read
            data1.check();
            data1.read();
        }
        System.out.println("Check finished.");
    }
}
