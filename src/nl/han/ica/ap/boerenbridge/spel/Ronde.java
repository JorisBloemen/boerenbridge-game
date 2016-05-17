package nl.han.ica.ap.boerenbridge.spel;

import nl.han.ica.ap.boerenbridge.speler.ISpeler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Deel de kaarten. Vraag spelers om biedingen, speel de slagen en bepaal de
 * score per speler.
 */
class Ronde {
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
    Ronde(ArrayList<ISpeler> spelers, int aantalSlagen) {
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
    HashMap<ISpeler, Integer> speelRonde() {
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
        ArrayList<String>        spelerNamen   = new ArrayList<String>();
        HashMap<String, Integer> biedingen     = new HashMap<String, Integer>();
        int                      totaleBieding = 0;

        for (ISpeler speler : this.spelers)
            totaleBieding += vraagValideBieding(
                    speler,
                    totaleBieding,
                    spelerNamen,
                    biedingen);
    }

    /**
     * Laat de speler een bod doen en blijf dit vragen totdat het bod valide is.
     * @param speler De speler die het bod moet doen.
     * @param totaleBieding De som van de tot nu to gekozen biedingen.
     * @param spelerNamen De namen van spelers die een bod hebben uitgebracht,
     *                    in volgorde van uitbrengen.
     * @param biedingen De biedingen die voorgaande spelers hebben uitgebracht.
     * @return Het bod van de speler.
     */
    private int vraagValideBieding(ISpeler speler, int totaleBieding,
                                   ArrayList<String> spelerNamen,
                                   HashMap<String, Integer> biedingen) {
        int bieding;
        do {
            bieding = speler.doeBieding(spelerNamen, biedingen);
        } while (!(biedingNietHogerDanAantalSlagen(bieding)
                && somOngelijkAanAantalSlagen(bieding, totaleBieding, speler)));
        spelerNamen.add(speler.geefNaam());
        biedingen.put(speler.geefNaam(), bieding);
        this.scores.put(speler, new Score(speler, bieding));
        return bieding;
    }

    /**
     * Checked of het bod niet hoger is dan het aantal kaarten waarmee gespeeld
     * wordt.
     * @param bieding Het bod van de speler.
     * @return True of False
     */
    private boolean biedingNietHogerDanAantalSlagen(int bieding) {
        return bieding <= this.aantalSlagen;
    }

    /**
     * Controleerd of de totale som van de biedingen niet gelijk is aan het
     * aantal kaarten.
     * @param bieding Het bod van de speler.
     * @param totaleBieding De som van de biedingen tot nu toe.
     * @param speler De speler die het bod uit brengt.
     * @return True of Fasle
     */
    private boolean somOngelijkAanAantalSlagen(int bieding,
                                               int totaleBieding,
                                               ISpeler speler) {
        if (speler == this.spelers.get(this.spelers.size() - 1))
            if ((bieding + totaleBieding) == this.aantalSlagen)
                return false;
        return true;
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
            roteerNaarWinnaar(slagWinnaar);
            geefTussenstandDoor();
        }
    }

    /**
     * Roteer de lijst met spelers zodat de winnar vooraan de lijst staat.
     * @param winnaar De speler die vooraan de lijst moet komen te staan.
     */
    private void roteerNaarWinnaar(ISpeler winnaar) {
        Collections.rotate(this.spelers, -(this.spelers.indexOf(winnaar)));
    }

    /**
     * Geeft de tussenstand door aan de spelers.
     */
    private void geefTussenstandDoor() {
        HashMap<String, int[]> tussenstand = new HashMap<String, int[]>();
        for (ISpeler speler : this.spelers)
            tussenstand.put(speler.geefNaam(),
                    this.scores.get(speler).getValue());
        for (ISpeler speler : this.spelers)
            speler.updateSlagTussenstand(
                    new HashMap<String, int[]>(tussenstand));
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
