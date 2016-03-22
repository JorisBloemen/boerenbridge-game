package nl.han.ica.ap.boerenbridge.spel;

import java.util.*;

/**
 * Het boerenbridge spel.
 */
public class Spel implements ISpel {
    private ArrayList<ISpeler> spelers;
    private HashMap<ISpeler, Number> tussenstand;
    private int beurt;

    /**
     * Initialiseert het spel.
     */
    public Spel() {
        this.spelers = new ArrayList<ISpeler>();
        this.tussenstand = new HashMap<ISpeler, Number>();
        this.beurt = 0;
    }

    // TODO: 2016-03-22: Implementeren unit test voor Spel
    /**
     * Voeg een speler toe aan het spel, start het spel bij voldoende spelers.
     * @param speler Deelnemer aan het spel.
     */
    public void neemDeel(ISpeler speler) {
        this.spelers.add(speler);
        if (this.spelers.size() == 5)
            speelSpel();
    }

    /**
     * Start het spel en speel 19 ronden.
     */
    private void speelSpel() {
        while (this.beurt < 19){
            Ronde ronde = new Ronde(this.spelers, bepaalSlagen());
            berekenTussenstand(ronde.speelRonde());
            this.beurt++;
            roteerDeler();
        }
    }

    /**
     * Update de tussenstand
     * @param scores de scores aan het eind van de gespeelde ronde.
     */
    private void berekenTussenstand(HashMap<ISpeler, Number> scores){
        for (Map.Entry<ISpeler, Number> entry : scores.entrySet()){
            this.tussenstand.put(entry.getKey(),
                    entry.getValue().intValue()
                    + this.tussenstand.get(entry.getKey()).intValue());
        }
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
            return this.beurt -8;
    }

    /**
     * De deler rol verplaatsen naar de volgende speler uit de lijst van
     * spelers.
     */
    private void roteerDeler() {
        Collections.rotate(this.spelers, -1);
    }
}
