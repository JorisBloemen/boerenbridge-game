package nl.han.ica.ap.boerenbridge.spel;

import java.util.*;

/**
 * Het boerenbridge spel.
 */
public class Spel implements ISpel {
    private ArrayList<ISpeler> spelers;
    private HashMap<ISpeler, Integer> tussenstand;
    private int beurt;

    /**
     * Initialiseert het spel.
     */
    public Spel() {
        this.spelers = new ArrayList<ISpeler>();
        this.tussenstand = new HashMap<ISpeler, Integer>();
        this.beurt = 0;
    }

    // TODO: 2016-03-22: Implementeren unit test voor Spel
    /**
     * Voeg een speler toe aan het spel, start het spel bij voldoende spelers.
     * @param speler Deelnemer aan het spel.
     */
    public void neemDeel(ISpeler speler) {
        this.spelers.add(speler);
        this.tussenstand.put(speler, 0);
        if (this.spelers.size() == 5)
            speelSpel();
    }

    /**
     * Start het spel en speel 19 ronden.
     */
    private void speelSpel() {
        for (; this.beurt < 19; this.beurt++) {
            Ronde ronde = new Ronde(
                    new ArrayList<ISpeler>(this.spelers), bepaalSlagen());
            berekenTussenstand(ronde.speelRonde());
            roteerDeler();
        }
    }

    /**
     * Update de tussenstand
     * @param scores de scores aan het eind van de gespeelde ronde.
     */
    private void berekenTussenstand(HashMap<ISpeler, Integer> scores){
        for (Map.Entry<ISpeler, Integer> entry : this.tussenstand.entrySet())
            entry.setValue(entry.getValue() + scores.get(entry.getKey()));
    }

    /**
     * Bepaalt aan de hand van welke beurt het is hoeveel slagen er in die
     * ronde gespeelt moeten worden
     * @return het aantal slagen van de huidige ronde
     */
    private int bepaalSlagen(){
        if (this.beurt < 10)
            return 10 - this.beurt;
        else
            return this.beurt - 8;
    }

    /**
     * De deler rol verplaatsen naar de volgende speler uit de lijst van
     * spelers.
     */
    private void roteerDeler() {
        Collections.rotate(this.spelers, -1);
    }
}
