package nl.han.ica.ap.boerenbridge.spel;

import java.util.*;

/**
 * Deel de kaarten. Vraag spelers om biedingen, speel de slagen en bepaal de
 * score per speler.
 */
public class Ronde {
    private Kaartspel kaartspel;
    private ArrayList<ISpeler> spelers;
    private int aantalSlagen;
    private HashMap<ISpeler, Score> scores;

    /**
     * Initialiseert de ronde. Spelers dienen op volgorde van spelen mee te
     * worden gegeven.
     * @param spelers De deelnemende spelers aan de slag met de beginnende
     *                speler op de eerste plek.
     * @param aantalSlagen Het aantal slagen die in deze ronde gespeelt worden.
     */
    public Ronde(ArrayList<ISpeler> spelers, int aantalSlagen) {
        this.spelers = spelers;
        this.aantalSlagen = aantalSlagen;
        this.kaartspel = new Kaartspel();
        this.kaartspel.schud();
        this.scores = new HashMap<ISpeler, Score>();
    }

    /**
     * Speel de ronde. Deelt kaarten uit aan spelers, vraagt biedingen van de
     * spelers, speelt de slagen in de ronde en berekend de scores per speler.
     * @return De score per speler.
     */
    public HashMap<ISpeler, Integer> speelRonde() {
        deelKaarten();
        vraagBiedingen();
        speelSlagen();
        return berekenScores();
    }

    /**
     * Geef iedere speler een aantal kaarten, te beginnen bij de eerste speler
     * in de lijst. Iedere speler ontvangt evenveel kaarten als er slagen zijn.
     */
    private void deelKaarten() {
        for (int i = 0; i < this.aantalSlagen; i++)
            for(ISpeler speler : this.spelers)
                speler.ontvangKaart(this.kaartspel.trekKaart());
    }

    /**
     * Vraag elke speler een bieding te doen over het verwachte aantal gewonnen
     * slagen.
     */
    private void vraagBiedingen() {
        // TODO: 2016-03-22: Biedingen moeten gevalideerd worden. De som mag niet hetzelfde zijn als het aantal slagen.
        for (ISpeler speler : this.spelers)
            this.scores.put(speler, new Score(speler, speler.doeBieding()));
    }

    /**
     * Speel de slagen uit en werk het aantal gewonnen slagen per speler bij.
     * De winnaar van de slag begint de volgende slag.
     */
    private void speelSlagen() {
        for (int i = 0; i < this.aantalSlagen; i++) {
            Slag slag = new Slag(new ArrayList<ISpeler>(this.spelers));
            ISpeler slagWinnaar = slag.speelSlag();
            this.scores.get(slagWinnaar).winSlag();
            Collections.rotate(this.spelers,
                    -(this.spelers.indexOf(slagWinnaar)));
        }
    }

    /**
     * Bereken de rondescore per speler.
     * @return De score per speler.
     */
    private HashMap<ISpeler, Integer> berekenScores() {
        HashMap<ISpeler, Integer> rondeScores = new HashMap<ISpeler, Integer>();
        for (Map.Entry<ISpeler, Score> entry : this.scores.entrySet())
            rondeScores.put(entry.getKey(), entry.getValue().berekenScore());
        return rondeScores;
    }
}
