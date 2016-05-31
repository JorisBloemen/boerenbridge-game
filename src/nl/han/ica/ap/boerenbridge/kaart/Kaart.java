package nl.han.ica.ap.boerenbridge.kaart;

import static nl.han.ica.ap.boerenbridge.kaart.KaartType.*;

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
     * Vergelijkt een kaart met een andere kaart.
     * @param kaart De kaart waarmee vergeleken moet worden.
     * @return Geeft true terug als de kaarten gelijk zijn en false als ze
     *         ongelijk zijn.
     */
    public boolean compareTo(Kaart kaart) {
        return this.compareType(kaart) && (this.compareValue(kaart) == 0);
    }

    /**
     * Vergelijkt of het type van de kaart overeenkomt.
     * @param kaart Kaart om mee te vergelijken.
     * @return Of het type overeenkomt.
     */
    public boolean compareType(Kaart kaart) {
        return kaart.getKaartType() == this.kaartType;
    }

    /**
     * Vergelijk een kaart met een andere kaart.
     * @param kaart De kaart waarmee vergeleken moet worden.
     * @param teBedienen Het type dat bedient moet worden.
     * @return Geeft 1 terug als de gegeven kaart hoger is, -1 als de gegeven
     *         kaart lager is, 0 als ze dezelfde waarde hebben en -2 als beide
     *         kaarten een flop zijn (geen troef en niet voldoen aan het type).
     */
    public int compareTo(Kaart kaart, KaartType teBedienen) {
        if (kaart.getKaartType() == SCHOPPEN) {
            if (this.kaartType != SCHOPPEN) return 1;
            return this.compareValue(kaart);
        } else if (this.kaartType == SCHOPPEN) {
            if (kaart.getKaartType() != SCHOPPEN) return -1;
            return this.compareValue(kaart);
        } else if (kaart.getKaartType() == teBedienen) {
            if (this.kaartType != teBedienen) return 1;
            return this.compareValue(kaart);
        } else if (this.kaartType == teBedienen) {
            if (kaart.getKaartType() != teBedienen) return -1;
            return this.compareValue(kaart);
        }
        return -2;
    }

    /**
     * Vergelijk de waarde van een kaart met de huidige kaart.
     * @param kaart De kaart waarmee vergeleken moet worden.
     * @return Geeft 1 terug als de gegeven kaart een hogere waarde heeft,
     *         -1 bij een lagere waarde en 0 als de waarde hetzelfde is.
     */
    private int compareValue(Kaart kaart) {
        int difWaarde = kaart.getKaartWaarde().ordinal()
                - this.kaartWaarde.ordinal();
        if (difWaarde < 0) return -1;
        if (difWaarde > 0) return 1;
        return 0;
    }

    /**
     * Geeft een String van de kaart.
     * @return De kaart in String vorm
     *         example: "HARTEN DRIE"
     */
    public String toString() {
        return this.kaartType + " " + this.kaartWaarde;
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
