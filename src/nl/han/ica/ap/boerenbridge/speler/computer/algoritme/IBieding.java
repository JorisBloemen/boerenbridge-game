package nl.han.ica.ap.boerenbridge.speler.computer.algoritme;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;

import java.util.ArrayList;

/**
 * Interface om een bieding te laten berekenen.
 */
public interface IBieding {
    /**
     * Berekend de meest optimale bieding.
     * @param hand De kaarten die de speler ontvangen heeft.
     * @param biedingen De biedingen die door andere spelers zijn uitgebracht.
     * @return De optimale bieding.
     */
    int bepaalBieding(ArrayList<Kaart> hand, ArrayList<Integer> biedingen);
}
