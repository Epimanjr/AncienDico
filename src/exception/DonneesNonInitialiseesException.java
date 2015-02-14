/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Maxime
 */
public class DonneesNonInitialiseesException extends Exception {
    
    /**
     * Appelé si on souhaite accéder aux données alors qu'on ne les a pas initialisé.
     */
    public DonneesNonInitialiseesException() {
        
    }
}
