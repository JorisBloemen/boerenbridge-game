package nl.han.ica.ap.boerenbridge.initializer;

import nl.han.ica.ap.boerenbridge.spel.Spel;
import nl.han.ica.ap.boerenbridge.speler.computer.ComputerSpeler;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.willekeurig.BodBepaler;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.willekeurig.KaartBepaler;
import nl.han.ica.ap.boerenbridge.speler.mens.Speler;

/**
 * Initialiseert het spel.
 */
public class Main {

    /**
     * Initialiseert het spel.
     * @param args nvt
     */
    public static void main(String[] args) {
        Spel spel = new Spel();
        spel.neemDeel(new ComputerSpeler("cpu 1", "willekeurig", "montecarlo"));
        spel.neemDeel(new ComputerSpeler("cpu 2", "willekeurig", "montecarlo"));
        spel.neemDeel(new ComputerSpeler("cpu 3", "willekeurig", "montecarlo"));
        spel.neemDeel(new ComputerSpeler("cpu 4", "willekeurig", "montecarlo"));
        spel.neemDeel(new Speler("Max"));
    }
}
