package nl.han.ica.ap.boerenbridge.spel;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.kaart.KaartType;
import nl.han.ica.ap.boerenbridge.kaart.KaartWaarde;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Een kaartspel bestaat uit 52 kaarten, bestaande uit 4 types van 13 kaarten.
 */
public class Kaartspel {
    private ArrayList<Kaart> kaartspel;

    /**
     * Initialiseert het kaartspel.
     */
    public Kaartspel() {
        this.kaartspel = new ArrayList<Kaart>();
        for (KaartType kaartType : KaartType.values())
            for (KaartWaarde kaartWaarde : KaartWaarde.values())
                this.kaartspel.add(new Kaart(kaartType, kaartWaarde));
    }

    /**
     *  Verranderd de volgorde van de kaarten in het kaartspel.
     */
    public void schud() {
        Collections.shuffle(this.kaartspel);
    }

    /**
     * Geeft de bovenste kaart van de stapel en
     * verwijderd deze uit het kaartspel.
     * @return De getrokken kaart.
     */
    public Kaart trekKaart() {
        return this.kaartspel.remove(0);
    }
}
