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

    /**
     * Reset de counter van het bod algoritme. Deze counter zorgt ervoor dat
     * een bod niet meerdere keren hetzelfde zegt als de som van de biedingen
     * gelijk is aan het aantal gedeelde kaarten.
     */
    void resetBodCounter();
}
