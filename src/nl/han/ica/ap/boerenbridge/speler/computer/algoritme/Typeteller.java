package nl.han.ica.ap.boerenbridge.speler.computer.algoritme;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.kaart.KaartType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Houdt bij hoeveel spelers een kaarttype kunnen bekennen.
 */
public class Typeteller {
    private HashMap<KaartType, Integer> bekendeTypes;

    /**
     * Initialiseerd de typeteller en zet de waarde op het aantal spelers.
     */
    public Typeteller(int aantalSpelers) {
        this.bekendeTypes = new HashMap<KaartType, Integer>();
        for (KaartType kaartType : KaartType.values())
            this.bekendeTypes.put(kaartType, aantalSpelers);
    }

    /**
     * Update het aantal spelers dat een bepaald kaarttype kan bekennen.
     * @param bord De uiteindelijk opgegooide kaarten. Het aantal kaarten moet
     *             hierbij gelijk zijn aan het aantal spelers.
     */
    public void update(ArrayList<Kaart> bord) {
        KaartType teBekennenType = bord.get(0).getKaartType();
        int aantalBekend = 0;

        for (Kaart kaart : bord)
            if (kaart.getKaartType() == teBekennenType)
                aantalBekend++;

        this.bekendeTypes.put(teBekennenType, aantalBekend);
    }

    public int getBekendType(KaartType kaartType) {
        return this.bekendeTypes.get(kaartType);
    }
}
