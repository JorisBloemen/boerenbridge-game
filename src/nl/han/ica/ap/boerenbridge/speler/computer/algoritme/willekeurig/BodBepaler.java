package nl.han.ica.ap.boerenbridge.speler.computer.algoritme.willekeurig;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.IBieding;

import java.util.ArrayList;

import static nl.han.ica.ap.boerenbridge.kaart.KaartType.SCHOPPEN;

/**
 * Bepaald een bod aan de hand van het algoritme.
 */
public class BodBepaler implements IBieding {

    private int bodCounter;

    public BodBepaler() {
        this.bodCounter = 0;
    }

    /**
     * Bedenkt een bod op basis van het aantal schoppen in de hand.
     * @param hand De kaarten die de speler ontvangen heeft.
     * @param biedingen De biedingen die door andere spelers zijn uitgebracht.
     * @return Het bod.
     */
    @Override
    public int bepaalBieding(ArrayList<Kaart> hand, ArrayList<Integer> biedingen) {
        int bod = (int) (countSchoppen(hand) * 0.67);
        if (bod > 0) bod -= this.bodCounter;
        else         bod += this.bodCounter;
        this.bodCounter++;
        return bod;
    }

    @Override
    public void resetBodCounter() {
        this.bodCounter = 0;
    }

    /**
     * Telt het aantal schoppen in de gegeven set.
     * @param kaarten De set waarin de schoppen geteld moeten worden.
     * @return Het aantal schoppen.
     */
    private int countSchoppen(ArrayList<Kaart> kaarten)
    {
        int schoppenCounter = 0;
        for (Kaart kaart : kaarten)
            if (kaart.getKaartType() == SCHOPPEN)
                schoppenCounter++;
        return schoppenCounter;
    }
}
