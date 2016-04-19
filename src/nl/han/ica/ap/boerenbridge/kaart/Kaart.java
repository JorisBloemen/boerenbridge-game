package nl.han.ica.ap.boerenbridge.kaart;

/**
 * Een combinatie van KaartType en KaartWaarde.
 */
public class Kaart {
    private KaartType kaartType;
    private KaartWaarde kaartWaarde;

    /**
     * Initializeert de kaart met de opgegeven waarden.
     * @param kaartType Het type van de kaart.
     * @param kaartWaarde De waarde van de kaart.
     */
    public Kaart(KaartType kaartType, KaartWaarde kaartWaarde) {
        this.kaartType = kaartType;
        this.kaartWaarde = kaartWaarde;
    }

    /**
     * Geeft het type kaart van de terug.
     * @return Het type van de kaart.
     */
    public KaartType getKaartType() {
        return kaartType;
    }

    /**
     * Geeft de waarde van de kaart terug.
     * @return De waarde van de kaart.
     */
    public KaartWaarde getKaartWaarde() {
        return kaartWaarde;
    }

    // TODO: 20160415: Compair kaart in geval van andere indentifier?
}
