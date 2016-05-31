package nl.han.ica.ap.boerenbridge.speler.computer.algoritme;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;

import java.util.ArrayList;

/**
 * Interface om de te spelen kaart te bepalen.
 */
public abstract class AKaart {

    protected Kaartteller kaartteller;
    protected Typeteller typeteller;

    public AKaart(Kaartteller kaartteller, Typeteller typeteller) {
        this.kaartteller = kaartteller;
        this.typeteller = typeteller;
    }

    /**
     * Berekend de meest optimale kaart om uit te spelen.
     * @param hand De kaarten die de speler in zijn hand heeft.
     * @param gespeeldeKaarten De kaarten die door andere spelers zijn
     *                         opgegooid.
     * @param nogTeWinnen Aantal slagen die nog gewonnen moeten worden om aan
     *                    de bieding te voldoen.
     * @return De meest optimale kaart om uit te spelen.
     */
    abstract public Kaart bepaalKaart(ArrayList<Kaart> hand,
                               ArrayList<Kaart> gespeeldeKaarten,
                                      int nogTeWinnen);
}
