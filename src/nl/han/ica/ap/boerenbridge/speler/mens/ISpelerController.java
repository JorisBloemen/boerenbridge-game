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
     * @param hand De kaarten die de speler in zijn hand heeft.
     * @return Het bod van de gebruiker.
     */
    int vraagBod(ArrayList<Kaart> hand);

    /**
     * Toon de kaarten die gespeeld zijn.
     * @param bord Kaarten die gespeeld zijn.
     */
    void toonBord(ArrayList<Kaart> bord);

    /**
     * Vraag de gebruiker een kaart op te spelen op basis van de al gespeelde
     * kaarten.
     * @param spelerNamen De namen van de spelers die opgegooit hebben.
     * @param bord De gespeelde kaarten per speler.
     * @param hand De kaarten die de speler in zijn hand heeft.
     * @return De kaart die de gebruiker speelt.
     */
    Kaart vraagKaart(ArrayList<String> spelerNamen,
                     HashMap<String, Kaart> bord,
                     ArrayList<Kaart> hand);

    /**
     * Toon de gebruiker de tussenstand in punten per speler.
     * @param tussenstand Het aantal punten per speler.
     */
    void toonTussenstandRonde(HashMap<String, Integer> tussenstand,
                              int rondenummer);

    /**
     * Toon de gebruiker de tussenstand in slagen per speler.
     * @param tussenstand Het aantal slagen dat elke speler gewonnen heeft.
     */
    void toonTussenstandSlag(HashMap<String, int[]> tussenstand,
                             int slagnummer);

    /**
     * Laat de speler zien welke kaarten deze ronde gespeeld zijn.
     * @param bord De kaarten die gespeeld zijn i.c.m. de naam van de speler
     *             aan het eind van de ronde.
     */
    void bekijkBord(ArrayList<String> spelerNamen, HashMap<String, Kaart> bord);
}
