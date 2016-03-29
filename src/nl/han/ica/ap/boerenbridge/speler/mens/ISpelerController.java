package nl.han.ica.ap.boerenbridge.speler.mens;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface voor ui-afhandeling.
 */
public interface ISpelerController {

    /**
     * Vraag gebruiker een bod te doen.
     * @return Het bod van de gebruiker.
     */
    int vraagBod(ArrayList<Kaart> kaarten);

    /**
     * Vraag de gebruiker een kaart op te spelen op basis van de al gespeelde
     * kaarten.
     * @param kaarten De gespeelde kaarten.
     * @return De kaart die de gebruiker speelt.
     */
    Kaart vraagKaart(ArrayList<Kaart> kaarten);

    /**
     * Toon de gebruiker de tussenstand in punten per speler.
     * @param tussenstand Het aantal punten per speler.
     */
    void toonTussenstandRonde(HashMap<String, Integer> tussenstand);

    /**
     * Toon de gebruiker de tussenstand in slagen per speler.
     * @param tussenstand Het aantal slagen dat elke speler gewonnen heeft.
     */
    void toonTussenstandSlag(HashMap<String, Integer> tussenstand);
}
