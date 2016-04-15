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

    // TODO: 2016-03-22: Speler moet de hand terug geven zodat het spel kan controleren of het een valide kaart is.
    // ArrayList<Kaart> toonHand();

    // TODO: 2016-03-22: Speler kan nu alleen zijn eigen score zien, niet die van zijn medespelers.
    /**
     * Geef de tussenstand door aan de speler.
     * @param score De score van de speler.
     */
    void updateScore(int score);

    /**
     * De speler krijgt van het spel alle kaarten door die deze ronde gespeelt
     * zijn.
     * @param bord De kaarten die gespeelt zijn.
     */
    void bekijkBord(ArrayList<Kaart> bord);
}
