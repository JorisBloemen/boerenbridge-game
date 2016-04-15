package nl.han.ica.ap.boerenbridge.speler.computer.algoritme;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;

import java.util.ArrayList;

/**
 * Interface om de te spelen kaart te bepalen.
 */
public interface IKaart {
    /**
     * Berekend de meest optimale kaart om uit te spelen.
     * @param hand De kaarten die de speler in zijn hand heeft.
     * @param gespeeldeKaarten De kaarten die door andere spelers zijn
     *                         opgegooid.
     * @return De meest optimale kaart om uit te spelen.
     */
    Kaart bepaalKaart(ArrayList<Kaart> hand, ArrayList<Kaart> gespeeldeKaarten);
}
