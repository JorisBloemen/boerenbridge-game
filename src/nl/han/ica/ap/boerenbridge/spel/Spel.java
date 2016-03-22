package nl.han.ica.ap.boerenbridge.spel;

import java.util.*;

/**
 * Het boerenbridge spel.
 */
public class Spel implements ISpel {
    private ArrayList<ISpeler> spelers;
    private HashMap<ISpeler, Number> tussenstand;

    /**
     * Initialiseert het spel.
     */
    public Spel() {
        this.spelers = new ArrayList<ISpeler>();
        this.tussenstand = new HashMap<ISpeler, Number>();
    }

    /**
     * Voeg een speler toe aan het spel, start het spel bij voldoende spelers.
     * @param speler Deelnemer aan het spel.
     */
    public void neemDeel(ISpeler speler) {
        this.spelers.add(speler);
        if (this.spelers.size() == 5)
            speelSpel();
    }

    /**
     * Start het spel.
     */
    private void speelSpel() {
    }

    private void speelRondes() {
        for (int i = 0; i < 19; i++) { // TODO: 2016-03-22: Verplaatsen naar functie 'bepaalSlag'.
            int slagen;
            if (i < 10)
                slagen = 10 - i;
            else
                slagen = i - 8;
        }
    }

    /**
     * De deler rol verplaatsen naar de volgende speler uit de lijst van
     * spelers.
     */
    private void roteerDeler() {
        Collections.rotate(this.spelers, -1);
    }
}
