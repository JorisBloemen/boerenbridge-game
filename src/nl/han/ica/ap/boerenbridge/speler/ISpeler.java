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
     * @param spelerNamen De namen van de spelers die al een bod gedaan hebben.
     * @param biedingen De biedingen die gedaan zijn door voorgaande spelers.
     * @return Het gemaakte bod.
     */
    int doeBieding(ArrayList<String> spelerNamen,
                   HashMap<String, Integer> biedingen);

    /**
     * Ontvang een kaart van de deler (spel).
     * @param kaart De te ontvangen kaart.
     */
    void ontvangKaart(Kaart kaart);

    /**
     * Gooi een kaart op, de beste kaart wint de slag.
     * @param spelerNamen De namen van spelers die al opgegooid hebben.
     * @param bord De kaart per speler die opgegooit is.
     * @return De op te gooien kaart.
     */
    Kaart geefKaart(ArrayList<String> spelerNamen, HashMap<String, Kaart> bord);

    /**
     * Verwijderd de kaart uit de hand van de speler.
     * @param kaart De kaart die de speler dient te verwijderen.
     */
    Kaart verwijderKaartUitHand(Kaart kaart);

    /**
     * Ontvang de tussenstand in punten per speler.
     * @param tussenstand De score per speler.
     */
    void updateRondeTussenstand(HashMap<String, Integer> tussenstand);

    /**
     * Ontvang het aantal gewonnen slagen per speler, met hun bijbehorend bod.
     * @param tussenstand Tussenstand per speler (naam) met hierin eerst het
     *                    bod, gevolgd door het aantal gewonnen slagen.
     */
    void updateSlagTussenstand(HashMap<String, int[]> tussenstand);

    /**
     * Geef alle kaarten in de hand van de speler terug.
     * @return Kaarten in de hand van de speler.
     */
    ArrayList<Kaart> toonHand();

    /**
     * De speler krijgt van het spel alle kaarten door die deze ronde gespeelt
     * zijn.
     * @param bord De kaarten die gespeelt zijn i.c.m. de naam van de speler.
     */
    void bekijkBord(ArrayList<String> spelerNamen, HashMap<String, Kaart> bord);
}
