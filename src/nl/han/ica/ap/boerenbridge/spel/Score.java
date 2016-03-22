package nl.han.ica.ap.boerenbridge.spel;

/**
 * Een score object zit gekoppeld aan een ISpeler, bewaard het bod van de
 * speler, houd het aantal gewonnen slagen bij en kan de score berekenen.
 */
public class Score {
    // TODO: 2016-03-22: We doen niks met de speler. Verwijder of implementeren met vergelijk functie.
    private ISpeler speler;
    private int bod;
    private int gewonnenSlagen;

    /**
     * Initialiseert het score object.
     * @param speler De speler waar de score bij hoort.
     * @param bod Het bod dat de speler heeft gedaan.
     */
    public Score(ISpeler speler, int bod) {
        this.speler = speler;
        this.bod = bod;
        this.gewonnenSlagen = 0;
    }

    /**
     * Verhoog het aantal gewonnen slagen met 1.
     */
    public void winSlag() {
        this.gewonnenSlagen++;
    }

    /**
     * Bereken de score aan de hand van het bod van de speler en het aantal
     * gewonnen slagen.
     * @return De score van de speler in de huidige ronde.
     */
    public int berekenScore() {
        if (this.gewonnenSlagen == this.bod)
            return 10 + this.gewonnenSlagen * 2;
        else
            return Math.abs(this.bod - this.gewonnenSlagen) * -2;
    }
}
