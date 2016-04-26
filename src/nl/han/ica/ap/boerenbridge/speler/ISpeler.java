package nl.han.ica.ap.boerenbridge.speler;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface voor spelers van het spel.
 * Een speler moet uitgedeelde kaarten kunnen ontvangen en bewaren. Als er wordt
 * gevraagd een bieding te doen moet een speler een bod kunnen indienen over het
 * aantal slagen hij verwacht te halen. Ook moet hij een kaart kunnen opgooien
 * als hierom wordt gevraagd.
 */
public interface ISpeler {

    /**
     * Geef de naam van de speler.
     * @return De naam van de speler.
     */
    String geefNaam();

    /**
     * Doe een bod over het aantal verwachte gewonnen slagen.
     * @return Het gemaakte bod.
     */
    int doeBieding();

    /**
     * Ontvang een kaart van de deler (spel).
     * @param kaart De te ontvangen kaart.
     */
    void ontvangKaart(Kaart kaart);

    /**
     * Gooi een kaart op, de beste kaart wint de slag.
     * @param kaarten De kaarten die zichtbaar op tafel liggen.
     * @return De op te gooien kaart.
     */
    Kaart geefKaart(ArrayList<Kaart> kaarten);

    /**
     * Verwijderd de kaart uit de hand van de speler.
     * @param kaart De kaart die de speler dient te verwijderen.
     */
    void verwijderKaartUitHand(Kaart kaart);

    /**
     * Geef de tussenstand door aan de speler.
     * @param tussenstand De score per speler.
     */
    void updateTussenstand(HashMap<String, Integer> tussenstand);

    /**
     * Geef alle kaarten in de hand van de speler terug.
     * @return Kaarten in de hand van de speler.
     */
    ArrayList<Kaart> toonHand();

    /**
     * De speler krijgt van het spel alle kaarten door die deze ronde gespeelt
     * zijn.
     * @param bord De kaarten die gespeelt zijn.
     */
    void bekijkBord(ArrayList<Kaart> bord);
}
