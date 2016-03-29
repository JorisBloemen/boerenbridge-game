package nl.han.ica.ap.boerenbridge.speler.mens;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.spel.ISpeler;

import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * Implementatie van de menselijke speler.
 */
public class Speler implements ISpeler {

    private String naam;

    /**
     * Initialiseert de speler
     * @param naam De indentificatie van de speler.
     */
    public Speler(String naam) {
        this.naam = naam;
    }

    @Override
    public int doeBieding() {
        return 0;
    }

    @Override
    public void ontvangKaart(Kaart kaart) {

    }

    @Override
    public Kaart geefKaart(ArrayList<Kaart> kaarten) {
        return null;
    }

    @Override
    public void updateScore(int score) {

    }
}
